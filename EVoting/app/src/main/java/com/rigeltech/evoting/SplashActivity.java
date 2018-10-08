package com.rigeltech.evoting;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.rigeltech.evoting.view.activity.LoginActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash);

        (new Thread(){
            @Override
            public void run(){
                try{
                    sleep(3000);
                    Intent intent=new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                    finish();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
