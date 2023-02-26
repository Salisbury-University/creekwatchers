package com.dylanlarrabee.watersurveyapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class HomePage extends AppCompatActivity {
    private final int estInd = 0,measInd = 1,commInd = 2;
    private final int totalEst = 6, totalMeas = 5, totalComm = 1, numBtns = 3;
    private final String observerText = "Observer:\n", siteText = "Site:\n", estStr = "Estimates\n", commStr = "Comments\n", measStr = "Measurements\n";
    private String m_Text = "";
    private Button[] btns = new Button[numBtns];
    private Button subBtn;
    private String userName,siteName;
    private int curEst = 0, curMeas = 0, curComm = 0;
    private boolean estDone = false, measDone = false, commDone = false;
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
        Intent estIntent = new Intent(this,MeasurementsPage.class);
        Intent measIntent = new Intent(this,MeasurementsPage.class);

        subBtn = (Button) findViewById(R.id.submitBtnHome);
        if (extras != null) {
             userName = observerText +  extras.getString("name");
             siteName = siteText + extras.getString("site");
            }
        userText.setText(userName );
        userSite.setText(siteName);

        //OCL
        userText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeBtnText(userText, "Name");
            }
        });
        userSite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeBtnText(userSite, "Site");
            }
        });
        estBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            startActivity(estIntent);
            }
        });
        measBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(measIntent);
            }
        });
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
    void changeBtnText(Button btn,String type)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter new " + type);

// Set up the input
        final EditText input = new EditText(this);
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_NORMAL);
        builder.setView(input);

// Set up the buttons
        builder.setPositiveButton("Enter", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                m_Text = input.getText().toString();
                if(type == "name")
                {
                    btn.setText(observerText +  m_Text);
                }else
                {
                    btn.setText(siteText +  m_Text);
                }

            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();

    }

}