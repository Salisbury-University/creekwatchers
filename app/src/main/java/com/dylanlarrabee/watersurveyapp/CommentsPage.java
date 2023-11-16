package com.dylanlarrabee.watersurveyapp;
import static com.dylanlarrabee.watersurveyapp.R.*;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
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
import android.text.InputFilter;
import android.text.InputType;
import android.text.Spanned;
import android.text.TextWatcher;
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

        CheckBox checkBox = findViewById(R.id.bottomout);
        ConstraintLayout root = findViewById(R.id.commLay);
        View rootView = getWindow().getDecorView().getRootView();

        LinearLayout linearLayoutPB = findViewById(id.LLPB);
        LinearLayout linearLayoutGB = findViewById(id.LLGB);

        if (ReviewPage.isReviewing) {
            comm_h.setText("Return");
            BasicCommands.setActivity(this, comm_h, ReviewPage.class);
        } else
            BasicCommands.setActivity(this, comm_h, HomePage.class);
        BasicCommands.setActivity(this, comm_b, SecchiDepth.class);
        BasicCommands.setActivity(this, comm_n, ReviewPage.class);


        EditText inText = (EditText) findViewById(id.commentText);
        EditText in_p_bottle = (EditText) findViewById(id.plasticbottle);
        EditText in_g_bottle = (EditText) findViewById(id.glassbottle);
        // Set filters to restrict input to 2 characters for both fields
        in_p_bottle.setFilters(new InputFilter[]{new InputFilter.LengthFilter(3)});
        in_g_bottle.setFilters(new InputFilter[]{new InputFilter.LengthFilter(2)});

        if(!SurveyData.Pbottle.isEmpty()) {
            in_p_bottle.setText(SurveyData.Pbottle);
        }

        if(!SurveyData.Gbottle.isEmpty()) {
            in_g_bottle.setText(SurveyData.Gbottle);
        }


        // Add TextWatcher for plasticbottle
        in_p_bottle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Not needed
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Not needed
            }

            @Override
            public void afterTextChanged(Editable s) {
                String input = s.toString().toUpperCase(); // Convert to uppercase

                if (input.length() > 1) {
                    char secondChar = input.charAt(1);

                    if (Character.isLetter(secondChar) && input.length() > 2) {
                        // If second character is a letter, delete any third character
                        s.delete(2, input.length());
                    } else if (Character.isDigit(secondChar) && input.length() > 2) {
                        // If second character is a digit, ensure third character is also a digit
                        if (!Character.isDigit(input.charAt(2))) {
                            s.delete(2, input.length());
                        }
                    }
                }

                if (input.length() == 1 && !Character.isLetter(input.charAt(0))) {
                    // Ensure first character is a letter
                    s.delete(0, input.length());
                }
            }
        });


        // Add TextWatcher for glassbottle
        in_g_bottle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Not needed
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Not needed
            }

            @Override
            public void afterTextChanged(Editable s) {
                String input = s.toString().toUpperCase(); // Convert to uppercase
                if (input.length() == 2) {
                    char firstChar = input.charAt(0);
                    char secondChar = input.charAt(1);
                    if (!Character.isLetter(firstChar) || !Character.isLetter(secondChar)) {
                        s.delete(1, 2); // Remove the second character if it's not alphabetic
                    }
                }
            }
        });



        if (ReviewPage.isReviewing) {
            comm_h.setText("BACK");
            comm_h.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent toPage = new Intent(CommentsPage.this, ReviewPage.class);

                    String comments = inText.getEditableText().toString();
                    SurveyData.comments = comments;
                    SurveyData.curComm = 1;

                    String p_bottle = in_p_bottle.getEditableText().toString();
                    SurveyData.Pbottle = p_bottle;

                    String g_bottle = in_g_bottle.getEditableText().toString();
                    SurveyData.Gbottle = g_bottle;

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

                    String p_bottle = in_p_bottle.getEditableText().toString();
                    SurveyData.Pbottle = p_bottle;

                    String g_bottle = in_g_bottle.getEditableText().toString();
                    SurveyData.Gbottle = g_bottle;


                    startActivity(toPage);
                }
            });
        }

        if (SurveyData.comments.isEmpty()) {
            SurveyData.comments = "";
        } else {
            inText.setText(SurveyData.comments);
        }

        linearLayoutPB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Update the content of the plasticbottle EditText
                in_p_bottle.setText("AA");

            }
        });

        linearLayoutGB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Update the content of the glassbottle EditText
                in_g_bottle.setText("A1");
            }
        });


        comm_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toPage = new Intent(CommentsPage.this, SecchiDepth.class);

                String comments = inText.getEditableText().toString();
                SurveyData.comments = comments;
                SurveyData.curComm = 1;

                String p_bottle = in_p_bottle.getEditableText().toString();
                SurveyData.Pbottle = p_bottle;

                String g_bottle = in_g_bottle.getEditableText().toString();
                SurveyData.Gbottle = g_bottle;

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

                String p_bottle = in_p_bottle.getEditableText().toString();
                SurveyData.Pbottle = p_bottle;

                String g_bottle = in_g_bottle.getEditableText().toString();
                SurveyData.Gbottle = g_bottle;

                startActivity(toPage);
            }
        });

        if ((SurveyData.secchiDepth[0] > SurveyData.waterDepth[0]) || (SurveyData.secchiDepth[1] > SurveyData.waterDepth[0])) {
            SurveyData.bottomedOut = true;
            checkBox.setChecked(true);
            Showpopup();

        }
    }
    public void Showpopup(){
        myDiag.setContentView(layout.tube_test);
        RadioGroup radioGroup = (RadioGroup) myDiag.findViewById(id.tubeRadio);
        TextView proceed = (TextView) myDiag.findViewById(id.tube_proceed);

        TextView waterDepthText = (TextView) myDiag.findViewById(R.id.tube_water);
        TextView secchiDepth1Text = (TextView) myDiag.findViewById(R.id.tube_sec1);
        TextView secchiDepth2Text = (TextView) myDiag.findViewById(R.id.tube_sec2);

        EditText waterDepthInput = myDiag.findViewById(R.id.waterDepthInput);
        EditText secchiDepth1Input = myDiag.findViewById(id.secchiDepthIn1);
        EditText secchiDepth2Input = myDiag.findViewById(id.secchiDepthIn2);

        // Set input constraints for the EditText
        waterDepthInput.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        waterDepthInput.setFilters(new InputFilter[]{
                new InputFilterMinMax(0.0, 500.0) // Custom InputFilter to limit the value
        });

        // Set input constraints for the EditText
        secchiDepth1Input.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        secchiDepth1Input.setFilters(new InputFilter[]{
                new InputFilterMinMax(0.0, 500.0) // Custom InputFilter to limit the value
        });

        // Set input constraints for the EditText
        secchiDepth2Input.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        secchiDepth2Input.setFilters(new InputFilter[]{
                new InputFilterMinMax(0.0, 500.0) // Custom InputFilter to limit the value
        });

        waterDepthText.setText("Water Depth:");
        secchiDepth1Text.setText("Secchi Depth 1:");
        secchiDepth2Text.setText("Secchi Depth 2:");

        // Set hint text for EditText fields to display current values
        waterDepthInput.setHint(String.valueOf(SurveyData.waterDepth[0]));
        secchiDepth1Input.setHint(String.valueOf(SurveyData.secchiDepth[0]));
        secchiDepth2Input.setHint(String.valueOf(SurveyData.secchiDepth[1]));


        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectId = radioGroup.getCheckedRadioButtonId();
                CheckBox checkBox = findViewById(R.id.bottomout);

                if (selectId == R.id.tubeT) {
                    SurveyData.tubeUsed = true;
                } else {
                    SurveyData.tubeUsed = false;
                }

                // Get values from EditText fields
                String waterDepthStr = waterDepthInput.getText().toString();
                String secchiDepth1Str = secchiDepth1Input.getText().toString();
                String secchiDepth2Str = secchiDepth2Input.getText().toString();

                // Check if the input fields are empty
                if (waterDepthStr.isEmpty() && secchiDepth1Str.isEmpty() && secchiDepth2Str.isEmpty()) {
                    // If all fields are empty, save the old values
                    if (SurveyData.waterDepth[0] != -1 && SurveyData.secchiDepth[0] != -1 && SurveyData.secchiDepth[1] != -1) {
                        double waterDepth = SurveyData.waterDepth[0];
                        double secchiDepth1 = SurveyData.secchiDepth[0];
                        double secchiDepth2 = SurveyData.secchiDepth[1];

                        // Update SurveyData values
                        SurveyData.waterDepth[0] = waterDepth;
                        SurveyData.secchiDepth[0] = secchiDepth1;
                        SurveyData.secchiDepth[1] = secchiDepth2;

                        if ((SurveyData.secchiDepth[0] > SurveyData.waterDepth[0]) || (SurveyData.secchiDepth[1] > SurveyData.waterDepth[0])) {
                            SurveyData.bottomedOut = true;
                            checkBox.setChecked(true);
                        } else {
                            SurveyData.bottomedOut = false;
                            checkBox.setChecked(false);
                        }

                        myDiag.dismiss();
                    }
                } else {
                    if (!waterDepthStr.isEmpty() || !secchiDepth1Str.isEmpty() || !secchiDepth2Str.isEmpty()) {
                        // If any of the fields is not empty, proceed with the new values

                        if (waterDepthStr.isEmpty()) {
                            waterDepthStr = String.valueOf(SurveyData.waterDepth[0]);
                        }
                        if (secchiDepth1Str.isEmpty()) {
                            secchiDepth1Str = String.valueOf(SurveyData.secchiDepth[0]);
                        }
                        if (secchiDepth2Str.isEmpty()) {
                            secchiDepth2Str = String.valueOf(SurveyData.secchiDepth[1]);
                        }

                        // Parse the values to double
                        double waterDepth = Double.parseDouble(waterDepthStr);
                        double secchiDepth1 = Double.parseDouble(secchiDepth1Str);
                        double secchiDepth2 = Double.parseDouble(secchiDepth2Str);

                        // Update SurveyData values
                        SurveyData.waterDepth[0] = waterDepth;
                        SurveyData.secchiDepth[0] = secchiDepth1;
                        SurveyData.secchiDepth[1] = secchiDepth2;

                        if ((SurveyData.secchiDepth[0] > SurveyData.waterDepth[0]) || (SurveyData.secchiDepth[1] > SurveyData.waterDepth[0])) {
                            SurveyData.bottomedOut = true;
                            checkBox.setChecked(true);
                        } else {
                            SurveyData.bottomedOut = false;
                            checkBox.setChecked(false);
                        }

                        myDiag.dismiss();
                    }
                }
            }
        });
        myDiag.show();
    }
}