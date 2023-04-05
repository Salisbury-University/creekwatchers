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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.measurements_main);

        Button toDepth = (Button) findViewById(id.waterdepth);
        Button toSample = (Button) findViewById(id.samdist);
        Button toAirTemp = (Button) findViewById(id.airtemp);
        Button toWaterTemp = (Button) findViewById(id.watertemp);
        Button toSecchiDepth = (Button) findViewById(id.secdepth);

        TextView wdblack = (TextView) findViewById(id.wdblackbg);
        TextView sampblack = (TextView) findViewById(id.sdblackbg);
        TextView airblack = (TextView) findViewById(id.atblackbg);
        TextView wtblack = (TextView) findViewById(id.wtblackbg);
        TextView secblack = (TextView) findViewById(id.secblackbg);

        //Creates text views for measurement home page button
        TextView meashome_btn = (TextView) findViewById(id.meashome);

        //Allows the user to go back to main page when clicked the Home button
        BasicCommands.setActivity(this, meashome_btn, HomePage.class);
        BasicCommands.setActivity(this, toDepth, WaterDepth.class);
        BasicCommands.setActivity(this, toSample, SamplePage.class);
        BasicCommands.setActivity(this, toAirTemp, AirTemp.class);
        BasicCommands.setActivity(this, toWaterTemp, WaterTemp.class);
        BasicCommands.setActivity(this, toSecchiDepth, SecchiDepth.class);

        if(SurveyData.waterDepth[0] >=0) {
            toDepth.setBackgroundColor(getResources().getColor(R.color.maroon_dim));
            wdblack.setBackgroundColor(getResources().getColor(R.color.black_dim));
            toDepth.setTextColor(getResources().getColor(R.color.black));
        }
        if(SurveyData.sampleDist[0] >=0) {
            toSample.setBackgroundColor(getResources().getColor(R.color.maroon_dim));
            sampblack.setBackgroundColor(getResources().getColor(R.color.black_dim));
            toSample.setTextColor(getResources().getColor(R.color.black));
        }
        if(SurveyData.airTemp[0] >=0 && SurveyData.airTemp[1] >= 0) {
            toAirTemp.setBackgroundColor(getResources().getColor(R.color.maroon_dim));
            airblack.setBackgroundColor(getResources().getColor(R.color.black_dim));
            toAirTemp.setTextColor(getResources().getColor(R.color.black));
        }
        if(SurveyData.waterTemp[0] >=0 && SurveyData.waterTemp[1] >=0) {
            toWaterTemp.setBackgroundColor(getResources().getColor(R.color.maroon_dim));
            wtblack.setBackgroundColor(getResources().getColor(R.color.black_dim));
            toWaterTemp.setTextColor(getResources().getColor(R.color.black));
        }
        if(SurveyData.secchiDepth[0] >=0 && SurveyData.secchiDepth[1] >=0) {
            toSecchiDepth.setBackgroundColor(getResources().getColor(R.color.maroon_dim));
            secblack.setBackgroundColor(getResources().getColor(R.color.black_dim));
            toSecchiDepth.setTextColor(getResources().getColor(R.color.black));
        }
    }

}