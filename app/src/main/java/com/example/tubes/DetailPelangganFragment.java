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
import com.example.tubes.API.UserResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailPelangganFragment extends DialogFragment {
    private TextView twNama, twRoom, twTanggal, twDewasa, twAnak;
    private String sIdPelanggan, sNama, sRoom, sTanggal, sDewasa, sAnak;
    private ImageButton ibClose;
    private ProgressDialog progressDialog;

    private Button btnDelete, btnEdit;

    public static DetailPelangganFragment newInstance() {return new DetailPelangganFragment();}

    @Override
    public void onStart() {
        super.onStart();
        getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstance) {
        View v = inflater.inflate(R.layout.fragment_detail_pelanggan, container, false);

        progressDialog = new ProgressDialog(getContext());
        progressDialog.show();

        ibClose = v.findViewById(R.id.ibClose);
        ibClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        twNama = v.findViewById(R.id.twNama);
        twRoom = v.findViewById(R.id.twRoom);
        twTanggal = v.findViewById(R.id.twTanggal);
        twDewasa = v.findViewById(R.id.twDewasa);
        twAnak = v.findViewById(R.id.twAnak);
        btnDelete = v.findViewById(R.id.btnDelete);
        btnEdit = v.findViewById(R.id.btnEdit);

        sIdPelanggan = getArguments().getString("id", "");
        loadUserById(sIdPelanggan);

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delete(sIdPelanggan);
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), EditActivity.class);
                i.putExtra("id", sIdPelanggan);
                startActivity(i);
                dismiss();
            }
        });

        return v;
    }

    private void loadUserById(String id) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<UserResponse> add = apiService.getPelangganById(id, "data");

        add.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                sNama = response.body().getPelanggans().get(0).getNama_pelanggan();
                 sDewasa = response.body().getPelanggans().get(0).getJml_dewasa();
                 sTanggal = response.body().getPelanggans().get(0).getDate();
                sRoom = response.body().getPelanggans().get(0).getRoom();
                 sAnak = response.body().getPelanggans().get(0).getJml_kecil();

                twNama.setText(sNama);
                twRoom.setText(sRoom);
                twTanggal.setText(sTanggal);
                twDewasa.setText(sDewasa);
                twAnak.setText(sAnak);
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Kesalahan Jaringan", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
    }

    private void delete(String id) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        final Call<UserResponse> req = apiService.deletePelanggan(id, "", "", "", "", "");


        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        req.enqueue(new Callback<UserResponse>() {
                            @Override
                            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                                Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                Log.i("DELETE", response.body().getMessage());

                                dismiss();
                            }

                            @Override
                            public void onFailure(Call<UserResponse> call, Throwable t) {
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