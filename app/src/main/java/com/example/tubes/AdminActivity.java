package com.example.tubes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
}