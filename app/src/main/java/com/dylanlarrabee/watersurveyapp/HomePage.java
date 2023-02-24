package com.dylanlarrabee.watersurveyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class HomePage extends AppCompatActivity {
    String userName,siteName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        Bundle extras = getIntent().getExtras();
        Button userText = (Button) findViewById(R.id.homeName);
        Button userSite = (Button) findViewById(R.id.homeSite);
        if (extras != null) {
             userName = extras.getString("name");
             siteName = extras.getString("site");
            }
        userText.setText(userName );
        userSite.setText(siteName);
    }
}