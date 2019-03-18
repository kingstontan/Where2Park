package com.where2park.where2park;

import android.content.Intent;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class DestinationSelectionActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destination_selection);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

        ArrayList<Destination> destinations = new ArrayList<>();

        destinations.add(new Destination("Sunway Pyramid"));
        destinations.add(new Destination("Sunway University"));
        destinations.add(new Destination("Monash University"));
        destinations.add(new Destination("Sunway Geo"));
        destinations.add(new Destination("Sunway Medical"));

        RecyclerView destinationList = findViewById(R.id.destinationList);

        DestinationAdapter destinationAdapter = new DestinationAdapter(destinations, this);

        destinationList.setAdapter(destinationAdapter);

        destinationList.setLayoutManager(new LinearLayoutManager(this));

    }
}
