package com.dylanlarrabee.watersurveyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;


public class HomePage extends AppCompatActivity {
    final int estInd = 0,measInd = 1,commInd = 2;
    final int totalEst = 6, totalMeas = 5, totalComm = 1, numBtns = 3;
    final String estStr = "Estimates\n", commStr = "Comments\n", measStr = "Measurements\n";
    Button[] btns = new Button[numBtns];
    Button subBtn;
    String userName,siteName;
    int curEst = 0, curMeas = 0, curComm = 0;
    boolean estDone = false, measDone = false, commDone = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        Bundle extras = getIntent().getExtras();
        Button userText = (Button) findViewById(R.id.homeName);
        Button userSite = (Button) findViewById(R.id.homeSite);
        Button estBtn = (Button) findViewById(R.id.estimateHomeBtn);
        Button measBtn = (Button) findViewById(R.id.measurementHomeBtn);
        Button commBtn = (Button) findViewById(R.id.comments);
        subBtn = (Button) findViewById(R.id.submitBtnHome);
        if (extras != null) {
             userName = extras.getString("name");
             siteName = extras.getString("site");
            }
        userText.setText(userName );
        userSite.setText(siteName);

        //main Code
        btns[0] = estBtn;
        btns[1] = measBtn;
        btns[2] = commBtn;
        curEst = 6;
        curMeas = 5;
        curComm = 1;
        setAllBtnTxt();
        setBtnTextColors();
    }
    String setBtnTxt(String btnStr,int cur, int max)
    {
        String newStr = btnStr + cur + "/" + max;
        return newStr;
    }
    void setAllBtnTxt()
    {
        btns[0].setText(setBtnTxt(estStr,curEst,totalEst));
        btns[1].setText(setBtnTxt(measStr,curMeas,totalMeas));
        btns[2].setText(setBtnTxt(commStr,curComm,totalComm));
    }
    void setBtnTextColors()
    {
        if(curEst == 0) {
            btns[0].setTextColor(getResources().getColor(R.color.maroon));
        }else if (curEst <6) {
            btns[0].setTextColor(getResources().getColor(R.color.gold));
        } else {
            btns[0].setTextColor(getResources().getColor(R.color.finishGreen));
            estDone = true;
        }
        if(curMeas == 0) {
            btns[1].setTextColor(getResources().getColor(R.color.maroon));
        }else if (curMeas <5) {
            btns[1].setTextColor(getResources().getColor(R.color.gold));
        } else {
            btns[1].setTextColor(getResources().getColor(R.color.finishGreen));
            measDone = true;
        }
        if(curComm == 0) {
            btns[2].setTextColor(getResources().getColor(R.color.maroon));
        } else {
            btns[2].setTextColor(getResources().getColor(R.color.finishGreen));
            commDone = true;
        }
        if(estDone && measDone && commDone)
        {
            subBtn.setTextColor(getResources().getColor(R.color.finishGreen));
        }
    }
}