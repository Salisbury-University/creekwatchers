package com.dylanlarrabee.watersurveyapp;

import android.content.Intent;

import java.io.Serializable;

public class altStartActivity {
    Intent altStartAct(Intent intent,SurveyData mySD)
    {
        intent.putExtra("mysd",mySD);
        return intent;
    }

}
