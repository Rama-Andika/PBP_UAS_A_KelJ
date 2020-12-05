package com.example.tubes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

public class AdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_admin);
    }

    public void createRoom(View view) {
        Intent i = new Intent(AdminActivity.this, RoomActivity.class);
        startActivity(i);
    }

    public void back(View view) {
        Intent i = new Intent(AdminActivity.this, LoginActivity.class);
        startActivity(i);
    }

    public void listPelanggan(View view) {
        Intent i = new Intent(AdminActivity.this, ShowListPelangganActivity.class);
        startActivity(i);
    }

    public void listRoom(View view) {
        Intent i = new Intent(AdminActivity.this, ShowListRoomActivity.class);
        startActivity(i);
    }

    public void createPelanggan(View view) {
        Intent i = new Intent(AdminActivity.this, BookingActivity.class);
        startActivity(i);
    }
}