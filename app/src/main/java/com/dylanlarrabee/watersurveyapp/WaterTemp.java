package com.dylanlarrabee.watersurveyapp;
import static com.dylanlarrabee.watersurveyapp.R.*;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class WaterTemp extends AppCompatActivity {
    String unit = " °C";
    String title = "Enter in Water Temperature ";
    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(layout.water_temp);

        TextView wtmeas1 = (TextView) findViewById(R.id.wt_title_meas1);
        TextView wtmeas2 = (TextView) findViewById(R.id.wt_title_meas2);

        TextView waterTemp_h = (TextView) findViewById(R.id.waterTemp_home);
        ImageView waterTemp_n = (ImageView) findViewById(R.id.waterTemp_next);
        ImageView waterTemp_b = (ImageView) findViewById(id.waterTemp_back);

        BasicCommands.setActivity(this, waterTemp_h, MeasurementsPage.class);
        BasicCommands.setActivity(this, waterTemp_b, AirTemp.class);
        BasicCommands.setActivity(this, waterTemp_n, SecchiDepth.class);

        BasicCommands.setAlertBox(this, wtmeas1, 0, SurveyData.waterTemp,unit, title);
        BasicCommands.setAlertBox(this, wtmeas2, 1, SurveyData.waterTemp,unit, title);
        if(SurveyData.waterTemp[0] > 0)
        {
            wtmeas1.setText(""+SurveyData.waterTemp[0] + unit);
        }
        if(SurveyData.waterTemp[1] > 0)
        {
            wtmeas2.setText(""+SurveyData.waterTemp[1]+ unit);
        }
    }
}
