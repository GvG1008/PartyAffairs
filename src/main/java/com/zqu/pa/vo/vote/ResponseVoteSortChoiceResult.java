package com.zqu.pa.vo.vote;

import java.util.List;

import com.zqu.pa.entity.vote.VoteInfo;

public class ResponseVoteSortChoiceResult {

    private VoteInfo voteInfo;
    
    private List<VoteChoice2> choice;

    public VoteInfo getVoteInfo() {
        return voteInfo;
    }

    public void setVoteInfo(VoteInfo voteInfo) {
        this.voteInfo = voteInfo;
    }

    public List<VoteChoice2> getChoice() {
        return choice;
    }

    public void setChoice(List<VoteChoice2> choice) {
        this.choice = choice;
    }
}
