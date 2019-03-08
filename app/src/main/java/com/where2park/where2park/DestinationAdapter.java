package com.where2park.where2park;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class DestinationAdapter extends ArrayAdapter<Destination> {

    public DestinationAdapter(Context context, List<Destination> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        Destination currentDest = getItem(position);

        TextView destinationName = listItemView.findViewById(R.id.destinationName);

        destinationName.setText(currentDest.getName());

        return listItemView;
    }
}
