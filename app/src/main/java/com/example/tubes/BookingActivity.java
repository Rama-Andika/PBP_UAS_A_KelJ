package com.example.tubes;

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
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tubes.API.ApiClient;
import com.example.tubes.API.ApiInterface;
import com.example.tubes.API.UserResponse;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookingActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    FirebaseUser firebaseUser;

    protected Cursor cursor;
    DatePickerDialog.OnDateSetListener setListener;
    private static final String TAG = "BookingActivity";
    private AutoCompleteTextView exposedDropdownRoom;
    private TextInputLayout name, room, date, adult, child;
    private TextInputEditText input_name, input_date, input_adult, input_child;
    MaterialButton btn_book;
    private String sRoom = "";
    private String[] saRoom = new String[] {"Classic", "Standart", "Deluxe"};
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_booking);
        progressDialog = new ProgressDialog(this);

        exposedDropdownRoom = findViewById(R.id.edRoom);

        name = (TextInputLayout) findViewById(R.id.name);
        room = (TextInputLayout) findViewById(R.id.room);
        date = (TextInputLayout) findViewById(R.id.date);
        adult = (TextInputLayout) findViewById(R.id.adult);
        child = (TextInputLayout) findViewById(R.id.child);

        input_name = (TextInputEditText) findViewById(R.id.input_name);
        input_date = (TextInputEditText) findViewById(R.id.input_date);
        input_adult = (TextInputEditText) findViewById(R.id.input_adult);
        input_child = (TextInputEditText) findViewById(R.id.input_child);
        btn_book = findViewById(R.id.btn_book);

        ArrayAdapter<String> adapterRoom = new ArrayAdapter<>(Objects.requireNonNull(this),
                R.layout.list_item, R.id.item_list, saRoom );
        exposedDropdownRoom.setAdapter(adapterRoom);
        exposedDropdownRoom.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                sRoom = saRoom[i];
            }
        });

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        input_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        BookingActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month+1;
                        String date = year+"-"+month+"-"+day;
                        input_date.setText(date);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });


        btn_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                booking();
            }
        });

    }

    public void booking(){
        Log.d(TAG, "Booking");

        if (!validate()) {
            onBookingFailed();
            return;
        }
        progressDialog.show();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<UserResponse> add = apiService.createPelanggan(input_name.getText().toString(),
                sRoom, input_date.getText().toString(), input_adult.getText().toString(), input_child.getText().toString());

        add.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                Toast.makeText(BookingActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
                onBackPressed();
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Toast.makeText(BookingActivity.this, "Kesalahan jaringan", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
        onBookingSuccess();
    }

    public void onBookingSuccess() {
        Toast.makeText(getBaseContext(), "Booking success", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    public void onBookingFailed() {
        Toast.makeText(getBaseContext(), "Booking failed", Toast.LENGTH_LONG).show();

        btn_book.setEnabled(true);
    }

    public boolean validate(){
        boolean valid = true;

        String name = input_name.getText().toString();
        String date = input_date.getText().toString();
        String adult = input_adult.getText().toString();
        String child = input_child.getText().toString();

        if (name.isEmpty() || name.length() < 3) {
            input_name.setError("At Least 3 Characters");
            valid = false;
        } else {
            input_name.setError(null);
        }




        if (date.isEmpty()){
            input_date.setError("Enter Valid Date");
            valid = false;
        } else {
            input_date.setError(null);
        }

        if (adult.isEmpty()){
            input_adult.setError("Enter Valid Number of Adult");
            valid = false;
        } else {
            input_adult.setError(null);
        }

        if (child.isEmpty()){
            input_child.setError("Enter Valid Number of Child");
            valid = false;
        } else {
            input_child.setError(null);
        }

        return valid;
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
