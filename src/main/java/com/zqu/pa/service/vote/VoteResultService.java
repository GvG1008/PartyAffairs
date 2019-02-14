package com.zqu.pa.service.vote;

import java.util.List;

import com.zqu.pa.common.ServerResponse;
import com.zqu.pa.vo.vote.ResponseVoteChoiceResult;
import com.zqu.pa.vo.vote.ResponseVoteSortChoiceResult;

public interface VoteResultService {

    //插入用户投票结果
    ServerResponse insertVoteResult(Long voteId, Integer abandon, List<Long> choice);
    //单选/多选结果
    ServerResponse<ResponseVoteChoiceResult> getChoiceResult(Long voteId);
    //排序结果
    ServerResponse<ResponseVoteSortChoiceResult> getSortChoiceResult(Long voteId);
}
