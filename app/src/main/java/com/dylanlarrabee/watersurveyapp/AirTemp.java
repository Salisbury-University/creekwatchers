package com.dylanlarrabee.watersurveyapp;
import static com.dylanlarrabee.watersurveyapp.R.*;


import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class AirTemp extends SaveFormAct {
    String unit = " °C";
    String title = "Enter in Air Temperature ";
    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(layout.air_temp);

        TextView ameas1 = (TextView) findViewById(R.id.at_title_meas1);
        TextView ameas2 = (TextView) findViewById(R.id.a_title_meas2);

        TextView air_h = (TextView) findViewById(R.id.airhome);
        ImageView air_n = (ImageView) findViewById(R.id.air_next);
        ImageView air_b =  (ImageView) findViewById(R.id.air_back);


        if(ReviewPage.isReviewing) {
            air_h.setText("BACK");
            BasicCommands.setActivity(this, air_h, ReviewPage.class);
        }
        else
            BasicCommands.setActivity(this, air_h, MeasurementsPage.class);

        BasicCommands.setActivity(this, air_b, SamplePage.class);
        BasicCommands.setActivity(this, air_n, WaterTemp.class);

        BasicCommands.setAlertBox(this, ameas1, 0, SurveyData.airTemp,unit,title, 40);
        BasicCommands.setAlertBox(this, ameas2, 1, SurveyData.airTemp,unit,title, 40);

        if(SurveyData.airTemp[0] > 0)
        {
         ameas1.setText(""+SurveyData.airTemp[0] + unit);
        }
        if(SurveyData.airTemp[1] > 0)
        {
            ameas2.setText(""+SurveyData.airTemp[1]+ unit);
        }

    }
}
