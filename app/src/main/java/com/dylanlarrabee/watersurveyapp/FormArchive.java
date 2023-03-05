package com.dylanlarrabee.watersurveyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.gson.Gson;

public class FormArchive extends AppCompatActivity {
    private Button newFormBtn;
    private LinearLayout formLay;
    SharedPreferences mPrefs;
    SharedPreferences.Editor myEdit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_archive);

        //find views
        formLay = findViewById(R.id.formSelLay);
        newFormBtn = findViewById(R.id.formSelAddBtn);
        SurveyData.newForm = false;


        //listeners
        newFormBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SurveyData.newForm = true;
                toLogin();
            }
        });


        mPrefs = getSharedPreferences("formids",MODE_PRIVATE);
        myEdit = mPrefs.edit();

        formLay.removeAllViews();
        for(int i = 0; i < mPrefs.getAll().size(); i++)
        {
            String formID = "form" + i;
            addForm(formID);
        }
        formLay.addView(newFormBtn);



    }
    private void addForm(String formID)
    {
        View view = getLayoutInflater().inflate(R.layout.form_card,null);
        TextView text = view.findViewById(R.id.formName);
        Button btn = view.findViewById(R.id.cardDelBtn);
        text.setText(formID);
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SurveyData.myID = formID;
                toHome();
            }
        });
        formLay.addView(view);
    }
    private void toLogin()
    {
        SurveyData.newForm = true;
        Intent intent = new Intent(this,LoginPage.class);
        startActivity(intent);
    }
    private void toHome()
    {
        SurveyData.newForm = false;
        SurveyData.firstEntry=true;
        Intent intent = new Intent(this,HomePage.class);
        startActivity(intent);
    }
}