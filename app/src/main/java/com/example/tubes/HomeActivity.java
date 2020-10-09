package com.example.tubes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {

    FirebaseAuth mFirebaseAuth;
    private String CHANNEL_ID = "Channel 1";
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN );
        setContentView(R.layout.activity_home);
        mFirebaseAuth = FirebaseAuth.getInstance();
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);





    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_aboutUs){
            Toast.makeText(getApplicationContext(), "You Click about us", Toast.LENGTH_SHORT).show();
        }
        else if (id == R.id.nav_booking){
            Toast.makeText(getApplicationContext(), "You Click booking", Toast.LENGTH_SHORT).show();
        }
        else if(id == R.id.nav_history){
            Toast.makeText(getApplicationContext(), "You Click history", Toast.LENGTH_SHORT).show();
        }
        else if(id == R.id.nav_location){
            Toast.makeText(getApplicationContext(), "You Click location", Toast.LENGTH_SHORT).show();
        }
        else if(id == R.id.nav_ourRoom){
            Toast.makeText(getApplicationContext(), "You Click our room", Toast.LENGTH_SHORT).show();
        }
        else if(id == R.id.nav_profile)
        {
            Toast.makeText(getApplicationContext(), "You Click profile", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(getApplicationContext(), "You Click logout", Toast.LENGTH_SHORT).show();
        }
    return true;
    }

    public void profileMenu(View view) {
        Intent i = new Intent(HomeActivity.this, ProfileActivity.class);
        startActivity(i);
    }

    public void BookingHotel(View view) {
        Intent i = new Intent(HomeActivity.this, BookingActivity.class);
        startActivity(i);
    }

}