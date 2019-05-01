package com.where2park.where2park;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import com.google.firebase.firestore.GeoPoint;


public class ParkingSelectionActivity extends AppCompatActivity {

    private static final String TAG = "ParkingSelectionAct";

    private FusedLocationProviderClient mFusedLocationClient;

    private void getLastKnownLocation() {
        Log.d(TAG, "getLastKnownLocation: called.");
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mFusedLocationClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<android.location.Location>() {
            @Override
            public void onComplete(@NonNull Task<android.location.Location> task) {
                if (task.isSuccessful()) {
                    Location location = task.getResult();
                    GeoPoint geoPoint = new GeoPoint(location.getLatitude(), location.getLongitude());
                    Log.d(TAG, "onComplete: latitude: " + geoPoint.getLatitude());
                    Log.d(TAG, "onComplete: longitude: " + geoPoint.getLongitude());
                }
            }
        });

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_selection);



        ArrayList<Parking> sunwayUniversityParkings = new ArrayList<>();
        sunwayUniversityParkings.add(new Parking("Sunway University Basement"));
        sunwayUniversityParkings.add(new Parking("BRT Parking"));



        String destinationName;


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

            //3. retrieve user's location

            mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

            getLastKnownLocation();

            //4. updates parking objects in the list

            for(Parking p:parkings){
                p.updateRealTimeInfo();
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































































































