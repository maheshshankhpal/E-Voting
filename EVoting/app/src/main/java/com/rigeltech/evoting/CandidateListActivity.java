package com.rigeltech.evoting;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CandidateListActivity extends BaseAppCompatActivity implements CandidateListAdapter.OnClickListener{

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setContentView(R.layout.activity_candidate_list);
        ButterKnife.bind(this);
        setTitle(R.string.title_activity_cand_list);

        loadList();

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

    void loadList(){
        showProgressDialog();
        List<CandidateModel> candidateModelList= new ArrayList<>();
        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset(getAssets().open("candidate_list.json")));
            JSONArray jsonArray = obj.getJSONArray("Candidate");

            for (int headerIndex = 0; headerIndex < jsonArray.length(); headerIndex++) {
                CandidateModel candidateModel=new CandidateModel();
                JSONObject jsonCandidate = jsonArray.getJSONObject(headerIndex);
                candidateModel.setName(jsonCandidate.getString("name"));
                candidateModel.setSymbol(jsonCandidate.getString("symbol"));
                candidateModel.setSrno(jsonCandidate.getString("Srno"));

                candidateModelList.add(candidateModel);
            }

            showRecyclerView();
            CandidateListAdapter candidateListAdapter= new CandidateListAdapter(candidateModelList,this);
            recyclerView.setAdapter(candidateListAdapter);

            hideProgressDialog();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void showRecyclerView() {
        try {

            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setNestedScrollingEnabled(false);

            recyclerView.setVisibility(View.VISIBLE);


        } catch (Exception e) {

        }
    }

    public String loadJSONFromAsset(InputStream inputStream) {
        String json = null;
        try {

            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, "UTF-8");
        } catch (Exception ex) {

            return null;
        }
        return json;
    }

    @Override
    public void onItemClick(CandidateModel item) {
        showSnackBar(item.getName(),(LinearLayout) findViewById(R.id.root_layout));
    }
}
