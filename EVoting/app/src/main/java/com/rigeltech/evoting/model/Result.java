package com.rigeltech.evoting.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Mahesh on 2018-10-14.
 */

public class Result {
    @SerializedName("StatusCode")
    @Expose
    private long statusCode;
    @SerializedName("StatusMessage")
    @Expose
    private String statusMessage;

    public long getStatusCode() {
        return statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }
}
