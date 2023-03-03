package com.dylanlarrabee.watersurveyapp;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;

import static com.dylanlarrabee.watersurveyapp.R.id;
import static com.dylanlarrabee.watersurveyapp.R.layout;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

public class EstTide extends AppCompatActivity {

    Intent toEstHome, toEstWaterSurface, toEstTideInfo;
    Button homeButton, highButton, midFallingButton, lowButton, midFloodingButton, nontidalButton;
    Button allButtons[];
    ImageView rightButton;
    TextView infoButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.estimate_tide);


        toEstHome = new Intent(this, EstimatesPage.class);
        toEstWaterSurface = new Intent(this, EstWaterSurface.class);
        toEstTideInfo = new Intent(this, EstTideInfo.class);

        setupButtons();
        setupListeners();
        if(SurveyData.tideEst >=0)
        {
            highlightButton(allButtons[SurveyData.tideEst],SurveyData.tideEst);
        }
    }

    void setupButtons() {
        homeButton = (Button) findViewById(id.home_button);
        rightButton = (ImageView) findViewById(id.rightbutton_image);
        infoButton = (TextView) findViewById(id.estimateTitle);

        highButton = (Button) findViewById(id.high_tide);
        midFallingButton = (Button) findViewById(id.mid_falling_tide);
        lowButton = (Button) findViewById(id.low_tide);
        midFloodingButton = (Button) findViewById(id.middle_flooding_tide);
        nontidalButton = (Button) findViewById(id.non_tidal_tide);

        allButtons = new Button[] {highButton, midFallingButton, lowButton, midFloodingButton, nontidalButton};

    }
    void setupListeners() {

        for(int i = 0; i < 5; i++) {
            int finalI = i;
            allButtons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    highlightButton(allButtons[finalI],finalI);
                }
            });
        }

        setListener(infoButton, toEstTideInfo);
        setListener(rightButton, toEstWaterSurface);
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


    void highlightButton(Button button,int ival)
    {
        highButton.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.hightide)));
        midFallingButton.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.middlefallingtide)));
        lowButton.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.lowtide)));
        midFloodingButton.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.middlefloodingtide)));
        nontidalButton.setBackgroundTintList(ColorStateList.valueOf(Color.WHITE));

        SurveyData.tideEst = ival;
        button.setBackgroundTintList(ContextCompat.getColorStateList(this,R.color.green_main));
    }



}
