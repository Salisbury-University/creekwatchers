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

        TextView secchi_home = (TextView) findViewById(id.secchihome);
        ImageView secchi_next = (ImageView) findViewById(id.secchi_next);
        ImageView secchi_back = (ImageView) findViewById(id.secchi_back);

        BasicCommands.setActivity(this, secchi_home, HomePage.class);
        BasicCommands.setActivity(this, secchi_back, WaterTemp.class);
        //BasicCommands.setActivity(this, secchi_next, Comments.class);

    }

}
