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
    SurveyData mysd;
    Intent toEstHome, toEstWaterSurface, toEstWindSpeed, toEstWeatherInfo;
    int weatherNum;
    ImageView allBackgrounds[];
    Button allButtons[];
    Button homeButton;
    ImageView clearBackground, partlycloudyBackground, overcastBackground, lightrainBackground, rainBackground, heavyrainBackground, fogBackground, snowBackground;
    ImageView rightButton, leftButton;
    TextView infoButton;
    Button clearButton, partlycloudyButton, overcastButton, lightrainButton, rainButton, heavyrainButton, fogButton, snowButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.estimate_weather);
        mysd = (SurveyData) getIntent().getSerializableExtra("mysd");

        toEstHome = new Intent(this, EstimatesPage.class);
        toEstWindSpeed = new Intent(this, EstWindSpeed.class);
        toEstWaterSurface = new Intent(this, EstWaterSurface.class);
        toEstWeatherInfo = new Intent(this, EstWeatherInfo.class);

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

        allBackgrounds = new ImageView[] {clearBackground, partlycloudyBackground, overcastBackground, lightrainBackground, rainBackground, heavyrainBackground, fogBackground, snowBackground};
        allButtons = new Button[] {clearButton, partlycloudyButton, overcastButton, lightrainButton, rainButton, heavyrainButton, fogButton, snowButton };

        if(mysd.weathEst >= 0)
            highlightButton(allBackgrounds[mysd.weathEst]);

    }

    void setupListeners() {
        for(int i = 0; i < 8; i++) {
            int finalI = i;
            allButtons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    highlightButton(allBackgrounds[finalI]);
                    weatherNum = finalI + 1;
                    mysd.weathEst = finalI;
                }
            });
        }
        setListener(infoButton, toEstWeatherInfo);
        setListener(leftButton, toEstWaterSurface);
        setListener(rightButton, toEstWindSpeed);
        setListener(homeButton, toEstHome);

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

    void highlightButton(ImageView background){

        for(int i = 0; i < 8; i++)
            allBackgrounds[i].setImageResource(R.color.white);
        background.setImageResource(R.color.green_main);
    }

}
