package com.dylanlarrabee.watersurveyapp;

import static com.dylanlarrabee.watersurveyapp.R.id;
import static com.dylanlarrabee.watersurveyapp.R.layout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class EstWeatherInfo extends SaveFormAct {

    private Intent toEstHome, toEstWaterSurface, toEstWindSpeed, toEstWeather;
    private Button homeButton, exitButton;
    private ImageView rightButton, leftButton;
    private TextView headerBox;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.estimate_weather_info);


        toEstHome = new Intent(this, EstimatesPage.class);
        toEstWaterSurface = new Intent(this, EstWaterSurface.class);
        toEstWindSpeed = new Intent(this, EstWindSpeed.class);
        toEstWeather = new Intent(this, EstWeather.class);

        setupButtons();
    }

    void setupButtons()
    {
        homeButton = (Button) findViewById(id.home_button);
        rightButton = (ImageView) findViewById(id.rightbutton_image);
        leftButton = (ImageView) findViewById(id.leftbutton_image);

        exitButton = (Button) findViewById(id.exit_button);
        headerBox = (TextView) findViewById(id.estimateTitle);

        setListener(homeButton, toEstHome);
        setListener(rightButton, toEstWindSpeed);
        setListener(leftButton, toEstWaterSurface);
        setListener(exitButton, toEstWeather);
        setListener(headerBox, toEstWeather);
    }

    void setListener(View button, Intent intent)
    {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });
    }


}
