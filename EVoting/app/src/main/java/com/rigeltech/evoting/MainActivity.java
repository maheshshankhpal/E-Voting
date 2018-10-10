package com.rigeltech.evoting;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.rigeltech.evoting.utility.SessionManager;
import com.rigeltech.evoting.view.activity.LoginActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseAppCompatActivity {

    private AdView mAdView;

    @BindView(R.id.candidate_list)
    CardView candidate_list;

    @BindView(R.id.vote)
    CardView vote;

    @BindView(R.id.result)
    CardView result;

    @BindView(R.id.more_info)
    CardView more_info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
       // setSupportActionBar();
        loadAdView();

    }

    void loadAdView()
    {
        mAdView = (AdView)findViewById(R.id.adView);


        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("C683C1441666BE0F79A6EBE6E25FE1F4")
                .addTestDevice("A2D9402027C0D65490FDDB53EAE26089")
                .build();

        mAdView.loadAd(adRequest);

        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
            }

            @Override
            public void onAdClosed() {
                Toast.makeText(getApplicationContext(), "Ad is closed!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                Toast.makeText(getApplicationContext(), "Ad failed to load! error code: " + errorCode, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdLeftApplication() {
                Toast.makeText(getApplicationContext(), "Ad left application!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdOpened() {
                super.onAdOpened();
            }
        });
    }

    @OnClick(R.id.candidate_list)
    public void onCandidateListClicked(){
        showSnackBar("Clicked",(LinearLayout) findViewById(R.id.root_layout));

        Intent intent=new Intent(getApplicationContext(), CandidateListActivity.class);
        intent.putExtra("call_from","candidate");
        startActivity(intent);
    }

    @OnClick(R.id.vote)
    public void onVoteClicked(){

        if(SessionManager.getString(getApplicationContext(), getString(R.string.vote)).equals("0")) {

            Intent intent = new Intent(getApplicationContext(), CandidateListActivity.class);
            intent.putExtra("call_from", "vote");
            startActivity(intent);
        }
        else{
            showSnackBar("Hey "+SessionManager.getString(getApplicationContext(),getString(R.string.profile_name))
                    +", Your vote has already been done. Thank You...",(LinearLayout) findViewById(R.id.root_layout));
        }
       // showSnackBar("voting_date",(LinearLayout) findViewById(R.id.root_layout));
    }

    @OnClick(R.id.result)
    public void onResultClicked(){
        showSnackBar("result_date",(LinearLayout) findViewById(R.id.root_layout));
    }

    @OnClick(R.id.more_info)
    public void onMoreInfoClicked(){
        showSnackBar("Clicked",(LinearLayout) findViewById(R.id.root_layout));
    }


    private void onLoad() {
        showProgressDialog();
    }
}
