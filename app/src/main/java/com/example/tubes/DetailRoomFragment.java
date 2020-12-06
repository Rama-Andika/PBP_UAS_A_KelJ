package com.example.tubes;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.tubes.API.ApiClient;
import com.example.tubes.API.ApiInterface;
import com.example.tubes.API.RoomResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailRoomFragment extends DialogFragment {
    private TextView twKamar, twPrice, twLayanan;
    private String sIdRoom, sPrice, sRoom, sLayanan;
    private ImageButton ibClose;
    private ProgressDialog progressDialog;

    private Button btnDelete, btnEdit;

    public static DetailRoomFragment newInstance() {return new DetailRoomFragment();}

    @Override
    public void onStart() {
        super.onStart();
        getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstance) {
        View v = inflater.inflate(R.layout.fragment_detail_room, container, false);

        progressDialog = new ProgressDialog(getContext());
        progressDialog.show();

        ibClose = v.findViewById(R.id.ibClose);
        ibClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        twKamar = v.findViewById(R.id.twKamar);
        twPrice = v.findViewById(R.id.twPrice);
        twLayanan = v.findViewById(R.id.twLayanan);
        btnDelete = v.findViewById(R.id.btnDelete);
        btnEdit = v.findViewById(R.id.btnEdit);

        sIdRoom = getArguments().getString("id", "");
        loadUserById(sIdRoom);

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delete(sIdRoom);
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), EditRoomActivity.class);
                i.putExtra("id", sIdRoom);
                startActivity(i);
                dismiss();
            }
        });

        return v;
    }

    private void loadUserById(String id) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<RoomResponse> add = apiService.getRoomById(id, "dataRoom");

        add.enqueue(new Callback<RoomResponse>() {
            @Override
            public void onResponse(Call<RoomResponse> call, Response<RoomResponse> response) {
                sLayanan = response.body().getRooms().get(0).getLayanan();
                sPrice = response.body().getRooms().get(0).getHarga_kamar();
                sRoom = response.body().getRooms().get(0).getJenis_kamar();

                twPrice.setText(sPrice);
                twKamar.setText(sRoom);
                twLayanan.setText(sLayanan);
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<RoomResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Kesalahan Jaringan", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
    }

    private void delete(String id) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        final Call<RoomResponse> req = apiService.deleteRoom(id, "", "", "", "");


        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        req.enqueue(new Callback<RoomResponse>() {
                            @Override
                            public void onResponse(Call<RoomResponse> call, Response<RoomResponse> response) {
                                Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                Log.i("DELETE", response.body().getMessage());

                                dismiss();
                            }

                            @Override
                            public void onFailure(Call<RoomResponse> call, Throwable t) {
                                Toast.makeText(getContext(), "Gagal Delete", Toast.LENGTH_SHORT).show();
                                Log.i("DELETE", "Msg: " + t.getMessage());

                                dismiss();
                            }
                        });
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        dismiss();
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("Hapus data ini ?").setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();



    }

}