package com.example.tubes.adapter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.tubes.databinding.AdapterRecyclerViewBinding;
import com.example.tubes.databinding.AdapterRecyclerViewBinding;
import com.example.tubes.model.Rooms;

import java.util.List;

public class AdapterRecyclerView extends RecyclerView.Adapter<AdapterRecyclerView.MyViewHolder> {
    private Context context;
    private List<Rooms> result;
    private AdapterRecyclerViewBinding adapterbinding;


    public AdapterRecyclerView(){}

    public AdapterRecyclerView(Context context, List<Rooms> result){
        this.context = context;
        this.result = result;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        adapterbinding = AdapterRecyclerViewBinding.inflate(layoutInflater, parent, false);
        return new MyViewHolder(adapterbinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        final Rooms rm = result.get(position);
        holder.adapterbinding.setRoom(rm);
        holder.adapterbinding.executePendingBindings();

    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        AdapterRecyclerViewBinding adapterbinding;


        public MyViewHolder(@NonNull AdapterRecyclerViewBinding adapterbinding){
            super(adapterbinding.getRoot());
            this.adapterbinding = adapterbinding;

        }
        public void onClick(View view) {
            Toast.makeText(context, "You touch me?", Toast.LENGTH_SHORT).show();
        }
    }
}