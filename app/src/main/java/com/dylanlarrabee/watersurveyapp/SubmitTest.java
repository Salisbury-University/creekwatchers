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
    private Intent toRestart;



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

        //Creates a map to store the data
        Map<String, Object> otherData = new HashMap<>();
        Map<String, Object> measurementData = new HashMap<>();
        Map<String, Object> estimateData = new HashMap<>();
        otherData.put("userName", SurveyData.userName);
        otherData.put("userSite", SurveyData.userSite);
        otherData.put("userId", SurveyData.myID);
        estimateData.put("tideEst", SurveyData.tideEst);
        estimateData.put("waterSurf", SurveyData.waterSurf);
        estimateData.put("weathEst", SurveyData.weathEst);
        estimateData.put("windSpeed", SurveyData.windSpeed);
        estimateData.put("windDir", SurveyData.windDir);
        estimateData.put("rainfall", SurveyData.rainfall);
        measurementData.put("waterDepth", SurveyData.waterDepth[0]);
        measurementData.put("sampleDist", SurveyData.sampleDist[0]);
        measurementData.put("airTemp1", SurveyData.airTemp[0]);
        measurementData.put("airTemp2", SurveyData.airTemp[1]);
        measurementData.put("waterTemp1", SurveyData.waterTemp[0]);
        measurementData.put("waterTemp2", SurveyData.waterTemp[1]);
        measurementData.put("secchiDepth1", SurveyData.secchiDepth[0]);
        measurementData.put("secchiDepth2", SurveyData.secchiDepth[1]);
        otherData.put("date", date);
        otherData.put("comments", SurveyData.comments);
        otherData.put("bottomedOut", SurveyData.bottomedOut);

        // Add other data to Firestore
        database.collection("otherData")
                .add(otherData)
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

        // Add estimate data to Firestore
        database.collection("estimates")
                .add(estimateData)
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

        // Add measurement data to Firestore
        database.collection("measurements")
                .add(measurementData)
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
}