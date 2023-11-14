package com.dylanlarrabee.watersurveyapp;
import static com.dylanlarrabee.watersurveyapp.R.*;

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

import androidx.appcompat.app.AppCompatActivity;
public class SecchiDepth extends SaveFormAct{

    String unit = " cm";
    String title = "Enter in Secchi Depth ";

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.secchi_depth);

        EditText secchiDepth1 = findViewById(id.secchiEdit1);
        EditText secchiDepth2 = findViewById(id.secchiEdit2);

        // Set input constraints for the EditText
        secchiDepth1.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        secchiDepth1.setFilters(new InputFilter[]{
                new InputFilterMinMax(0.0, 500.0) // Custom InputFilter to limit the value
        });

        // Set input constraints for the EditText
        secchiDepth2.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        secchiDepth2.setFilters(new InputFilter[]{
                new InputFilterMinMax(0.0, 500.0) // Custom InputFilter to limit the value
        });


        TextView secchi_h = (TextView) findViewById(id.secchihome);
        ImageView secchi_n = (ImageView) findViewById(id.secchi_next);
        ImageView secchi_b = (ImageView) findViewById(id.secchi_back);

        if(ReviewPage.isReviewing) {
            secchi_h.setText("BACK");
            BasicCommands.setActivity(this, secchi_h, ReviewPage.class);
        }
        else
            BasicCommands.setActivity(this, secchi_h, MeasurementsPage.class);

        BasicCommands.setActivity(this, secchi_b, WaterTemp.class);
        BasicCommands.setActivity(this, secchi_n, CommentsPage.class);

        if(SurveyData.secchiDepth[0] >= 0)
        {
            secchiDepth1.setText(String.valueOf(SurveyData.secchiDepth[0]));
        }else{
            secchiDepth1.setHint("0.0");
        }

        secchiDepth1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().isEmpty()) {
                    SurveyData.secchiDepth[0] = Double.parseDouble(s.toString());
                }
            }
        });

        if(SurveyData.secchiDepth[1] >= 0)
        {
            secchiDepth2.setText(String.valueOf(SurveyData.secchiDepth[1]));
        }else{
            secchiDepth2.setHint("0.0");
        }

        secchiDepth2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().isEmpty()) {
                    SurveyData.secchiDepth[1] = Double.parseDouble(s.toString());
                }
            }
        });
    }
}
