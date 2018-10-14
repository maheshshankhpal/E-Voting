package com.rigeltech.evoting.contract;

import com.rigeltech.evoting.Interface.IBaseAppCompant;
import com.rigeltech.evoting.model.Result;
import com.rigeltech.evoting.model.voting.SubmitVoteRequestModel;
import com.rigeltech.evoting.model.voting.SubmitVotingResponseModel;
import com.rigeltech.evoting.model.voting.VotingResultModel;
import com.rigeltech.evoting.model.voting.VotingResultResponseModel;

import java.util.List;

/**
 * Created by Mahesh on 2018-10-14.
 */

public interface IVotingContract {
    interface IVotingInteractor {
        void submitVote(SubmitVoteRequestModel submitVoteRequestModel, final onVotingFinishedListener onVotingFinishedListener);
        void GetVotingResult(final onVotingFinishedListener onVotingFinishedListener);

        interface onVotingFinishedListener {
            void onSuccess(SubmitVotingResponseModel submitVotingResponseModel);
            void onSuccess(VotingResultResponseModel votingResultResponseModel);
            void onFailure(String failureMessage);
        }
    }

    interface IVotingPresenter {
        void submitVote(SubmitVoteRequestModel submitVoteRequestModel);
        void GetVotingResult();
    }

    interface IVotingView extends IBaseAppCompant {
        void onSubmitVoteSuccess(List<Result> result);
        void onGetVotingResult(List<VotingResultModel> votingResultModelList);
        void onFailure(String message);
    }
}
