package com.example.tubes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.tubes.adapter.AdapterRecyclerView;
import com.example.tubes.databinding.ActivityRoomBinding;
import com.example.tubes.DaftarRooms;
import com.example.tubes.model.Rooms;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class RoomActivity extends AppCompatActivity {

    private ArrayList<Rooms> ListRoom;
    private RecyclerView recyclerView;
    private AdapterRecyclerView adapterRecyclerView;
    private RecyclerView.LayoutManager manager;
    private ActivityRoomBinding binding;

    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN );
        binding = DataBindingUtil.setContentView(this,R.layout.activity_room);


        ListRoom = new DaftarRooms().ROOMS;

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //recycler view
        recyclerView = findViewById(R.id.recycler_view_room);
        adapterRecyclerView = new AdapterRecyclerView(RoomActivity.this, ListRoom);
        manager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(manager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapterRecyclerView);
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
            startActivity(new Intent(RoomActivity.this, HomeActivity.class));
        }
        else if (id == R.id.nav_aboutUs){
            startActivity(new Intent(RoomActivity.this, AboutUsActivity.class));
        }
        else if (id == R.id.nav_booking){
            startActivity(new Intent(RoomActivity.this, BookingActivity.class));
        }
        else if(id == R.id.nav_location){
            startActivity(new Intent(RoomActivity.this, HotelLocationActivity.class));
        }
        else if(id == R.id.nav_ourRoom){
            Intent i = new Intent(RoomActivity.this, RoomActivity.class);
            startActivity(i);
        }
        else if(id == R.id.nav_profile)
        {
            startActivity(new Intent(RoomActivity.this, ProfileActivity.class));
        }
        else
        {
            AlertDialog.Builder alert = new AlertDialog.Builder(RoomActivity.this);
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
        startActivity(new Intent(RoomActivity.this, LoginActivity.class));
    }
}