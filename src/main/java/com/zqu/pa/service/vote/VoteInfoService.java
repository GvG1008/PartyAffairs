package com.zqu.pa.service.vote;

import java.util.List;
import java.util.Map;

import com.zqu.pa.common.ServerResponse;
import com.zqu.pa.entity.vote.VoteInfo;

public interface VoteInfoService {

    //创建一个新投票
    ServerResponse createVote(VoteInfo voteInfo, Map<String, Object> choice_user);
}
