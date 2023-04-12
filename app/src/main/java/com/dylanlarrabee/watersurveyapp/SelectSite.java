package com.dylanlarrabee.watersurveyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SelectSite extends SaveFormAct {
    private final int numSites = 5;
    private Button site1,site2,site3,site4,site5;
    private Button siteBtns[] = new Button[] {site1,site2,site3,site4,site5};
    private Bundle getExtras;
    private String userName;
    private Intent toHome;
    private SharedPreferences formPref;
    private SharedPreferences.Editor formEdit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_site);
        //intents
        toHome = new Intent(this,HomePage.class);
        //find views
        site1 = findViewById(R.id.sitebtn1);
        site2 =  findViewById(R.id.sitebtn2);
        site3 = findViewById(R.id.sitebtn3);
        site4 =  findViewById(R.id.sitebtn4);
        site5 = findViewById(R.id.sitebtn5);
        getExtras = getIntent().getExtras();
        if(getExtras != null)
         userName = getExtras.getString("name");
        siteBtns[0] = site1;
        siteBtns[1] = site2;
        siteBtns[2] = site3;
        siteBtns[3] = site4;
        siteBtns[4] = site5;
        // Prefs
        formPref = getSharedPreferences("formids",MODE_PRIVATE);
        formEdit = formPref.edit();
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
            String newID = "form" + formPref.getAll().size();
            formEdit.putString(newID, newID);
            formEdit.commit();
            SurveyData.resetData();
            SurveyData.myID = newID;
        }

        toHome.putExtra("site",siteName);
        toHome.putExtra("name",userName);
    startActivity(toHome);
    finish();
    }
}