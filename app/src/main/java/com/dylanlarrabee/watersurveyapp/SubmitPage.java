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

    //official email: creekwatchers@salisbury.edu
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
        boolean success = false;
        statusText.setText("Submitting...");
        progressBar.setVisibility(View.VISIBLE);
        thankyouText.setVisibility(View.INVISIBLE);
        try {
            sendEmail();
        } catch (MessagingException e) {
            resubmitButton.setVisibility(View.VISIBLE);
            greencheck.setImageResource(R.drawable.submitx);
            greencheck.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.INVISIBLE);
            statusText.setText("Submission Failed.");
            thankyouText.setText("Try again?");
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
        Date currentTime = Calendar.getInstance().getTime();
        // Set subject
        String emailSubject = SurveyData.userName + " " + SurveyData.userSite + " " + currentTime;
        message.setSubject(emailSubject);
        Log.d("MAIL", "Subject: " + emailSubject );

        // Set body
        String emailBody =
            "Name: " + SurveyData.userName + "\n" +
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
            SurveyData.userName + ", " +
            SurveyData.userSite + ", " +
            SurveyData.tideEst + ", " +
            SurveyData.weathEst + ", " +
            SurveyData.waterSurf + ", " +
            SurveyData.windSpeed+ ", " +
            SurveyData.windDir+ ", " +
            SurveyData.rainfall+ ", " +
            SurveyData.waterDepth[0] + ", " +
            SurveyData.sampleDist[0]+ ", " +
            SurveyData.airTemp[0]+ ", " +
            SurveyData.airTemp[1] + ", " +
            SurveyData.waterTemp[0]+ ", " +
            SurveyData.waterTemp[1]+ ", " +
            SurveyData.secchiDepth[0]+ ", " +
            SurveyData.secchiDepth[1];

        String filename = "survey.txt";
        File directory = getDir("surveys", Context.MODE_PRIVATE);
        File myFile = new File(directory, filename);
        try {
            FileWriter fr = new FileWriter(myFile, false);
            fr.write(csvText);
            fr.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Add file to email
        BodyPart messageBodyPart2 = new MimeBodyPart();
        DataSource source = new FileDataSource(myFile);
        messageBodyPart2.setDataHandler(new DataHandler(source));
        messageBodyPart2.setFileName(filename);

        // Combine text body and file in email
        Multipart multipartObject = new MimeMultipart();
        multipartObject.addBodyPart(messageBodyPart1);
        multipartObject.addBodyPart(messageBodyPart2);
        message.setContent(multipartObject);

        // Must use Async SendMail class to send the email
        SendMail mail = new SendMail();
        mail.execute(message);
    }

    void setListener(Button button, Intent intent) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);

            }
        });
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
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    submitSuccess();
                }
            });
        }
    }
    void submitSuccess(){

        progressBar.setVisibility(View.INVISIBLE);
        thankyouText.setText("Thank you for being a Creekwatcher!");
        thankyouText.setVisibility(View.VISIBLE);
        greencheck.setImageResource(R.drawable.greencheck);
        greencheck.setVisibility(View.VISIBLE);
        statusText.setText("Form Submitted.");
    }
}


