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

        TextView wdmeas = (TextView) findViewById(R.id.waterdep_meas);
        double []waterDep = {-1};


        //Buttons for the new views
        TextView water_h = (TextView) findViewById(id.waterdep_home);
        ImageView water_b = (ImageView) findViewById(id.waterdep_back);
        ImageView water_n = (ImageView) findViewById(id.waterdep_next);

        BasicCommands.setActivity(this, water_h, MeasurementsPage.class);
        BasicCommands.setActivity(this, water_b, EstRainfall.class);
        BasicCommands.setActivity(this, water_n, SamplePage.class);

        BasicCommands.setAlertBox(this, wdmeas, 0, waterDep," cm");
        SurveyData.waterDepth = waterDep[0];

        if(SurveyData.waterDepth > 0)
        {
            wdmeas.setText(""+SurveyData.waterDepth);
        }
    }
}