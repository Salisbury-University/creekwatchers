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
    altStartActivity asa;
    Intent toHome, toWeather, toTide, toWaterSurface, toWindSpeed, toWindDirection;
    SurveyData mysd;
    Button homeButton, tideButton, watersurfaceButton, weatherButton, windspeedButton, winddirectButton, rainfallButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.estimate_main);

        mysd = (SurveyData) getIntent().getSerializableExtra("mysd");

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

        if(mysd.tideEst >=0) {
            tideButton.setBackgroundColor(Color.BLACK);
        }
        if(mysd.waterSurf >=0) {
            watersurfaceButton.setBackgroundColor(Color.BLACK);
        }
        if(mysd.weathEst >=0) {
            weatherButton.setBackgroundColor(Color.BLACK);
        }
        if(mysd.windSpeed >=0)
            windspeedButton.setBackgroundColor(Color.BLACK);
        if(mysd.windDir >=0)
            winddirectButton.setBackgroundColor(Color.BLACK);
        if(mysd.rainfall >=0)
            rainfallButton.setBackgroundColor(Color.BLACK);
    }

    void setupIntents() {
        toHome = new Intent(this,HomePage.class);
        toWeather = new Intent(this, EstWeather.class);
        toTide = new Intent(this, EstTide.class);
        toWaterSurface = new Intent(this, EstWaterSurface.class);
        toWindSpeed = new Intent(this, EstWindSpeed.class);
        toWindDirection = new Intent(this, EstWindDirection.class);
//      toEstRainfall = new Intent(this, EstRainfall.class);
    }

    void setupListeners()
    {
        setListener(homeButton, toHome);
        setListener(tideButton, toTide);
        setListener(watersurfaceButton, toWaterSurface);
        setListener(weatherButton, toWeather);
        setListener(windspeedButton, toWindSpeed);
       setListener(winddirectButton, toWindDirection);
//      setListener(rainfallButton, toRainfall);
    }

    void setListener(Button button, Intent intent)
    {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("mysd", mysd);
                startActivity(intent);
            }
        });
    }



}