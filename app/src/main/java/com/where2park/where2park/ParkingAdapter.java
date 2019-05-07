package com.where2park.where2park;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static com.android.volley.VolleyLog.TAG;

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

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                String latitude = String.valueOf(parking.getLat());
                String longitude = String.valueOf(parking.getLon());
                Uri gmmIntentUri = Uri.parse("google.navigation:q=" + latitude + "," + longitude);
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");

                context.startActivity(mapIntent);
//                try{
//                    if (mapIntent.resolveActivity(getActivity().getPackageManager()) != null) {
//                        startActivity(mapIntent);
//                    }
//                }catch (NullPointerException e){
//                    Log.e(TAG, "onClick: NullPointerException: Couldn't open map." + e.getMessage() );
//                    Toast.makeText(getActivity(), "Couldn't open map", Toast.LENGTH_SHORT).show();
//                }
            }
        });

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
