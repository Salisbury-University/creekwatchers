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

public class EstRainfall extends AppCompatActivity {

    private Intent toEstHome, toEstWindDirection, toMeasWaterDepth, toEstRainfallInfo, toReview;
    private Button homeButton, stormButton, heavyButton, moderateButton, lightButton, traceButton, noneButton,
            stormButtonbg, heavyButtonbg, moderateButtonbg,lightButtonbg, traceButtonbg, noneButtonbg;
    private Button allButtons[], allBtnBgs[];
    private ImageView rightButton, leftButton;
    private TextView infoButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.estimate_rainfall);


        toEstHome = new Intent(this, EstimatesPage.class);
        toEstWindDirection = new Intent(this, EstWindDirection.class);
        toMeasWaterDepth = new Intent(this, WaterDepth.class);
        toEstRainfallInfo = new Intent(this, EstRainfallInfo.class);
        toReview = new Intent(this, ReviewPage.class);
        setupButtons();
        setupListeners();
        if(SurveyData.rainfall >=0)
        {
            highlightButton(SurveyData.rainfall);
        }
    }

    void setupButtons() {
        homeButton = (Button) findViewById(id.home_button);
        rightButton = (ImageView) findViewById(id.rightbutton_image);
        leftButton = (ImageView) findViewById(id.leftbutton_image);
        infoButton = (TextView) findViewById(id.estimateTitle);

        stormButton = (Button) findViewById(id.storm_button);
        heavyButton = (Button) findViewById(id.heavy_button);
        moderateButton= (Button) findViewById(id.moderate_button);
        lightButton = (Button) findViewById(id.light_button);
        traceButton = (Button) findViewById(id.trace_button);
        noneButton = (Button) findViewById(id.none_button);

        stormButtonbg = (Button) findViewById(id.storm_button2);
        heavyButtonbg = (Button) findViewById(id.heavy_button2);
        moderateButtonbg= (Button) findViewById(id.moderate_button2);
        lightButtonbg = (Button) findViewById(id.light_button2);
        traceButtonbg = (Button) findViewById(id.trace_button2);
        noneButtonbg = (Button) findViewById(id.none_button2);

        allButtons = new Button[] {stormButton, heavyButton, moderateButton, lightButton, traceButton, noneButton};
        allBtnBgs = new Button[] {stormButtonbg, heavyButtonbg, moderateButtonbg, lightButtonbg, traceButtonbg, noneButtonbg};
    }
    void setupListeners() {

        for(int i = 0; i < 6; i++) {
            int finalI = i;
            allBtnBgs[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    highlightButton(finalI);
                }
            });
        }

        setListener(infoButton, toEstRainfallInfo);
        setListener(rightButton, toMeasWaterDepth);
        setListener(leftButton, toEstWindDirection);
        if(Config.isReviewing)
            setListener(homeButton, toReview);
        else
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


    void highlightButton(int ival)
    {
        for(int i = 0; i < allButtons.length; i++)
        {
            allButtons[i].setTextColor(getResources().getColor(R.color.black));
            allBtnBgs[i].setAlpha(0.33333F);
        }
        allBtnBgs[ival].setAlpha(0F);
        if(ival!=5) //don't change the "none" text white
            allButtons[ival].setTextColor(getResources().getColor(R.color.white));

        SurveyData.rainfall = ival;
    }



}
