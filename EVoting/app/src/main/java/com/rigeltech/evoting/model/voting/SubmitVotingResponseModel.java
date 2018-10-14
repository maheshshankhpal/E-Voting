package com.rigeltech.evoting.model.voting;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.rigeltech.evoting.model.Result;
import com.rigeltech.evoting.model.Status;

import java.util.List;

/**
 * Created by Mahesh on 2018-10-14.
 */

public class SubmitVotingResponseModel {

    @SerializedName("Result")
    @Expose
    private List<Result> result = null;
    @SerializedName("Status")
    @Expose
    private List<Status> status = null;

    public List<Result> getResult() {
        return result;
    }

    public List<Status> getStatus() {
        return status;
    }

}
