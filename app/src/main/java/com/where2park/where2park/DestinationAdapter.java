package com.where2park.where2park;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class DestinationAdapter extends RecyclerView.Adapter<DestinationAdapter.ViewHolder> {

    private ArrayList<Destination> destinations;
    private Context context;

    public DestinationAdapter(ArrayList<Destination> destinations, Context context) {
        this.destinations = destinations;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.destination_list_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        final Destination destination = destinations.get(position);
        holder.destinationName.setText(destination.getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent i = new Intent(context, ParkingSelectionActivity.class);
                i.putExtra("DESTINATION_NAME",destination.getName());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return destinations.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private FrameLayout frame;
        private TextView destinationName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            frame = itemView.findViewById(R.id.frame);
            destinationName = itemView.findViewById(R.id.destinationName);
        }

    }
}
