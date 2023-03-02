package com.dylanlarrabee.watersurveyapp;

import android.content.Intent;
import android.os.Bundle;

import static com.dylanlarrabee.watersurveyapp.R.id;
import static com.dylanlarrabee.watersurveyapp.R.layout;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class EstWaterSurface extends AppCompatActivity {
    SurveyData mysd;
    Intent toEstHome, toEstWeather, toEstTide;
    int watersurfaceNum;
    ImageView allBackgrounds[];
    Button allButtons[];
    ImageView calmBackground, ripplesBackground, choppyBackground, heavychopBackground;
    ImageView rightButton, leftButton;
    Button homeButton, calmButton, ripplesButton, choppyButton, heavychopButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.estimate_watersurface);
        mysd = (SurveyData) getIntent().getSerializableExtra("mysd");
        toEstHome = new Intent(this, EstimatesPage.class);
        toEstTide = new Intent(this, EstTide.class);
        toEstWeather = new Intent(this, EstWeather.class);

        homeButton = (Button) findViewById(id.home_button);
        rightButton = (ImageView) findViewById(id.rightbutton_image);
        leftButton = (ImageView) findViewById(id.leftbutton_image);

        calmButton = (Button) findViewById(id.calm_button);
        ripplesButton = (Button) findViewById(id.ripples_button);
        choppyButton = (Button) findViewById(id.choppy_button);
        heavychopButton = (Button) findViewById(id.heavychop_button);

        calmBackground = (ImageView) findViewById(id.calm_background);
        ripplesBackground = (ImageView) findViewById(id.ripples_background);
        choppyBackground = (ImageView) findViewById(id.choppy_background);
        heavychopBackground = (ImageView) findViewById(id.heavychop_background);

        allBackgrounds = new ImageView[] {calmBackground, ripplesBackground, choppyBackground, heavychopBackground};
        allButtons = new Button[] {calmButton, ripplesButton, choppyButton, heavychopButton};
        if(mysd.warterSurf >= 0)
        {
            highlightButton(allBackgrounds[mysd.warterSurf]);
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
                toEstWeather.putExtra("mysd",mysd);
                startActivity(toEstWeather);
            }
        });

        leftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toEstTide.putExtra("mysd",mysd);
                startActivity(toEstTide);
            }
        });

        setListeners();
    }

    void setListeners() {
        for(int i = 0; i < 4; i++) {
            int finalI = i;
            allButtons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    highlightButton(allBackgrounds[finalI]);
                    watersurfaceNum = finalI + 1;
                    mysd.warterSurf = finalI;
                }
            });
        }
    }

    void highlightButton(ImageView background){

        for(int i = 0; i < 4; i++)
            allBackgrounds[i].setImageResource(R.color.white);
        background.setImageResource(R.color.green_main);
    }

}
