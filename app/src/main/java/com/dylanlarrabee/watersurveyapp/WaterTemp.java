package com.dylanlarrabee.watersurveyapp;
import static com.dylanlarrabee.watersurveyapp.R.*;


import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class WaterTemp extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(layout.water_temp);

        TextView waterTemp_h = (TextView) findViewById(R.id.waterTemp_home);
        ImageView waterTemp_n = (ImageView) findViewById(R.id.waterTemp_next);
        ImageView waterTemp_b = (ImageView) findViewById(R.id.waterdep_back);

        BasicCommands.setActivity(this, waterTemp_h, HomePage.class);
        BasicCommands.setActivity(this, waterTemp_b, AirTemp.class);
        BasicCommands.setActivity(this, waterTemp_n, SecchiDepth.class);
    }
}
