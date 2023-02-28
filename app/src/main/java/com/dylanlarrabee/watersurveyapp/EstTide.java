package com.dylanlarrabee.watersurveyapp;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;

import static com.dylanlarrabee.watersurveyapp.R.id;
import static com.dylanlarrabee.watersurveyapp.R.layout;

import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class EstTide extends AppCompatActivity {

    Intent toEstHome;
    int tideNum;
    Button homeButton, highButton, midFallingButton, lowButton, midFloodingButton, nontidalButton;
    Button allButtons[];

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.estimate_tide);

        toEstHome = new Intent(this, EstimatesPage.class);

        homeButton = (Button) findViewById(id.home_button);
        highButton = (Button) findViewById(id.high_tide);
        midFallingButton = (Button) findViewById(id.mid_falling_tide);
        lowButton = (Button) findViewById(id.low_tide);
        midFloodingButton = (Button) findViewById(id.middle_flooding_tide);
        nontidalButton = (Button) findViewById(id.non_tidal_tide);

        allButtons = new Button[] {highButton, midFallingButton, lowButton, midFloodingButton, nontidalButton};

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(toEstHome);
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
                }
            });
        }
    }

    void highlightButton(Button button) {
        highButton.setBackgroundTintList(ColorStateList.valueOf(R.color.hightide));
        midFallingButton.setBackgroundTintList(ColorStateList.valueOf(R.color.middlefallingtide));
        lowButton.setBackgroundTintList(ColorStateList.valueOf(R.color.lowtide));
        midFloodingButton.setBackgroundTintList(ColorStateList.valueOf(R.color.middlefloodingtide));
        nontidalButton.setBackgroundTintList(ColorStateList.valueOf(Color.WHITE));

        button.setBackgroundTintList(ContextCompat.getColorStateList(this,R.color.green_main));
    }


}
