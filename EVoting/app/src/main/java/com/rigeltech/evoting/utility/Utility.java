package com.rigeltech.evoting.utility;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.rigeltech.evoting.base.BaseApplication;


/**
 * Created by komaltanajimahadik on 15/10/17.
 */

public class Utility {
    public static boolean checkConnection()
    {
        ConnectivityManager mConnectivityManager = (ConnectivityManager) BaseApplication.getAppContext().getSystemService(Context.CONNECTIVITY_SERVICE);
    /*   TelephonyManager telephonyManager = (TelephonyManager) c
               .getSystemService(Context.TELEPHONY_SERVICE);*/
       /*NetworkInfo ni = ((ConnectivityManager)c.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();*/
        NetworkInfo ni = mConnectivityManager.getActiveNetworkInfo();
        if (ni == null)
        {
            // There are no active networks.
            return false;
        }
        else
        {
            return true;
        }
    }
}
