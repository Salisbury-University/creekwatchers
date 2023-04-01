package com.dylanlarrabee.watersurveyapp;

import static com.dylanlarrabee.watersurveyapp.R.id;
import static com.dylanlarrabee.watersurveyapp.R.layout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ViewFlipper;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;


public class ReviewPage extends AppCompatActivity {

    private Intent toHome, toSubmit, toTide, toWaterSurface, toWeather, toWindSpeed, toWindDirection, toRainfall, toWaterDepth, toSampleDist, toAirTemp, toWaterTemp, toSecchiDepth;
    private Button homeButton, submitButton, tideButton, watersurfaceButton, weatherButton, windspeedButton, winddirectButton, rainfallButton, waterDepthButton, sampleDistButton, airTempButton, waterTempButton, secchiDepthButton;
    private TextView tideTxt, watersurfaceTxt, weatherTxt, windspeedTxt, winddirectTxt, rainfallTxt, waterDepthTxt, sampleDistTxt, airTempTxt, waterTempTxt, secchiDepthTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.review_page);

        setupButtons();
        setupIntents();
        setupListeners();
    }

    void setupButtons()
    {
        homeButton = (Button) findViewById(id.header);
        submitButton = (Button) findViewById(id.submitbtn);
        tideButton = (Button) findViewById(id.tidebtn);
        watersurfaceButton = (Button) findViewById(id.waterbtn);
        weatherButton = (Button) findViewById(id.weatherbtn);
        windspeedButton = (Button) findViewById(id.windspeedbtn);
        winddirectButton = (Button) findViewById(id.winddirectbtn);
        rainfallButton = (Button) findViewById(id.rainbtn);
        waterDepthButton = (Button) findViewById(id.waterdepthbtn);
        sampleDistButton = (Button) findViewById(id.sampledistbtn);
        airTempButton = (Button) findViewById(id.airtempbtn);
        waterTempButton = (Button) findViewById(id.watertempbtn);
        secchiDepthButton = (Button) findViewById(id.secchidepthbtn);

        tideTxt = (TextView) findViewById(id.tidetxt);
        watersurfaceTxt = (TextView) findViewById(id.watertxt);
        weatherTxt = (TextView) findViewById(id.weathertxt);
        windspeedTxt = (TextView) findViewById(id.windspeedtxt);
        winddirectTxt = (TextView) findViewById(id.winddirecttxt);
        rainfallTxt = (TextView) findViewById(id.raintxt);
        waterDepthTxt = (TextView) findViewById(id.waterdepthtxt);
        sampleDistTxt = (TextView) findViewById(id.sampledisttxt);
        airTempTxt = (TextView) findViewById(id.airtemptxt);
        waterTempTxt = (TextView) findViewById(id.watertemptxt);
        secchiDepthTxt = (TextView) findViewById(id.secchidepthtxt);

        if(SurveyData.tideEst <=0) {
            tideButton.setBackgroundColor(getResources().getColor(R.color.maroon_dim));
            tideTxt.setText("Tap to set");
        }
        if(SurveyData.waterSurf <=0) {
            tideButton.setBackgroundColor(getResources().getColor(R.color.maroon_dim));
            watersurfaceTxt.setText("Tap to set");
        }
        if(SurveyData.weathEst <=0) {
            weatherButton.setBackgroundColor(getResources().getColor(R.color.maroon_dim));
            weatherTxt.setText("Tap to set");
        }
        if(SurveyData.windSpeed <=0) {
            windspeedButton.setBackgroundColor(getResources().getColor(R.color.maroon_dim));
            windspeedTxt.setText("Tap to set");
        }
        if(SurveyData.windDir <=0) {
            winddirectButton.setBackgroundColor(getResources().getColor(R.color.maroon_dim));
            windspeedTxt.setText("Tap to set");
        }
        if(SurveyData.rainfall <=0) {
            rainfallButton.setBackgroundColor(getResources().getColor(R.color.maroon_dim));
            rainfallTxt.setText("Tap to set");
        }
        if(SurveyData.waterDepth <=0) {
            waterDepthButton.setBackgroundColor(getResources().getColor(R.color.maroon_dim));
            waterDepthTxt.setText("Tap to set");
        }
        if(SurveyData.sampleDist <=0) {
            sampleDistButton.setBackgroundColor(getResources().getColor(R.color.maroon_dim));
            sampleDistTxt.setText("Tap to set");
        }
        if(SurveyData.airTemp[0] <=0 || SurveyData.airTemp[1] <=0 ) {
            airTempButton.setBackgroundColor(getResources().getColor(R.color.maroon_dim));
            airTempTxt.setText("Tap to set");
        }
        if(SurveyData.waterTemp[0] <=0 || SurveyData.waterTemp[1] <=0 ) {
            waterTempButton.setBackgroundColor(getResources().getColor(R.color.maroon_dim));
            waterTempTxt.setText("Tap to set");
        }
        if(SurveyData.secchiDepth[0] <=0 || SurveyData.secchiDepth[1] <=0 ) {
            secchiDepthButton.setBackgroundColor(getResources().getColor(R.color.maroon_dim));
            secchiDepthTxt.setText("Tap to set");
        }
    }

    void setupIntents()
    {
        toHome = new Intent(this,HomePage.class);
        toSubmit = new Intent(this,HomePage.class); //Needs update later
        toWeather = new Intent(this, EstWeather.class);
        toTide = new Intent(this, EstTide.class);
        toWaterSurface = new Intent(this, EstWaterSurface.class);
        toWindSpeed = new Intent(this, EstWindSpeed.class);
        toWindDirection = new Intent(this, EstWindDirection.class);
        toRainfall = new Intent(this, EstRainfall.class);
        toSampleDist = new Intent(this, SamplePage.class);
        toAirTemp = new Intent(this, AirTemp.class);
    }

    void setupListeners()
    {
        setListener(homeButton, toHome);
        setListener(submitButton, toSubmit); //Needs update later
        setListener(tideButton, toTide);
        setListener(watersurfaceButton, toWaterSurface);
        setListener(weatherButton, toWeather);
        setListener(windspeedButton, toWindSpeed);
        setListener(winddirectButton, toWindDirection);
        setListener(rainfallButton, toRainfall);
        setListener(airTempButton, toAirTemp);
        setListener(sampleDistButton, toSampleDist);
        setListener(waterDepthButton, toWaterDepth);
        setListener(airTempButton, toAirTemp);
        setListener(waterTempButton, toWaterTemp);
        setListener(secchiDepthButton, toSecchiDepth);
    }

    void setListener(Button button, Intent intent)
    {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });
    }


}