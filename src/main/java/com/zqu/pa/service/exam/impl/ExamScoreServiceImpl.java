package com.zqu.pa.service.exam.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zqu.pa.common.ServerResponse;
import com.zqu.pa.dao.exam.ExamInfoMapper;
import com.zqu.pa.dao.exam.ExamInfoReviewMapper;
import com.zqu.pa.dao.exam.ExamScoreMapper;
import com.zqu.pa.dao.exam.ExamUserMapper;
import com.zqu.pa.entity.exam.ExamInfo;
import com.zqu.pa.entity.exam.ExamInfoExample;
import com.zqu.pa.entity.exam.ExamInfoReview;
import com.zqu.pa.entity.exam.ExamInfoReviewExample;
import com.zqu.pa.entity.exam.ExamScoreExample;
import com.zqu.pa.entity.exam.ExamUserExample;
import com.zqu.pa.service.exam.ExamScoreService;
import com.zqu.pa.utils.DateUtil;
import com.zqu.pa.vo.exam.ExamScoreList;
import com.zqu.pa.vo.userInfo.UserBasicInfo;

@Service
public class ExamScoreServiceImpl implements ExamScoreService {

    private Logger logger = LoggerFactory.getLogger(ExamScoreServiceImpl.class);
    
    @Autowired
    private ExamInfoReviewMapper examInfoReviewMapper;
    
    @Autowired
    private ExamInfoMapper examInfoMapper;
    
    @Autowired
    private ExamUserMapper examUserMapper;
    
    @Autowired
    private ExamScoreMapper examScoreMapper;
    
    @Override
    public ServerResponse<List<ExamScoreList>> listExamScore() {
        
        UserBasicInfo basicInfo = (UserBasicInfo)SecurityUtils.getSubject().getSession().getAttribute("basicInfo");
        if (basicInfo == null) {
            logger.error("用户未登录");
            return ServerResponse.createByErrorMessage("用户未登录");
        }
        int branchId = basicInfo.getBranchId();
        
        //获取已审核的所有考试ID
        ExamInfoReviewExample example = new ExamInfoReviewExample();
        //已审核的考试
        int review = 1;
        example.createCriteria().andReviewEqualTo(review);
        
        List<ExamInfoReview> listExamInfoReview = examInfoReviewMapper.selectByExample(example);
        if (listExamInfoReview == null || listExamInfoReview.size() == 0) {
            logger.debug("已审核的考试为空");
            //返回空
            return ServerResponse.createBySuccess();
        }
        
        List<ExamScoreList> result = new ArrayList<>();
        for (ExamInfoReview eir : listExamInfoReview) {
            ExamScoreList esl = new ExamScoreList();
            int examID = eir.getExamId();
            
            //查找对应党支部下的考试信息
            ExamInfoExample examInfoExample = new ExamInfoExample();
            examInfoExample.createCriteria().andExamIdEqualTo(examID).andBranchIdEqualTo(branchId);
            List<ExamInfo> temp = examInfoMapper.selectByExample(examInfoExample);
            if (temp == null || temp.size() == 0) {
                logger.debug("考试信息为空");
                return ServerResponse.createBySuccess();
            }
            ExamInfo ei = temp.get(0);
            esl.setExamId(examID);
            esl.setExamTitle(ei.getExamTitle());
            esl.setBranchId(ei.getBranchId());
            esl.setStartTime(ei.getStartTime());
            esl.setEndTime(ei.getEndTime());
            esl.setStringStartTime(DateUtil.formatTime(ei.getStartTime()));
            esl.setStringEndTime(DateUtil.formatTime(ei.getEndTime()));
            esl.setPassScore(ei.getPassScore());
            
            //考试人数
            ExamUserExample examUserExample = new ExamUserExample();
            examUserExample.createCriteria().andExamIdEqualTo(examID);
            int examNumbers = (int) examUserMapper.countByExample(examUserExample);
            esl.setExamNumbers(examNumbers);
            
            //及格人数
            ExamScoreExample examScoreExample = new ExamScoreExample();
            examScoreExample.createCriteria().andExamIdEqualTo(examID).andScoreGreaterThanOrEqualTo(ei.getPassScore());
            int passNumbers = (int) examScoreMapper.countByExample(examScoreExample);
            esl.setPassNumbers(passNumbers);
            
            //还未参加考试人数            
            int score = -1; //未参加考试
            ExamScoreExample examScoreExample2 = new ExamScoreExample();
            examScoreExample2.createCriteria().andExamIdEqualTo(examID).andScoreEqualTo(score);
            int notTakeExamNumbers = (int) examScoreMapper.countByExample(examScoreExample2);
            esl.setNotTakeExamNumbers(notTakeExamNumbers);
            
            result.add(esl);
        }
        
        return ServerResponse.createBySuccess(result);
    }

}
