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

public class AirTemp extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(layout.air_temp);

        TextView ameas1 = (TextView) findViewById(id.at_title_meas1);

        TextView air_h = (TextView) findViewById(R.id.airhome);
        ImageView air_n = (ImageView) findViewById(R.id.air_next);
        ImageView air_b = (ImageView) findViewById(R.id.air_back);


        BasicCommands.setActivity(this, air_h, HomePage.class);
        BasicCommands.setActivity(this, air_b, SamplePage.class);
        BasicCommands.setActivity(this, air_n, WaterTemp.class);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter in measurement 1");
        EditText meas1in = new EditText(this);
        ameas1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText input = new EditText(AirTemp.this);
                input.setInputType(InputType.TYPE_CLASS_NUMBER);
                builder.setView(input);

                builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String input = meas1in.getText().toString();
                        if (!input.isEmpty()) {
                            int num = Integer.parseInt(input);
                            SurveyData.airTemp[0] = num;
                        }
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });


    }
}
