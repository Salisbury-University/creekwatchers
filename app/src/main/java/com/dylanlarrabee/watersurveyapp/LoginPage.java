package com.dylanlarrabee.watersurveyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginPage extends AppCompatActivity {

    //vars
    int countClick = 0;
    int clicked = 0;
    String userName,siteName;
    Button selName;
    Intent toHome, toSelName,toSelSite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
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
            toSelSite.putExtra("name",enterName.getText().toString());
            startActivity(toSelSite);


           }




            }
        });
    }
    void nameView()
    {
        startActivity(toSelName);
    }

}