package com.dylanlarrabee.watersurveyapp;

import android.content.Context;
import android.os.AsyncTask;
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
import javax.mail.internet.MimeMessage;


public class SendMail extends AsyncTask<MimeMessage, Void, Void> {

    protected Void doInBackground(MimeMessage... message) {
        sendMail(message[0]);
        return null;
    }
    public void sendMail(MimeMessage message) {
        try {
            Log.d("MAIL", "Sending email now...");
            Transport.send(message);
            Log.d("MAIL", "Mail sent");
        }
        catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }

    protected void onPreExecute() {
    }
    protected void onPostExecute() {
    }

}