package com.rigeltech.evoting.base;


import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.rigeltech.evoting.Interface.IBaseAppCompant;
import com.rigeltech.evoting.R;


/**
 * Created by DM365 on 30/08/2017.
 */

public class BaseFragment extends android.support.v4.app.Fragment implements IBaseAppCompant {

    public RecyclerView recyclerView;
    public RelativeLayout relLayEmptyListMessage;

    public void setTitle(String title) {
        try {
            AppCompatActivity activity = (AppCompatActivity) getActivity();
            if (activity != null) {
                ActionBar actionBar = activity.getSupportActionBar();
                if (actionBar != null) {

                    try {
                        actionBar.setTitle(title);
                    } catch (Exception e) {
                        actionBar.setTitle(getString(R.string.app_name));
                    }
                }
            }
        } catch (Exception e) {

        }
    }

    //region setTitle
    public void setTitle(android.support.v4.app.Fragment fragment) {
        try {
            AppCompatActivity activity = (AppCompatActivity) getActivity();
            if (activity != null) {
                ActionBar actionBar = activity.getSupportActionBar();
                if (actionBar != null) {

                    try {
                        int resId = getResources().getIdentifier(fragment.getClass().getSimpleName(), "string", getContext().getPackageName());
                        actionBar.setTitle(getString(resId));
                    } catch (Exception e) {
                        actionBar.setTitle(getString(R.string.app_name));
                    }
                }
            }
        } catch (Exception e) {

        }
    }
    //endregion

    public void initilizeRecycleView() {
        View view = getView();
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
    }

    public void showRecyclerView() {
        try {
            View view = getView();
            if (recyclerView == null)
                recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);

            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setNestedScrollingEnabled(false);

            recyclerView.setVisibility(View.VISIBLE);

            relLayEmptyListMessage = (RelativeLayout) view.findViewById(R.id.relLayEmptyListMessage);
            relLayEmptyListMessage.setVisibility(View.GONE);
        } catch (Exception e) {

        }
    }

    public void showListEmptyMessage() {
        try {
            View view = getView();
            recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
            recyclerView.setVisibility(View.GONE);

            relLayEmptyListMessage = (RelativeLayout) view.findViewById(R.id.relLayEmptyListMessage);
            relLayEmptyListMessage.setVisibility(View.VISIBLE);
        } catch (Exception e) {

        }
    }

    public ProgressDialog progressDialog;

    @Override
    public void showProgressDialog() {
        try {
            progressDialog = new ProgressDialog(getContext());
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
        try {
            if (!TextUtils.isEmpty(message)) {
                int resourceId = getResources().getIdentifier(message, "string", getContext().getPackageName());
                if (resourceId > 0)
                    Toast.makeText(getContext(), resourceId, Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        ActionBar actionBar = activity.getSupportActionBar();
        //setTitle(this);
        if (getView() == null) {
            return;
        }

        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                    return exit();
                    // handle back button's click listener

                }
                return false;
            }
        });
    }

    public boolean exit() {
        AlertDialog.Builder alertbox = new AlertDialog.Builder(getActivity());

        alertbox.setTitle("Do You Want To Exit ?");
        alertbox.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                getActivity().moveTaskToBack(true);
                getActivity().finish();
            }
        });

        alertbox.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                // Nothing will be happened when clicked on no button
                // of Dialog
            }
        });

        alertbox.show();

        return true;
    }

    public void sendSMS(String mobileNo, String message) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (getContext().checkSelfPermission(Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
                showMessage("");
            } else {
                sendsms(mobileNo, message);
            }
        } else
            sendsms(mobileNo, message);
    }



    private boolean sendsms(String mobileNo, String message) {
        try {
            if(!mobileNo.isEmpty()) {
                SmsManager sms = SmsManager.getDefault();
                sms.sendTextMessage(mobileNo, null, message, null, null);
            }

        }
        catch (Exception e)
        {
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private static final int PERMISSION_REQUEST = 100;

    public void call(String mobileNo)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (getContext().checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                showMessage("");
            } else {
                makeCall(mobileNo);
            }
        }
        makeCall(mobileNo);
    }

    @SuppressLint("MissingPermission")
    private void makeCall(String mobileNo){
        Intent intent = new Intent(Intent.ACTION_CALL);

        intent.setData(Uri.parse("tel:" + mobileNo));
        getContext().startActivity(intent);
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
