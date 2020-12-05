package com.example.tubes.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tubes.API.BookingDAO;
import com.example.tubes.DetailPelangganFragment;
import com.example.tubes.R;

import java.util.ArrayList;
import java.util.List;

public class PelangganRecyclerAdapter extends RecyclerView.Adapter<PelangganRecyclerAdapter.RoomViewHolder> implements Filterable {
    private List<BookingDAO> dataList;
    private List<BookingDAO> filteredDataList;
    private Context context;

    public PelangganRecyclerAdapter(Context context, List<BookingDAO> dataList){
        this.context = context;
        this.dataList = dataList;
        this.filteredDataList = dataList;
    }

    @NonNull
    @Override
    public RoomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recycler_adapter_pelanggan, parent, false);
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
                    List<BookingDAO> filteredList = new ArrayList<>();
                    for (BookingDAO BookingDAO : dataList){
                        if (BookingDAO.getNama_pelanggan().toLowerCase().contains(charSequenceString.toLowerCase())) {
                            filteredList.add(BookingDAO);
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
                filteredDataList = (List<BookingDAO>) results.values;
                notifyDataSetChanged();
            }
        };
    }



    @Override
    public void onBindViewHolder(@NonNull PelangganRecyclerAdapter.RoomViewHolder holder, int position) {

        final BookingDAO brg = filteredDataList.get(position);
        holder.twNama.setText(brg.getNama_pelanggan());
        holder.twRoom.setText(brg.getRoom());

        holder.mParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = ((AppCompatActivity) context).getSupportFragmentManager();
                DetailPelangganFragment dialog = new DetailPelangganFragment();
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
        private TextView twNama, twRoom;
        private LinearLayout mParent;

        public RoomViewHolder(@NonNull View itemView){
            super(itemView);
            twNama = itemView.findViewById(R.id.twNama);
            twRoom = itemView.findViewById(R.id.twRoom);
            mParent = itemView.findViewById(R.id.linearLayout);
        }
    }
}
