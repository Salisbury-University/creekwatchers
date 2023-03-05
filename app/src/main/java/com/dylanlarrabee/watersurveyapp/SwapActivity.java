package com.dylanlarrabee.watersurveyapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

public class SwapActivity {
    public static void navHome(Context cont)
    {
        Intent intent = new Intent(cont,HomePage.class);
        cont.startActivity(intent);
    }
    public static void navForms(Context cont)
    {
        Intent intent = new Intent(cont,FormArchive.class);
        cont.startActivity(intent);
    }
}
