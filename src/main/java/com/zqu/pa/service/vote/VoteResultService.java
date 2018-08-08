package com.zqu.pa.service.vote;

import java.util.List;

import com.zqu.pa.common.ServerResponse;

public interface VoteResultService {

    //插入用户投票结果
    ServerResponse insertVoteResult(Long voteId, Integer abandon, List<Long> choice);
}
