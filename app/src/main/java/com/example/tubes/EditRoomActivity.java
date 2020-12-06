package com.example.tubes;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.tubes.API.ApiClient;
import com.example.tubes.API.ApiInterface;
import com.example.tubes.API.RoomResponse;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditRoomActivity extends AppCompatActivity {

    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    FirebaseUser firebaseUser;


    protected Cursor cursor;
    DatePickerDialog.OnDateSetListener setListener;
    private static final String TAG = "BookingActivity";
    private AutoCompleteTextView exposedDropdownRoom;
    private TextInputLayout layananLayout;
    private TextInputLayout priceLayout;
    private TextInputEditText layanan;
    private TextInputEditText price;
    private TextInputEditText gambar;
    private MaterialButton btn_edit;
    private String sRoom = "";
    private String[] saRoom = new String[] {"Classic", "Standart", "Deluxe"};
    private ProgressDialog progressDialog;
    private ImageView ivGambar;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_edit_room);

        id = getIntent().getStringExtra("id");
        Log.i("EDITUSERID", "ID User: " + id);

        progressDialog = new ProgressDialog(this);

        exposedDropdownRoom = findViewById(R.id.edKamar);

        layananLayout = (TextInputLayout) findViewById(R.id.layananLayout);
        priceLayout = (TextInputLayout) findViewById(R.id.priceLayout);

        layanan = findViewById(R.id.layanan);
        price = findViewById(R.id.price);
        gambar = findViewById(R.id.gambar);
        btn_edit = findViewById(R.id.btn_edit);

        ArrayAdapter<String> adapterRoom = new ArrayAdapter<>(Objects.requireNonNull(this),
                R.layout.list_item, R.id.item_list, saRoom );
        exposedDropdownRoom.setAdapter(adapterRoom);
        exposedDropdownRoom.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                sRoom = saRoom[i];
            }
        });


        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editRoom();
            }
        });

        loadUser(id);
    }

    private void loadUser(String id) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<RoomResponse> call = apiService.getRoomById(id, "data");

        call.enqueue(new Callback<RoomResponse>() {
            @Override
            public void onResponse(Call<RoomResponse> call, Response<RoomResponse> response) {
                String layanan1 = response.body().getRooms().get(0).getLayanan();
                sRoom = response.body().getRooms().get(0).getJenis_kamar();
                String harga1 = response.body().getRooms().get(0).getHarga_kamar();
                String image = response.body().getRooms().get(0).getGambar();

                exposedDropdownRoom.setText(sRoom, false);
                layanan.setText(layanan1);
                price.setText(harga1);
                gambar.setText(image);


                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<RoomResponse> call, Throwable t) {
                Toast.makeText(EditRoomActivity.this, "Kesalahan Jaringan", Toast.LENGTH_SHORT).show();
                Log.i("EDITERROR", t.getMessage());
                progressDialog.dismiss();
            }
        });
    }

    public void editRoom(){
        Log.d(TAG, "Booking");

        if (!validate()) {
            onBookingFailed();
            return;
        }
        progressDialog.show();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<RoomResponse> add = apiService.updateRoom(id, sRoom, price.getText().toString(),
                layanan.getText().toString(), gambar.getText().toString());

        add.enqueue(new Callback<RoomResponse>() {
            @Override
            public void onResponse(Call<RoomResponse> call, Response<RoomResponse> response) {
                Toast.makeText(EditRoomActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
                onBackPressed();
            }

            @Override
            public void onFailure(Call<RoomResponse> call, Throwable t) {
                Toast.makeText(EditRoomActivity.this, "Kesalahan jaringan", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
        onBookingSuccess();
    }

    public void onBookingSuccess() {
        Toast.makeText(getBaseContext(), "Edit Room success", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, ShowListRoomActivity.class);
        startActivity(intent);
    }

    public void onBookingFailed() {
        Toast.makeText(getBaseContext(), "Edit Room failed", Toast.LENGTH_LONG).show();

        btn_edit.setEnabled(true);
    }

    public boolean validate(){
        boolean valid = true;

        String layanan = this.layanan.getText().toString();
        String price = this.price.getText().toString();

        if (layanan.isEmpty() || layanan.length() < 3) {
            this.layanan.setError("At Least 3 Characters");
            valid = false;
        } else {
            this.layanan.setError(null);
        }

        if (price.isEmpty()){
            this.price.setError("Enter Valid price");
            valid = false;
        } else {
            this.price.setError(null);
        }


        return valid;
    }
}