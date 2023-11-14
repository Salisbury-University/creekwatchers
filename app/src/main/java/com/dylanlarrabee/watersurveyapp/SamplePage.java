package com.dylanlarrabee.watersurveyapp;
import static com.dylanlarrabee.watersurveyapp.R.*;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SamplePage extends SaveFormAct{
    String unit = " m";
    String title = "Enter in Sample Distance ";

    private EditText sampleDistEditText;
    private Spinner sampleUnitSpinner;
    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(layout.sample_dist);

        // Initialize UI elements
        sampleDistEditText = findViewById(R.id.sampledistEditTxt);
        sampleUnitSpinner = findViewById(R.id.samp_distance_unit_spinner);
        sampleDistEditText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);

        TextView samp_home = (TextView) findViewById(id.sampdist_home);
        ImageView samp_n = (ImageView) findViewById(id.samp_next);
        ImageView samp_b = (ImageView) findViewById(id.samp_back);


        // Set up the spinner with unit options
        ArrayAdapter<CharSequence> unitAdapter = ArrayAdapter.createFromResource(this, R.array.unit_options, layout.custom_spinner);
        unitAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sampleUnitSpinner.setAdapter(unitAdapter);

        // Load the selected unit from SharedPreferences
        SharedPreferences preferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String savedUnit = preferences.getString("selectedUnit", "m"); // "m" is the default unit

        // Find the position of SurveyData.sampleunit in the unitOptions array
        int position = unitAdapter.getPosition(SurveyData.sampUnit);
        // Set the initial selection based on SurveyData.sampleunit
        sampleUnitSpinner.setSelection(position);
        sampleUnitSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Update SurveyData.sampUnit when a new unit is selected
                String selectedUnit = unitAdapter.getItem(position).toString();
                SurveyData.sampUnit = selectedUnit;

                // Save the selected unit in SharedPreferences
                SharedPreferences preferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("selectedUnit", selectedUnit);
                editor.apply();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Handle the case where nothing is selected if necessary
            }
        });


        if(ReviewPage.isReviewing) {
            samp_home.setText("BACK");
            BasicCommands.setActivity(this, samp_home, ReviewPage.class);
        }
        else
            BasicCommands.setActivity(this, samp_home, MeasurementsPage.class);
        BasicCommands.setActivity(this, samp_b, WaterDepth.class);
        BasicCommands.setActivity(this, samp_n, AirTemp.class);


        if(SurveyData.sampleDist[0] >= 0)
        {
            sampleDistEditText.setText(String.valueOf(SurveyData.sampleDist[0]));
        }else{
            sampleDistEditText.setHint("0.0");
        }

        sampleDistEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().isEmpty()) {
                    SurveyData.sampleDist[0] = Double.parseDouble(s.toString());
                }
            }
        });
    }
}
