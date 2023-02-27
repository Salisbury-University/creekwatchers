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
import android.widget.TextView;
import android.widget.Toast;

public class LoginPage extends AppCompatActivity {

    //vars
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor myEdit;
    int numNames;
    int countClick = 0;
    int clicked = 0;
    String userName,siteName;
    Button selName;
    Intent toHome, toSelName,toSelSite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
        //sharedPref
        sharedPreferences = getSharedPreferences("userNames",MODE_PRIVATE);
        myEdit = sharedPreferences.edit();
        numNames = sharedPreferences.getAll().size();
        //intents
        toHome = new Intent(this,HomePage.class);
        toSelName = new Intent(this,SelectName.class);
        toSelSite = new Intent(this,SelectSite.class);

        //Find views
        EditText enterName = (EditText) findViewById(R.id.entername);
        Button donebtn = (Button) findViewById(R.id.btndone);
        TextView user = (TextView) findViewById(R.id.userName);
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
              myEdit.putString("name" + numNames,newName);
              myEdit.commit();
          }else
          {
              Context context = getApplicationContext();
              CharSequence text = "Max Saved Names.\nDelete or Use a Saved name.";
              int duration = Toast.LENGTH_SHORT;

              Toast toast = Toast.makeText(context, text, duration);
              toast.show();

          }


    }

    private void nameView()
    {
        startActivity(toSelName);
    }

}