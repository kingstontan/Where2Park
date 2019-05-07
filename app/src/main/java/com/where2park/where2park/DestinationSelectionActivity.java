package com.where2park.where2park;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

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



    @Override
    public void finish() {
        super.finish();
        onLeaveThisActivity();
    }

    protected void onLeaveThisActivity() {
       // overridePendingTransition(R.anim.enter_from_left, R.anim.exit_to_right);
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
