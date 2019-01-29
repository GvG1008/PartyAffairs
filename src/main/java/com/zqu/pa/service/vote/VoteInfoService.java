package com.zqu.pa.service.vote;

import java.util.List;
import java.util.Map;

import com.zqu.pa.common.ServerResponse;
import com.zqu.pa.entity.vote.VoteInfo;
import com.zqu.pa.vo.vote.ResponseVoteInfo;

public interface VoteInfoService {

    //创建一个新投票
    ServerResponse createVote(VoteInfo voteInfo, Map<String, Object> choice_user);
    
    //根据投票ID获取用户能参与的投票信息
    ServerResponse<ResponseVoteInfo> getVote(Long voteId);
    
    //获取投票信息
    VoteInfo getVoteInfo(Long voteId);
    
    //获取用户需要进行投票的投票列表
    List<VoteInfo> listVote();
    
    //判断用户是否投过票（true：已经投过票；false：未投过票）
    boolean existVoteResult(Long voteId, String userId);
    
    //根据时间更新投票状态
    void updateVoteStatus();
    
    //获取全部投票信息
    List<ResponseVoteInfo> getAdminVote();
    
    //停止投票
    ServerResponse suspendVote(Long voteId);
    
    //删除投票
    ServerResponse removeVote(Long voteId);   
}
