package com.dylanlarrabee.watersurveyapp;

import android.content.Context;
import android.content.Intent;

public class BasicCommands {

    Intent setIntent(Context from, Class to)
    {
        Intent intent = new Intent(from,to);

        return intent;

    }
}
