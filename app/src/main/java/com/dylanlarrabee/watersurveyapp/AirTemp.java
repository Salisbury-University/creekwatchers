package com.dylanlarrabee.watersurveyapp;
import static com.dylanlarrabee.watersurveyapp.R.*;


import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AirTemp extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(layout.air_temp);

        TextView air_h = (TextView) findViewById(R.id.airhome);
        ImageView air_n = (ImageView) findViewById(R.id.air_next);
        ImageView air_b = (ImageView) findViewById(R.id.air_back);

        BasicCommands.setActivity(this, air_h, HomePage.class);
        BasicCommands.setActivity(this, air_b, SamplePage.class);
        BasicCommands.setActivity(this, air_n, WaterTemp.class);
    }
}
