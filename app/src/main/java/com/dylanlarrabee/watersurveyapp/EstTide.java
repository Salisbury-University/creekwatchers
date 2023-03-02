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

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class EstTide extends AppCompatActivity {
    Intent toEstHome, toEstWaterSurface;
    int tideNum;
    Button homeButton, highButton, midFallingButton, lowButton, midFloodingButton, nontidalButton;
    Button allButtons[];

    ImageView rightButton;
    SurveyData mysd;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.estimate_tide);
        toEstHome = new Intent(this, EstimatesPage.class);
        toEstWaterSurface = new Intent(this, EstWaterSurface.class);
        mysd = (SurveyData) getIntent().getSerializableExtra("mysd");
        homeButton = (Button) findViewById(id.home_button);
        rightButton = (ImageView) findViewById(id.rightbutton_image);

        highButton = (Button) findViewById(id.high_tide);
        midFallingButton = (Button) findViewById(id.mid_falling_tide);
        lowButton = (Button) findViewById(id.low_tide);
        midFloodingButton = (Button) findViewById(id.middle_flooding_tide);
        nontidalButton = (Button) findViewById(id.non_tidal_tide);

        allButtons = new Button[] {highButton, midFallingButton, lowButton, midFloodingButton, nontidalButton};
        if(mysd.tideEst >= 0)
        {
            highlightButton(allButtons[mysd.tideEst]);
        }
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toEstHome.putExtra("mysd",mysd);
                startActivity(toEstHome);
            }
        });
        rightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toEstWaterSurface.putExtra("mysd",mysd);
                startActivity(toEstWaterSurface);
            }
        });

        setListeners();
    }

    void setListeners()
    {
        for(int i = 0; i < 5; i++)
        {
            int finalI = i;
            allButtons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    highlightButton(allButtons[finalI]);
                    tideNum = finalI + 1;
                    mysd.tideEst = finalI;
                }
            });
        }
    }

    void highlightButton(Button button) {
        highButton.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.hightide)));
        midFallingButton.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.middlefallingtide)));
        lowButton.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.lowtide)));
        midFloodingButton.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.middlefloodingtide)));
        nontidalButton.setBackgroundTintList(ColorStateList.valueOf(Color.WHITE));

        button.setBackgroundTintList(ContextCompat.getColorStateList(this,R.color.green_main));
    }


}
