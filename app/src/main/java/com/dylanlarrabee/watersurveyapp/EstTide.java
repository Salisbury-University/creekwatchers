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

public class EstTide extends SaveFormAct {

    private Intent toEstHome, toEstWaterSurface, toEstTideInfo, toReview;
    private Button homeButton, highButton, midFallingButton, lowButton, midFloodingButton, nontidalButton,
            highButtonbg, midFallingButtonbg, lowButtonbg, midFloodingButtonbg, nontidalButtonbg;
    private Button allButtons[],allBtnBgs[];
    private ImageView rightButton;
    private TextView infoButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.estimate_tide);


        toEstHome = new Intent(this, EstimatesPage.class);
        toEstWaterSurface = new Intent(this, EstWaterSurface.class);
        toEstTideInfo = new Intent(this, EstTideInfo.class);

        toReview = new Intent(this, ReviewPage.class);

        setupButtons();
        setupListeners();
        if(SurveyData.tideEst >=0)
        {
            highlightButton(SurveyData.tideEst);
        }
        if(ReviewPage.isReviewing){
            homeButton.setText("BACK");
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

        highButtonbg = (Button) findViewById(id.high_tide2);
        midFallingButtonbg = (Button) findViewById(id.mid_falling_tide2);
        lowButtonbg = (Button) findViewById(id.low_tide2);
        midFloodingButtonbg = (Button) findViewById(id.middle_flooding_tide2);
        nontidalButtonbg = (Button) findViewById(id.non_tidal_tide2);

        allButtons = new Button[] {highButton, midFallingButton, lowButton, midFloodingButton, nontidalButton};
        allBtnBgs = new Button[] {highButtonbg, midFallingButtonbg, lowButtonbg, midFloodingButtonbg, nontidalButtonbg};
    }
    void setupListeners() {

        for(int i = 0; i < 5; i++) {
            int finalI = i;
            allBtnBgs[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    highlightButton(finalI);
                }
            });
        }

        setListener(infoButton, toEstTideInfo);
        setListener(rightButton, toEstWaterSurface);

        if(ReviewPage.isReviewing)
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
        allButtons[ival].setTextColor(getResources().getColor(R.color.white));

        SurveyData.tideEst = ival;
    }



}
