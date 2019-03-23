package com.zqu.pa.service.vote.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.HashMap;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zqu.pa.common.ServerResponse;
import com.zqu.pa.dao.vote.VoteChoiceMapper;
import com.zqu.pa.dao.vote.VoteInfoMapper;
import com.zqu.pa.dao.vote.VoteResultMapper;
import com.zqu.pa.entity.vote.VoteChoice;
import com.zqu.pa.entity.vote.VoteChoiceExample;
import com.zqu.pa.entity.vote.VoteInfo;
import com.zqu.pa.entity.vote.VoteResult;
import com.zqu.pa.service.vote.VoteInfoService;
import com.zqu.pa.service.vote.VoteResultService;
import com.zqu.pa.utils.DateUtil;
import com.zqu.pa.vo.userInfo.UserBasicInfo;
import com.zqu.pa.vo.vote.ResponseVoteChoiceResult;
import com.zqu.pa.vo.vote.ResponseVoteInfo;
import com.zqu.pa.vo.vote.ResponseVoteSortChoiceResult;
import com.zqu.pa.vo.vote.VoteChoice1;
import com.zqu.pa.vo.vote.VoteChoice2;

@Service
public class VoteResultServiceImpl implements VoteResultService {

    private Logger logger = LoggerFactory.getLogger(VoteResultServiceImpl.class);
    
    @Autowired
    private VoteResultMapper voteResultMapper;
    
    @Autowired
    private VoteInfoMapper voteInfoMapper;
    
    @Autowired
    private VoteInfoService voteInfoService;
    
    @Autowired
    private VoteChoiceMapper voteChoiceMapper;
    
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

    @Override
    public ServerResponse<ResponseVoteChoiceResult> getChoiceResult(Long voteId) {
        
        ResponseVoteInfo rvi = getResponseVoteInfo(voteId);
        if (rvi == null) {
            return ServerResponse.createByErrorMessage("获取投票信息出错");
        }
        
        ResponseVoteChoiceResult result = new ResponseVoteChoiceResult();
        
        //投票类型为排序
        int type = 2;
        if (rvi.getVoteInfo().getType() == 2) {
            return ServerResponse.createByErrorMessage("投票类型非单选/多选");
        }
        
        List<VoteChoice> choice = rvi.getChoice();
        List<VoteChoice1> choice1 = new ArrayList<>();
        for (VoteChoice vc : choice) {
            VoteChoice1 vc1 = new VoteChoice1();           
            vc1.setVoteId(vc.getVoteId());
            vc1.setChoiceId(vc.getChoiceId());
            vc1.setChoiceContent(vc.getChoiceContent());
            vc1.setStatus(vc.getStatus());
            //选项被选中次数
            vc1.setCount(voteResultMapper.countVoteChoice(vc.getVoteId(), vc.getChoiceId()));
            
            choice1.add(vc1);
        }
        result.setVoteInfo(rvi.getVoteInfo());
        result.setChoice(choice1);
        return ServerResponse.createBySuccess(result);
    }

    @Override
    public ServerResponse<ResponseVoteSortChoiceResult> getSortChoiceResult(Long voteId) {
        
        //XXX 这个排序结果取数逻辑不严谨，看着改吧  ╮(๑•́ ₃•̀๑)╭
        ResponseVoteInfo rvi = getResponseVoteInfo(voteId);
        if (rvi == null) {
            return ServerResponse.createByErrorMessage("获取投票信息出错");
        }
        
        ResponseVoteSortChoiceResult result = new ResponseVoteSortChoiceResult();
       
        //投票类型为排序
        int type = 2;
        if (rvi.getVoteInfo().getType() != 2) {
            return ServerResponse.createByErrorMessage("投票类型非排序");
        }
        List<VoteChoice> choice = rvi.getChoice();
        List<VoteChoice2> choice2 = new ArrayList<>();
        // 投票得分排序
        Map<Long,Long> sortResult = new HashMap<Long,Long>();
        for (VoteChoice vc : choice) {
            VoteChoice2 vc2 = new VoteChoice2();           
            vc2.setVoteId(vc.getVoteId());
            vc2.setChoiceId(vc.getChoiceId());
            vc2.setChoiceContent(vc.getChoiceContent());
            vc2.setStatus(vc.getStatus());
            
            Long tatalScore = 0L;
            
            for(int i=0;i<choice.size();i++) {
            	// 投票选取次数
            	Long times = voteResultMapper.countVoteSortChoice(vc.getVoteId(), vc.getChoiceId(), i+1);
            	// 投票得分
            	Long score = times * (choice.size()-i);
            	tatalScore += score;
            }
            sortResult.put(vc.getChoiceId(), tatalScore);           
            
            choice2.add(vc2);
        }
        
        //这里将map.entrySet()转换成list
        List<Map.Entry<Long,Long>> list = new ArrayList<Map.Entry<Long,Long>>(sortResult.entrySet());
        //然后通过比较器来实现排序
        Collections.sort(list,new Comparator<Map.Entry<Long,Long>>() {
            //升序排序
            public int compare(Entry<Long, Long> o1,
                    Entry<Long, Long> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
            
        });
               
        result.setSortList(list);
        result.setVoteInfo(rvi.getVoteInfo());
        result.setChoice(choice2);
        return ServerResponse.createBySuccess(result);
    }
    
    public ResponseVoteInfo getResponseVoteInfo(Long voteId) {
        
        VoteInfo voteInfo = voteInfoService.getVoteInfo(voteId);
        if (voteInfo == null) {
            logger.debug("不存在此投票");
            return null;
        }   
        
        VoteChoiceExample example = new VoteChoiceExample();
        example.createCriteria().andVoteIdEqualTo(voteId);      
        List<VoteChoice> listVoteChoice = voteChoiceMapper.selectByExample(example);
        if (listVoteChoice == null || listVoteChoice.size() == 0) {
            logger.debug("不存在投票选项");
            return null;
        }
        
        ResponseVoteInfo responseVoteInfo = new ResponseVoteInfo();
        voteInfo.setStringStartTime(DateUtil.formatTime(voteInfo.getStartTime()));
        voteInfo.setStringEndTime(DateUtil.formatTime(voteInfo.getEndTime()));
        responseVoteInfo.setVoteInfo(voteInfo);
        responseVoteInfo.setChoice(listVoteChoice);
        return responseVoteInfo;
    }
    
}
