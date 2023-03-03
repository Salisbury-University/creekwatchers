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

import pl.droidsonroids.gif.GifImageView;

public class EstWindSpeed extends AppCompatActivity {
    Intent toEstHome, toEstWeather, toEstWindDirection, toEstWindSpeedInfo;
    int watersurfaceNum;
    ImageView allBackgrounds[], allImages[];
    Button allButtons[];
    TextView allText[];
    ImageView calmBackground, ripplesBackground, choppyBackground, heavychopBackground;
    ImageView calmImage, ripplesImage, choppyImage, heavychopImage;
    ImageView rightButton, leftButton;
    TextView infoButton;
    Button homeButton, calmButton, lightButton, mediumButton, heavyButton;
    TextView calmText,ripplesText,choppyText,heavychopText;

    GifImageView heavyGif, mediumGif, lightGif;
    GifImageView allGifs[];


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.estimate_windspeed);


        toEstHome = new Intent(this, EstimatesPage.class);
        toEstWindDirection = new Intent(this, EstWindDirection.class);
        toEstWeather = new Intent(this, EstWeather.class);
        toEstWindSpeedInfo = new Intent(this, EstWindSpeedInfo.class);

        setupButtons();
        setupListeners();

    }

    void setupButtons(){

        homeButton = (Button) findViewById(id.home_button);
        rightButton = (ImageView) findViewById(id.rightbutton_image);
        leftButton = (ImageView) findViewById(id.leftbutton_image);
        infoButton = (TextView) findViewById(id.estimateTitle);

        calmButton = (Button) findViewById(id.calm_button);
        lightButton = (Button) findViewById(id.ripples_button);
        mediumButton = (Button) findViewById(id.choppy_button);
        heavyButton = (Button) findViewById(id.heavychop_button);

        calmBackground = (ImageView) findViewById(id.calm_background);
        ripplesBackground = (ImageView) findViewById(id.ripples_background);
        choppyBackground = (ImageView) findViewById(id.choppy_background);
        heavychopBackground = (ImageView) findViewById(id.heavychop_background);

        calmImage = (ImageView) findViewById(id.calm_image);
        ripplesImage = (ImageView) findViewById(id.ripples_image);
        choppyImage = (ImageView) findViewById(id.choppy_image);
        heavychopImage = (ImageView) findViewById(id.heavychop_image);

        lightGif = (GifImageView) findViewById(id.light_gif);
        mediumGif = (GifImageView) findViewById(id.medium_gif);
        heavyGif = (GifImageView) findViewById(id.heavy_gif);

        calmText = (TextView) findViewById(id.calm_text);
        ripplesText = (TextView) findViewById(id.ripples_text);
        choppyText = (TextView) findViewById(id.choppy_text);
        heavychopText = (TextView) findViewById(id.heavychop_text);

        allBackgrounds = new ImageView[] {calmBackground, ripplesBackground, choppyBackground, heavychopBackground};
        allButtons = new Button[] {calmButton, lightButton, mediumButton, heavyButton};
        allImages = new ImageView[] {calmImage,ripplesImage,choppyImage,heavychopImage};
        allText = new TextView[] {calmText,ripplesText,choppyText,heavychopText};
        allGifs = new GifImageView[] {lightGif, mediumGif, heavyGif};

        if(SurveyData.windSpeed >= 0)
        {
            highlightButton(allBackgrounds[SurveyData.windSpeed],SurveyData.windSpeed);
        }

    }

    void setupListeners() {

        for(int i = 0; i < 4; i++) {
            int finalI = i;
            allButtons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    highlightButton(allBackgrounds[finalI],finalI);
                    watersurfaceNum = finalI + 1;
                    SurveyData.windSpeed = finalI;
                }
            });
        }

        setListener(infoButton, toEstWindSpeedInfo);
        setListener(leftButton, toEstWeather);
        setListener(rightButton, toEstWindDirection);
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

    void highlightButton(ImageView background, int iVal) {

        for(int i = 0; i < 4; i++)
        {
            allBackgrounds[i].setImageResource(R.color.black);
            allImages[i].setAlpha(0.6F);
            allText[i].setTextColor(getResources().getColor(R.color.black));
            allText[i].setShadowLayer(0,0,0,getResources().getColor(R.color.black));

                //Freeze GIFs and make invisible
            if(i != 0) {
                allGifs[i - 1].setFreezesAnimation(true);
                allGifs[i - 1].setAlpha(0.0F);
            }
        }
        allText[iVal].setTextColor(getResources().getColor(R.color.white));
        allText[iVal].setShadowLayer(4,0,0,getResources().getColor(R.color.black));
        allImages[iVal].setAlpha(0.0F);

        //Unfreeze GIFS and make visible
        if(iVal != 0) {
            allGifs[iVal-1].setAlpha(0.875F);
            allGifs[iVal-1].setFreezesAnimation(false);
        }
        else
            allImages[iVal].setAlpha(0.875F);

        background.setImageResource(R.color.white);
    }

}
