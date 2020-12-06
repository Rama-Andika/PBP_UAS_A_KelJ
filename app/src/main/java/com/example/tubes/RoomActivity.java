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

public class RoomActivity extends AppCompatActivity {

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
    MaterialButton btn_create, btnUnggah;
    private String sRoom = "";
    private String[] saRoom = new String[] {"Classic", "Standart", "Deluxe"};
    private ProgressDialog progressDialog;
    private ImageView ivGambar;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_room);

        progressDialog = new ProgressDialog(this);

        exposedDropdownRoom = findViewById(R.id.edKamar);
        
        ivGambar = findViewById(R.id.ivGambar);

        layananLayout = (TextInputLayout) findViewById(R.id.layananLayout);
        priceLayout = (TextInputLayout) findViewById(R.id.priceLayout);

        layanan = (TextInputEditText) findViewById(R.id.layanan);
        price = (TextInputEditText) findViewById(R.id.price);
        gambar = (TextInputEditText) findViewById(R.id.gambar);
        btn_create = findViewById(R.id.btn_create);

        ArrayAdapter<String> adapterRoom = new ArrayAdapter<>(Objects.requireNonNull(this),
                R.layout.list_item, R.id.item_list, saRoom );
        exposedDropdownRoom.setAdapter(adapterRoom);
        exposedDropdownRoom.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                sRoom = saRoom[i];
            }
        });


        btn_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createRoom();
            }
        });

    }

    public void createRoom(){
        Log.d(TAG, "Booking");

        if (!validate()) {
            onBookingFailed();
            return;
        }

        progressDialog.show();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<RoomResponse> add = apiService.createRoom(sRoom, price.getText().toString(),
                layanan.getText().toString(), gambar.getText().toString());

        add.enqueue(new Callback<RoomResponse>() {
            @Override
            public void onResponse(Call<RoomResponse> call, Response<RoomResponse> response) {
                Toast.makeText(RoomActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
                onBackPressed();
            }

            @Override
            public void onFailure(Call<RoomResponse> call, Throwable t) {
                Toast.makeText(RoomActivity.this, "Kesalahan jaringan", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
//        onBookingSuccess();
    }

    public void onBookingSuccess() {
        finish();
    }

    public void onBookingFailed() {
        Toast.makeText(getBaseContext(), "Create Room failed", Toast.LENGTH_LONG).show();

        btn_create.setEnabled(true);
    }

    public boolean validate(){
        boolean valid = true;

        String layanan = this.layanan.getText().toString();
        String price = this.price.getText().toString();
        String gambar = this.gambar.getText().toString();

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
        if (gambar.isEmpty()){
            this.gambar.setError("Enter Url image");
            valid = false;
        } else {
            this.gambar.setError(null);
        }

        return valid;
    }


}