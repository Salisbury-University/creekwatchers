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

public class EstWeatherInfo extends AppCompatActivity {

    Intent toEstHome, toEstWaterSurface, toEstWindSpeed, toEstWeather;
    Button homeButton, exitButton;
    ImageView rightButton, leftButton;
    TextView headerBox;
    SurveyData mysd;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.estimate_weather_info);

        mysd = (SurveyData) getIntent().getSerializableExtra("mysd");

        toEstHome = new Intent(this, EstimatesPage.class);
        toEstWaterSurface = new Intent(this, EstWaterSurface.class);
        toEstWindSpeed = new Intent(this, EstimatesPage.class);
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
                intent.putExtra("mysd", mysd);
                startActivity(intent);
            }
        });
    }


}
