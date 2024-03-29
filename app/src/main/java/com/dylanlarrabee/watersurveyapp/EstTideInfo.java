package com.dylanlarrabee.watersurveyapp;

import static com.dylanlarrabee.watersurveyapp.R.id;
import static com.dylanlarrabee.watersurveyapp.R.layout;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class EstTideInfo extends SaveFormAct {

    private Intent toEstHome, toEstWaterSurface, toEstTide;
    private Button homeButton, exitButton;
    private ImageView rightButton;
    private TextView headerBox, link1, link2, link3;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.estimate_tide_info);

        toEstHome = new Intent(this, EstimatesPage.class);
        toEstWaterSurface = new Intent(this, EstWaterSurface.class);
        toEstTide = new Intent(this, EstTide.class);

        setupButtons();
    }

    void setupButtons()
    {
        homeButton = (Button) findViewById(id.home_button);
        rightButton = (ImageView) findViewById(id.rightbutton_image);
        exitButton = (Button) findViewById(id.exit_button);
        headerBox = (TextView) findViewById(id.estimateTitle);

        setListener(homeButton, toEstHome);
        setListener(rightButton, toEstWaterSurface);
        setListener(exitButton, toEstTide);
        setListener(headerBox, toEstTide);


        link1 = findViewById(R.id.tidelink1);
        link1.setMovementMethod(LinkMovementMethod.getInstance());

        link2 = findViewById(R.id.tidelink2);
        link2.setMovementMethod(LinkMovementMethod.getInstance());

        link3 = findViewById(R.id.tidelink3);
        link3.setMovementMethod(LinkMovementMethod.getInstance());
    }

    void setListener(View button, Intent intent)
    {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });
    }


}
