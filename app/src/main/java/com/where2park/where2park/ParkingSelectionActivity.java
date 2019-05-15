package com.where2park.where2park;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;
import java.util.Collections;


public class ParkingSelectionActivity extends AppCompatActivity {

    private static final String TAG = "ParkingSelectionAct";






    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_selection);



        ArrayList<Parking> sunwayUniversityParkings = new ArrayList<>();
        sunwayUniversityParkings.add(new Parking("Sunway University Car Park", "Sunway+University+Car+Park"));
        sunwayUniversityParkings.add(new Parking("Sun-U Monash BRT Station", "Rapid+Bus+Sdn+Bhd+(BRT+Sunway+Depot)"));



        String destinationName;


        //2. take in parameter of destination chosen and user's location

        Intent intent = getIntent();

        Bundle extras = intent.getExtras();

        if (extras != null) {
            destinationName = extras.getString("DESTINATION_NAME");

            TextView parkingSelectionTitle = findViewById(R.id.parkingSelectionTitle);

            parkingSelectionTitle.setText("Parkings for " + destinationName);

            //3. retrieve the appropriate arrayList of places to search

            ArrayList<Parking> parkings = new ArrayList<>();

            switch (destinationName){
                case "Sunway University":
                    parkings = sunwayUniversityParkings;

                    default:
            }

            //4. updates parking objects in the list

            for(Parking p:parkings){
                p.updateRealTimeInfo(getString(R.string.google_map_api_key),DestinationSelectionActivity.userLocation, this);
            }

            //5. inserts objects into arrayList in CORRECT sequence

            Collections.sort(parkings);

            //6. create recycler view
            //7. populate recycler view with cardview

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































































































