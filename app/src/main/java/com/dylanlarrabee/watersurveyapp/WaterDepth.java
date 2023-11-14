package com.dylanlarrabee.watersurveyapp;
import static com.dylanlarrabee.watersurveyapp.R.*;
import android.app.Activity;
import android.content.Intent;
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
import android.text.InputFilter;
import android.text.Spanned;

import androidx.appcompat.app.AppCompatActivity;

public class WaterDepth extends SaveFormAct {
    String unit = " cm";
    String title = "Enter in Water Depth ";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.water_depth);

        TextView wdmeas = (TextView) findViewById(R.id.waterdep_meas);
        EditText waterDepthEditText = findViewById(id.waterdep_measurement_edittext);
        TextView unitTextView = findViewById(id.waterdep_measurement_unit);

        // Set input constraints for the EditText
        waterDepthEditText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        waterDepthEditText.setFilters(new InputFilter[]{
                new InputFilterMinMax(0.0, 500.0) // Custom InputFilter to limit the value
        });

        //Buttons for the new views
        TextView water_h = (TextView) findViewById(id.waterdep_home);
        ImageView water_b = (ImageView) findViewById(id.waterdep_back);
        ImageView water_n = (ImageView) findViewById(id.waterdep_next);

        if(ReviewPage.isReviewing) {
            water_h.setText("BACK");
            BasicCommands.setActivity(this, water_h, ReviewPage.class);
        }
        else
            BasicCommands.setActivity(this, water_h, MeasurementsPage.class);


        BasicCommands.setActivity(this, water_b, EstRainfall.class);
        BasicCommands.setActivity(this, water_n, SamplePage.class);

        if(SurveyData.waterDepth[0] >= 0)
        {
            waterDepthEditText.setText(String.valueOf(SurveyData.waterDepth[0]));
        }else{
            waterDepthEditText.setHint("0.0");
        }

        waterDepthEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().isEmpty()) {
                    SurveyData.waterDepth[0] = Double.parseDouble(s.toString());
                }
            }
        });
    }
}

