package com.rigeltech.evoting.Interface;

import com.rigeltech.evoting.model.login.LoginRequest;
import com.rigeltech.evoting.model.login.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;


public interface ApiInterface {
    @POST("System/Process/")
    Call<LoginResponse> Login(@Header("P_ID") String header, @Body LoginRequest loginRequest);

}

