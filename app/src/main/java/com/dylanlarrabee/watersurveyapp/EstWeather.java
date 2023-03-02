package com.dylanlarrabee.watersurveyapp;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;

import static com.dylanlarrabee.watersurveyapp.R.id;
import static com.dylanlarrabee.watersurveyapp.R.layout;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class EstWeather extends AppCompatActivity {
    SurveyData mysd;
    Intent toEstHome, toEstWaterSurface, toEstWindSpeed;
    int weatherNum;
    ImageView allBackgrounds[];
    Button allButtons[];
    ImageView clearBackground, partlycloudyBackground, overcastBackground, lightrainBackground, rainBackground, heavyrainBackground, fogBackground, snowBackground;
    ImageView rightButton, leftButton;
    Button clearButton, partlycloudyButton, overcastButton, lightrainButton, rainButton, heavyrainButton, fogButton, snowButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.estimate_weather);
        mysd = (SurveyData) getIntent().getSerializableExtra("mysd");
        toEstHome = new Intent(this, EstimatesPage.class);
        toEstWindSpeed = new Intent(this, EstimatesPage.class);
        toEstWaterSurface = new Intent(this, EstWaterSurface.class);

        Button homeButton = (Button) findViewById(id.home_button);
        ImageView leftButton = (ImageView) findViewById(id.leftbutton_image);
        ImageView rightButton = (ImageView) findViewById(id.rightbutton_image);

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
                toEstWindSpeed.putExtra("mysd",mysd);
                startActivity(toEstWindSpeed);
            }
        });
        leftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toEstWaterSurface.putExtra("mysd",mysd);
                startActivity(toEstWaterSurface);
            }
        });

        setListeners();
    }


    void setListeners() {
        for(int i = 0; i < 8; i++) {
            int finalI = i;
            allButtons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    highlightButton(allBackgrounds[finalI]);
                    weatherNum = finalI + 1;
                }
            });
        }
    }

    void highlightButton(ImageView background){

        for(int i = 0; i < 8; i++)
            allBackgrounds[i].setImageResource(R.color.white);
        background.setImageResource(R.color.green_main);
    }

}
