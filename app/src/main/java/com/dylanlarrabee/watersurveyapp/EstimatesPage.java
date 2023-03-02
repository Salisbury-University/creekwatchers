package com.dylanlarrabee.watersurveyapp;

import static com.dylanlarrabee.watersurveyapp.R.id;
import static com.dylanlarrabee.watersurveyapp.R.layout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class EstimatesPage extends AppCompatActivity {
    altStartActivity asa;
    Intent toHome, toWeather, toTide, toWaterSurface;
    SurveyData mysd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.estimate_main);

        mysd = (SurveyData) getIntent().getSerializableExtra("mysd");


        toHome = new Intent(this,HomePage.class);
        toWeather = new Intent(this, EstWeather.class);
        toTide = new Intent(this, EstTide.class);
        toWaterSurface = new Intent(this, EstWaterSurface.class);

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
                toHome.putExtra("mysd",mysd);
                startActivity(toHome);
            }
        });

        tideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toTide.putExtra("mysd",mysd);
                startActivity(toTide);
            }
        });

        watersurfaceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toWaterSurface.putExtra("mysd",mysd);
                startActivity(toWaterSurface);
            }
        });


        weatherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toWeather.putExtra("mysd",mysd);
                startActivity(toWeather);}
        });

        windspeedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(layout.estimate_windspeed);
            }
        });


        winddirectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(layout.estimate_winddirection);
            }
        });

        rainfallButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(layout.estimate_rainfall);
            }
        });


    }



}