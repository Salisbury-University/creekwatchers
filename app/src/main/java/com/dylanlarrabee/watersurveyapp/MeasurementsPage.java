package com.dylanlarrabee.watersurveyapp;


import static com.example.creekwatchers.R.*;
import static com.example.creekwatchers.R.id.waterdep_home;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class MeasurementsPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.measurements_main);



        XmlPage[] views = new XmlPage[1];

        views[0].btnId = id.waterdepth;
        views[0].toPagebtn = (Button) findViewById(views[0].btnId);
        views[0].homeId = waterdep_home;
        views[0].homeBtn=(TextView) findViewById(views[0].homeId);
        views[0].nxtId=id.waterdep_next;
        views[0].backId=id.waterdep_back;
        views[0].nxtBtn=(ImageView) findViewById(views[0].nxtId);
        views[0].backBtn=(ImageView) findViewById(views[0].backId);
        views[0].page=layout.water_depth;

        //Creates text views for measurement home page button
        TextView meashome_btn=(TextView) findViewById(id.meashome);

        //Allows the user to go back to main page when clicked the Home button
        setActivity(meashome_btn);

        setListener(views, 0, 1);

    }

    //Function to easily set the new view
    void setListener(XmlPage[] xmls, int size, int index)
    {

        for (int i = 0; i < size; i++){
            xmls[i].index = i;
        }

        for(int i = index; i < size; i++) {
            int btnId = xmls[i].btnId;
            int homeId = xmls[i].homeId;
            int nxtId = xmls[i].nxtId;
            int backId = xmls[i].backId;
            Button toPagebtn = xmls[i].toPagebtn;
            TextView homeBtn = xmls[i].homeBtn;
            ImageView nxtBtn = xmls[i].nxtBtn;
            ImageView backBtn = xmls[i].backBtn;
            int page = xmls[i].page;
            int j = i;
            toPagebtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int k = j;
                    setContentView(page);
                    setActivity(homeBtn);
                    if(k == 0){
                        backBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                            setContentView(layout.measurements_main);
                            }
                        });
                        nxtBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                int l = k;
                                setListener(xmls, l++, size);
                            }
                        });
                    }else if(k == size-1){
                        nxtBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                setContentView(layout.measurements_main);
                            }
                        });
                        backBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                int l = k;
                                setListener(xmls, l--, size);
                            }
                        });
                    }else {
                        backBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                int l = k;
                                setListener(xmls, l--, size);
                            }
                        });
                        nxtBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                int l = k;
                                setListener(xmls, l++, size);
                            }
                        });
                    }
                }
            });
        }
    }

    //Function to easily go back to main page
    void setActivity(TextView home)
    {
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Takes back to main activity when clicked
                Intent homepage=new Intent(MeasurementsPage.this, MainActivity.class);

                startActivity(homepage);
            }
        });
    }

}