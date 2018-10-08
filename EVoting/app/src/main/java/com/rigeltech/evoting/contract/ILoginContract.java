package com.rigeltech.evoting.contract;


import com.rigeltech.evoting.IBaseAppCompant;
import com.rigeltech.evoting.model.login.LoginRequest;
import com.rigeltech.evoting.model.login.LoginResponse;
import com.rigeltech.evoting.model.login.UserDetail;

/**
 * Created by Mahesh on 2017-10-28.
 */

public interface ILoginContract {
    interface ILoginInteractor {
        void login(LoginRequest loginRequest, final onLoginFinishedListener onLoginFinishedListener);

        interface onLoginFinishedListener {
            void onSuccess(LoginResponse loginResponse);
            void onFailure(String failureMessage);
        }
    }

    interface ILoginPresenter {
        void ValidateUser(String username, String password);
    }

    interface ILoginView extends IBaseAppCompant {
        void setUserNameError();
        void setPasswordError();
        void onLoginSuccess(UserDetail userDetail);
        void onLoginFailure(String message);
    }
}
