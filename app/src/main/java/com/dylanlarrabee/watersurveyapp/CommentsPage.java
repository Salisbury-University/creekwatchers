package com.dylanlarrabee.watersurveyapp;
import static com.dylanlarrabee.watersurveyapp.R.*;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CommentsPage extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(layout.commentspage);

        TextView comm_h = (TextView) findViewById(id.comhome);
        ImageView comm_n = (ImageView) findViewById(id.com_next);
        ImageView comm_b = (ImageView) findViewById(id.com_back);

        BasicCommands.setActivity(this, comm_h, HomePage.class);
        BasicCommands.setActivity(this, comm_b, SecchiDepth.class);
        BasicCommands.setActivity(this, comm_n, ReviewPage.class);

        EditText inText = (EditText) findViewById(id.commentText);
        SurveyData.comments = inText.getEditableText().toString();

        CheckBox bottomOut = findViewById(id.bottomout);

        if(SurveyData.secchiDepth[0] == SurveyData.waterDepth[0] || SurveyData.secchiDepth[1] == SurveyData.waterDepth[0]){
            bottomOut.setChecked(true);
            SurveyData.bottomedOut = true;
        }
    }
}