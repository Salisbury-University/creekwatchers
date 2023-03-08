package com.dylanlarrabee.watersurveyapp;

import static com.dylanlarrabee.watersurveyapp.R.id;
import static com.dylanlarrabee.watersurveyapp.R.layout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ViewFlipper;

import androidx.appcompat.app.AppCompatActivity;


public class EstimatesPage extends AppCompatActivity {

    private Intent toHome, toWeather, toTide, toWaterSurface, toWindSpeed, toWindDirection, toRainfall;

    private Button homeButton, tideButton, watersurfaceButton, weatherButton, windspeedButton, winddirectButton, rainfallButton;
    private TextView tideblack,waterblack,weatherblack,speedblack,winddirblack,rainfallblack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.estimate_main);

        setupIntents();
        setupButtons();
        setupListeners();

    }

    void setupButtons()
    {
        homeButton = (Button) findViewById(id.esthome);
        tideButton = (Button) findViewById(id.tide_button);
        watersurfaceButton = (Button) findViewById(id.watersurface_button);
        weatherButton = (Button) findViewById(id.weather_button);
        windspeedButton = (Button) findViewById(id.windspeed_button);
        winddirectButton = (Button) findViewById(id.winddirect_button);
        rainfallButton = (Button) findViewById(id.rainfall_button);

        tideblack = (TextView) findViewById(id.tideblackbg);
        waterblack = (TextView) findViewById(id.tideblackbg2);
        weatherblack = (TextView) findViewById(id.tideblackbg3);
        speedblack = (TextView) findViewById(id.tideblackbg4);
        winddirblack = (TextView) findViewById(id.tideblackbg5);
        rainfallblack = (TextView) findViewById(id.tideblackbg6);

        if(SurveyData.tideEst >=0) {
            tideButton.setBackgroundColor(getResources().getColor(R.color.maroon_dim));
            tideblack.setBackgroundColor(getResources().getColor(R.color.black_dim));
            tideButton.setTextColor(getResources().getColor(R.color.black));
        }
        if(SurveyData.waterSurf >=0) {
            watersurfaceButton.setBackgroundColor(getResources().getColor(R.color.maroon_dim));
            waterblack.setBackgroundColor(getResources().getColor(R.color.black_dim));
            watersurfaceButton.setTextColor(getResources().getColor(R.color.black));
        }
        if(SurveyData.weathEst >=0) {
            weatherButton.setBackgroundColor(getResources().getColor(R.color.maroon_dim));
            weatherblack.setBackgroundColor(getResources().getColor(R.color.black_dim));
            weatherButton.setTextColor(getResources().getColor(R.color.black));
        }
        if(SurveyData.windSpeed >=0) {
            windspeedButton.setBackgroundColor(getResources().getColor(R.color.maroon_dim));
            speedblack.setBackgroundColor(getResources().getColor(R.color.black_dim));
            windspeedButton.setTextColor(getResources().getColor(R.color.black));
        }
        if(SurveyData.windDir >=0) {
            winddirectButton.setBackgroundColor(getResources().getColor(R.color.maroon_dim));
            winddirblack.setBackgroundColor(getResources().getColor(R.color.black_dim));
            winddirectButton.setTextColor(getResources().getColor(R.color.black));
        }
        if(SurveyData.rainfall >=0) {
            rainfallButton.setBackgroundColor(getResources().getColor(R.color.maroon_dim));
            rainfallblack.setBackgroundColor(getResources().getColor(R.color.black_dim));
            rainfallButton.setTextColor(getResources().getColor(R.color.black));
        }
    }

    void setupIntents() {
        toHome = new Intent(this,HomePage.class);
        toWeather = new Intent(this, EstWeather.class);
        toTide = new Intent(this, EstTide.class);
        toWaterSurface = new Intent(this, EstWaterSurface.class);
        toWindSpeed = new Intent(this, EstWindSpeed.class);
        toWindDirection = new Intent(this, EstWindDirection.class);
        toRainfall = new Intent(this, EstRainfall.class);
    }

    void setupListeners()
    {
        setListener(homeButton, toHome);
        setListener(tideButton, toTide);
        setListener(watersurfaceButton, toWaterSurface);
        setListener(weatherButton, toWeather);
        setListener(windspeedButton, toWindSpeed);
        setListener(winddirectButton, toWindDirection);
        setListener(rainfallButton, toRainfall);
    }

    void setListener(Button button, Intent intent)
    {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });
    }


}