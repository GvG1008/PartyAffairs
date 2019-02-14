package com.zqu.pa.vo.vote;

import java.util.List;

import com.zqu.pa.entity.vote.VoteInfo;

public class ResponseVoteChoiceResult {

    private VoteInfo voteInfo;
    
    private List<VoteChoice1> choice;

    public VoteInfo getVoteInfo() {
        return voteInfo;
    }

    public void setVoteInfo(VoteInfo voteInfo) {
        this.voteInfo = voteInfo;
    }

    public List<VoteChoice1> getChoice() {
        return choice;
    }

    public void setChoice(List<VoteChoice1> choice) {
        this.choice = choice;
    }
}
