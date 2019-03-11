package com.where2park.where2park;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

        ArrayList<Destination> destinations = new ArrayList<>();

        destinations.add(new Destination("Sunway Pyramid"));
        destinations.add(new Destination("Sunway University"));
        destinations.add(new Destination("Monash University"));
        destinations.add(new Destination("Sunway Geo"));
        destinations.add(new Destination("Sunway Medical"));



        RecyclerView destinationList = findViewById(R.id.destinationList);

        destinationList.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        destinationList.setLayoutManager(layoutManager);

        DestinationAdapter destinationAdapter = new DestinationAdapter(destinations);

        destinationList.setAdapter(destinationAdapter);




//        destinationList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent i = new Intent(DestinationSelectionActivity.this, ParkingSelectionActivity.class);
//                startActivity(i);
//            }
//        });
    }
}
