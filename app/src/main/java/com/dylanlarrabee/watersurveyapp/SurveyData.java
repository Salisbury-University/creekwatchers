package com.dylanlarrabee.watersurveyapp;

import static android.content.Context.MODE_PRIVATE;

import android.app.Activity;
import android.content.SharedPreferences;

import com.google.gson.Gson;


public class
SurveyData  {
        static String myID = "";
        static String formName = "";
        static final int maxEst = 6, maxMeas = 5, maxComm = 1, numMeas = 2;
        static String userName = "", userSite = "", comments = "";
        static int curEst = 0, curMeas = 0, curComm = 0, formIDnum = 0;
        static int tideEst=-1,weathEst=-1,windSpeed=-1, waterSurf =-1,windDir=-1,rainfall=-1;
        static double[] airTemp = {-1,-1},waterTemp={-1,-1},secchiDepth={-1,-1};
        static double[] waterDepth = {-1}, sampleDist = {-1};
        static int estArr[] = {tideEst,waterSurf,weathEst,windSpeed,windDir,rainfall};
        static boolean bottomedOut, newForm = false,firstEntry = true;

         String myuserName = "", myuserSite = "", myUserID, myComm = "";
         int mycurEst = 0, mycurMeas = 0, mycurComm = 0, myformIDnum = 0;
         int mytideEst=-1,myweathEst=-1,mywindSpeed=-1, mywaterSurf =-1,mywindDir=-1,myrainfall=-1;
         double [] mywaterDepth= {-1},mysampleDist= {-1};
         double[] myairTemp = {-1,-1},mywaterTemp={-1,-1},mysecchiDepth={-1,-1};
         int myestArr[] = {mytideEst,mywaterSurf,myweathEst,mywindSpeed,mywindDir,myrainfall};
         boolean mybottomedOut;


        public SurveyData()
        {
//                Shouldn't you initialize the variables in here instead?
//                tideEst = -1;
//                weathEst = -1;
//                windSpeed = -1;
//                waterSurf = -1;
//                windDir = -1;
//                rainfall = -1;
//                waterDepth = -1;
//                sampleDist = -1;
//                airTemp = new int[]{-1, -1};
//                waterTemp = new int[]{-1, -1};
//                secchiDepth = new int[]{-1, -1};
        }
        static public void resetData()
        {
                userName = "";
                userSite = "";
                curEst = 0;
                curComm = 0;
                curMeas = 0;
                for(int i = 0; i < maxEst; i++)
                {
                        estArr[i] = -1;
                }
                for(int i = 0; i < numMeas; i ++)
                {
                        airTemp[i] = -1;
                        waterTemp[i] = -1;
                        secchiDepth[i] = -1;
                }
                bottomedOut = false;

        }
        static public void updateCompleted()
        {
                int setVal = 0;
                if(tideEst >= 0)
                        setVal++;
                if (waterSurf >= 0)
                        setVal++;
                if(weathEst >= 0)
                        setVal++;
                if(windSpeed >= 0)
                        setVal++;
                if(windDir >= 0)
                        setVal++;
                if(rainfall >= 0)
                        setVal++;

                curEst = setVal;
                setVal = 0;
            if(airTemp[0] > 0 && airTemp[1] > 0)
            {
                setVal++;
            }
            if(secchiDepth[0] > 0 && secchiDepth[1] > 0)
            {
                setVal++;
            }
            if(waterTemp[0] > 0 && waterTemp[1] > 0)
            {
                setVal++;
            }
            if(sampleDist[0] > 0)
            {
                setVal++;
            }
            if(waterDepth[0] > 0)
            {
                setVal++;
            }
            curMeas = setVal;


        }
         public static SurveyData SaveData()
        {
                SurveyData mysd = new SurveyData();

                mysd.myuserName = SurveyData.userName;
                mysd.myuserSite = SurveyData.userSite;
                mysd.myUserID = SurveyData.myID;
                mysd.mycurEst = SurveyData.curEst;
                mysd.mycurMeas = SurveyData.curMeas;
                mysd.mycurComm = SurveyData.curComm;
                mysd.mybottomedOut = SurveyData.bottomedOut;
                mysd.mytideEst = SurveyData.tideEst;
                mysd.mywaterSurf = SurveyData.waterSurf;
                mysd.myweathEst = SurveyData.weathEst;
                mysd.mywindDir = SurveyData.windDir;
                mysd.mywindSpeed = SurveyData.windSpeed;
                mysd.myrainfall = SurveyData.rainfall;
                mysd.mywaterTemp[0] = SurveyData.waterTemp[0];
                mysd.mywaterTemp[1] = SurveyData.waterTemp[1];
                mysd.myairTemp[0] = SurveyData.airTemp[0];
                mysd.myairTemp[1] = SurveyData.airTemp[1];
                mysd.mysecchiDepth[0] = SurveyData.secchiDepth[0];
                mysd.mysecchiDepth[1] = SurveyData.secchiDepth[1];
                mysd.mywaterDepth[0] = SurveyData.waterDepth[0];
                mysd.mysampleDist[0] = SurveyData.sampleDist[0];
                mysd.myComm = SurveyData.comments;
                mysd.myformIDnum = SurveyData.formIDnum;
                return mysd;
        }
        public static void RetrieveData(SurveyData mysd)
        {

               SurveyData.userName = mysd.myuserName;
               SurveyData.userSite = mysd.myuserSite;
               SurveyData.myID = mysd.myUserID;
               SurveyData.curEst = mysd.mycurEst;
               SurveyData.curMeas = mysd.mycurMeas;
               SurveyData.curComm = mysd.mycurComm;
               SurveyData.bottomedOut = mysd.mybottomedOut;
                SurveyData.tideEst = mysd.mytideEst;
                SurveyData.waterSurf = mysd.mywaterSurf;
                SurveyData.weathEst = mysd.myweathEst;
                SurveyData.windDir = mysd.mywindDir;
                SurveyData.windSpeed = mysd.mywindSpeed;
                SurveyData.rainfall = mysd.myrainfall;
            SurveyData.waterTemp[0] = mysd.mywaterTemp[0];
            SurveyData.waterTemp[1] = mysd.mywaterTemp[1];
            SurveyData.airTemp[0] = mysd.myairTemp[0];
            SurveyData.airTemp[1] = mysd.myairTemp[1];
            SurveyData.secchiDepth[0] = mysd.mysecchiDepth[0];
            SurveyData.secchiDepth[1] = mysd.mysecchiDepth[1];
            SurveyData.sampleDist[0] = mysd.mysampleDist[0];
            SurveyData.waterDepth[0] = mysd.mywaterDepth[0];
            SurveyData.comments = mysd.myComm;
            SurveyData.formIDnum = mysd.myformIDnum;
        }
}
