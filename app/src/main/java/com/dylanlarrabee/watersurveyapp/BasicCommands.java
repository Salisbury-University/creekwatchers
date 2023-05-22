package com.dylanlarrabee.watersurveyapp;

import static android.content.Context.MODE_PRIVATE;
import static androidx.core.content.ContextCompat.startActivity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.google.gson.Gson;

public class BasicCommands {
    //Function to save the current form


    Intent setIntent(Context from, Class to)
    {
        Intent intent = new Intent(from,to);

        return intent;

    }

    //Function to easily go to a different activity
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

    public static void setAlertBox(Activity act, TextView view, int measNum, double []val,String unit, String title, double max) {
        AlertDialog.Builder builder = new AlertDialog.Builder(act);
        builder.setTitle(title + (measNum+1) + " in range of 0" + unit + " - " + max + unit);
        if(max < 0)
        {
            builder.setTitle(title + (measNum+1) + " (Unlimited Range)");
            max = 1000000;
        }
        final double finMax = max;
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final EditText input = new EditText(act);
                    input.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
                    builder.setView(input);
                    builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if(input.getText().toString().equals(".") || input.getText().toString().equals("")) {

                                Toast msg = Toast.makeText(act.getApplicationContext(), "Improper input, try again", Toast.LENGTH_LONG);
                                msg.show();
                            }else {

                                Double inpDoub = Double.parseDouble(input.getText().toString());
                                if (inpDoub >= 0 && inpDoub <= finMax) {
                                    view.setText(inpDoub + " " + unit);
                                    val[measNum] = inpDoub;
                                } else {
                                    Toast msg = Toast.makeText(act.getApplicationContext(), "Please enter a number in the proper range", Toast.LENGTH_LONG);
                                    msg.show();
                                }
                            }
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                builder.show();
            }
        });
    }
}
