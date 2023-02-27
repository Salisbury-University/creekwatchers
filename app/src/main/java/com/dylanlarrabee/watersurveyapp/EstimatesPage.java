package com.dylanlarrabee.watersurveyapp;

import static com.dylanlarrabee.watersurveyapp.R.id;
import static com.dylanlarrabee.watersurveyapp.R.id.waterdep_home;
import static com.dylanlarrabee.watersurveyapp.R.layout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class EstimatesPage extends AppCompatActivity {

    Intent toHome, toWeather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.estimates_main);

        toHome = new Intent(this,HomePage.class);
        toWeather = new Intent(this, EstWeather.class);

        Button homeButton = (Button) findViewById(id.esthome);
        Button tideButton = (Button) findViewById(id.tide_button);
        Button watersurfaceButton = (Button) findViewById(id.watersurface_button);
        Button weatherButton = (Button) findViewById(id.weather_button);
        Button windspeedButton = (Button) findViewById(id.windspeed_button);
        Button winddirectButton = (Button) findViewById(id.winddirect_button);
        Button rainfallButton = (Button) findViewById(id.rainfall_button);

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(toHome);
            }
        });

        tideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(layout.tide_estimate);
            }
        });

        watersurfaceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(layout.water_surface_estimate);
            }
        });


        weatherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { startActivity(toWeather);}
        });

        windspeedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(layout.wind_estimate);
            }
        });


        winddirectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(layout.wind_direction_estimate);
            }
        });

        rainfallButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(layout.rainfall_estimate);
            }
        });


    }



}