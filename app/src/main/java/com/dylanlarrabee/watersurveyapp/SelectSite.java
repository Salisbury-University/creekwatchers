package com.dylanlarrabee.watersurveyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

public class SelectSite extends SaveFormAct {
    private final int numSites = 26;
    private int highestIDnum = 0;
    private Button site1,site2,site3,site4,site5,site6,site7,site8,site9,site10,
                   site11,site12,site13,site14,site15,site16,site17,site18,site19,site20,
                   site21,site22,site23,site24,site25,site26;
    private Button siteBtns[];
    private Bundle getExtras;
    private String userName;
    private Intent toHome;
    private SharedPreferences formPref,survPref;
    private SharedPreferences.Editor formEdit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_site);
        //intents
        toHome = new Intent(this,HomePage.class);
        //find views
        site1 = findViewById(R.id.siteBtn1);
        site2 =  findViewById(R.id.siteBtn2);
        site3 = findViewById(R.id.siteBtn3);
        site4 =  findViewById(R.id.siteBtn4);
        site5 = findViewById(R.id.siteBtn5);
        site6 = findViewById(R.id.siteBtn6);
        site7 =  findViewById(R.id.siteBtn7);
        site8 = findViewById(R.id.siteBtn8);
        site9 =  findViewById(R.id.siteBtn9);
        site10 = findViewById(R.id.siteBtn10);
        site11 = findViewById(R.id.siteBtn11);
        site12 =  findViewById(R.id.siteBtn12);
        site13 = findViewById(R.id.siteBtn13);
        site14 =  findViewById(R.id.siteBtn14);
        site15 = findViewById(R.id.siteBtn15);
        site16 = findViewById(R.id.siteBtn16);
        site17 =  findViewById(R.id.siteBtn17);
        site18 = findViewById(R.id.siteBtn18);
        site19 =  findViewById(R.id.siteBtn19);
        site20 = findViewById(R.id.siteBtn20);
        site21 = findViewById(R.id.siteBtn21);
        site22 =  findViewById(R.id.siteBtn22);
        site23 = findViewById(R.id.siteBtn23);
        site24 =  findViewById(R.id.siteBtn24);
        site25 = findViewById(R.id.siteBtn25);
        site26 = findViewById(R.id.siteBtn26);
        getExtras = getIntent().getExtras();
        if(getExtras != null)
         userName = getExtras.getString("name");

        siteBtns = new Button[]{site1,site2,site3,site4,site5,site6,site7,site8,site9,site10,
                                site11,site12,site13,site14,site15,site16,site17,site18,site19,site20,
                                site21,site22,site23,site24,site25,site26};
        // Prefs
        formPref = getSharedPreferences("formids",MODE_PRIVATE);
        formEdit = formPref.edit();
        survPref = getSharedPreferences("forms",MODE_PRIVATE);
        int survNum = survPref.getAll().size();
        Map<String, ?> allEntries = survPref.getAll();
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            SurveyData mysd;
            Gson gson = new Gson();
            String json = survPref.getString(key,null);
            mysd = gson.fromJson(json,SurveyData.class);
            if(mysd.myformIDnum > highestIDnum)
            {
                highestIDnum = mysd.myformIDnum;
            }
        }

        //listeners
       for(int i = 0; i < numSites; i++)
       {
          setBtnListeners( siteBtns[i]);
       }
    }
    void setBtnListeners(Button btn)
    {
        String btnStr = btn.getText().toString();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               toHome(btnStr );
            }
        });
    }
    void toHome(String siteName)
    {
        if(SurveyData.newForm == true) {
            // Create a calendar instance
            Calendar calendar = Calendar.getInstance();

            // Get the current date in a desired format
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
            String currentDate = dateFormat.format(calendar.getTime());
            String newID = userName + " | " + siteName + " | " + currentDate;
            formEdit.putString(newID, newID);
            formEdit.commit();
            SurveyData.resetData();
            SurveyData.formIDnum = highestIDnum + 1;
            SurveyData.myID = newID;
        }

        toHome.putExtra("site",siteName);
        toHome.putExtra("name",userName);
    startActivity(toHome);
    finish();
    }
}