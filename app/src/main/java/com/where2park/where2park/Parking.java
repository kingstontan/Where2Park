package com.where2park.where2park;

import android.content.Context;
import android.location.Location;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.maps.GeoApiContext;

public class Parking implements Comparable<Parking>{

    private String name;
    private double lat;
    private double lon;
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

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
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


    @Override
    public int compareTo(Parking o) {
        return o.getScore() - this.getScore() ;
    }



    public void updateRealTimeInfo(String apikey, Location location, Context context){

        this.setLat(location.getLatitude());
        this.setLon(location.getLongitude());

        if(mGeoApiContext == null){
            mGeoApiContext = new GeoApiContext.Builder().apiKey(apikey).build();
        }

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(context);

        String url =("https://maps.googleapis.com/maps/api/directions/json?" +
                "origin=" + this.getLat() + "," + this.getLon() +
                "&destination=" + "place_id:ChIJS2QSWY9MzDERaa1cIqAvYXc" +
                "&key=" + apikey);



        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        Log.d(TAG, "calculateDirections: " + response.substring(0));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "calculateDirections: ERROR");
            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);


        setEtadrive(3);
        setEtawalk(3);
        setLotsavailable(500);
        setScore(lotsavailable - etadrive - etawalk);
    }




}














































