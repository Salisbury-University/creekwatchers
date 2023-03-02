package com.dylanlarrabee.watersurveyapp;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class WaterTemp extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);

        TextView waterTemp_home = (TextView) findViewById(R.id.waterTemp_home);
        ImageView waterTemp_next = (ImageView) findViewById(R.id.waterTemp_next);
        ImageView waterTemp_back = (ImageView) findViewById(R.id.waterdep_back);

        BasicCommands.setActivity(this, waterTemp_home, HomePage.class);
        BasicCommands.setActivity(this, waterTemp_back, AirTemp.class);
        BasicCommands.setActivity(this, waterTemp_next, SecchiDepth.class);
    }
}
