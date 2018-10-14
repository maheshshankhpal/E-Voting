package com.rigeltech.evoting.model.voting;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Mahesh on 2018-10-14.
 */

public class SubmitVoteRequestModel {
    @SerializedName("Username")
    @Expose
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCand_id() {
        return cand_id;
    }

    public void setCand_id(String cand_id) {
        this.cand_id = cand_id;
    }

    @SerializedName("cand_id")
    @Expose
    private String cand_id;
}
