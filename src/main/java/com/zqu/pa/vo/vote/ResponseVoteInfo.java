package com.zqu.pa.vo.vote;

import java.util.List;

import com.zqu.pa.entity.vote.VoteChoice;
import com.zqu.pa.entity.vote.VoteInfo;

public class ResponseVoteInfo {

    private VoteInfo voteInfo;
    
    private List<VoteChoice> choice; //选项数组

    public List<VoteChoice> getChoice() {
        return choice;
    }

    public void setChoice(List<VoteChoice> choice) {
        this.choice = choice;
    }

    public VoteInfo getVoteInfo() {
        return voteInfo;
    }

    public void setVoteInfo(VoteInfo voteInfo) {
        this.voteInfo = voteInfo;
    }
}
