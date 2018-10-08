package com.rigeltech.evoting.interactor;


import com.rigeltech.evoting.Interface.ApiInterface;
import com.rigeltech.evoting.contract.ILoginContract;
import com.rigeltech.evoting.model.login.LoginRequest;
import com.rigeltech.evoting.model.login.LoginResponse;
import com.rigeltech.evoting.rest.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by DM365 on 26/10/2017.
 */

public class LoginInteractor implements ILoginContract.ILoginInteractor {
    @Override
    public void login(LoginRequest loginRequest, final onLoginFinishedListener callback) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<LoginResponse> call = apiService.Login("Login", loginRequest);
        call.enqueue(new Callback<LoginResponse>() {
            // If success
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                if (response.isSuccessful()) {
                    callback.onSuccess(response.body()); // pass the list
                } else {
                    callback.onSuccess(null);
                }
            }

            // If failed
            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                callback.onFailure(t.toString());
            }
        });
    }
}
