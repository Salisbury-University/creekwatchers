package com.dylanlarrabee.watersurveyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SelectSite extends AppCompatActivity {
    SurveyData mysd;
    final int numSites = 1;
Button site1;
Bundle getExtras;
String userName;
Intent toHome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_site);
        if (getIntent().getSerializableExtra("mysd") != null)
            mysd = (SurveyData) getIntent().getSerializableExtra("mysd");
        //intents
        toHome = new Intent(this,HomePage.class);
        //find views
        site1 = findViewById(R.id.name1);
        getExtras = getIntent().getExtras();
        userName = getExtras.getString("name");
        //listeners
        site1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            toHome(site1.getText().toString());
            }
        });
    }
    void toHome(String siteName)
    {
        toHome.putExtra("site",siteName);
        toHome.putExtra("name",userName);
        if(mysd != null)
        {
        toHome.putExtra("mysd",mysd);
        }
    startActivity(toHome);
    finish();
    }
}