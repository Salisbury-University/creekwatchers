package com.dylanlarrabee.watersurveyapp;

import static com.dylanlarrabee.watersurveyapp.R.id;
import static com.dylanlarrabee.watersurveyapp.R.layout;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.DecimalFormat;
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

import android.util.Log;

import java.io.FileWriter;
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
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Calendar;
import java.util.Date;
import java.io.File;
import java.io.IOException;


//white text when button is red
//submit text yellow/white
// back button when editing pages
//

public class ReviewPage extends AppCompatActivity {

    private Intent toHome, toSubmit, toTide, toWaterSurface, toWeather, toWindSpeed, toWindDirection, toRainfall, toWaterDepth, toSampleDist, toAirTemp, toWaterTemp, toSecchiDepth;
    private Button homeButton, submitButton, tideButton, watersurfaceButton, weatherButton, windspeedButton, winddirectButton, rainfallButton, waterDepthButton, sampleDistButton, airTempButton, waterTempButton, secchiDepthButton;
    private TextView tideTxt, watersurfaceTxt, weatherTxt, windspeedTxt, winddirectTxt, rainfallTxt, waterDepthTxt, sampleDistTxt, airTempTxt, waterTempTxt, secchiDepthTxt;

    private ImageView tideBG, watersurfaceBG, weatherBG, windspeedBG, winddirectBG, rainfallBG, waterDepthBG, sampleDistBG, airTempBG, waterTempBG, secchiDepthBG;
    private static DecimalFormat REAL_FORMATTER = new DecimalFormat("0.###");

    private Boolean canSubmit;

   //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.review_page);

        canSubmit = true;

