package com.dylanlarrabee.watersurveyapp;

import static com.dylanlarrabee.watersurveyapp.R.id;
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
import androidx.appcompat.app.AppCompatActivity;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class SubmitPage extends SaveFormAct {

  private final String RECIPIENT = "chrismorse301@gmail.com";
  //private final String RECIPIENT = "creekwatchers@salisbury.edu";
    //official email: creekwatchers@salisbury.edu

    private TextView statusText;
    private TextView thankyouText;
    private ImageView greencheck;
    private ProgressBar progressBar;
    private Button resubmitButton;
    private String date;
    private Intent toRestart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.submit_page);
        toRestart = new Intent(this, FormArchive.class);

        setupViews();
        attemptSubmit();
    }

    void setupViews() {
        statusText = (TextView) findViewById(id.statusText);
        thankyouText = (TextView) findViewById(id.thankyouText);
        progressBar = (ProgressBar) findViewById(id.progressBar);
        resubmitButton = (Button) findViewById(id.submitButton);
        greencheck = (ImageView) findViewById(id.greencheck);

        resubmitButton.setOnClickListener(v -> attemptSubmit());
    }

    void attemptSubmit() {
        statusText.setText("Submitting...");
        progressBar.setVisibility(View.VISIBLE);
        greencheck.setVisibility(View.INVISIBLE);
        resubmitButton.setVisibility(View.INVISIBLE);
        thankyouText.setVisibility(View.INVISIBLE);

        try {
            // Setup email client
            Session session = setupEmailClient();
            // Create email message
            MimeMessage message = makeEmail(session);
            // use Asynchronous SendMail class to send the email
            SendMail mail = new SendMail();
            mail.execute(message);

        } catch (Exception e) {
            submitFailure();
        }
    }

    void submitSuccess() {
        statusText.setText("Form Submitted");
        thankyouText.setText("Thank you for being a Creekwatcher!");
        greencheck.setImageResource(R.drawable.greencheck);
        progressBar.setVisibility(View.INVISIBLE);
        thankyouText.setVisibility(View.VISIBLE);
        greencheck.setVisibility(View.VISIBLE);

        resubmitButton.setText("Home");
        resubmitButton.setOnClickListener(v -> startActivity(toRestart));
        resubmitButton.setVisibility(View.VISIBLE);

    }

    void submitFailure() {
        statusText.setText("Submission Failed");
        thankyouText.setText("Please try again or try submission later");
        greencheck.setImageResource(R.drawable.submitx);
        progressBar.setVisibility(View.INVISIBLE);
        greencheck.setVisibility(View.VISIBLE);
        resubmitButton.setVisibility(View.VISIBLE);
    }

    private Session setupEmailClient() {
        // Properties for running a gmail email session
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
            }});
        return session;
    }

    private MimeMessage makeEmail(Session session) throws MessagingException {
        // Create email object (MimeMessage)
        MimeMessage message = new MimeMessage(session);

        // Set sender
        message.setFrom(new InternetAddress(Config.EMAIL));

        // Set recipient
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(RECIPIENT));

        // Get date
        Calendar currentTime = Calendar.getInstance();
        int year = currentTime.get(Calendar.YEAR); year %= 100;
        date = (currentTime.get(Calendar.MONTH) + 1) + "/" + currentTime.get(Calendar.DAY_OF_MONTH) + "/" + year;

        // Set subject
        String subject = SurveyData.userSite + " " + SurveyData.userName + " " + date;
        message.setSubject(subject);
        Log.d("MAIL", "Subject: " + subject);

        // Set body
        BodyPart messageBodyPart1 = new MimeBodyPart();
        messageBodyPart1.setText(getEmailBody());
        Log.d("MAIL", "Body: " + getEmailBody());

        // Create CSV file
        String filename = SurveyData.userSite + " " + SurveyData.userName + " " + (currentTime.get(Calendar.MONTH)+1) + "-" + currentTime.get(Calendar.DAY_OF_MONTH) + "-" + year + ".csv";
        File directory = getDir("surveys", Context.MODE_PRIVATE);
        File myFile = new File(directory, filename);
        try {
            FileWriter fr = new FileWriter(myFile, false);
            fr.write(getCSV());
            fr.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Create file attachment
        BodyPart messageBodyPart2 = new MimeBodyPart();
        DataSource source = new FileDataSource(myFile);
        messageBodyPart2.setDataHandler(new DataHandler(source));
        messageBodyPart2.setFileName(filename);

        // Combine file attachment with email body
        Multipart multipartObject = new MimeMultipart();
        multipartObject.addBodyPart(messageBodyPart1);
        multipartObject.addBodyPart(messageBodyPart2);
        message.setContent(multipartObject);

        return message;
    }

    private String getEmailBody() {
        String email = "Volunteer: " + SurveyData.userName + "\n" +
                "Date: " + date + "\n" +
                "Site: " + SurveyData.userSite + "\n\n" +
                "Tide: " + SurveyData.tideEst + "\n" +
                "Weather: " + SurveyData.weathEst + "\n" +
                "Water Surface: " + SurveyData.waterSurf + "\n" +
                "Wind Speed: " + SurveyData.windSpeed + "\n" +
                "Wind Direction: " + SurveyData.windDir + "\n" +
                "Rainfall: " + SurveyData.rainfall + "\n" +
                "Water Depth: " + SurveyData.waterDepth[0] + "\n" +
                "Sample Distance: " + SurveyData.sampleDist[0] + "\n" +
                "Air Temp: " + SurveyData.airTemp[0] + ", " + SurveyData.airTemp[1] + "\n" +
                "Water Temp: " + SurveyData.waterTemp[0] + ", " + SurveyData.waterTemp[1] + "\n" +
                "Secchi Depth: " + SurveyData.secchiDepth[0] + ", " + SurveyData.secchiDepth[1] + "\n\n";
                if(SurveyData.comments != "")  email += "Comments: " + SurveyData.comments + "\n\n\n";
        return email;
    }

    private String getCSV() {
        return "\"Name\",\"Site\",\"Tide\",\"Weather\",\"WaterSurf\",\"WindSpeed\",\"WindDirect\",\"Rainfall\",\"WaterDepth1\",\"WaterDepth2\",\"AirTemp1\",\"AirTemp2\",\"WaterTemp1\",\"WaterTemp2\",\"SecchiDepth1\",\"SecchiDepth2\"\n\"" +
                SurveyData.userName + "\",\"" +
                SurveyData.userSite + "\",\"" +
                SurveyData.tideEst + "\",\"" +
                SurveyData.weathEst + "\",\"" +
                SurveyData.waterSurf + "\",\"" +
                SurveyData.windSpeed + "\",\"" +
                SurveyData.windDir + "\",\"" +
                SurveyData.rainfall + "\",\"" +
                SurveyData.waterDepth[0] + "\",\"" +
                SurveyData.sampleDist[0] + "\",\"" +
                SurveyData.airTemp[0] + "\",\"" +
                SurveyData.airTemp[1] + "\",\"" +
                SurveyData.waterTemp[0] + "\",\"" +
                SurveyData.waterTemp[1] + "\",\"" +
                SurveyData.secchiDepth[0] + "\",\"" +
                SurveyData.secchiDepth[1] + "\"";
    }

    private class SendMail extends AsyncTask<MimeMessage, Void, Void> {

        protected Void doInBackground(MimeMessage... message) {
            try {
                Log.d("MAIL", "Sending email...");
                Transport.send(message[0]);
                Log.d("MAIL", "Email sent.");
                onPostExecute();
            } catch (MessagingException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        submitFailure();
                    }
                });
            }
            return null;
        }
        // Need run function to bypass rule blocking UI change outside of original thread
        protected void onPostExecute() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    submitSuccess();
                }
            });
        }
    }

}