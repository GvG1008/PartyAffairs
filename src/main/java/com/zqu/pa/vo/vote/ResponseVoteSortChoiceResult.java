package com.zqu.pa.vo.vote;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import com.zqu.pa.entity.vote.VoteInfo;

public class ResponseVoteSortChoiceResult {

    private VoteInfo voteInfo;
    
    private List<VoteChoice2> choice;
    
    private List<Map.Entry<String,Long>> sortList;

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

	public List<Map.Entry<String,Long>> getSortList() {
		return sortList;
	}

	public void setSortList(List<Map.Entry<String,Long>> sortList) {
		this.sortList = sortList;
	}
    
    
}
