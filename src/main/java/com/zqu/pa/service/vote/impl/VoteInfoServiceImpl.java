package com.zqu.pa.service.vote.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zqu.pa.common.ServerResponse;
import com.zqu.pa.dao.vote.VoteChoiceMapper;
import com.zqu.pa.dao.vote.VoteInfoMapper;
import com.zqu.pa.dao.vote.VoteUserMapper;
import com.zqu.pa.entity.vote.VoteChoice;
import com.zqu.pa.entity.vote.VoteInfo;
import com.zqu.pa.entity.vote.VoteUserKey;
import com.zqu.pa.service.vote.VoteInfoService;
import com.zqu.pa.vo.userInfo.UserBasicInfo;

@Service
public class VoteInfoServiceImpl implements VoteInfoService {

    private Logger logger = LoggerFactory.getLogger(VoteInfoServiceImpl.class);
    
    @Autowired
    private VoteInfoMapper voteInfoMapper;
    
    @Autowired
    private VoteChoiceMapper voteChoiceMapper;
    
    @Autowired
    private VoteUserMapper voteUserMapper;
    
    @Transactional
    @Override
    public ServerResponse createVote(VoteInfo voteInfo, Map<String, Object> choice_user) {
        
        UserBasicInfo basicInfo = (UserBasicInfo)SecurityUtils.getSubject().getSession().getAttribute("basicInfo");
        if (basicInfo == null) {
            logger.debug("用户未登录");
            return ServerResponse.createByErrorMessage("用户未登录");
        }    
        String userId = basicInfo.getUserId();
        if (voteInfo == null) {
            logger.debug("投票信息类为null，无法创建投票");
            return ServerResponse.createByError();
        }
        if (choice_user == null) {
            logger.debug("投票选项和参与人员Map类为null");
            return ServerResponse.createByError();
        }
        List<String> choice = (List<String>)choice_user.get("choice");
        List<String> voteUser = (List<String>)choice_user.get("voteUser");
        if (choice == null || choice.size() == 0) {
            logger.debug("投票选项为null或为空");
            return ServerResponse.createByErrorMessage("投票选项不能为空");
        }
        if (voteInfo.getTitle() == null || voteInfo.getType() == null) {
            logger.debug("投票信息为空");
            return ServerResponse.createByError();
        }
        
        Long voteId = new Date().getTime();
        voteInfo.setVoteId(voteId);
        voteInfo.setCreateId(userId);
        insertVoteInfo(voteInfo);
        insertVoteChoice(voteId, choice);
        insertVoteUser(voteId, voteUser);
        return ServerResponse.createBySuccess();
    }
    
    //插入投票信息表
    public void insertVoteInfo(VoteInfo voteInfo) {
        
        voteInfoMapper.insertSelective(voteInfo);
    }
    
    //插入投票选项表
    public void insertVoteChoice(Long voteId, List<String> choice) {
        
        for (String c : choice) {
            VoteChoice vc = new VoteChoice();
            vc.setVoteId(voteId);
            vc.setChoiceContent(c);
            voteChoiceMapper.insertSelective(vc);
        }
    }
    
    //插入投票参与人员表
    public void insertVoteUser(Long voteId, List<String> voteUser) {
        
        if (voteUser == null || voteUser.size() == 0) {
            return;
        }
        for (String user : voteUser) {
            VoteUserKey vu = new VoteUserKey();
            vu.setVoteId(voteId);
            vu.setUserId(user);
            voteUserMapper.insertSelective(vu);
        }
    }

}
