package com.where2park.where2park;

import android.location.Location;
import android.util.Log;

import com.google.maps.DirectionsApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.PendingResult;
import com.google.maps.model.DirectionsResult;

public class Parking implements Comparable<Parking>{

    private String name;
    private int lotsavailable;
    private int etadrive;
    private int etawalk;
    private int score;

    private static final String TAG = "Parking";
    private GeoApiContext mGeoApiContext = null;


    public Parking(String name) {
        this.name = name;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLotsavailable() {
        return lotsavailable;
    }

    public void setLotsavailable(int lotsavailable) {
        this.lotsavailable = lotsavailable;
    }

    public int getEtadrive() {
        return etadrive;
    }

    public void setEtadrive(int etadrive) {
        this.etadrive = etadrive;
    }

    public int getEtawalk() {
        return etawalk;
    }

    public void setEtawalk(int etawalk) {
        this.etawalk = etawalk;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }


//    public void updateRealTimeInfo(){
//
//        if(mGeoApiContext == null){
//            mGeoApiContext = new GeoApiContext.Builder().apiKey("AIzaSyBVyN6DLB8sIaJckdLb7EmHKcPvEeNmb9E").build();
//        }
//
//        calculateDirections(ParkingSelectionActivity.userLocation);
//
//        setEtadrive(3);
//        setEtawalk(3);
//        setLotsavailable(500);
//        setScore(lotsavailable - etadrive - etawalk);
//    }

    public void updateRealTimeInfo(String apikey, double lat, double lon){

        if(mGeoApiContext == null){
            mGeoApiContext = new GeoApiContext.Builder().apiKey(apikey).build();
        }

        calculateDirections(lat,lon);

        setEtadrive(3);
        setEtawalk(3);
        setLotsavailable(500);
        setScore(lotsavailable - etadrive - etawalk);
    }

    @Override
    public int compareTo(Parking o) {
        return o.getScore() - this.getScore() ;
    }

    private void calculateDirections(double lat, double lon){
        Log.d(TAG, "calculateDirections: calculating directions.");

        com.google.maps.model.LatLng destination = new com.google.maps.model.LatLng(
                3.066658, 101.603523
                //HARDCODE
        );
        DirectionsApiRequest directions = new DirectionsApiRequest(mGeoApiContext);

        directions.alternatives(true);
        directions.origin(
                new com.google.maps.model.LatLng(
                        lat, lon
                )
        );
        Log.d(TAG, "calculateDirections: destination: " + destination.toString());
        directions.destination(destination).setCallback(new PendingResult.Callback<DirectionsResult>() {
            @Override
            public void onResult(DirectionsResult result) {
                Log.d(TAG, "onResult: routes: " + result.routes[0].toString());
                Log.d(TAG, "onResult: geocodedWayPoints: " + result.geocodedWaypoints[0].toString());
            }

            @Override
            public void onFailure(Throwable e) {
                Log.e(TAG, "onFailure: " + e.getMessage() );

            }
        });
    }
}















































