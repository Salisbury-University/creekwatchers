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

public class EstRainfallInfo extends AppCompatActivity {

    private Intent toEstHome, toEstWindDirection, toMeasWaterDepth;
    private Button homeButton, exitButton;
    private ImageView rightButton, leftButton;
    private TextView headerBox;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.estimate_rainfall_info);

        toEstHome = new Intent(this, EstRainfall.class);
        toEstWindDirection = new Intent(this, EstWindDirection.class);
        toMeasWaterDepth = new Intent(this, WaterDepth.class);
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
        setListener(rightButton, toMeasWaterDepth);
        setListener(leftButton, toEstWindDirection);
        setListener(exitButton, toEstHome);
        setListener(headerBox, toEstHome);
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
