package com.zqu.pa.service.vote.impl;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zqu.pa.common.ServerResponse;
import com.zqu.pa.dao.vote.VoteInfoMapper;
import com.zqu.pa.dao.vote.VoteResultMapper;
import com.zqu.pa.entity.vote.VoteInfo;
import com.zqu.pa.entity.vote.VoteResult;
import com.zqu.pa.service.vote.VoteInfoService;
import com.zqu.pa.service.vote.VoteResultService;
import com.zqu.pa.vo.userInfo.UserBasicInfo;

@Service
public class VoteResultServiceImpl implements VoteResultService {

    private Logger logger = LoggerFactory.getLogger(VoteResultServiceImpl.class);
    
    @Autowired
    private VoteResultMapper voteResultMapper;
    
    @Autowired
    private VoteInfoMapper voteInfoMapper;
    
    @Autowired
    private VoteInfoService voteInfoService;
    
    @Transactional
    @Override
    public ServerResponse insertVoteResult(Long voteId, Integer abandon, List<Long> choice) {
        
        UserBasicInfo basicInfo = (UserBasicInfo)SecurityUtils.getSubject().getSession().getAttribute("basicInfo");
        if (basicInfo == null) {
            logger.debug("用户未登录");
            return ServerResponse.createByErrorMessage("用户未登录");
        }    
        String userId = basicInfo.getUserId();
        
        VoteResult voteResult = new VoteResult();
        voteResult.setVoteId(voteId);
        voteResult.setUserId(userId);
        //弃选
        if (abandon == 1) {
            voteResult.setAbandon(abandon);
            int i = voteResultMapper.insertSelective(voteResult);
            if (i > 0)
                return ServerResponse.createBySuccess();
            return ServerResponse.createByError();
        }
        
        VoteInfo voteInfo = voteInfoService.getVoteInfo(voteId); 
        if (voteInfo == null) {
            logger.debug("找不到此投票id为 " + voteId + " 的信息");
            return ServerResponse.createByError();
        }
        Integer type = voteInfo.getType();
        
        if (choice == null) {
            logger.debug("用户选中选项为null");
            return ServerResponse.createByError();
        }
        //排序类型
        if (type == 2) {
            for (int i = 0; i < choice.size(); i++) {
                voteResult.setChoiceId(choice.get(i));
                voteResult.setSort(i + 1);
                voteResultMapper.insertSelective(voteResult);
            }
        } else {
            for (int i = 0; i < choice.size(); i++) {
                voteResult.setChoiceId(choice.get(i));
                voteResultMapper.insertSelective(voteResult);
            }
        }
        return ServerResponse.createBySuccess();
    }
}
