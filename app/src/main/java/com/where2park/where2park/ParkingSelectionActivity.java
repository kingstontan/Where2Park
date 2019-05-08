package com.where2park.where2park;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;

import java.util.ArrayList;
import java.util.Collections;


public class ParkingSelectionActivity extends AppCompatActivity {

    private static final String TAG = "ParkingSelectionAct";

    public static Location userLocation = new Location("");

    private FusedLocationProviderClient fusedLocationClient;

    private LocationCallback locationCallback;

    private LocationRequest locationRequest = new LocationRequest().setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

    private void startLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        fusedLocationClient.requestLocationUpdates(locationRequest,
                locationCallback,
                null /* Looper */);
    }

    @Override
    protected void onResume() {
        super.onResume();
        startLocationUpdates();
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopLocationUpdates();
    }

    private void stopLocationUpdates() {
        fusedLocationClient.removeLocationUpdates(locationCallback);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //1. retrieve user's location

        fusedLocationClient = new FusedLocationProviderClient(this);

        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult == null) {
                    return;
                }
                for (Location location : locationResult.getLocations()) {
                    userLocation = location;
                }
            };
        };



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
                p.updateRealTimeInfo(getString(R.string.google_map_api_key),userLocation, this);
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































































































