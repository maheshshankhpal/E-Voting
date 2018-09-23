package com.rigeltech.evoting;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;


/**
 * Created by Mahesh on 2017-10-25.
 */

public class BaseAppCompatActivity extends AppCompatActivity implements IBaseAppCompant {

    private static final int PERMISSION_REQUEST = 100;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    //region onTouchEvent
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) this.getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
            return super.onTouchEvent(event);
        } catch (Exception e) {
            return true;
        }
    }
    //endregion

    //region dispatchTouchEvent
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        onTouchEvent(ev);
        return super.dispatchTouchEvent(ev);
    }
    //endregion

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    //region ProgressDialog
    public ProgressDialog progressDialog;

    @Override
    public void showProgressDialog() {
        try {
            progressDialog = new ProgressDialog(this);
            progressDialog.setMax(100);
            // progressDialog.setMessage("");
            progressDialog.setMessage("Loading...");
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setCancelable(false);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
        } catch (Exception e) {
        }
    }

    @Override
    public void hideProgressDialog() {
        try {
            progressDialog.dismiss();
        } catch (Exception e) {
        }
    }

    @Override
    public void showMessage(String message) {
        int resourceId = getResources().getIdentifier(message, "string", getPackageName());
        if (resourceId > 0)
            Toast.makeText(getApplicationContext(), resourceId, Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }


    public void showSnackBar(String message, View view) {
        int resourceId = getResources().getIdentifier(message, "string", getPackageName());
        if (resourceId > 0) {
            Snackbar.make(view, resourceId, Snackbar.LENGTH_LONG)
                    .setAction("CLOSE", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                        }
                    })
                    .setActionTextColor(getResources().getColor(android.R.color.holo_red_light))
                    .show();
        }
        else
        {
            Snackbar.make(view, message, Snackbar.LENGTH_LONG)
                    .setAction("CLOSE", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                        }
                    })
                    .setActionTextColor(getResources().getColor(android.R.color.holo_red_light))
                    .show();
        }
    }

    public void sendSMS(String mobileNo, String message) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
                showMessage("");
            } else {
                sendsms(mobileNo, message);
            }
        } else
            sendsms(mobileNo, message);
    }

    private boolean sendsms(String mobileNo, String message) {
        try {
            if (!mobileNo.isEmpty()) {
                SmsManager sms = SmsManager.getDefault();
                sms.sendTextMessage(mobileNo, null, message, null, null);
            }

        } catch (Exception e) {
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public void requestPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{Manifest.permission.SEND_SMS, Manifest.permission.CALL_PHONE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST);
        }
    }

    public void requestSendSMSPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.SEND_SMS}, PERMISSION_REQUEST);
            }
        }
    }

    public void requestCallPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, PERMISSION_REQUEST);
            }
        }
    }

    public void call(String mobileNo) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                showMessage("");
            } else {
                makeCall(mobileNo);
            }
        }
        makeCall(mobileNo);
    }

    @SuppressLint("MissingPermission")
    private void makeCall(String mobileNo) {
        Intent intent = new Intent(Intent.ACTION_CALL);

        intent.setData(Uri.parse("tel:" + mobileNo));
        startActivity(intent);
    }

    public static boolean isExternalStorageReadOnly() {
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(extStorageState)) {
            return true;
        }
        return false;
    }

    public static boolean isExternalStorageAvailable() {
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(extStorageState)) {
            return true;
        }
        return false;
    }
}
