

package com.rigeltech.evoting;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.rigeltech.evoting.contract.IVotingContract;
import com.rigeltech.evoting.model.Result;
import com.rigeltech.evoting.model.voting.SubmitVoteRequestModel;
import com.rigeltech.evoting.model.voting.VotingResultModel;
import com.rigeltech.evoting.presenter.VotingPresenter;
import com.rigeltech.evoting.utility.SessionManager;

import java.io.InputStream;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CandidateListActivity extends BaseAppCompatActivity implements
        IVotingContract.IVotingView,
        CandidateListAdapter.OnClickListener{

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private AdView mAdView;

    String call_from="";

    IVotingContract.IVotingPresenter votingPresenter;

    public  static boolean isResult =false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setContentView(R.layout.activity_candidate_list);
        ButterKnife.bind(this);
        setTitle(R.string.title_activity_cand_list);

        call_from = getIntent().getStringExtra("call_from");

        votingPresenter = new VotingPresenter(this);

        getVotingResult();

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

    void loadList(List<VotingResultModel> votingResultResponseModelList){
      //  showProgressDialog();

        try {
            showRecyclerView();
            CandidateListAdapter candidateListAdapter= new CandidateListAdapter(votingResultResponseModelList,this);
            recyclerView.setAdapter(candidateListAdapter);
        } catch (Exception e){
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
    public void onItemClick(VotingResultModel item) {
        if(call_from.equals("vote")){
            showSnackBar("Vote",(LinearLayout) findViewById(R.id.root_layout));
            voteDialog(item);
        }
        else
        showSnackBar(item.getName(),(LinearLayout) findViewById(R.id.root_layout));
    }

    private void voteDialog(final VotingResultModel candidateModel){
        new AlertDialog.Builder(this)
                .setTitle("Vote Confirmation")
                .setMessage("Do you want to Vote to "+candidateModel.getName()+"?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        showProgressDialog();
                        SubmitVoteRequestModel submitVoteRequestModel = new SubmitVoteRequestModel();

                        submitVoteRequestModel.setUsername(SessionManager.getString(getApplicationContext(),getString(R.string.user_name)));
                        submitVoteRequestModel.setCand_id(candidateModel.getCandId());

                        votingPresenter.submitVote(submitVoteRequestModel);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .show();
    }

    void getVotingResult(){
        showProgressDialog();
        votingPresenter.GetVotingResult();
    }

    @Override
    public void onSubmitVoteSuccess(List<Result> result) {
        hideProgressDialog();
        SessionManager.putString(getApplicationContext(), getString(R.string.vote),"1");
        showSnackBar("Vote Submitted Successfully",(LinearLayout) findViewById(R.id.root_layout));
        finish();


    }

    @Override
    public void onGetVotingResult(List<VotingResultModel> votingResultResponseModel) {

        isResult = call_from.equals("result");
        loadList(votingResultResponseModel);

        hideProgressDialog();
    }

    @Override
    public void onFailure(String message) {
        hideProgressDialog();
    }
}
