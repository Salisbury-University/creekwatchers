package com.dylanlarrabee.watersurveyapp;
import static com.dylanlarrabee.watersurveyapp.R.*;
import static com.dylanlarrabee.watersurveyapp.R.id.waterdep_home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MeasurementsPage extends AppCompatActivity {
    Button toDepth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.measurements_main);

        toDepth = (Button) findViewById(id.waterdepth);

        //Creates text views for measurement home page button
        TextView meashome_btn = (TextView) findViewById(id.meashome);

        //Allows the user to go back to main page when clicked the Home button
        BasicCommands.setActivity(this, meashome_btn, HomePage.class);
        BasicCommands.setActivity(this, toDepth, WaterDepth.class);


    }

}