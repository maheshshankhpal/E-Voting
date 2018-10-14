package com.rigeltech.evoting.Interface;

import com.rigeltech.evoting.model.login.LoginRequest;
import com.rigeltech.evoting.model.login.LoginResponse;
import com.rigeltech.evoting.model.voting.SubmitVoteRequestModel;
import com.rigeltech.evoting.model.voting.SubmitVotingResponseModel;
import com.rigeltech.evoting.model.voting.VotingResultModel;
import com.rigeltech.evoting.model.voting.VotingResultResponseModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;


public interface ApiInterface {
    @POST("System/Process/")
    Call<LoginResponse> Login(@Header("P_ID") String header, @Body LoginRequest loginRequest);

    @POST("System/Process/")
    Call<SubmitVotingResponseModel> SubmitVote(@Header("P_ID") String header, @Body SubmitVoteRequestModel submitVoteRequestModel);

    @POST("System/Process/")
    Call<VotingResultResponseModel> GetVotingResult(@Header("P_ID") String header);

}

