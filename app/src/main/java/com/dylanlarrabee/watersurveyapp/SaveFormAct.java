package com.dylanlarrabee.watersurveyapp;

import android.content.SharedPreferences;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

public class SaveFormAct extends AppCompatActivity {

//    @Override
//    protected void onPause() {
//        super.onPause();
//        //saveForm();
//        Log.d("pause","Test - " + SurveyData.userName);
//
//    }

    @Override
    protected void onStop() {
        super.onStop();
        if(SurveyData.myID != "")
        {
            saveForm();
        }
        //Log.d("stop","Test - " + SurveyData.userName);
    }

    public void saveForm()
    {
        SharedPreferences spForms = getSharedPreferences("forms",MODE_PRIVATE);
        Gson gson = new Gson();
        SurveyData mysd = SurveyData.SaveData();
        SharedPreferences.Editor editor = spForms.edit();
        String json = gson.toJson(mysd);
        editor.putString(SurveyData.myID,json);
        editor.commit();
    }
}
