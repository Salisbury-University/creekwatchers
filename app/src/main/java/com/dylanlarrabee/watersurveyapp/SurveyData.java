package com.dylanlarrabee.watersurveyapp;

import static android.content.Context.MODE_PRIVATE;

import android.app.Activity;
import android.content.SharedPreferences;


public class
SurveyData  {
        static String myID = "";
        static String formName = "";
        static final int maxEst = 6, maxMeas = 5, maxComm = 1, numMeas = 2;
        static String userName = "", userSite = "";
        static int curEst = 0, curMeas = 0, curComm = 0;
        static int tideEst=-1,weathEst=-1,windSpeed=-1, waterSurf =-1,windDir=-1,rainfall=-1,waterDepth=-1,sampleDist=-1;
        static int[] airTemp = {-1,-1},waterTemp={-1,-1},secchiDepth={-1,-1};
        static int estArr[] = {tideEst,waterSurf,weathEst,windSpeed,windDir,rainfall};
        static boolean bottomedOut, newForm = false,firstEntry = true;

         String myuserName = "", myuserSite = "";
         int mycurEst = 0, mycurMeas = 0, mycurComm = 0;
         int mytideEst=-1,myweathEst=-1,mywindSpeed=-1, mywaterSurf =-1,mywindDir=-1,myrainfall=-1,mywaterDepth=-1,mysampleDist=-1;
         int[] myairTemp = {-1,-1},mywaterTemp={-1,-1},mysecchiDepth={-1,-1};
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
        }
         public static void SaveData(SharedPreferences sp, String formID)
        {
                SharedPreferences.Editor spEdit = sp.edit();
                spEdit.putString(formID + "name",userName);
                spEdit.putString(formID+"site",userSite);
                spEdit.putInt(formID+"tide",tideEst);
                spEdit.putInt(formID+"surf",waterSurf);
                spEdit.putInt(formID+"weath",weathEst);
                spEdit.putInt(formID+"wspeed",windSpeed);
                spEdit.putInt(formID+"wdir",windDir);
                spEdit.putInt(formID+"curest",curEst);
                spEdit.putInt(formID+"curmeas",curMeas);
                spEdit.putInt(formID+"curcomm",curComm);
                //spEdit.putInt(formID+"rain",rainfall);
                spEdit.commit();
        }
        public static void RetrieveData(SharedPreferences sp, String formID)
        {
                userName = sp.getString(formID+"name","Null Name");
                userSite = sp.getString(formID+"site","Null Site");
                tideEst = sp.getInt(formID+"tide",-1);
                waterSurf = sp.getInt(formID+"surf",-1);
                weathEst = sp.getInt(formID+"weath",-1);
                windSpeed = sp.getInt(formID+"wspeed",-1);
                windDir = sp.getInt(formID+"wdir",-1);
                curEst = sp.getInt(formID+"curest",0);
                curMeas = sp.getInt(formID+"curmeas",0);
                curComm = sp.getInt(formID+"curcomm",0);
                //rainfall = sp.getInt(formID+"rain",-1);
        }
}
