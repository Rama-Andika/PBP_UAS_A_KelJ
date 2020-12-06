package com.example.tubes.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.tubes.API.BookingDAO;
import com.example.tubes.API.RoomDAO;
import com.example.tubes.DetailPelangganFragment;
import com.example.tubes.DetailRoomFragment;
import com.example.tubes.R;

import java.util.ArrayList;
import java.util.List;

public class RoomRecyclerAdapter extends RecyclerView.Adapter<RoomRecyclerAdapter.RoomViewHolder> implements Filterable {
    private List<RoomDAO> dataList;
    private List<RoomDAO> filteredDataList;
    private Context context;

    public RoomRecyclerAdapter(Context context, List<RoomDAO> dataList){
        this.context = context;
        this.dataList = dataList;
        this.filteredDataList = dataList;
    }

    @NonNull
    @Override
    public RoomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recycler_adapter_room, parent, false);
        return  new RoomViewHolder(view);
    }


    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String charSequenceString = constraint.toString();
                if(charSequenceString.isEmpty()){
                    filteredDataList = dataList;
                } else{
                    List<RoomDAO> filteredList = new ArrayList<>();
                    for (RoomDAO RoomDAO : dataList){
                        if (RoomDAO.getJenis_kamar().toLowerCase().contains(charSequenceString.toLowerCase())) {
                            filteredList.add(RoomDAO);
                        }
                        filteredDataList = filteredList;
                    }
                }
                FilterResults results = new FilterResults();
                results.values = filteredDataList;
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                filteredDataList = (List<RoomDAO>) results.values;
                notifyDataSetChanged();
            }
        };
    }



    @Override
    public void onBindViewHolder(@NonNull RoomRecyclerAdapter.RoomViewHolder holder, int position) {

        final RoomDAO brg = filteredDataList.get(position);
        holder.twKamar.setText(brg.getJenis_kamar());
        holder.twPrice.setText(brg.getHarga_kamar());
        holder.twLayanan.setText(brg.getLayanan());
        Glide.with(context)
                .load(brg.getGambar())
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(holder.ivGambar);
        holder.mParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = ((AppCompatActivity) context).getSupportFragmentManager();
                DetailRoomFragment dialog = new DetailRoomFragment();
                dialog.show(manager, "dialog");

                Bundle args = new Bundle();
                args.putString("id", brg.getId());
                dialog.setArguments(args);
            }
        });
    }

    @Override
    public int getItemCount() {
        return filteredDataList.size();
    }

    public class RoomViewHolder extends RecyclerView.ViewHolder{
        private TextView twKamar, twPrice, twLayanan;
        private LinearLayout mParent;
        private ImageView ivGambar;

        public RoomViewHolder(@NonNull View itemView){
            super(itemView);
            twKamar = itemView.findViewById(R.id.twKamar);
            twPrice = itemView.findViewById(R.id.twPrice);
            twLayanan = itemView.findViewById(R.id.twLayanan);
            ivGambar = itemView.findViewById(R.id.ivGambar);
            mParent = itemView.findViewById(R.id.linearLayout);
        }
    }
}
