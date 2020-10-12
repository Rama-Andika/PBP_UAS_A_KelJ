package com.example.tubes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class AboutUsActivity extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_about_us);
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
            startActivity(new Intent(AboutUsActivity.this, HomeActivity.class));
        }
        else if (id == R.id.nav_aboutUs){
            startActivity(new Intent(AboutUsActivity.this, AboutUsActivity.class));
        }
        else if (id == R.id.nav_booking){
            startActivity(new Intent(AboutUsActivity.this, BookingActivity.class));
        }
        else if(id == R.id.nav_history){
            Toast.makeText(getApplicationContext(), "You Click history", Toast.LENGTH_SHORT).show();
        }
        else if(id == R.id.nav_location){
            startActivity(new Intent(AboutUsActivity.this, HotelLocationActivity.class));
        }
        else if(id == R.id.nav_ourRoom){
            Intent i = new Intent(AboutUsActivity.this, RoomActivity.class);
            startActivity(i);
        }
        else if(id == R.id.nav_profile)
        {
            startActivity(new Intent(AboutUsActivity.this, ProfileActivity.class));
        }
        else
        {
            AlertDialog.Builder alert = new AlertDialog.Builder(AboutUsActivity.this);
            alert.setMessage("Are you sure?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener()                 {

                        public void onClick(DialogInterface dialog, int which) {

                            logout(); // Last step. Logout function

                        }
                    }).setNegativeButton("No", null);

            AlertDialog alert1 = alert.create();
            alert1.show();
        }
        return true;
    }

    private void logout() {
        FirebaseAuth.getInstance().signOut();
        finish();
        startActivity(new Intent(AboutUsActivity.this, LoginActivity.class));
    }
}