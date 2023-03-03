package com.dylanlarrabee.watersurveyapp;

import java.io.Serializable;

public class SurveyData implements Serializable {
        static final int maxEst = 6, maxMeas = 5, maxComm = 1;
        static String userName = "", userSite = "";
        static int curEst = 0, curMeas = 0, curComm = 0;
        static int tideEst=-1,weathEst=-1,windSpeed=-1, waterSurf =-1,windDir=-1,rainfall=-1,waterDepth=-1,sampleDist=-1;
        static int[] airTemp = {-1,-1},waterTemp={-1,-1},secchiDepth={-1,-1};
        static int estArr[] = {tideEst,waterSurf,weathEst,windSpeed,windDir,rainfall};


        static boolean bottomedOut;

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
}
