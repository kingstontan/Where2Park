package com.where2park.where2park;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class ParkingAdapter extends RecyclerView.Adapter<ParkingAdapter.ViewHolder> {

    private ArrayList<Parking> parkings;

    public ParkingAdapter(ArrayList<Parking> parkings) {
        this.parkings = parkings;
    }

    @NonNull
    @Override
    public ParkingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.parking_list_item, viewGroup, false);
        ParkingAdapter.ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ParkingAdapter.ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return parkings.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView parkingName;
        private TextView parkingsAvailable;
        private TextView eta;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            parkingName = itemView.findViewById(R.id.parkingName);
            parkingsAvailable = itemView.findViewById(R.id.parkingsAvailable);
            eta = itemView.findViewById(R.id.eta);
        }

    }
}
