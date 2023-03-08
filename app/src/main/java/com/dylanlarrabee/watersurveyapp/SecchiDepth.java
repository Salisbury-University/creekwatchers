package com.dylanlarrabee.watersurveyapp;
import static com.dylanlarrabee.watersurveyapp.R.*;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
public class SecchiDepth extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.secchi_depth);

        TextView secmeas1 = (TextView) findViewById(id.sd_title_meas1);
        TextView secmeas2 = (TextView) findViewById(id.sd_title_meas2);


        TextView secchi_h = (TextView) findViewById(id.secchihome);
        ImageView secchi_n = (ImageView) findViewById(id.secchi_next);
        ImageView secchi_b = (ImageView) findViewById(id.secchi_back);

        BasicCommands.setActivity(this, secchi_h, MeasurementsPage.class);
        BasicCommands.setActivity(this, secchi_b, WaterTemp.class);
        //BasicCommands.setActivity(this, secchi_n Comments.class);

        BasicCommands.setAlertBox(this, secmeas1, 1, SurveyData.secchiDepth);
        BasicCommands.setAlertBox(this, secmeas2, 2, SurveyData.secchiDepth);


    }

}
