package com.dylanlarrabee.watersurveyapp;
import static com.dylanlarrabee.watersurveyapp.R.*;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

public class CommentsPage extends SaveFormAct{

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(layout.commentspage);

        TextView comm_h = (TextView) findViewById(id.comhome);
        ImageView comm_n = (ImageView) findViewById(id.com_next);
        ImageView comm_b = (ImageView) findViewById(id.com_back);

        LayoutInflater inflater = LayoutInflater.from(this);
        View popup = inflater.inflate(layout.tube_pop, null);

        EditText inText = (EditText) findViewById(id.commentText);
        if(ReviewPage.isReviewing) {
            comm_h.setText("BACK");
            comm_h.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent  toPage = new Intent(CommentsPage.this, ReviewPage.class);

                    String comments = inText.getEditableText().toString();
                    SurveyData.comments = comments;
                    Toast msg = Toast.makeText(getApplicationContext(), "Comment Saved", Toast.LENGTH_SHORT);
                    msg.show();
                    SurveyData.curComm = 1;

                    startActivity(toPage);
                }
            });
            BasicCommands.setActivity(this, comm_h, ReviewPage.class);
        }
        else {
            comm_h.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent  toPage = new Intent(CommentsPage.this, HomePage.class);

                    String comments = inText.getEditableText().toString();
                    SurveyData.comments = comments;
                    Toast msg = Toast.makeText(getApplicationContext(), "Comment Saved", Toast.LENGTH_SHORT);
                    msg.show();
                    SurveyData.curComm = 1;

                    startActivity(toPage);
                }
            });
        }

        if(SurveyData.comments.isEmpty()){
            SurveyData.comments = "Enter Comments Here";
        }else{
            inText.setText(SurveyData.comments);
        }

        comm_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  toPage = new Intent(CommentsPage.this, SecchiDepth.class);

                String comments = inText.getEditableText().toString();
                SurveyData.comments = comments;
                Toast msg = Toast.makeText(getApplicationContext(), "Comment Saved", Toast.LENGTH_SHORT);
                msg.show();
                SurveyData.curComm = 1;

                startActivity(toPage);
            }
        });

        comm_n.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  toPage = new Intent(CommentsPage.this, ReviewPage.class);

                String comments = inText.getEditableText().toString();
                SurveyData.comments = comments;
                Toast msg = Toast.makeText(getApplicationContext(), "Comment Saved", Toast.LENGTH_SHORT);
                msg.show();
                SurveyData.curComm = 1;

                startActivity(toPage);
            }
        });

        CheckBox bottomOut = findViewById(id.bottomout);g
        if((SurveyData.secchiDepth[0] > SurveyData.waterDepth[0]) || );

    }
    public void showPopup(View anchorView) {
        View popupView = getLayoutInflater().inflate(layout.tube_pop, null);

        PopupWindow popup = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popup.setOutsideTouchable(true);
        popup.setFocusable(true);

        popup.showAsDropDown(anchorView);
    }
}