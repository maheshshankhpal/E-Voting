package com.rigeltech.evoting.model.login;

/**
 * Created by komaltanajimahadik on 15/10/17.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserDetail {

    @SerializedName("ProfileOwner")
    @Expose
    private String profileOwner;
    @SerializedName("Userrole")
    @Expose
    private String userrole;

    @SerializedName("TimeKey")
    @Expose
    private String TimeKey;

    public String getTimeKey() {
        return TimeKey;
    }

    public String getProfileOwner() {
        return profileOwner;
    }

    public String getUserrole() {
        return userrole;
    }
}
