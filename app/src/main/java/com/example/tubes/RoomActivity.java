package com.example.tubes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.WindowManager;

import com.example.tubes.adapter.AdapterRecyclerView;
import com.example.tubes.databinding.ActivityRoomBinding;
import com.example.tubes.DaftarRooms;
import com.example.tubes.model.Rooms;

import java.util.ArrayList;

public class RoomActivity extends AppCompatActivity {

    private ArrayList<Rooms> ListRoom;
    private RecyclerView recyclerView;
    private AdapterRecyclerView adapterRecyclerView;
    private RecyclerView.LayoutManager manager;
    private ActivityRoomBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN );
        binding = DataBindingUtil.setContentView(this,R.layout.activity_room);


        ListRoom = new DaftarRooms().ROOMS;

        //recycler view
        recyclerView = findViewById(R.id.recycler_view_room);
        adapterRecyclerView = new AdapterRecyclerView(RoomActivity.this, ListRoom);
        manager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(manager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapterRecyclerView);
    }
}