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

public class EstWindDirection  extends SaveFormAct {

    private Intent toEstHome, toEstWindSpeed, toEstWindDirectInfo, toEstRainfall, toReview;
    private ImageView allButtons[];
    private TextView allText[];
    private Button homeButton;
    private ImageView rightButton, leftButton;
    private TextView infoButton,
    northTxt,eastTxt,southTxt,westTxt,northeastTxt,northwestTxt,southeastTxt,southwestTxt;
    private ImageView northButton, northeastButton, eastButton, southeastButton, southButton, southwestButton, westButton, northwestButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.estimate_winddirection);

        toEstHome = new Intent(this, EstimatesPage.class);
        toEstWindSpeed = new Intent(this, EstWindSpeed.class);
        toEstWindDirectInfo = new Intent(this, EstWindDirectionInfo.class);
        toEstRainfall = new Intent(this, EstRainfall.class);
        toReview = new Intent(this, ReviewPage.class);
        setupButtons();
        setupListeners();

        if(Config.isReviewing){
            homeButton.setText("BACK");
        }

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

        northTxt = (TextView) findViewById(R.id.northTxt);
        northeastTxt = (TextView) findViewById(R.id.northEastTxt);
        eastTxt = (TextView) findViewById(R.id.eastTxt);
        southeastTxt = (TextView) findViewById(R.id.southEastTxt);
        southTxt = (TextView) findViewById(R.id.southTxt);
        southwestTxt = (TextView) findViewById(R.id.southWestTxt);
        westTxt = (TextView) findViewById(R.id.westTxt);
        northwestTxt = (TextView) findViewById(R.id.northWestTxt);

       allButtons = new ImageView[] {northButton, northeastButton, eastButton, southeastButton, southButton, southwestButton, westButton, northwestButton};
        allText = new TextView[] {northTxt,northeastTxt,eastTxt,southeastTxt,southTxt,southwestTxt,westTxt,northwestTxt};
        if(SurveyData.windDir >= 0)
            highlightButton(allButtons[SurveyData.windDir],allText[SurveyData.windDir]);

    }

    void setupListeners() {

        for(int i = 0; i < 8; i++) {
            int finalI = i;
            allButtons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    highlightButton(allButtons[finalI],allText[finalI]);
                    SurveyData.windDir = finalI;
                }
            });
        }
        setListener(infoButton, toEstWindDirectInfo);
        setListener(leftButton, toEstWindSpeed);
        setListener(rightButton, toEstRainfall);
        if(ReviewPage.isReviewing)
            setListener(homeButton, toReview);
        else
            setListener(homeButton, toEstHome);

        if(ReviewPage.isReviewing){
            homeButton.setText("BACK");
        }

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

    void highlightButton(ImageView background,TextView tv){

        for(int i = 0; i < 8; i++)
        {
            allButtons[i].setImageTintList(ColorStateList.valueOf(getResources().getColor(R.color.whiteDim)));
            allText[i].setTextColor(getResources().getColor(R.color.black));
        }
        background.setImageTintList(ColorStateList.valueOf(getResources().getColor(R.color.maroon)));
        tv.setTextColor(getResources().getColor(R.color.gold));
    }




}
