package com.rigeltech.evoting.interactor;

import com.rigeltech.evoting.Interface.ApiInterface;
import com.rigeltech.evoting.contract.IVotingContract;
import com.rigeltech.evoting.model.voting.SubmitVoteRequestModel;
import com.rigeltech.evoting.model.voting.SubmitVotingResponseModel;
import com.rigeltech.evoting.model.voting.VotingResultModel;
import com.rigeltech.evoting.model.voting.VotingResultResponseModel;
import com.rigeltech.evoting.rest.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Mahesh on 2018-10-14.
 */

public class VotingInteractor implements IVotingContract.IVotingInteractor{

    @Override
    public void submitVote(SubmitVoteRequestModel submitVoteRequestModel,final onVotingFinishedListener callback) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<SubmitVotingResponseModel> call = apiService.SubmitVote("SubmitVote", submitVoteRequestModel);
        call.enqueue(new Callback<SubmitVotingResponseModel>() {
            // If success
            @Override
            public void onResponse(Call<SubmitVotingResponseModel> call, Response<SubmitVotingResponseModel> response) {

                if (response.isSuccessful()) {
                    callback.onSuccess(response.body()); // pass the list
                } else {
                    callback.onFailure("");
                }
            }

            // If failed
            @Override
            public void onFailure(Call<SubmitVotingResponseModel> call, Throwable t) {
                callback.onFailure(t.toString());
            }
        });
    }

    @Override
    public void GetVotingResult(final onVotingFinishedListener callback) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<VotingResultResponseModel> call = apiService.GetVotingResult("VotingResult");
        call.enqueue(new Callback<VotingResultResponseModel>() {
            // If success
            @Override
            public void onResponse(Call<VotingResultResponseModel> call, Response<VotingResultResponseModel> response) {

                if (response.isSuccessful()) {
                    callback.onSuccess(response.body()); // pass the list
                } else {
                    callback.onFailure("");
                }
            }

            // If failed
            @Override
            public void onFailure(Call<VotingResultResponseModel> call, Throwable t) {
                callback.onFailure(t.toString());
            }
        });
    }
}
