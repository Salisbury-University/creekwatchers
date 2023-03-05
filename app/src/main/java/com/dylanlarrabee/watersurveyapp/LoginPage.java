package com.dylanlarrabee.watersurveyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginPage extends AppCompatActivity {
    //vars
    private SharedPreferences namePref;
    private SharedPreferences.Editor nameEdit;
    private int numNames;
    private String userName,siteName;
    private Button selName;
    private Intent toHome, toSelName,toSelSite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
        //sharedPref
        namePref = getSharedPreferences("userNames",MODE_PRIVATE);
        nameEdit = namePref.edit();

        numNames = namePref.getAll().size();
        //intents
        toHome = new Intent(this,HomePage.class);
        toSelName = new Intent(this,SelectName.class);
        toSelSite = new Intent(this,SelectSite.class);

        //Find views
        EditText enterName = (EditText) findViewById(R.id.entername);
        Button donebtn = (Button) findViewById(R.id.btndone);
        Animation fadeIn = AnimationUtils.loadAnimation(this,R.anim.fade_in);
        Animation fadeOut = AnimationUtils.loadAnimation(this,R.anim.fade_out);
        selName = (Button) findViewById(R.id.selNameLog);
        //listeners
        selName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                nameView();
            }
        });
        donebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
           if(enterName.getText().toString().length() < 20 && enterName.getText().toString().length() > 1)
           {
               saveName(enterName.getText().toString());
            toSelSite.putExtra("name",enterName.getText().toString());
            startActivity(toSelSite);
           }
            }
        });
    }

    private void saveName(String newName) {

          if(numNames < 5)
          {
              nameEdit.putString("name" + numNames,newName);
              nameEdit.commit();
          }else
          {
              Context context = getApplicationContext();
              CharSequence text = "Max Saved Names.\nNew name will not save.";
              int duration = Toast.LENGTH_LONG;

              Toast toast = Toast.makeText(context, text, duration);
              toast.show();

          }


    }

    private void nameView()
    {
        startActivity(toSelName);
    }

}