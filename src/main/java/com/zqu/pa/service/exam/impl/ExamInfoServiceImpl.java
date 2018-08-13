package com.zqu.pa.service.exam.impl;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zqu.pa.common.ServerResponse;
import com.zqu.pa.dao.exam.ExamInfoMapper;
import com.zqu.pa.dao.exam.ExamInfoReviewMapper;
import com.zqu.pa.entity.exam.ExamInfo;
import com.zqu.pa.entity.exam.ExamInfoReview;
import com.zqu.pa.service.exam.ExamInfoService;
import com.zqu.pa.vo.userInfo.UserBasicInfo;

@Service
public class ExamInfoServiceImpl implements ExamInfoService {

    private Logger logger = LoggerFactory.getLogger(ExamInfoServiceImpl.class);
    
    @Autowired
    private ExamInfoMapper examInfoMapper;
    
    @Autowired
    private ExamInfoReviewMapper examInfoReviewMapper;
    
    @Transactional
    @Override
    public ServerResponse createExamInfo(ExamInfo examInfo, ExamInfoReview examInfoReview) {
        
        UserBasicInfo basicInfo = (UserBasicInfo)SecurityUtils.getSubject().getSession().getAttribute("basicInfo");
        if (basicInfo == null) {
            logger.debug("用户未登录");
            return ServerResponse.createByErrorMessage("用户未登录");
        }
        String userId = basicInfo.getUserId();
        int branchId = basicInfo.getBranchId();
        int roleId = basicInfo.getRoleId();
        
        examInfo.setBranchId(branchId);
        int i = examInfoMapper.insertExamInfo(examInfo);
        if (i <= 0)
            return ServerResponse.createByError();
        Integer examId = examInfo.getExamId();
        examInfoReview.setExamId(examId);
        examInfoReview.setCreateId(userId);
        i = examInfoReviewMapper.insertSelective(examInfoReview);
        if (i <= 0)
            return ServerResponse.createByError();
        
        return ServerResponse.createBySuccess();
    }

}
