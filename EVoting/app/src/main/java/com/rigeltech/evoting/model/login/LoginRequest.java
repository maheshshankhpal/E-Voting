package com.rigeltech.evoting.model.login;

/**
 * Created by komaltanajimahadik on 15/10/17.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginRequest {

    @SerializedName("Username")
    @Expose
    private String username;
    @SerializedName("Password")
    @Expose
    private String password;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public LoginRequest(String username, String password)
    {
        this.username = username;
        this.password = password;
    }

}
