package com.where2park.where2park;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

public class ParkingSelectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_selection);



        ArrayList<Parking> sunwayUniversityParkings = new ArrayList<>();
        sunwayUniversityParkings.add(new Parking("Sunway University Basement"));
        sunwayUniversityParkings.add(new Parking("BRT Parking"));



        String destinationName;

        //0. retrieve user's location

        //1. take in parameter of destination chosen and user's location

        Intent intent = getIntent();

        Bundle extras = intent.getExtras();

        if (extras != null) {
            destinationName = extras.getString("DESTINATION_NAME");

            TextView parkingSelectionTitle = findViewById(R.id.parkingSelectionTitle);

            parkingSelectionTitle.setText("Parkings at " + destinationName);

            //2. retrieve the appropriate arrayList of places to search

            ArrayList<Parking> parkings = new ArrayList<>();

            switch (destinationName){
                case "Sunway University":
                    parkings = sunwayUniversityParkings;

                    default:
            }

            //3. updates parking objects in the list

            for(Parking p:parkings){
                p.updateRealTimeInfo();
            }

            //4. inserts objects into arrayList in CORRECT sequence

            Collections.sort(parkings);

            //5. create recycler view
            //6. populate recycler view with cardview

            RecyclerView parkingList = findViewById(R.id.parkingList);

            ParkingAdapter parkingAdapter = new ParkingAdapter(parkings, this);

            parkingList.setAdapter(parkingAdapter);

            parkingList.setLayoutManager(new LinearLayoutManager(this));





        }













    }

    @Override
    public void finish() {
        super.finish();
        onLeaveThisActivity();
    }

    protected void onLeaveThisActivity() {
        overridePendingTransition(R.anim.enter_from_left, R.anim.exit_to_right);
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        onStartNewActivity();
    }

    @Override
    public void startActivity(Intent intent, Bundle options) {
        super.startActivity(intent, options);
        onStartNewActivity();
    }

    protected void onStartNewActivity() {
        overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);
    }
}































































































