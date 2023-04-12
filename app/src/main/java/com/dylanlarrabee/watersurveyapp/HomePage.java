package com.dylanlarrabee.watersurveyapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;


public class HomePage extends SaveFormAct {
    Gson gson = new Gson();
    private BasicCommands bc = new BasicCommands();
    private final int estInd = 0,measInd = 1,commInd = 2;
    private final int totalEst = 6, totalMeas = 5, totalComm = 1, numBtns = 3;
    private final String observerText = "Observer:\n", siteText = "Site:\n", estStr = "Estimates\n", commStr = "Comments\n", measStr = "Measurements\n";
    private String m_Text = "";
    private Button[] btns = new Button[numBtns];
    private Button subBtn,commBtn,formBtn;
    private String userName,siteName;
    private int curEst = 0, curMeas = 0, curComm = 0;
    private boolean estDone = false, measDone = false, commDone = false;
    private SharedPreferences curInfo,spForms;
    private SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        //Pull survey data from submenus if available


        //Find  views and Bundles
        Bundle extras = getIntent().getExtras();
        commBtn = findViewById(R.id.comments);
        formBtn = findViewById(R.id.formBtn);
        Button userName = (Button) findViewById(R.id.homeName);
        Button userSite = (Button) findViewById(R.id.homeSite);
        Button estBtn = (Button) findViewById(R.id.estimateHomeBtn);
        Button measBtn = (Button) findViewById(R.id.measurementHomeBtn);
        Button commBtn = (Button) findViewById(R.id.comments);
        Intent estIntent = new Intent(this,EstimatesPage.class);
        Intent measIntent = new Intent(this,MeasurementsPage.class);
        Intent toSelSite = bc.setIntent(this,SelectSite.class);
        Intent toCommPage = new Intent(this,CommentsPage.class);
        Intent toRevPage = new Intent(this,ReviewPage.class);
        Intent toFormsPage = new Intent(this,FormArchive.class);
        subBtn = (Button) findViewById(R.id.submitBtnHome);
        spForms = getSharedPreferences("forms",MODE_PRIVATE);
        //This pulls the name and site from login page or from SurveyData after initial login




        //OCL
        formBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(toFormsPage);
            }
        });
        commBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(toCommPage);
            }
        });
        userName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeBtnText(userName, "Name");
            }
        });
        userSite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startActivity(toSelSite);
               finish();
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
        subBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SurveyData mysd = SurveyData.SaveData();
                editor = spForms.edit();
                String json = gson.toJson(mysd);
                editor.putString(SurveyData.myID,json);
                editor.commit();

                startActivity(toRevPage);

            }
        });


        //main Code
        btns[0] = estBtn;
        btns[1] = measBtn;
        btns[2] = commBtn;
      if(SurveyData.firstEntry == true && SurveyData.newForm == false)
      {

          retrieveData();

          SurveyData.firstEntry = false;
          SurveyData.newForm = false;
      }else
      {
          SurveyData.firstEntry = false;
          SurveyData.newForm = false;
      }



        if(extras != null)
        {
            if(extras.getString("name") != null)
            {
                SurveyData.userName = extras.getString("name");
            }
            if(extras.getString("site") != null)
            {
                SurveyData.userSite = extras.getString("site");
            }
        }
        if(SurveyData.userName.length() > 0)
        {
            userName.setText(observerText + SurveyData.userName);
        }
        if(SurveyData.userSite.length() > 0)
        {
            userSite.setText(siteText + SurveyData.userSite);
        }
        checkCompletion();
    }

    String setBtnTxt(String btnStr,int cur, int max)
    {
        String newStr = btnStr + cur + "/" + max;
        return newStr;
    }
    void setAllBtnTxt()
    {
        btns[0].setText(setBtnTxt(estStr,SurveyData.curEst,SurveyData.maxEst));
        btns[1].setText(setBtnTxt(measStr,SurveyData.curMeas,SurveyData.maxMeas));
        btns[2].setText(setBtnTxt(commStr,SurveyData.curComm,SurveyData.maxComm));
    }
    void setBtnTextColors()
    {
        if(SurveyData.curEst == 0) {
            btns[0].setTextColor(getResources().getColor(R.color.maroon));
        }else if (SurveyData.curEst <6) {
            btns[0].setTextColor(getResources().getColor(R.color.gold));
        } else {
            btns[0].setTextColor(getResources().getColor(R.color.finishGreen));
            estDone = true;
        }
        if(SurveyData.curMeas == 0) {
            btns[1].setTextColor(getResources().getColor(R.color.maroon));
        }else if (SurveyData.curMeas <5) {
            btns[1].setTextColor(getResources().getColor(R.color.gold));
        } else {
            btns[1].setTextColor(getResources().getColor(R.color.finishGreen));
            measDone = true;
        }
        if(SurveyData.curComm == 0) {
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
                if(type == "Name")
                {
                    btn.setText(observerText +  m_Text);
                    SurveyData.userName = m_Text;
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
    void checkCompletion()
    {
        SurveyData.updateCompleted();
        setAllBtnTxt();
        setBtnTextColors();
    }

    void retrieveData()
    {
        SurveyData mysd;
        String json = spForms.getString(SurveyData.myID,null);
        mysd = gson.fromJson(json,SurveyData.class);
        SurveyData.RetrieveData(mysd);
    }

}