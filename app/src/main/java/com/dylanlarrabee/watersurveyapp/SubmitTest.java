package com.dylanlarrabee.watersurveyapp;

import static com.dylanlarrabee.watersurveyapp.R.id;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.*;
import androidx.annotation.NonNull;
import com.google.firebase.auth.FirebaseAuth;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.DocumentReference;
import java.util.HashMap;
import java.util.Map;

public class SubmitTest extends SaveFormAct {


    private TextView statusText;
    private TextView thankyouText;
    private ImageView greencheck;
    private ProgressBar progressBar;
    private Button resubmitButton;
    private String date;
    private String sampDist;
    private Intent toRestart;


    private String tide, weather, watersurf, windspd, winddir, rain;
    private float avgWaterTemp, avgAirTemp, avgSecchi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.submit_page);
        toRestart = new Intent(this, FormArchive.class);

        setupViews();
        attemptSubmit();
    }

    void setupViews() {
        statusText = (TextView) findViewById(id.statusText);
        thankyouText = (TextView) findViewById(id.thankyouText);
        progressBar = (ProgressBar) findViewById(id.progressBar);
        resubmitButton = (Button) findViewById(id.submitButton);
        greencheck = (ImageView) findViewById(id.greencheck);

        resubmitButton.setOnClickListener(v -> attemptSubmit());
    }

    void attemptSubmit() {
        statusText.setText("Submitting...");
        progressBar.setVisibility(View.VISIBLE);
        greencheck.setVisibility(View.INVISIBLE);
        resubmitButton.setVisibility(View.INVISIBLE);
        thankyouText.setVisibility(View.INVISIBLE);

        //Creates a firestore instance
        FirebaseFirestore database = FirebaseFirestore.getInstance();
        FirebaseAuth auth = FirebaseAuth.getInstance();

        // Get date
        Calendar currentTime = Calendar.getInstance();
        int year = currentTime.get(Calendar.YEAR); year %= 100;
        date = (currentTime.get(Calendar.MONTH) + 1) + "/" + currentTime.get(Calendar.DAY_OF_MONTH) + "/" + year;

        setupEst();

        avgWaterTemp = (float) ((SurveyData.waterTemp[0] + SurveyData.waterTemp[1]) / 2);
        avgAirTemp = (float) ((SurveyData.airTemp[0] + SurveyData.airTemp[1]) / 2);
        avgSecchi = (float) ((SurveyData.secchiDepth[0] + SurveyData.secchiDepth[1]) / 2);

        sampDist = SurveyData.sampleDist + " " + SurveyData.sampUnit;

        //Creates a map to store the data
        Map<String, Object> UserData = new HashMap<>();
        UserData.put("userName", SurveyData.userName);
        UserData.put("userSite", SurveyData.userSite);
        UserData.put("userId", SurveyData.myID);
        UserData.put("tideEst", tide);
        UserData.put("waterSurf", watersurf);
        UserData.put("weathEst", weather);
        UserData.put("windSpeed", windspd);
        UserData.put("windDir", winddir);
        UserData.put("rainfall", rain);
        UserData.put("waterDepth", SurveyData.waterDepth[0]);
        UserData.put("sampleDist", sampDist);
        UserData.put("airTemp1", SurveyData.airTemp[0]);
        UserData.put("airTemp2", SurveyData.airTemp[1]);
        UserData.put("waterTemp1", SurveyData.waterTemp[0]);
        UserData.put("waterTemp2", SurveyData.waterTemp[1]);
        UserData.put("secchiDepth1", SurveyData.secchiDepth[0]);
        UserData.put("secchiDepth2", SurveyData.secchiDepth[1]);
        UserData.put("date", date);
        UserData.put("comments", SurveyData.comments);
        UserData.put("bottomedOut", SurveyData.bottomedOut);
        UserData.put("glassbottle", SurveyData.Gbottle);
        UserData.put("pbottle", SurveyData.Pbottle);
        UserData.put("usedTube", SurveyData.tubeUsed);
        UserData.put("waterTempAvg", avgWaterTemp);
        UserData.put("airTempAvg", avgAirTemp);
        UserData.put("secchiAvg", avgSecchi);

        // Add other data to Firestore
        database.collection("UserData")
                .add(UserData)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        // Data was successfully added to Firestore
                        submitSuccess();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Failed to add data to Firestore
                        submitFailure();
                    }
                });
    }

    void submitSuccess() {
        statusText.setText("Form Submitted");
        thankyouText.setText("Thank you for being a Creekwatcher!");
        greencheck.setImageResource(R.drawable.greencheck);
        progressBar.setVisibility(View.INVISIBLE);
        thankyouText.setVisibility(View.VISIBLE);
        greencheck.setVisibility(View.VISIBLE);

        resubmitButton.setText("Home");
        resubmitButton.setOnClickListener(v -> startActivity(toRestart));
        resubmitButton.setVisibility(View.VISIBLE);
    }

    void submitFailure() {
        statusText.setText("Submission Failed");
        thankyouText.setText("Please try again or try submission later");
        greencheck.setImageResource(R.drawable.submitx);
        progressBar.setVisibility(View.INVISIBLE);
        greencheck.setVisibility(View.VISIBLE);
        resubmitButton.setVisibility(View.VISIBLE);
    }

    void setupEst(){
        switch (SurveyData.tideEst){
            case 0:
                tide = "High";
                break;
            case 1:
                tide = "Middle Falling";
                break;
            case 2:
                tide = "Low";
                break;
            case 3:
                tide = "Middle Flooding";
                break;
            case 4:
                tide = "Non-Tridal";
                break;
        }
        switch (SurveyData.waterSurf){
            case 0:
                watersurf = "Heavy Chop";
                break;
            case 1:
                watersurf = "Choppy";
                break;
            case 2:
                watersurf = "Ripples";
                break;
            case 3:
                watersurf = "Calm";
                break;
        }
        switch (SurveyData.weathEst){
            case 0:
                weather = "Clear";
                break;
            case 1:
                weather = "Partly Cloudy";
                break;
            case 2:
                weather = "Cloudy";
                break;
            case 3:
                weather = "Light Rain";
                break;
            case 4:
                weather = "Rain";
                break;
            case 5:
                weather = "Heavy Rain";
                break;
            case 6:
                weather = "Fog";
                break;
            case 7:
                weather = "Snow";
                break;
        }
        switch (SurveyData.windSpeed){
            case 0:
                windspd = "Heavy";
                break;
            case 1:
                windspd = "Medium";
                break;
            case 2:
                windspd = "Light";
                break;
            case 3:
                windspd = "Calm";
                break;
        }
        switch (SurveyData.windDir){
            case 0:
                winddir = "North";
                break;
            case 1:
                winddir = "North East";
                break;
            case 2:
                winddir = "East";
                break;
            case 3:
                winddir = "South East";
                break;
            case 4:
                winddir = "South";
                break;
            case 5:
                winddir = "South West";
                break;
            case 6:
                winddir = "West";
                break;
            case 7:
                winddir = "North West";
                break;
        }
        switch (SurveyData.rainfall){
            case 0:
                rain = "Unusual Storm";
                break;
            case 1:
                rain = "Heavy";
                break;
            case 2:
                rain = "Medium";
                break;
            case 3:
                rain = "Light";
                break;
            case 4:
                rain = "Trace";
                break;
            case 5:
                rain = "None";
                break;
        }
    }
}