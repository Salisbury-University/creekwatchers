package com.dylanlarrabee.watersurveyapp;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class EstWindDirection  extends AppCompatActivity {
    
    Intent toEstHome, toEstWindSpeed, toEstWindDirectInfo, toEstRainfall;
    ImageView allButtons[];
    Button homeButton;
    ImageView rightButton, leftButton;
    TextView infoButton;
    ImageView northButton, northeastButton, eastButton, southeastButton, southButton, southwestButton, westButton, northwestButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.estimate_winddirection);

        toEstHome = new Intent(this, EstimatesPage.class);
        toEstWindSpeed = new Intent(this, EstWindSpeed.class);
        toEstWindDirectInfo = new Intent(this, EstWindDirectionInfo.class);
        toEstRainfall = new Intent(this, EstimatesPage.class);

        setupButtons();
        setupListeners();
    }

    void setupButtons(){

        homeButton = (Button) findViewById(R.id.home_button);
        leftButton = (ImageView) findViewById(R.id.leftbutton_image);
        rightButton = (ImageView) findViewById(R.id.rightbutton_image);
        infoButton = (TextView) findViewById(R.id.estimateTitle);

        northButton = (ImageView) findViewById(R.id.north_button);
        northeastButton = (ImageView) findViewById(R.id.northeast_button);
        eastButton = (ImageView) findViewById(R.id.east_button);
        southeastButton = (ImageView) findViewById(R.id.southeast_button);
        southButton = (ImageView) findViewById(R.id.south_button);
        southwestButton = (ImageView) findViewById(R.id.southwest_button);
        westButton = (ImageView) findViewById(R.id.west_button);
        northwestButton = (ImageView) findViewById(R.id.northwest_button);

       allButtons = new ImageView[] {northButton, northeastButton, eastButton, southeastButton, southButton, southwestButton, westButton, northwestButton};

        if(SurveyData.windDir >= 0)
            highlightButton(allButtons[SurveyData.windDir]);

    }

    void setupListeners() {

        for(int i = 0; i < 8; i++) {
            int finalI = i;
            allButtons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    highlightButton(allButtons[finalI]);
                    SurveyData.windDir = finalI;
                }
            });
        }
        setListener(infoButton, toEstWindDirectInfo);
        setListener(leftButton, toEstWindSpeed);
        setListener(rightButton, toEstRainfall);
        setListener(homeButton, toEstHome);
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

    void highlightButton(ImageView background){

        for(int i = 0; i < 8; i++)
            allButtons[i].setImageTintList(ColorStateList.valueOf(Color.WHITE));
        background.setImageTintList(ColorStateList.valueOf(Color.GREEN));
    }




}
