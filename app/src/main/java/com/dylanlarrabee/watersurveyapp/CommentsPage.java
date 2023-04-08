package com.dylanlarrabee.watersurveyapp;
import static com.dylanlarrabee.watersurveyapp.R.*;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CommentsPage extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(layout.commentspage);

        TextView comms = (TextView) findViewById(id.comms_submit);

        TextView comm_h = (TextView) findViewById(id.comhome);
        ImageView comm_n = (ImageView) findViewById(id.com_next);
        ImageView comm_b = (ImageView) findViewById(id.com_back);

        if(ReviewPage.isReviewing) {
            comm_h.setText("BACK");
            BasicCommands.setActivity(this, comm_h, ReviewPage.class);
        }
        else
            BasicCommands.setActivity(this, comm_h, MeasurementsPage.class);
        BasicCommands.setActivity(this, comm_b, SecchiDepth.class);
        BasicCommands.setActivity(this, comm_n, ReviewPage.class);

        EditText inText = (EditText) findViewById(id.commentText);
        if(SurveyData.comments.isEmpty()){
            SurveyData.comments = "Enter Comments Here";
        }else{
            inText.setText(SurveyData.comments);
        }
        comms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String comments = inText.getEditableText().toString();
                SurveyData.comments = comments;
                Toast msg = Toast.makeText(getApplicationContext(), "Comment Saved", Toast.LENGTH_SHORT);
                msg.show();
            }
        });

        CheckBox bottomOut = findViewById(id.bottomout);

        if((SurveyData.secchiDepth[0] == SurveyData.waterDepth[0] || SurveyData.secchiDepth[1] == SurveyData.waterDepth[0]) && SurveyData.waterDepth[0] >= 0){
            bottomOut.setChecked(true);
            SurveyData.bottomedOut = true;
        }

        if(SurveyData.bottomedOut){

        }
    }
}