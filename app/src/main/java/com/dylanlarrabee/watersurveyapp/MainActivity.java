package com.dylanlarrabee.watersurveyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //vars
    int countClick = 0;
    int clicked = 0;
    String userName,siteName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
        Intent toHome = new Intent(this,HomePage.class);
        //Find views
        TextView cwName = (TextView) findViewById(R.id.cwname);
        EditText enterName = (EditText) findViewById(R.id.entername);
        Button donebtn = (Button) findViewById(R.id.btndone);
        TextView user = (TextView) findViewById(R.id.userName);
        Animation fadeIn = AnimationUtils.loadAnimation(this,R.anim.fade_in);
        Animation fadeOut = AnimationUtils.loadAnimation(this,R.anim.fade_out);

        donebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
           if(clicked == 0 && enterName.getText().toString().length() > 3)
           {
               Handler handler = new Handler();

               //Runnables
               final Runnable r0 = new Runnable() {
                   public void run() {
                       donebtn.setText("Begin");
                       enterName.setHint("Enter Site");
                       clicked = 0;
                       enterName.startAnimation(fadeIn);
                   }
               };

               final Runnable r2 = new Runnable() {
                   @Override
                   public void run() {
                       toHome.putExtra("name", userName);
                       toHome.putExtra("site", siteName);
                      startActivity(toHome);
                      finish();
                   }
               };


               clicked = 1;
               switch (countClick)
               {
                   case 0:
                       user.startAnimation(fadeIn);
                       user.setText("Hello, " + enterName.getText().toString());
                       userName = enterName.getText().toString();
                       enterName.setText("");
                       enterName.setHint("");
                       donebtn.setText("Wait...");
                       enterName.startAnimation(fadeOut);
                       handler.postDelayed(r0, 1500);
                       countClick++;
                       break;
                   case 1:
                       siteName = enterName.getText().toString();
                       donebtn.setEnabled(false);
                       enterName.startAnimation(fadeOut);
                       donebtn.startAnimation((fadeOut));
                       handler.postDelayed(r2,1499);
                       break;
               }

              





           }
            }
        });
    }

}