package com.dylanlarrabee.watersurveyapp;
import static com.dylanlarrabee.watersurveyapp.R.*;


import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
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

        EditText airTemp1 = findViewById(id.airTempEdit1);
        EditText airTemp2 = findViewById(id.airTempEdit2);

        // Set input constraints for the EditText
        airTemp1.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        airTemp1.setFilters(new InputFilter[]{
                new InputFilterMinMax(0.0, 40.0) // Custom InputFilter to limit the value
        });

        // Set input constraints for the EditText
        airTemp2.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        airTemp2.setFilters(new InputFilter[]{
                new InputFilterMinMax(0.0, 40.0) // Custom InputFilter to limit the value
        });

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

        if(SurveyData.waterTemp[0] >= 0)
        {
            airTemp1.setText(String.valueOf(SurveyData.airTemp[0]));
        }else{
            airTemp1.setHint("0.0");
        }

        airTemp1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().isEmpty()) {
                    SurveyData.airTemp[0] = Double.parseDouble(s.toString());
                }
            }
        });

        if(SurveyData.airTemp[1] >= 0)
        {
            airTemp2.setText(String.valueOf(SurveyData.airTemp[1]));
        }else{
            airTemp2.setHint("0.0");
        }

        airTemp2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().isEmpty()) {
                    SurveyData.airTemp[1] = Double.parseDouble(s.toString());
                }
            }
        });
    }
}
