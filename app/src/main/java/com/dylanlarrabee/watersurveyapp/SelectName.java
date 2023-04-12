package com.dylanlarrabee.watersurveyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class SelectName extends SaveFormAct {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private BasicCommands bc = new BasicCommands();
    private Intent toLogin;

    private LayoutInflater inflater;
    private final int maxNames = 5;
    private int nameNum;
    private String[] userStr;
    private Button[] userBtns, delBtns;
    private Button returnBtn;
    private int[] nameBtnIds = {R.id.name1,R.id.name2,R.id.name3,R.id.name4,R.id.name5};
    private int[] delBtnIds ={R.id.delName1,R.id.delName2,R.id.delName3,R.id.delName4,R.id.delName5};
    private Intent siteSel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_name);

        //sharedpref
        sharedPreferences = getSharedPreferences("userNames",MODE_PRIVATE);
        editor = sharedPreferences.edit();
        nameNum = sharedPreferences.getAll().size();
        userStr = new String[nameNum];
        userBtns = new Button[maxNames];
        delBtns = new Button[maxNames];
        //findviews
       returnBtn =(Button) findViewById(R.id.returnLog);
        //Other
        toLogin = bc.setIntent(this,LoginPage.class);
        //listeners
        returnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(toLogin);
                finish();
            }
        });
        //setup button Text
        for(int i = 0; i < maxNames; i++)
        {
           if(i < nameNum)
           {
               final int iVal = i;
               userStr[i] = sharedPreferences.getString("name" + i,"No Name");
               userBtns[i] = findViewById(nameBtnIds[i]);
               userBtns[i].setText(userStr[i]);
               delBtns[i] = findViewById(delBtnIds[i]);
               userBtns[i].setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View view) {
                       toSiteSel(userStr[iVal] );
                   }
               });
               delBtns[i].setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View view) {
                       delBtnFunc(iVal);
                   }
               });
           }
           else
           {
               userBtns[i] = findViewById(nameBtnIds[i]);
               delBtns[i] = findViewById(delBtnIds[i]);
               userBtns[i].setAlpha(0);
               delBtns[i].setAlpha(0);
           }
        }
    }

    private void toSiteSel(String nameVal) {
        siteSel = new Intent(this,SelectSite.class);
        siteSel.putExtra("name",nameVal);
        startActivity(siteSel);
        finish();
    }

    private void delBtnFunc(int iVal) {
        userBtns[iVal].setText("Empty");
        editor.remove("name" + iVal);
        editor.commit();
        delBtns[iVal].setOnClickListener(null);
        userBtns[iVal].setOnClickListener(null);
    }
}