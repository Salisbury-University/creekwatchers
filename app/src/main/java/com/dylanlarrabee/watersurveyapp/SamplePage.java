package com.dylanlarrabee.watersurveyapp;
import static com.dylanlarrabee.watersurveyapp.R.*;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SamplePage extends SaveFormAct{
    String unit = " cm";
    String title = "Enter in Sample Distance ";
    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(layout.sample_dist);

        TextView sampmeas = (TextView) findViewById(id.samp_meas);

        TextView samp_home = (TextView) findViewById(id.sampdist_home);
        ImageView samp_n = (ImageView) findViewById(id.samp_next);
        ImageView samp_b = (ImageView) findViewById(id.samp_back);

        if(Config.isReviewing) {
            samp_home.setText("BACK");
            BasicCommands.setActivity(this, samp_home, ReviewPage.class);
        }
        else
            BasicCommands.setActivity(this, samp_home, MeasurementsPage.class);
        BasicCommands.setActivity(this, samp_b, WaterDepth.class);
        BasicCommands.setActivity(this, samp_n, AirTemp.class);
        BasicCommands.setAlertBox(this, sampmeas, 0, SurveyData.sampleDist, unit,title, 20);


        if(SurveyData.sampleDist[0] > 0)
        {
            sampmeas.setText(""+SurveyData.sampleDist[0] + unit);
        }

    }
}
