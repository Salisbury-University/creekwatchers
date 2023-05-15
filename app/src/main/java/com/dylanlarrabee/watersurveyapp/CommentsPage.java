package com.dylanlarrabee.watersurveyapp;
import static com.dylanlarrabee.watersurveyapp.R.*;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;

import org.w3c.dom.Text;

public class CommentsPage extends SaveFormAct {
    Dialog myDiag;
    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(layout.commentspage);

        myDiag = new Dialog(this);
        TextView comm_h = (TextView) findViewById(id.comhome);
        ImageView comm_n = (ImageView) findViewById(id.com_next);
        ImageView comm_b = (ImageView) findViewById(id.com_back);
        ConstraintLayout root = findViewById(R.id.commLay);
        View rootView = getWindow().getDecorView().getRootView();

        if (ReviewPage.isReviewing) {
            comm_h.setText("Return");
            BasicCommands.setActivity(this, comm_h, ReviewPage.class);
        } else
            BasicCommands.setActivity(this, comm_h, MeasurementsPage.class);
        BasicCommands.setActivity(this, comm_b, SecchiDepth.class);
        BasicCommands.setActivity(this, comm_n, ReviewPage.class);


        EditText inText = (EditText) findViewById(id.commentText);
        if (ReviewPage.isReviewing) {
            comm_h.setText("BACK");
            comm_h.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent toPage = new Intent(CommentsPage.this, ReviewPage.class);

                    String comments = inText.getEditableText().toString();
                    SurveyData.comments = comments;
                    SurveyData.curComm = 1;

                    startActivity(toPage);
                }
            });
            BasicCommands.setActivity(this, comm_h, ReviewPage.class);
        } else {
            comm_h.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent toPage = new Intent(CommentsPage.this, HomePage.class);

                    String comments = inText.getEditableText().toString();
                    SurveyData.comments = comments;
                    SurveyData.curComm = 1;

                    startActivity(toPage);
                }
            });
        }

        if (SurveyData.comments.isEmpty()) {
            SurveyData.comments = "";
        } else {
            inText.setText(SurveyData.comments);
        }

        comm_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toPage = new Intent(CommentsPage.this, SecchiDepth.class);

                String comments = inText.getEditableText().toString();
                SurveyData.comments = comments;
                SurveyData.curComm = 1;

                startActivity(toPage);
            }
        });

        comm_n.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toPage = new Intent(CommentsPage.this, ReviewPage.class);
                String comments = inText.getEditableText().toString();
                SurveyData.comments = comments;
                Toast msg = Toast.makeText(getApplicationContext(), "Comment Saved", Toast.LENGTH_SHORT);
                msg.show();
                SurveyData.curComm = 1;
                startActivity(toPage);
            }
        });
        /*
        if((SurveyData.secchiDepth[0] > SurveyData.waterDepth[0]) || (SurveyData.secchiDepth[1] > SurveyData.waterDepth[0])){
            // Inflate the XML layout file for the popup
            LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
            View popupView = inflater.inflate(R.layout.tube_test, null);
            RadioGroup radioGroup = popupView.findViewById(R.id.tube);
            int selectedId = radioGroup.getCheckedRadioButtonId();

            // create the popup window
            int width = LinearLayout.LayoutParams.WRAP_CONTENT;
            int height = LinearLayout.LayoutParams.WRAP_CONTENT;
            boolean focusable = true; //lets taps outside the popup also dismiss it
            final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

            popupWindow.showAtLocation(getWindow().getDecorView(), Gravity.CENTER, 0, 0);

            // Get references to the EditText views in the popup layout
            TextView waterDepthText = popupView.findViewById(R.id.tube_water);
            TextView secchiDepth1Text = popupView.findViewById(R.id.tube_sec1);
            TextView secchiDepth2Text = popupView.findViewById(R.id.tube_sec2);
            TextView proceed = popupView.findViewById(R.id.tube_proceed);

            waterDepthText.setText(SurveyData.waterDepth[0] + " cm");
            secchiDepth1Text.setText(SurveyData.secchiDepth[0] + " cm");
            secchiDepth2Text.setText(SurveyData.secchiDepth[1] + " cm");

            // Assign the value of the tubeUsed variable based on the answer from the popup
            if (selectedId == R.id.tubeT) {
                SurveyData.tubeUsed = true;
            } else {
                SurveyData.tubeUsed = false;
            }
            proceed.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    popupWindow.dismiss();
                }
            });
        }
         */
        if ((SurveyData.secchiDepth[0] > SurveyData.waterDepth[0]) || (SurveyData.secchiDepth[1] > SurveyData.waterDepth[0])) {
            Showpopup();
        }
    }
    public void Showpopup(){
        myDiag.setContentView(layout.tube_test);
        RadioGroup radioGroup = (RadioGroup) findViewById(id.tubeRadio);
        TextView proceed = (TextView) myDiag.findViewById(id.tube_proceed);

        TextView waterDepthText = (TextView)findViewById(R.id.tube_water);
        TextView secchiDepth1Text = (TextView) findViewById(R.id.tube_sec1);
        TextView secchiDepth2Text = (TextView) findViewById(R.id.tube_sec2);

        waterDepthText.setText("Water Dpeth: " + SurveyData.waterDepth[0] + " cm");
        secchiDepth1Text.setText("Secchi Depth 1: " + SurveyData.secchiDepth[0] + " cm");
        secchiDepth2Text.setText("Secchi Depth 2: " + SurveyData.secchiDepth[1] + " cm");

        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectId = radioGroup.getCheckedRadioButtonId();

                if (selectId == R.id.tubeT) {
                    SurveyData.tubeUsed = true;
                } else {
                    SurveyData.tubeUsed = false;
                }

                myDiag.dismiss();
            }
        });
        myDiag.show();
    }
}