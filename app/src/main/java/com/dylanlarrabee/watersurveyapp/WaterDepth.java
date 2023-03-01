package com.dylanlarrabee.watersurveyapp;
import static com.dylanlarrabee.watersurveyapp.R.*;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class WaterDepth extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.water_depth);

        //Buttons for the new views
        TextView water_home = (TextView) findViewById(id.waterdep_home);
        ImageView water_back = (ImageView) findViewById(id.waterdep_back);
        ImageView water_next = (ImageView) findViewById(id.waterdep_next);

        setActivity(water_home, HomePage.class);
        setActivity(water_back, MeasurementsPage.class);
        //setActivity((water_next, SampDist.class));
}

    //Function to easily go back to main page
    void setActivity(TextView view, Class page)
    {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Takes back to main activity when clicked
                Intent topage=new Intent(WaterDepth.this, page);

                startActivity(topage);
            }
        });
    }

    //Function to easily go back to main page
    void setActivity(Button btn, Class page)
    {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Takes back to main activity when clicked
                Intent topage=new Intent(WaterDepth.this, page);

                startActivity(topage);
            }
        });
    }

    void setActivity(ImageView view, Class page)
    {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Takes back to main activity when clicked
                Intent topage=new Intent(WaterDepth.this, page);

                startActivity(topage);
            }
        });
    }

}