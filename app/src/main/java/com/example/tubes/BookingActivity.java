package com.example.tubes;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import android.os.Handler;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;

public class BookingActivity extends AppCompatActivity{

    protected Cursor cursor;
    Spinner adult, child;
    private static final String TAG = "BookingActivity";

    TextInputEditText input_roomType;
    TextInputEditText input_name;
    TextInputEditText input_date;
    TextInputEditText input_adult;
    TextInputEditText input_child;
    MaterialButton btn_book;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_booking);


        input_name = (TextInputEditText) findViewById(R.id.input_name);
        input_roomType = (TextInputEditText) findViewById(R.id.input_roomType);
        input_date = (TextInputEditText) findViewById(R.id.input_date);
        input_adult = (TextInputEditText) findViewById(R.id.input_adult);
        input_child = (TextInputEditText) findViewById(R.id.input_child);
        btn_book = findViewById(R.id.btn_book);

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

        onBookingSuccess();
    }

    public void onBookingSuccess() {
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
        String roomType = input_roomType.getText().toString();
        String date = input_date.getText().toString();
        String adult = input_adult.getText().toString();
        String child = input_child.getText().toString();

        if (name.isEmpty() || name.length() < 3) {
            input_name.setError("At Least 3 Characters");
            valid = false;
        } else {
            input_name.setError(null);
        }

        if (roomType.isEmpty()) {
            input_roomType.setError("Enter Valid Room Type");
            valid = false;
        } else {
            input_roomType.setError(null);
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

}
