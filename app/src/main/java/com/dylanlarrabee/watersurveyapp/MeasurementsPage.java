package com.dylanlarrabee.watersurveyapp;
import static com.dylanlarrabee.watersurveyapp.R.*;
import static com.dylanlarrabee.watersurveyapp.R.id.waterdep_home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MeasurementsPage extends AppCompatActivity {
    Button toDepth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.measurements_main);

        toDepth = (Button) findViewById(id.waterdepth);

        //Creates text views for measurement home page button
        TextView meashome_btn = (TextView) findViewById(id.meashome);

        //Allows the user to go back to main page when clicked the Home button
        setActivity(meashome_btn, HomePage.class);
        setActivity(toDepth, WaterDepth.class);


    }

    //Function to easily go back to main page
    void setActivity(TextView tv, Class page) {
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Takes back to main activity when clicked
                Intent topage = new Intent(MeasurementsPage.this, page);

                startActivity(topage);
            }
        });
    }

    //Function to easily go back to main page
    void setActivity(Button btn, Class page) {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Takes back to main activity when clicked
                Intent topage = new Intent(MeasurementsPage.this, page);

                startActivity(topage);
            }
        });
    }

    void setActivity(ImageView view, Class page)
    {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Takes back to main activity when clicked
                Intent topage=new Intent(MeasurementsPage.this, page);

                startActivity(topage);
            }
        });
    }
}