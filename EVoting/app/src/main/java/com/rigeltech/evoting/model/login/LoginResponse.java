package com.rigeltech.evoting.model.login;

/**
 * Created by komaltanajimahadik on 15/10/17.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.rigeltech.evoting.model.Status;

import java.util.List;

public class LoginResponse {

    @SerializedName("Result")
    @Expose
    private List<LoginResult> result = null;
    @SerializedName("UserDetails")
    @Expose
    private List<UserDetail> userDetails = null;
    @SerializedName("Status")
    @Expose
    private List<Status> status = null;

    public List<LoginResult> getResult() {
        return result;
    }

    public void setResult(List<LoginResult> result) {
        this.result = result;
    }

    public List<UserDetail> getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(List<UserDetail> userDetails) {
        this.userDetails = userDetails;
    }

    public List<Status> getStatus() {
        return status;
    }

    public void setStatus(List<Status> status) {
        this.status = status;
    }

}
