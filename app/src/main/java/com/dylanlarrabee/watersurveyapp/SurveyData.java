package com.dylanlarrabee.watersurveyapp;

import java.io.Serializable;

public class SurveyData implements Serializable {
        int tideEst=2,weathEst=-1,windSpeed=-1, waterSurf =-1,windDir=-1,rainfall=-1,waterDepth=-1,sampleDist=-1;
        int[] airTemp = {-1,-1},waterTemp={-1,-1},secchiDepth={-1,-1};
        boolean bottomedOut;

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

}
