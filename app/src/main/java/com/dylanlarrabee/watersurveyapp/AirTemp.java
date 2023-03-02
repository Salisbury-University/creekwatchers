package com.dylanlarrabee.watersurveyapp;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AirTemp extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);

        TextView air_home = (TextView) findViewById(R.id.airhome);
        ImageView air_next = (ImageView) findViewById(R.id.air_next);
        ImageView air_back = (ImageView) findViewById(R.id.air_back);

        BasicCommands.setActivity(this, air_home, HomePage.class);
        BasicCommands.setActivity(this, air_back, SamplePage.class);
        BasicCommands.setActivity(this, air_next, WaterTemp.class);
    }
}
