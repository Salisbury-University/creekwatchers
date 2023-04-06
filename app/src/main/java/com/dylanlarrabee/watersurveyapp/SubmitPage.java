package com.dylanlarrabee.watersurveyapp;

import static com.dylanlarrabee.watersurveyapp.R.id;
import static com.dylanlarrabee.watersurveyapp.R.layout;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.graphics.Color;
import android.media.Image;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import javax.mail.Session;
import javax.mail.Transport;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;


public class SubmitPage extends AppCompatActivity {

    private TextView statusText;
    private TextView thankyouText;
    private ImageView greencheck;
    private ProgressBar progressBar;
    private Button resubmitButton;

    //official recipient email: creekwatchers@salisbury.edu
    private String recipient = "chrismorse301@gmail.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.submit_page);

        setupViews();
        attemptSubmit();
    }

    void setupViews() {
        statusText = (TextView) findViewById(id.statusText);
        thankyouText = (TextView) findViewById(id.thankyouText);
        progressBar = (ProgressBar) findViewById(id.progressBar);
        resubmitButton = (Button) findViewById(id.submitButton);
        greencheck = (ImageView) findViewById(id.greencheck);

        // Hide lower text box, submit button and green check image while submitting
        thankyouText.setVisibility(View.INVISIBLE);
        resubmitButton.setVisibility(View.INVISIBLE);
        greencheck.setVisibility(View.INVISIBLE);

        resubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptSubmit();
            }
        });
    }

    void attemptSubmit() {
        statusText.setText("Submitting...");
        progressBar.setVisibility(View.VISIBLE);
        greencheck.setVisibility(View.INVISIBLE);
        resubmitButton.setVisibility(View.INVISIBLE);
        thankyouText.setVisibility(View.INVISIBLE);
        try {
            sendEmail();
        } catch (MessagingException e) {
           submitFailure();
        }
    }

    void sendEmail() throws MessagingException {

        Log.d("MAIL", "sendMail() called");

        // Establish an email client
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        // Login to email account
        Session session = Session.getDefaultInstance(props,
            new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(Config.EMAIL, Config.PASSWORD);
                }
            });

        // Create email object (MimeMessage)
        MimeMessage message = new MimeMessage(session);
        // Set sender
        message.setFrom(new InternetAddress(Config.EMAIL));
        // Set recipient
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));

        // Get date
        Calendar currentTime = Calendar.getInstance();
        int year = currentTime.get(Calendar.YEAR); year %=100;
        String date = currentTime.get(Calendar.MONTH) + "/" + currentTime.get(Calendar.DAY_OF_MONTH) + "/" + year;

        String subject  = SurveyData.userSite + " " + SurveyData.userName + " " + date;

        // Set subject
        message.setSubject(subject);
        Log.d("MAIL", "Subject: " + subject);

        // Set body
        String emailBody =
            "Name: " + SurveyData.userName + "\n" +
            "Date: " + date + "\n" +
            "Site: " + SurveyData.userSite + "\n" +
            "Tide Est: " + SurveyData.tideEst + "\n" +
            "Weather Est: " +SurveyData.weathEst + "\n" +
            "Water Surface Est: " +SurveyData.waterSurf + "\n" +
            "Wind Speed Est: " +SurveyData.windSpeed + "\n" +
            "Wind Direction Est: " +SurveyData.windDir+ "\n" +
            "Rainfall Est: " +SurveyData.rainfall+ "\n" +
            "Water Depth: " +SurveyData.waterDepth[0] + "\n" +
            "Sample Distance: " +SurveyData.sampleDist[0] + "\n" +
            "Air Temp 1: " +SurveyData.airTemp[0]+ "\n" +
            "Air Temp 2: " +SurveyData.airTemp[1] + "\n" +
            "Water Temp 1: " +SurveyData.waterTemp[0]+ "\n" +
            "Water Temp 2: " +SurveyData.waterTemp[1]+ "\n" +
            "Secchi Depth 1: " +SurveyData.secchiDepth[0]+ "\n" +
            "Secchi Depth 2: " +SurveyData.secchiDepth[1]+ "\n";

        BodyPart messageBodyPart1 = new MimeBodyPart();
        messageBodyPart1.setText(emailBody);
        Log.d("MAIL", "Body: " + emailBody);

        // Create CSV file
        String csvText =
            "\"Name\",\"Site\",\"Tide\",\"Weather\",\"WaterSurf\",\"WindSpeed\",\"WindDirect\",\"Rainfall\",\"WaterDepth1\",\"WaterDepth2\",\"AirTemp1\",\"AirTemp2\",\"WaterTemp1\",\"WaterTemp2\",\"SecchiDepth1\",\"SecchiDepth2\"\n\"" +
            SurveyData.userName + "\",\"" +
            SurveyData.userSite + "\",\"" +
            SurveyData.tideEst + "\",\"" +
            SurveyData.weathEst + "\",\"" +
            SurveyData.waterSurf + "\",\"" +
            SurveyData.windSpeed+ "\",\"" +
            SurveyData.windDir+ "\",\"" +
            SurveyData.rainfall+ "\",\"" +
            SurveyData.waterDepth[0] +"\",\"" +
            SurveyData.sampleDist[0]+ "\",\"" +
            SurveyData.airTemp[0]+ "\",\"" +
            SurveyData.airTemp[1] + "\",\"" +
            SurveyData.waterTemp[0]+ "\",\"" +
            SurveyData.waterTemp[1]+ "\",\"" +
            SurveyData.secchiDepth[0]+ "\",\"" +
            SurveyData.secchiDepth[1] + "\"";

        String filename = SurveyData.userSite + " " + SurveyData.userName + " " + currentTime.get(Calendar.MONTH) + "-" + currentTime.get(Calendar.DAY_OF_MONTH) + "-" + year + ".csv";
        File directory = getDir("surveys", Context.MODE_PRIVATE);
        File myFile = new File(directory, filename);
        try {
            FileWriter fr = new FileWriter(myFile, false);
            fr.write(csvText);
            fr.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Create file attachment
        BodyPart messageBodyPart2 = new MimeBodyPart();
        DataSource source = new FileDataSource(myFile);
        messageBodyPart2.setDataHandler(new DataHandler(source));
        messageBodyPart2.setFileName(filename);

        // Attach file to email body
        Multipart multipartObject = new MimeMultipart();
        multipartObject.addBodyPart(messageBodyPart1);
        multipartObject.addBodyPart(messageBodyPart2);
        message.setContent(multipartObject);

        // Must use Asynchronous SendMail class to send the email
        // SendMail processes sending the email in the background on a separate thread from the main app thread.
        // SendMail member function onPostExecute is called when the email sends.
        // execute() calls doInBackground() function, takes email object array as parameter.
        SendMail mail = new SendMail();
        mail.execute(message);
    }

    private class SendMail extends AsyncTask<MimeMessage, Void, Void> {

        protected Void doInBackground(MimeMessage... message) {
            sendMail(message[0]);
            return null;
        }
        public void sendMail(MimeMessage message) {
            try {
                Log.d("MAIL", "Sending email now...");
                Transport.send(message);
                Log.d("MAIL", "Mail sent");
                onPostExecute();
            }
            catch (MessagingException mex) {
                mex.printStackTrace();
            }
        }
        protected void onPreExecute() {}
        protected void onPostExecute() {
            // Need run function to bypass rule blocking UI change outside of original thread
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    submitSuccess();
                }
            });
        }
    }

    void submitSuccess() {
        statusText.setText("Form Submitted");
        thankyouText.setText("Thank you for being a Creekwatcher!");
        greencheck.setImageResource(R.drawable.greencheck);
        progressBar.setVisibility(View.INVISIBLE);
        thankyouText.setVisibility(View.VISIBLE);
        greencheck.setVisibility(View.VISIBLE);
    }

    void submitFailure() {
        statusText.setText("Submission Failed");
        thankyouText.setText("Please try again or try submission later");
        greencheck.setImageResource(R.drawable.submitx);
        progressBar.setVisibility(View.INVISIBLE);
        greencheck.setVisibility(View.VISIBLE);
        resubmitButton.setVisibility(View.VISIBLE);
    }

    void setListener(Button button, Intent intent) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });
    }
}


