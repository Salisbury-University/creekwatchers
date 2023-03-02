package com.dylanlarrabee.watersurveyapp;

import static androidx.core.content.ContextCompat.startActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class BasicCommands {

    Intent setIntent(Context from, Class to)
    {
        Intent intent = new Intent(from,to);

        return intent;

    }

    //Function to easily go back to main page
    public static void setActivity(Activity act, TextView tv, Class page)
    {
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Takes back to main activity when clicked
                Intent topage=new Intent(act, page);

                act.startActivity(topage);
            }
        });
    }

    //Function to easily go back to main page
    public static void setActivity(Activity act, Button btn, Class page)
    {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Takes back to main activity when clicked
                Intent topage=new Intent(act, page);

                act.startActivity(topage);
            }
        });
    }

    public static void setActivity(Activity act, ImageView view, Class page)
    {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Takes back to main activity when clicked
                Intent topage=new Intent(act, page);

                act.startActivity(topage);
            }
        });
    }


}
