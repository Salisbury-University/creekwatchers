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

    String unit = " cm";
    String title = "Enter in Secchi Depth ";

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.secchi_depth);

        TextView secmeas1 = (TextView) findViewById(id.sd_title_meas1);
        TextView secmeas2 = (TextView) findViewById(id.sd_title_meas2);


        TextView secchi_h = (TextView) findViewById(id.secchihome);
        ImageView secchi_n = (ImageView) findViewById(id.secchi_next);
        ImageView secchi_b = (ImageView) findViewById(id.secchi_back);

        if(Config.isReviewing) {
            secchi_h.setText("BACK");
            BasicCommands.setActivity(this, secchi_h, ReviewPage.class);
        }
        else
            BasicCommands.setActivity(this, secchi_h, MeasurementsPage.class);

        BasicCommands.setActivity(this, secchi_b, WaterTemp.class);
        BasicCommands.setActivity(this, secchi_n, CommentsPage.class);

        BasicCommands.setAlertBox(this, secmeas1, 0, SurveyData.secchiDepth, unit, title, 20);
        BasicCommands.setAlertBox(this, secmeas2, 1, SurveyData.secchiDepth, unit, title, 20);
        if(SurveyData.secchiDepth[0] > 0)
        {
            secmeas1.setText(""+SurveyData.secchiDepth[0] + unit);
        }
        if(SurveyData.secchiDepth[1] > 0)
        {
            secmeas2.setText(""+SurveyData.secchiDepth[1]+ unit);
        }

    }

}
