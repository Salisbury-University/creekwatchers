package com.dylanlarrabee.watersurveyapp;
import static com.dylanlarrabee.watersurveyapp.R.*;

import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class WaterTemp extends SaveFormAct {
    String unit = " °C";
    String title = "Enter in Water Temperature ";
    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(layout.water_temp);

        EditText waterTemp1 = findViewById(id.waterTempEdit1);
        EditText waterTemp2 = findViewById(id.waterTempEdit2);

        // Set input constraints for the EditText
        waterTemp1.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        waterTemp1.setFilters(new InputFilter[]{
                new InputFilterMinMax(0.0, 40.0) // Custom InputFilter to limit the value
        });

        // Set input constraints for the EditText
        waterTemp2.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        waterTemp2.setFilters(new InputFilter[]{
                new InputFilterMinMax(0.0, 40.0) // Custom InputFilter to limit the value
        });

        TextView waterTemp_h = (TextView) findViewById(R.id.waterTemp_home);
        ImageView waterTemp_n = (ImageView) findViewById(R.id.waterTemp_next);
        ImageView waterTemp_b = (ImageView) findViewById(id.waterTemp_back);

        if(ReviewPage.isReviewing) {
            waterTemp_h.setText("BACK");
            BasicCommands.setActivity(this, waterTemp_h, ReviewPage.class);
        }
        else
            BasicCommands.setActivity(this, waterTemp_h, MeasurementsPage.class);

        BasicCommands.setActivity(this, waterTemp_b, AirTemp.class);
        BasicCommands.setActivity(this, waterTemp_n, SecchiDepth.class);


        if(SurveyData.waterTemp[0] >= 0)
        {
            waterTemp1.setText(String.valueOf(SurveyData.waterTemp[0]));
        }else{
            waterTemp1.setHint("0.0");
        }

        waterTemp1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().isEmpty()) {
                    SurveyData.waterTemp[0] = Double.parseDouble(s.toString());
                }
            }
        });

        if(SurveyData.waterTemp[1] >= 0)
        {
            waterTemp2.setText(String.valueOf(SurveyData.waterTemp[1]));
        }else{
            waterTemp2.setHint("0.0");
        }

        waterTemp2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().isEmpty()) {
                    SurveyData.waterTemp[1] = Double.parseDouble(s.toString());
                }
            }
        });

    }
}
