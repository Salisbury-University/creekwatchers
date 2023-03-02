package com.dylanlarrabee.watersurveyapp;
import static com.dylanlarrabee.watersurveyapp.R.*;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

public class WaterDepth extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.water_depth);

        //Buttons for the new views
        TextView water_home = (TextView) findViewById(id.waterdep_home);
        ImageView water_back = (ImageView) findViewById(id.waterdep_back);
        ImageView water_next = (ImageView) findViewById(id.waterdep_next);

        BasicCommands.setActivity(this, water_home, HomePage.class);
        //BasicCommands.setActivity(this, water_back, EstRainFall.class);
        BasicCommands.setActivity(this, water_next, SamplePage.class);
    }


}