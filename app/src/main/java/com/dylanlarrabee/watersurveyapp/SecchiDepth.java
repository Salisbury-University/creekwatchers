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

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.secchi_depth);

        TextView secchi_h = (TextView) findViewById(id.secchihome);
        ImageView secchi_n = (ImageView) findViewById(id.secchi_next);
        ImageView secchi_b = (ImageView) findViewById(id.secchi_back);

        BasicCommands.setActivity(this, secchi_h, HomePage.class);
        BasicCommands.setActivity(this, secchi_b, WaterTemp.class);
        //BasicCommands.setActivity(this, secchi_n Comments.class);

    }

}
