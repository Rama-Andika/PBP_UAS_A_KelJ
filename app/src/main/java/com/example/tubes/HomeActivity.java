package com.example.tubes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
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

        if (id == R.id.nav_home){
            startActivity(new Intent(HomeActivity.this, HomeActivity.class));
        }
        else if (id == R.id.nav_aboutUs){
            startActivity(new Intent(HomeActivity.this, AboutUsActivity.class));
        }
        else if (id == R.id.nav_booking){
            startActivity(new Intent(HomeActivity.this, BookingActivity.class));
        }
        else if(id == R.id.nav_location){
            startActivity(new Intent(HomeActivity.this, HotelLocationActivity.class));
        }
        else if(id == R.id.nav_ourRoom){

        }
        else if(id == R.id.nav_profile)
        {
            startActivity(new Intent(HomeActivity.this, ProfileActivity.class));
        }
        else
        {
            AlertDialog.Builder alert = new AlertDialog.Builder(HomeActivity.this);
            alert.setMessage("Are you sure?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener()                 {

                        public void onClick(DialogInterface dialog, int which) {

                            logout();

                        }
                    }).setNegativeButton("Cancel", null);

            AlertDialog alert1 = alert.create();
            alert1.show();
        }
    return true;
    }

    public void logout(){
        FirebaseAuth.getInstance().signOut();
        finish();
        startActivity(new Intent(HomeActivity.this, LoginActivity.class));

    }

    public void profileMenu(View view) {
        Intent i = new Intent(HomeActivity.this, ProfileActivity.class);
        startActivity(i);
    }

    public void BookingHotel(View view) {
        Intent i = new Intent(HomeActivity.this, BookingActivity.class);
        startActivity(i);
    }


    public void listUser(View view) {
        Intent i = new Intent(HomeActivity.this, ShowListPelangganActivity.class);
        startActivity(i);
    }

    public void Room(View view) {
        Intent i = new Intent(HomeActivity.this, JustShowListRoom.class);
        startActivity(i);
    }
}