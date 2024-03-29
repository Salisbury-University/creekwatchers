package com.dylanlarrabee.watersurveyapp;

import static com.dylanlarrabee.watersurveyapp.R.id;
import static com.dylanlarrabee.watersurveyapp.R.layout;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import pl.droidsonroids.gif.GifImageView;

public class EstWindSpeed extends SaveFormAct {
    private Intent toEstHome, toEstWeather, toEstWindDirection, toEstWindSpeedInfo, toReview;
    private ImageView allBackgrounds[], allImages[];
    private Button allButtons[];
    private TextView allText[];
    private TextView allTextNum[];
    private TextView allTextMph[];
    private ImageView calmBackground, ripplesBackground, choppyBackground, heavychopBackground;
    private ImageView calmImage, ripplesImage, choppyImage, heavychopImage;
    private ImageView rightButton, leftButton;
    private TextView infoButton;
    private Button homeButton, calmButton, lightButton, mediumButton, heavyButton;
    private TextView calmText,ripplesText,choppyText,heavychopText;
    private TextView calmNum, calmMph, lightNum, lightMph, medNum, medMph, heavyNum, heavyMph;

    private GifImageView heavyGif, mediumGif, lightGif;
    private GifImageView allGifs[];


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.estimate_windspeed);


        toEstHome = new Intent(this, EstimatesPage.class);
        toEstWindDirection = new Intent(this, EstWindDirection.class);
        toEstWeather = new Intent(this, EstWeather.class);
        toEstWindSpeedInfo = new Intent(this, EstWindSpeedInfo.class);
        toReview = new Intent(this, ReviewPage.class);
        setupButtons();
        setupListeners();

        if(ReviewPage.isReviewing){
            homeButton.setText("BACK");
        }

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

        calmNum = (TextView) findViewById(id.calmnum);
        calmMph = (TextView) findViewById(id.calmmph);
        lightNum = (TextView) findViewById(id.lightnum);
        lightMph = (TextView) findViewById(id.lightmph);
        medNum = (TextView) findViewById(id.mednum);
        medMph = (TextView) findViewById(id.medmph);
        heavyNum = (TextView) findViewById(id.heavynum);
        heavyMph = (TextView) findViewById(id.heavymph);

        allBackgrounds = new ImageView[] {calmBackground, ripplesBackground, choppyBackground, heavychopBackground};
        allButtons = new Button[] {calmButton, lightButton, mediumButton, heavyButton};
        allImages = new ImageView[] {calmImage,ripplesImage,choppyImage,heavychopImage};
        allTextNum = new TextView[] {calmNum, lightNum, medNum, heavyNum};
        allTextMph = new TextView[] {calmMph, lightMph, medMph, heavyMph};
        allText = new TextView[] {calmText,ripplesText,choppyText,heavychopText};
        allGifs = new GifImageView[] {lightGif, mediumGif, heavyGif};

        if(SurveyData.windSpeed >= 0) {
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
                    SurveyData.windSpeed = finalI;
                }
            });
        }

        setListener(infoButton, toEstWindSpeedInfo);
        setListener(leftButton, toEstWeather);
        setListener(rightButton, toEstWindDirection);
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

    void highlightButton(ImageView background, int iVal) {

        for(int i = 0; i < 4; i++)
        {
            allBackgrounds[i].setImageResource(R.color.black);
            allImages[i].setAlpha(0.6F);
            allText[i].setTextColor(getResources().getColor(R.color.black));
            allText[i].setShadowLayer(0,0,0,getResources().getColor(R.color.black));
            allTextNum[i].setTextColor(getResources().getColor(R.color.black));
            allTextNum[i].setShadowLayer(0,0,0,getResources().getColor(R.color.black));
            allTextMph[i].setTextColor(getResources().getColor(R.color.black));
            allTextMph[i].setShadowLayer(0,0,0,getResources().getColor(R.color.black));

                //Freeze GIFs and make invisible
            if(i != 0) {
                allGifs[i - 1].setFreezesAnimation(true);
                allGifs[i - 1].setAlpha(0.0F);
            }
        }
        allText[iVal].setTextColor(getResources().getColor(R.color.white));
        allText[iVal].setShadowLayer(4,0,0,getResources().getColor(R.color.black));
        allTextNum[iVal].setTextColor(getResources().getColor(R.color.white));
        allTextNum[iVal].setShadowLayer(4,0,0,getResources().getColor(R.color.black));
        allTextMph[iVal].setTextColor(getResources().getColor(R.color.white));
        allTextMph[iVal].setShadowLayer(4,0,0,getResources().getColor(R.color.black));
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
