package com.dylanlarrabee.watersurveyapp;

import static com.dylanlarrabee.watersurveyapp.R.id;
import static com.dylanlarrabee.watersurveyapp.R.layout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.text.DecimalFormat;

//white text when button is red
//submit text yellow/white
// back button when editing pages
//

public class ReviewPage extends SaveFormAct {

    private Intent toHome, toSubmit, toTide, toWaterSurface, toWeather, toWindSpeed, toWindDirection, toRainfall, toWaterDepth, toSampleDist, toAirTemp, toWaterTemp, toSecchiDepth;
    private Button homeButton, submitButton, tideButton, watersurfaceButton, weatherButton, windspeedButton, winddirectButton, rainfallButton, waterDepthButton, sampleDistButton, airTempButton, waterTempButton, secchiDepthButton;
    private TextView tideTxt, watersurfaceTxt, weatherTxt, windspeedTxt, winddirectTxt, rainfallTxt, waterDepthTxt, sampleDistTxt, airTempTxt, waterTempTxt, secchiDepthTxt;
    private ImageView tideBG, watersurfaceBG, weatherBG, windspeedBG, winddirectBG, rainfallBG, waterDepthBG, sampleDistBG, airTempBG, waterTempBG, secchiDepthBG;

    private static DecimalFormat REAL_FORMATTER = new DecimalFormat("0.##");
    private Boolean canSubmit;
    public static boolean isReviewing = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.review_page);

        Config.isReviewing = true;
        isReviewing = true;
        canSubmit = true;
        setupButtons();
        setupIntents();
        setupListeners();
    }

    void setupButtons() {
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
        rainfallTxt = (TextView) findViewById(id.rainfalltxt);
        waterDepthTxt = (TextView) findViewById(id.waterdepthtxt);
        sampleDistTxt = (TextView) findViewById(id.sampledisttxt);
        airTempTxt = (TextView) findViewById(id.airtemptxt);
        waterTempTxt = (TextView) findViewById(id.watertemptxt);
        secchiDepthTxt = (TextView) findViewById(id.secchidepthtxt);

        tideBG = (ImageView) findViewById(id.tideBG);
        watersurfaceBG = (ImageView) findViewById(id.waterBG);
        weatherBG = (ImageView) findViewById(id.weatherBG);
        windspeedBG = (ImageView) findViewById(id.windspeedBG);
        winddirectBG = (ImageView) findViewById(id.winddirectBG);
        rainfallBG = (ImageView) findViewById(id.rainfallBG);
        waterDepthBG = (ImageView) findViewById(id.waterdepthBG);
        sampleDistBG = (ImageView) findViewById(id.sampledistBG);
        airTempBG = (ImageView) findViewById(id.airtempBG);
        waterTempBG = (ImageView) findViewById(id.watertempBG);
        secchiDepthBG = (ImageView) findViewById(id.secchidepthBG);

        // Set text for each row, disable submitting if missing any data.
        String textBox = "";
        switch (SurveyData.tideEst){
            case -1:
                tideBG.setImageResource(R.color.maroon);
                textBox = "Tap to set";
                canSubmit = false;
                break;
            case 0:
                textBox = "High";
                break;
            case 1:
                textBox = "Mid Falling";
                break;
            case 2:
                textBox = "Low";
                break;
            case 3:
                textBox = "Mid Flooding";
                break;
            case 4:
                textBox = "Nontidal";
                break;
        }tideTxt.setText(textBox);

        switch (SurveyData.waterSurf){
            case -1:
                watersurfaceBG.setImageResource(R.color.maroon);
                textBox = "Tap to set";
                canSubmit = false;
                break;
            case 0:
                textBox = "Calm";
                break;
            case 1:
                textBox = "Ripples";
                break;
            case 2:
                textBox = "Choppy";
                break;
            case 3:
                textBox = "Heavy Chop";
                break;
        } watersurfaceTxt.setText(textBox);

        switch (SurveyData.weathEst){
            case -1:
                weatherBG.setImageResource(R.color.maroon);
                textBox = "Tap to set";
                canSubmit = false;
                break;
            case 0:
                textBox = "Clear";
                break;
            case 1:
                textBox = "Cloudy";
                break;
            case 2:
                textBox = "Overcast";
                break;
            case 3:
                textBox = "Light Rain";
                break;
            case 4:
                textBox = "Rainy";
                break;
            case 5:
                textBox = "Heavy Rain";
                break;
            case 6:
                textBox = "Foggy";
                break;
            case 7:
                textBox = "Snow";
                break;
        } weatherTxt.setText(textBox);

        switch (SurveyData.windSpeed){
            case -1:
                windspeedBG.setImageResource(R.color.maroon);
                textBox = "Tap to set";
                canSubmit = false;
                break;
            case 0:
                textBox = "Calm";
                break;
            case 1:
                textBox = "Light";
                break;
            case 2:
                textBox = "Medium";
                break;
            case 3:
                textBox = "Heavy";
                break;
        } windspeedTxt.setText(textBox);

        switch (SurveyData.windDir){
            case -1:
                winddirectBG.setImageResource(R.color.maroon);
                textBox = "Tap to set";
                canSubmit = false;
                break;
            case 0:
                textBox = "N";
                break;
            case 1:
                textBox = "NE";
                break;
            case 2:
                textBox = "E";
                break;
            case 3:
                textBox = "SE";
                break;
            case 4:
                textBox = "S";
                break;
            case 5:
                textBox = "SW";
                break;
            case 6:
                textBox = "W";
                break;
            case 7:
                textBox = "NW";
                break;
        } winddirectTxt.setText(textBox);

        switch (SurveyData.rainfall){
            case -1:
                rainfallBG.setImageResource(R.color.maroon);
                textBox = "Tap to set";
                canSubmit = false;
                break;
            case 0:
                textBox = "Storm";
                break;
            case 1:
                textBox = "Heavy";
                break;
            case 2:
                textBox = "Moderate";
                break;
            case 3:
                textBox = "Light";
                break;
            case 4:
                textBox = "Trace";
                break;
            case 5:
                textBox = "None";
                break;
        } rainfallTxt.setText(textBox);

        if(SurveyData.waterDepth[0] <=0) {
            waterDepthBG.setImageResource(R.color.maroon);
            waterDepthTxt.setText("Tap to set");
            canSubmit = false;
        } else { waterDepthTxt.setText(REAL_FORMATTER.format(SurveyData.waterDepth[0]) + " cm"); }

        if(SurveyData.sampleDist[0] <=0) {
            sampleDistBG.setImageResource(R.color.maroon);
            sampleDistTxt.setText("Tap to set");
            canSubmit = false;
        } else { sampleDistTxt.setText(REAL_FORMATTER.format(SurveyData.sampleDist[0])+ " m"); }

        if(SurveyData.airTemp[0] <=0 || SurveyData.airTemp[1] <=0 ) {
            airTempBG.setImageResource(R.color.maroon);
            airTempTxt.setText("Tap to set");
            canSubmit = false;
        }else { airTempTxt.setText(REAL_FORMATTER.format(SurveyData.airTemp[0]) + " °C, " + REAL_FORMATTER.format(SurveyData.airTemp[1])+" °C"); }

        if(SurveyData.waterTemp[0] <=0 || SurveyData.waterTemp[1] <=0 ) {
            waterTempBG.setImageResource(R.color.maroon);
            waterTempTxt.setText("Tap to set");
            canSubmit = false;
        }else { waterTempTxt.setText(REAL_FORMATTER.format(SurveyData.waterTemp[0]) + " °C, " + REAL_FORMATTER.format(SurveyData.waterTemp[1])+" °C" ); }

        if(SurveyData.secchiDepth[0] <=0 || SurveyData.secchiDepth[1] <=0 ) {
            secchiDepthBG.setImageResource(R.color.maroon);
            secchiDepthTxt.setText("Tap to set");
            canSubmit = false;
        }else { secchiDepthTxt.setText(REAL_FORMATTER.format(SurveyData.secchiDepth[0]) + " cm, " + REAL_FORMATTER.format(SurveyData.secchiDepth[1])+" cm" ); }

        // Disable submit button if unfinished
        if(canSubmit == false) {
            submitButton.setBackgroundColor(Color.GRAY);
            submitButton.setTextColor(getResources().getColor(R.color.gold));
            submitButton.setEnabled(false);
        }
    }

    void setupIntents() {
        toHome = new Intent(this,HomePage.class);
        toSubmit = new Intent(this,SubmitPage.class);
        toWeather = new Intent(this, EstWeather.class);
        toTide = new Intent(this, EstTide.class);
        toWaterSurface = new Intent(this, EstWaterSurface.class);
        toWindSpeed = new Intent(this, EstWindSpeed.class);
        toWindDirection = new Intent(this, EstWindDirection.class);
        toRainfall = new Intent(this, EstRainfall.class);
        toWaterDepth = new Intent(this, WaterDepth.class);
        toSampleDist = new Intent(this, SamplePage.class);
        toAirTemp = new Intent(this, AirTemp.class);
        toWaterTemp = new Intent(this, WaterTemp.class);
        toSecchiDepth = new Intent(this, SecchiDepth.class);
    }

    void setupListeners() {
        setListener(homeButton, toHome);
        setListener(submitButton, toSubmit);
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

    void setListener(Button button, Intent intent) {
        int id = button.getId();
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(id == R.id.header) isReviewing = false;
                startActivity(intent);
            }
        });
    }


}