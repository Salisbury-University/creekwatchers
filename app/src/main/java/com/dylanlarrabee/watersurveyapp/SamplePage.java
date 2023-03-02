package com.dylanlarrabee.watersurveyapp;
import static com.dylanlarrabee.watersurveyapp.R.*;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SamplePage extends AppCompatActivity{
    
    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(layout.sample_dist);

        TextView samp_home = (TextView) findViewById(id.sampdist_home);
        ImageView samp_n = (ImageView) findViewById(id.samp_next);
        ImageView samp_b = (ImageView) findViewById(id.samp_back);

        BasicCommands.setActivity(this, samp_home, HomePage.class);
        BasicCommands.setActivity(this, samp_b, WaterDepth.class);
        BasicCommands.setActivity(this, samp_n, AirTemp.class);

    }
}
