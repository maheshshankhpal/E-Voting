package com.rigeltech.evoting.utility;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by komaltanajimahadik on 15/10/17.
 */

public class SessionManager {
    private static final String PREFS_NAME = "TMS";

    public static void putString(Context context, String key, String value) {
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static String getString(Context context, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(key,"");
    }
}