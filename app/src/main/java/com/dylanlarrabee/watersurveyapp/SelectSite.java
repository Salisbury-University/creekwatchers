package com.dylanlarrabee.watersurveyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SelectSite extends AppCompatActivity {
    final int numSites = 5;
Button site1,site2,site3,site4,site5;
Button siteBtns[] = new Button[] {site1,site2,site3,site4,site5};
Bundle getExtras;
String userName;
Intent toHome;
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
        toHome.putExtra("site",siteName);
        toHome.putExtra("name",userName);
    startActivity(toHome);
    finish();
    }
}