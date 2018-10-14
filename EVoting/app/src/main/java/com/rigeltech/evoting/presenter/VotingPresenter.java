package com.rigeltech.evoting.presenter;

import com.rigeltech.evoting.contract.IVotingContract;
import com.rigeltech.evoting.interactor.VotingInteractor;
import com.rigeltech.evoting.model.voting.SubmitVoteRequestModel;
import com.rigeltech.evoting.model.voting.SubmitVotingResponseModel;
import com.rigeltech.evoting.model.voting.VotingResultModel;
import com.rigeltech.evoting.model.voting.VotingResultResponseModel;
import com.rigeltech.evoting.utility.Utility;

/**
 * Created by Mahesh on 2018-10-14.
 */

public class VotingPresenter implements IVotingContract.IVotingPresenter,
        IVotingContract.IVotingInteractor.onVotingFinishedListener
{

    private IVotingContract.IVotingInteractor votingInteractor;
    private IVotingContract.IVotingView votingView;

    public VotingPresenter(IVotingContract.IVotingView votingView)
    {
        this.votingView = votingView;
        this.votingInteractor = new VotingInteractor();
    }

    @Override
    public void submitVote(SubmitVoteRequestModel submitVoteRequestModel) {
        if(Utility.checkConnection()) {
            votingInteractor.submitVote(submitVoteRequestModel,this);
        }
        else {
            votingView.hideProgressDialog();
            votingView.showMessage("NoInternet");
        }
    }

    @Override
    public void GetVotingResult() {
        if(Utility.checkConnection()) {
            votingInteractor.GetVotingResult(this);
        }
        else {
            votingView.hideProgressDialog();
            votingView.showMessage("NoInternet");
        }
    }

    @Override
    public void onSuccess(SubmitVotingResponseModel submitVotingResponseModel) {
        votingView.onSubmitVoteSuccess(submitVotingResponseModel.getResult());
    }

    @Override
    public void onSuccess(VotingResultResponseModel votingResultResponseModel) {

        if(votingResultResponseModel.getStatus().get(0).getStatusCode().equals("1")){
            votingView.onGetVotingResult(votingResultResponseModel.getVotingResult());
        }

    }

    @Override
    public void onFailure(String message) {
        votingView.showMessage(message);
    }
}
