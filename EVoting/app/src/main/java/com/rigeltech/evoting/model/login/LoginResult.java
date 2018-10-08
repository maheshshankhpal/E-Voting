package com.rigeltech.evoting.model.login;

/**
 * Created by komaltanajimahadik on 15/10/17.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResult {

    @SerializedName("StatusCode")
    @Expose
    private String statusCode;
    @SerializedName("StatusMessage")
    @Expose
    private String statusMessage;

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }
}
