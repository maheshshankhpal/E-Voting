package com.rigeltech.evoting.presenter;

import android.text.TextUtils;

import com.rigeltech.evoting.contract.ILoginContract;
import com.rigeltech.evoting.interactor.LoginInteractor;
import com.rigeltech.evoting.model.login.LoginRequest;
import com.rigeltech.evoting.model.login.LoginResponse;
import com.rigeltech.evoting.utility.Utility;


/**
 * Created by Mahesh on 2017-10-25.
 */

public class LoginPresenter implements ILoginContract.ILoginPresenter,
        ILoginContract.ILoginInteractor.onLoginFinishedListener {


    private ILoginContract.ILoginInteractor loginInteractor;
    private ILoginContract.ILoginView loginView;

    public LoginPresenter(ILoginContract.ILoginView loginView)
    {
        this.loginView = loginView;
        this.loginInteractor = new LoginInteractor();
    }

    @Override
    public void ValidateUser(String username, String password) {
        if (TextUtils.isEmpty(username.trim())) {
            loginView.setUserNameError();
        }
        else if (TextUtils.isEmpty(password.trim())) {
            loginView.setPasswordError();
        }
        else
        {
            if(Utility.checkConnection()) {

                loginInteractor.login(new LoginRequest(username,password),this);
            }
            else {
                loginView.hideProgressDialog();
                loginView.showMessage("NoInternet");
            }
        }
    }

    @Override
    public void onSuccess(LoginResponse loginResponse) {
        if(loginResponse.getStatus().get(0).getStatusCode().equalsIgnoreCase("1")) {
            if (loginResponse.getResult().get(0).getStatusCode().equalsIgnoreCase("1"))
                loginView.onLoginSuccess(loginResponse.getUserDetails().get(0));
            else
                loginView.onLoginFailure("invalid_login");
        }
        else
        {
            loginView.onLoginFailure("invalid_login");
        }
    }

    @Override
    public void onFailure(String failureMessage) {
        loginView.onLoginFailure(failureMessage);
    }

}
