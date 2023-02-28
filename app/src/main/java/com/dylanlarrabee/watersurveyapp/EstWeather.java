package com.dylanlarrabee.watersurveyapp;

import android.content.Intent;
import android.os.Bundle;

import static com.dylanlarrabee.watersurveyapp.R.id;
import static com.dylanlarrabee.watersurveyapp.R.layout;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class EstWeather extends AppCompatActivity {

    Intent toEstHome;
    int weatherNum;
    ImageView allBackgrounds[];
    ImageView clearBackground, partlycloudyBackground, overcastBackground, lightrainBackground, rainBackground, heavyrainBackground, fogBackground, snowBackground;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.estimate_weather);

        toEstHome = new Intent(this, EstimatesPage.class);

        Button homeButton = (Button) findViewById(id.home_button);
        Button clearButton = (Button) findViewById(id.clear_button);
        Button partlycloudyButton = (Button) findViewById(id.partlycloudy_button);
        Button overcastButton = (Button) findViewById(id.overcast_button);
        Button lightrainButton = (Button) findViewById(id.lightrain_button);
        Button rainButton = (Button) findViewById(id.rain_button);
        Button heavyrainButton = (Button) findViewById(id.heavyrain_button);
        Button fogButton = (Button) findViewById(id.fog_button);
        Button snowButton = (Button) findViewById(id.snow_button);

        clearBackground = (ImageView) findViewById(id.clear_background);
        partlycloudyBackground = (ImageView) findViewById(id.partlycloudy_background);
        overcastBackground = (ImageView) findViewById(id.overcast_background);
        lightrainBackground = (ImageView) findViewById(id.lightrain_background);
        rainBackground = (ImageView) findViewById(id.rain_background);
        heavyrainBackground = (ImageView) findViewById(id.heavyrain_background);
        fogBackground = (ImageView) findViewById(id.fog_background);
        snowBackground = (ImageView) findViewById(id.snow_background);

        allBackgrounds = new ImageView[] {clearBackground, partlycloudyBackground, overcastBackground, lightrainBackground, rainBackground, heavyrainBackground, fogBackground, snowBackground};

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(toEstHome);
            }
        });

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                highlightButton(clearBackground);
                weatherNum = 1;
            }
        });

        partlycloudyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                highlightButton(partlycloudyBackground);
                weatherNum = 2;
            }
        });
        overcastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                highlightButton(overcastBackground);
                weatherNum = 3;
            }
        });
        lightrainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                highlightButton(lightrainBackground);
                weatherNum = 4;
            }
        });
        rainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                highlightButton(rainBackground);
                weatherNum = 5;
            }
        });
        heavyrainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                highlightButton(heavyrainBackground);
                weatherNum = 6;
            }
        });
        fogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                highlightButton(fogBackground);
                weatherNum = 7;
            }
        });
        snowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                highlightButton(snowBackground);
                weatherNum = 8;
            }
        });

    }

    void highlightButton(ImageView background){

        for(int i = 0; i < 8; i++)
            allBackgrounds[i].setImageResource(R.color.white);
        background.setImageResource(R.color.green_main);
    }

}
