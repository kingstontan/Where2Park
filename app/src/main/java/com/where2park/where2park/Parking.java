package com.where2park.where2park;

import android.content.Context;
import android.location.Location;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.maps.DirectionsApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.PendingResult;
import com.google.maps.model.DirectionsResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Parking implements Comparable<Parking>{

    private String name;
    private String address;
    private int lotsavailable;
    private String etadrive;
    private int etawalk;
    private int score;

    private static final String TAG = "Parking";
    private GeoApiContext mGeoApiContext = null;


    public Parking(String name, String address) {
        this.name = name;
        this.address = address;
        this.etadrive = "fafafa";
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getLotsavailable() {
        return lotsavailable;
    }

    public void setLotsavailable(int lotsavailable) {
        this.lotsavailable = lotsavailable;
    }

    public String getEtadrive() {
        return etadrive;
    }

    public void setEtadrive(String etadrive) {
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

        if(mGeoApiContext == null){
            mGeoApiContext = new GeoApiContext.Builder().apiKey(apikey).build();
        }

        //Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(context);

        String url =("https://maps.googleapis.com/maps/api/directions/json?" +
                "origin=" + location.getLatitude() + "," + location.getLongitude() +
                "&destination=" + this.getAddress() +
                "&key=" + apikey);



    //     Request a string response from the provided URL.



        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
            new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject responseObject) {
                    try {
                        Log.d(TAG, responseObject.toString());
                        JSONArray routesArray = responseObject.getJSONArray("routes");

                        JSONObject routesObject = routesArray.getJSONObject(0);

                        JSONArray legsArray = routesObject.getJSONArray("legs");

                        JSONObject legsObject = legsArray.getJSONObject(0);

                        JSONObject duration = legsObject.getJSONObject("duration");

                        setEtadrive(duration.getString("text"));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
             error.printStackTrace();
        }
    });

        // Add the request to the RequestQueue.
        queue.add(request);

        setEtawalk(3);
        setLotsavailable(500);
        setScore(lotsavailable - 124 - etawalk);
    }


//    private void calculateDirections(Location location){
//        Log.d(TAG, "calculateDirections: calculating directions.");
//
//        com.google.maps.model.LatLng destination = new com.google.maps.model.LatLng(
//                3.066308, 101.604136
//        );
//        DirectionsApiRequest directions = new DirectionsApiRequest(mGeoApiContext);
//
//        directions.alternatives(true);
//        directions.origin(
//                new com.google.maps.model.LatLng(
//                        location.getLatitude(), location.getLongitude()
//                )
//        );
//        Log.d(TAG, "calculateDirections: destination: " + destination.toString());
//        directions.destination(destination).setCallback(new PendingResult.Callback<DirectionsResult>() {
//            @Override
//            public void onResult(DirectionsResult result) {
//                Log.d(TAG, "calculateDirections: duration: " + result.routes[0].legs[0].duration);
//            }
//
//            @Override
//            public void onFailure(Throwable e) {
//                Log.e(TAG, "onFailure: " + e.getMessage() );
//
//            }
//        });
//    }


}














