        setupButtons();
        setupIntents();
        setupListeners();


    }

    void setupButtons()
    {
        homeButton = (Button) findViewById(id.header);
        submitButton = (Button) findViewById(id.submitbtn);
        tideButton = (Button) findViewById(id.tidebtn);
        watersurfaceButton = (Button) findViewById(id.waterbtn);
        weatherButton = (Button) findViewById(id.weatherbtn);
        windspeedButton = (Button) findViewById(id.windspeedbtn);
        winddirectButton = (Button) findViewById(id.winddirectbtn);
        rainfallButton = (Button) findViewById(id.rainbtn);
        waterDepthButton = (Button) findViewById(id.waterdepthbtn);
        sampleDistButton = (Button) findViewById(id.sampledistbtn);
        airTempButton = (Button) findViewById(id.airtempbtn);
        waterTempButton = (Button) findViewById(id.watertempbtn);
        secchiDepthButton = (Button) findViewById(id.secchidepthbtn);

        tideTxt = (TextView) findViewById(id.tidetxt);
        watersurfaceTxt = (TextView) findViewById(id.watertxt);
        weatherTxt = (TextView) findViewById(id.weathertxt);
        windspeedTxt = (TextView) findViewById(id.windspeedtxt);
        winddirectTxt = (TextView) findViewById(id.winddirecttxt);
        rainfallTxt = (TextView) findViewById(id.rainfalltxt);
        waterDepthTxt = (TextView) findViewById(id.waterdepthtxt);
        sampleDistTxt = (TextView) findViewById(id.sampledisttxt);
        airTempTxt = (TextView) findViewById(id.airtemptxt);
        waterTempTxt = (TextView) findViewById(id.watertemptxt);
        secchiDepthTxt = (TextView) findViewById(id.secchidepthtxt);

        tideBG = (ImageView) findViewById(id.tideBG);
        watersurfaceBG = (ImageView) findViewById(id.waterBG);
        weatherBG = (ImageView) findViewById(id.weatherBG);
        windspeedBG = (ImageView) findViewById(id.windspeedBG);
        winddirectBG = (ImageView) findViewById(id.winddirectBG);
        rainfallBG = (ImageView) findViewById(id.rainfallBG);
        waterDepthBG = (ImageView) findViewById(id.waterdepthBG);
        sampleDistBG = (ImageView) findViewById(id.sampledistBG);
        airTempBG = (ImageView) findViewById(id.airtempBG);
        waterTempBG = (ImageView) findViewById(id.watertempBG);
        secchiDepthBG = (ImageView) findViewById(id.secchidepthBG);

        String textBox = "";

        switch (SurveyData.tideEst){
            case -1:
                tideBG.setImageResource(R.color.maroon);
                textBox = "Tap to set";
                canSubmit = false;
                break;
            case 0:
                textBox = "High";
                break;
            case 1:
                textBox = "Mid Falling";
                break;
            case 2:
                textBox = "Low";
                break;
            case 3:
                textBox = "Mid Flooding";
                break;
            case 4:
                textBox = "Nontidal";
                break;
        }
        tideTxt.setText(textBox);


        switch (SurveyData.waterSurf){
            case -1:
                watersurfaceBG.setImageResource(R.color.maroon);
                textBox = "Tap to set";
                canSubmit = false;
                break;
            case 0:
                textBox = "Calm";
                break;
            case 1:
                textBox = "Ripples";
                break;
            case 2:
                textBox = "Choppy";
                break;
            case 3:
                textBox = "Heavy Chop";
                break;
        }
        watersurfaceTxt.setText(textBox);


        switch (SurveyData.weathEst){
            case -1:
                weatherBG.setImageResource(R.color.maroon);
                textBox = "Tap to set";
                canSubmit = false;
                break;
            case 0:
                textBox = "Clear";
                break;
            case 1:
                textBox = "Cloudy";
                break;
            case 2:
                textBox = "Overcast";
                break;
            case 3:
                textBox = "Light Rain";
                break;
            case 4:
                textBox = "Rainy";
                break;
            case 5:
                textBox = "Heavy Rain";
                break;
            case 6:
                textBox = "Foggy";
                break;
            case 7:
                textBox = "Snow";
                break;
        }
        weatherTxt.setText(textBox);


        switch (SurveyData.windSpeed){
            case -1:
                windspeedBG.setImageResource(R.color.maroon);
                textBox = "Tap to set";
                canSubmit = false;
                break;
            case 0:
                textBox = "Calm";
                break;
            case 1:
                textBox = "Light";
                break;
            case 2:
                textBox = "Medium";
                break;
            case 3:
                textBox = "Heavy";
                break;
        }
        windspeedTxt.setText(textBox);


        switch (SurveyData.windDir){
            case -1:
                winddirectBG.setImageResource(R.color.maroon);
                textBox = "Tap to set";
                canSubmit = false;
                break;
            case 0:
                textBox = "N";
                break;
            case 1:
                textBox = "NE";
                break;
            case 2:
                textBox = "E";
                break;
            case 3:
                textBox = "SE";
                break;
            case 4:
                textBox = "S";
                break;
            case 5:
                textBox = "SW";
                break;
            case 6:
                textBox = "W";
                break;
            case 7:
                textBox = "NW";
                break;
        }
        winddirectTxt.setText(textBox);


        switch (SurveyData.rainfall){
            case -1:
                rainfallBG.setImageResource(R.color.maroon);
                textBox = "Tap to set";
                canSubmit = false;
                break;
            case 0:
                textBox = "Storm";
                break;
            case 1:
                textBox = "Heavy";
                break;
            case 2:
                textBox = "Moderate";
                break;
            case 3:
                textBox = "Light";
                break;
            case 4:
                textBox = "Trace";
                break;
            case 5:
                textBox = "None";
                break;
        }
        rainfallTxt.setText(textBox);


        if(SurveyData.waterDepth[0] <=0) {
            waterDepthBG.setImageResource(R.color.maroon);
            waterDepthTxt.setText("Tap to set");
            canSubmit = false;
        } else {
            waterDepthTxt.setText(REAL_FORMATTER.format(SurveyData.waterDepth[0]));
        }

        if(SurveyData.sampleDist[0] <=0) {
            sampleDistBG.setImageResource(R.color.maroon);
            sampleDistTxt.setText("Tap to set");
            canSubmit = false;
        } else {
            sampleDistTxt.setText(REAL_FORMATTER.format(SurveyData.sampleDist[0]));
        }

        if(SurveyData.airTemp[0] <=0 || SurveyData.airTemp[1] <=0 ) {
            airTempBG.setImageResource(R.color.maroon);
            airTempTxt.setText("Tap to set");
            canSubmit = false;
        }else {
            airTempTxt.setText(REAL_FORMATTER.format(SurveyData.airTemp[0]) + ", " + REAL_FORMATTER.format(SurveyData.airTemp[1]) );
        }

        if(SurveyData.waterTemp[0] <=0 || SurveyData.waterTemp[1] <=0 ) {
            waterTempBG.setImageResource(R.color.maroon);
            waterTempTxt.setText("Tap to set");
            canSubmit = false;
        }else {
            waterTempTxt.setText(REAL_FORMATTER.format(SurveyData.waterTemp[0]) + ", " + REAL_FORMATTER.format(SurveyData.waterTemp[1]) );
            waterTempTxt.setText(""+ SurveyData.waterTemp[0]);

        }

        if(SurveyData.secchiDepth[0] <=0 || SurveyData.secchiDepth[1] <=0 ) {
            secchiDepthBG.setImageResource(R.color.maroon);
            secchiDepthTxt.setText("Tap to set");
            canSubmit = false;
        }else {
            secchiDepthTxt.setText(REAL_FORMATTER.format(SurveyData.secchiDepth[0]) + ", " + REAL_FORMATTER.format(SurveyData.secchiDepth[1]) );
        }

        if(canSubmit == false){
            submitButton.setBackgroundColor(Color.GRAY);
            submitButton.setEnabled(false);
        }

    }

    void setupIntents()
    {
        toHome = new Intent(this,HomePage.class);
        toSubmit = new Intent(this,HomePage.class); //Needs update later
        toWeather = new Intent(this, EstWeather.class);
        toTide = new Intent(this, EstTide.class);
        toWaterSurface = new Intent(this, EstWaterSurface.class);
        toWindSpeed = new Intent(this, EstWindSpeed.class);
        toWindDirection = new Intent(this, EstWindDirection.class);
        toRainfall = new Intent(this, EstRainfall.class);
        toWaterDepth = new Intent(this, WaterDepth.class);
        toSampleDist = new Intent(this, SamplePage.class);
        toAirTemp = new Intent(this, AirTemp.class);
        toWaterTemp = new Intent(this, WaterTemp.class);
        toSecchiDepth = new Intent(this, SecchiDepth.class);
    }

    void setupListeners()
    {
        setListener(homeButton, toHome);
        setListener(submitButton, toSubmit); //Needs update later
        setListener(tideButton, toTide);
        setListener(watersurfaceButton, toWaterSurface);
        setListener(weatherButton, toWeather);
        setListener(windspeedButton, toWindSpeed);
        setListener(winddirectButton, toWindDirection);
        setListener(rainfallButton, toRainfall);
        setListener(airTempButton, toAirTemp);
        setListener(sampleDistButton, toSampleDist);
        setListener(waterDepthButton, toWaterDepth);
        setListener(airTempButton, toAirTemp);
        setListener(waterTempButton, toWaterTemp);
        setListener(secchiDepthButton, toSecchiDepth);
    }

    void setListener(Button button, Intent intent)
    {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
                try {
                    sendEmail();
                } catch (MessagingException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }


    void sendEmail() throws MessagingException {
        Log.d("MAIL", "sendMail() called");

        //sending to chris's email so we don't spam the official one.
        //official email: creekwatchers@salisbury.edu
        String recipient = "put target email here";

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

}