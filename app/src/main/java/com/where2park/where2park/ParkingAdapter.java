package com.where2park.where2park;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class ParkingAdapter extends RecyclerView.Adapter<ParkingAdapter.ViewHolder> {

    private ArrayList<Parking> parkings;
    private Context context;

    public ParkingAdapter(ArrayList<Parking> parkings, Context context) {
        this.parkings = parkings;
        this.context = context;
    }

    @NonNull
    @Override
    public ParkingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.parking_list_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        final Parking parking = parkings.get(position);

        holder.parkingName.setText(parking.getName());
        holder.parkingsAvailable.setText(String.valueOf(parking.getLotsavailable()));
        holder.eta.setText(String.valueOf(parking.getEtadrive()) + " mins drive + " + String.valueOf(parking.getEtawalk()) + " mins walk");

    }

    @Override
    public int getItemCount() {
        return parkings.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private CardView cardView;
        private TextView parkingName;
        private TextView parkingsAvailable;
        private TextView eta;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cardView = itemView.findViewById(R.id.cardView);
            parkingName = itemView.findViewById(R.id.parkingName);
            parkingsAvailable = itemView.findViewById(R.id.parkingsAvailable);
            eta = itemView.findViewById(R.id.eta);
        }

    }
}
