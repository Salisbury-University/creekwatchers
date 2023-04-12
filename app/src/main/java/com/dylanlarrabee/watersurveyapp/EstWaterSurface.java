package com.dylanlarrabee.watersurveyapp;

import android.content.Intent;
import android.os.Bundle;

import static com.dylanlarrabee.watersurveyapp.R.id;
import static com.dylanlarrabee.watersurveyapp.R.layout;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class EstWaterSurface extends SaveFormAct {
    private Intent toEstHome, toEstWeather, toEstTide, toEstWaterSurfaceInfo, toReview;
    private ImageView allBackgrounds[], allImages[];
    private Button allButtons[];
    private TextView allText[];
    private ImageView calmBackground, ripplesBackground, choppyBackground, heavychopBackground;
    private ImageView calmImage, ripplesImage, choppyImage, heavychopImage;
    private ImageView rightButton, leftButton;
    private TextView infoButton;
    private Button homeButton, calmButton, ripplesButton, choppyButton, heavychopButton;
    private TextView calmText,ripplesText,choppyText,heavychopText;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.estimate_watersurface);
        toEstHome = new Intent(this, EstimatesPage.class);
        toEstTide = new Intent(this, EstTide.class);
        toEstWeather = new Intent(this, EstWeather.class);
        toEstWaterSurfaceInfo = new Intent(this, EstWaterSurfaceInfo.class);
        toReview = new Intent(this, ReviewPage.class);

        setupButtons();
        setupListeners();

        if(SurveyData.waterSurf >= 0)
        {
            highlightButton(allBackgrounds[SurveyData.waterSurf],SurveyData.waterSurf);
        }
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
        ripplesButton = (Button) findViewById(id.ripples_button);
        choppyButton = (Button) findViewById(id.choppy_button);
        heavychopButton = (Button) findViewById(id.heavychop_button);

        calmBackground = (ImageView) findViewById(id.calm_background);
        ripplesBackground = (ImageView) findViewById(id.ripples_background);
        choppyBackground = (ImageView) findViewById(id.choppy_background);
        heavychopBackground = (ImageView) findViewById(id.heavychop_background);

        calmImage = (ImageView) findViewById(id.calm_image);
        ripplesImage = (ImageView) findViewById(id.ripples_image);
        choppyImage = (ImageView) findViewById(id.choppy_image);
        heavychopImage = (ImageView) findViewById(id.heavychop_image);

        calmText = (TextView) findViewById(id.calm_text);
        ripplesText = (TextView) findViewById(id.ripples_text);
        choppyText = (TextView) findViewById(id.choppy_text);
        heavychopText = (TextView) findViewById(id.heavychop_text);

        allBackgrounds = new ImageView[] {calmBackground, ripplesBackground, choppyBackground, heavychopBackground};
        allButtons = new Button[] {calmButton, ripplesButton, choppyButton, heavychopButton};
        allImages = new ImageView[] {calmImage,ripplesImage,choppyImage,heavychopImage};
        allText = new TextView[] {calmText,ripplesText,choppyText,heavychopText};
    }

    void setupListeners() {

        for(int i = 0; i < 4; i++) {
            int finalI = i;
            allButtons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    highlightButton(allBackgrounds[finalI],finalI);
                    SurveyData.waterSurf = finalI;
                }
            });
        }

        setListener(infoButton, toEstWaterSurfaceInfo);
        setListener(leftButton, toEstTide);
        setListener(rightButton, toEstWeather);
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
        }
        allText[iVal].setTextColor(getResources().getColor(R.color.white));
        allText[iVal].setShadowLayer(4,0,0,getResources().getColor(R.color.black));
        allImages[iVal].setAlpha(0.875F);

        background.setImageResource(R.color.white);
    }

}
