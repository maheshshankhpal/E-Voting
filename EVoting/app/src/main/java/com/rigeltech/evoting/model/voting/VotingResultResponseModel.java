package com.rigeltech.evoting.model.voting;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.rigeltech.evoting.model.Status;

import java.util.List;

/**
 * Created by Mahesh on 2018-10-14.
 */

public class VotingResultResponseModel {
    @SerializedName("VotingResult")
    @Expose
    private List<VotingResultModel> votingResult = null;
    @SerializedName("Status")
    @Expose
    private List<Status> status = null;

    public List<VotingResultModel> getVotingResult() {
        return votingResult;
    }

    public void setVotingResult(List<VotingResultModel> votingResult) {
        this.votingResult = votingResult;
    }

    public List<Status> getStatus() {
        return status;
    }

    public void setStatus(List<Status> status) {
        this.status = status;
    }
}
