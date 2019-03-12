package com.where2park.where2park;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class DestinationAdapter extends RecyclerView.Adapter<DestinationAdapter.ViewHolder> {

    private ArrayList<Destination> destinations;

    public DestinationAdapter(ArrayList<Destination> destinations) {
        this.destinations = destinations;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.destination_list_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
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

        private TextView destinationName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            destinationName = itemView.findViewById(R.id.destinationName);
        }

    }
}
