package com.dylanlarrabee.watersurveyapp;

import static com.dylanlarrabee.watersurveyapp.R.id;
import static com.dylanlarrabee.watersurveyapp.R.id.waterdep_home;
import static com.dylanlarrabee.watersurveyapp.R.layout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class EstimatesPage extends AppCompatActivity {

    Intent toHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(layout.estimates_main);

        toHome = new Intent(this,HomePage.class);

        TextView homeButton = (TextView) findViewById(id.esthome);

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(toHome);
            }
        });


    }

    //Function to easily go back to main page
    void setActivity(TextView home)
    {
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Takes back to main activity when clicked
                Intent homepage=new Intent(EstimatesPage.this, HomePage.class);

                startActivity(homepage);
            }
        });
    }

}