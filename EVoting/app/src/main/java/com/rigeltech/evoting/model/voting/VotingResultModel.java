package com.rigeltech.evoting.model.voting;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Mahesh on 2018-10-14.
 */

public class VotingResultModel {
    @SerializedName("cand_id")
    @Expose
    private String candId;
    @SerializedName("Name")
    @Expose
    private String name;

    public String getSymbol() {
        return symbol;
    }

    @SerializedName("Symbol")
    @Expose
    private String symbol;

    public String getAbout() {
        return about;
    }

    @SerializedName("About")
    @Expose
    private String about;
    @SerializedName("vote")
    @Expose
    private long vote;

    public String getCandId() {
        return candId;
    }

    public String getName() {
        return name;
    }

    public long getVote() {
        return vote;
    }
}
