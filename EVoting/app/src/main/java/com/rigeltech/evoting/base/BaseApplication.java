package com.rigeltech.evoting.base;

import android.app.Application;
import android.content.Context;


/**
 * Created by DM365 on 26/10/2017.
 */

public class BaseApplication extends Application {

    private static Context context;

    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getAppContext() {
        return context;
    }
}
