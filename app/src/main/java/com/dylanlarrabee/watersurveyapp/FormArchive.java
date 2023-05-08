package com.dylanlarrabee.watersurveyapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

public class FormArchive extends SaveFormAct {
    private Button newFormBtn;
    private LinearLayout formLay;
    private int prefSize;
    SharedPreferences mPrefs, survPref;
    SharedPreferences.Editor myEdit;
    List<Integer> formIDints = new ArrayList<>();
    List<View> myListView= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_archive);

        survPref = getSharedPreferences("forms",MODE_PRIVATE);
        //find views
        formLay = findViewById(R.id.formSelLay);
        newFormBtn = findViewById(R.id.formSelAddBtn);



        //listeners
        newFormBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toLogin();
            }
        });


        mPrefs = getSharedPreferences("formids",MODE_PRIVATE);
        myEdit = mPrefs.edit();
        prefSize = mPrefs.getAll().size();
        formLay.removeAllViews();
        formLay.addView(newFormBtn);
        Map<String, ?> allEntries = mPrefs.getAll();
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            SurveyData mysd;
            Gson gson = new Gson();
            String json = survPref.getString(key,null);
            mysd = gson.fromJson(json,SurveyData.class);
            formIDints.add(mysd.myformIDnum);
            addForm(mPrefs.getString(key,null));
        }

//        while(!formIDints.isEmpty())
//        {
//            int smallestIndex = formIDints.get(0);
//            for (int i = 1; i < formIDints.size(); i++) {
//                if (formIDints.get(i) < formIDints.get(smallestIndex)) {
//                    smallestIndex = formIDints.get(i);
//                }
//            }
//            formLay.addView(myListView.get(smallestIndex));
//            formIDints.remove(smallestIndex);
//        }

    }
    private void addForm(String formID)
    {
        View formView = getLayoutInflater().inflate(R.layout.form_card,null);
        TextView text = formView.findViewById(R.id.formName);
        TextView btn = formView.findViewById(R.id.cardDelBtn);
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SurveyData.myID = formID;
                toHome();
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(FormArchive.this);
                builder.setTitle("Confirmation");
                builder.setMessage("Are you sure you want to delete this form?");

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Delete the key-value pair
                        SharedPreferences.Editor editor =mPrefs.edit();
                        formLay.removeView(formView);
                        editor.remove(formID);
                        editor.apply();
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing - user canceled the deletion
                    }
                });

                builder.show();
            }
        });
        //myListView.add(formView);
        formLay.addView(formView);
    }
    private void toLogin()
    {
        SurveyData.resetData();
        SurveyData.firstEntry = true;
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