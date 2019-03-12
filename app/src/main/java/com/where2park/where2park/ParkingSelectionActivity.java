package com.where2park.where2park;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ParkingSelectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_selection);

        //0. retrieve user's location

        //1. take in parameter of destination chosen and user's location

        Intent intent = getIntent();

        Bundle extras = intent.getExtras();

        if (extras != null) {
            String destinationName = extras.getString("DESTINATION_NAME");

//            TextView textView = findViewById(R.id.parkingSelectionTitle);
//            textView.setText(destinationName);
        }




        //2. retrieve the appropriate arrayList of places to search

        //3. creates parking objects based on the list

        //4. inserts objects into arrayList in CORRECT sequence

        //5. create recycler view

        //6. populate recycler view with cardview




    }
}
