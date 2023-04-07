package com.dylanlarrabee.watersurveyapp;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;

import static com.dylanlarrabee.watersurveyapp.R.id;
import static com.dylanlarrabee.watersurveyapp.R.layout;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class EstWeather extends AppCompatActivity {
    private Intent toEstHome, toEstWaterSurface, toEstWindSpeed, toEstWeatherInfo, toReview;
    private int weatherNum;
    private ImageView allBackgrounds[];
    private Button allButtons[];
    private  TextView allTv[];
    private Button homeButton;
    private ImageView clearBackground, partlycloudyBackground, overcastBackground, lightrainBackground, rainBackground, heavyrainBackground, fogBackground, snowBackground;
    private ImageView rightButton, leftButton;
    private TextView infoButton, clearTxt, partlycloudyTxt,overTxt,lightrainTxt,rainTxt,heavyrainTxt,fogTxt,snowTxt;
    private Button clearButton, partlycloudyButton, overcastButton, lightrainButton, rainButton, heavyrainButton, fogButton, snowButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.estimate_weather);

        toEstHome = new Intent(this, EstimatesPage.class);
        toEstWindSpeed = new Intent(this, EstWindSpeed.class);
        toEstWaterSurface = new Intent(this, EstWaterSurface.class);
        toEstWeatherInfo = new Intent(this, EstWeatherInfo.class);
        toReview= new Intent(this, ReviewPage.class);

        setupButtons();
        setupListeners();
    }

    void setupButtons(){

        homeButton = (Button) findViewById(id.home_button);
        leftButton = (ImageView) findViewById(id.leftbutton_image);
        rightButton = (ImageView) findViewById(id.rightbutton_image);
        infoButton = (TextView) findViewById(id.estimateTitle);

        clearButton = (Button) findViewById(id.clear_button);
        partlycloudyButton = (Button) findViewById(id.partlycloudy_button);
        overcastButton = (Button) findViewById(id.overcast_button);
        lightrainButton = (Button) findViewById(id.lightrain_button);
        rainButton = (Button) findViewById(id.rain_button);
        heavyrainButton = (Button) findViewById(id.heavyrain_button);
        fogButton = (Button) findViewById(id.fog_button);
        snowButton = (Button) findViewById(id.snow_button);

        clearBackground = (ImageView) findViewById(id.clear_background);
        partlycloudyBackground = (ImageView) findViewById(id.partlycloudy_background);
        overcastBackground = (ImageView) findViewById(id.overcast_background);
        lightrainBackground = (ImageView) findViewById(id.lightrain_background);
        rainBackground = (ImageView) findViewById(id.rain_background);
        heavyrainBackground = (ImageView) findViewById(id.heavyrain_background);
        fogBackground = (ImageView) findViewById(id.fog_background);
        snowBackground = (ImageView) findViewById(id.snow_background);

        clearTxt = (TextView) findViewById(id.clear_text);
        partlycloudyTxt = (TextView) findViewById(id.partlycloudy_text);
        overTxt = (TextView) findViewById(id.overcast_text);
        lightrainTxt = (TextView) findViewById(id.lightrain_text);
        rainTxt = (TextView) findViewById(id.rain_text);
        heavyrainTxt = (TextView) findViewById(id.heavyrain_text);
        fogTxt= (TextView) findViewById(id.fog_text);
        snowTxt = (TextView) findViewById(id.snow_text);



        allBackgrounds = new ImageView[] {clearBackground, partlycloudyBackground, overcastBackground, lightrainBackground, rainBackground, heavyrainBackground, fogBackground, snowBackground};
        allButtons = new Button[] {clearButton, partlycloudyButton, overcastButton, lightrainButton, rainButton, heavyrainButton, fogButton, snowButton };
        allTv = new TextView[] {clearTxt, partlycloudyTxt, overTxt, lightrainTxt, rainTxt, heavyrainTxt, fogTxt, snowTxt};
        if(SurveyData.weathEst >= 0)
            highlightButton(allBackgrounds[SurveyData.weathEst],allTv[SurveyData.weathEst]);

    }

    void setupListeners() {
        for(int i = 0; i < 8; i++) {
            int finalI = i;
            allButtons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    highlightButton(allBackgrounds[finalI],allTv[finalI]);
                    weatherNum = finalI + 1;
                    SurveyData.weathEst = finalI;
                }
            });
        }
        setListener(infoButton, toEstWeatherInfo);
        setListener(leftButton, toEstWaterSurface);
        setListener(rightButton, toEstWindSpeed);
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

    void highlightButton(ImageView background,TextView tv){

        for(int i = 0; i < 8; i++)
        {
            allBackgrounds[i].setImageResource(R.color.whiteDim);
            allTv[i].setTextColor(getResources().getColor(R.color.black));
            allTv[i].setBackgroundColor(getResources().getColor(R.color.transparent));
        }
        background.setImageResource(R.color.white);
        tv.setBackgroundColor(getResources().getColor(R.color.maroon));
        tv.setTextColor(getResources().getColor(R.color.gold));
    }

}
