package com.where2park.where2park;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class DestinationAdapter extends RecyclerView.Adapter<DestinationAdapter.ViewHolder> {

    private int numberOfItems;
    private ArrayList<Destination> destinations;

    public DestinationAdapter(int numberOfItems, ArrayList<Destination> destinations) {
        this.numberOfItems = numberOfItems;
        this.destinations = destinations;
    }


        @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Destination destination = destinations.get(position);
        holder.destinationName.setText(destination.getName());
    }

    @Override
    public int getItemCount() {
        return numberOfItems;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView destinationName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            destinationName = itemView.findViewById(R.id.destinationName);
        }

    }
}
