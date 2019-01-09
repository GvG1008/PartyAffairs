package com.zqu.pa.service.feedback.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zqu.pa.dao.feedback.FeedbackMapper;
import com.zqu.pa.entity.feedback.Feedback;
import com.zqu.pa.entity.feedback.FeedbackType;
import com.zqu.pa.service.feedback.FeedbackService;
import com.zqu.pa.vo.feedback.FeedbackInfo;

@Service("feedbackService")
public class FeedbackServiceImpl implements FeedbackService{
	
	//获取日志记录器Logger，名字为本类类名
    private static Logger log = LoggerFactory.getLogger(FeedbackService.class);
	
	@Autowired
	FeedbackMapper feedbackMapper;

	@Override
	public void insertFeedback(Feedback feedback) {
	    try {
            feedbackMapper.insertFeedback(feedback);
        } catch (Exception e) {
            log.error("添加思想反馈失败", e);
        }
	}

	@Override
	public List<FeedbackInfo> getFeedbackInfoList(int branchId) {
		List<FeedbackInfo> feedbackInfoList = new ArrayList<FeedbackInfo>();
		try {
			feedbackInfoList = feedbackMapper.getFeedbackList(branchId);
		} catch (Exception e) {
			log.error("获取思想反馈列表失败", e);
		}
		return feedbackInfoList;
	}

	@Override
	public List<FeedbackType> getFeedbackTypeList() {
	    List<FeedbackType> feedbackTypeList = new ArrayList<FeedbackType>();
	    try {
	        feedbackTypeList = feedbackMapper.getFeedbackType();
        } catch (Exception e) {
            log.error("获取思想反馈类型失败", e);
        }
		return feedbackTypeList;
	}

    @Override
    public FeedbackInfo getFeedback(int feedbackId) {
        FeedbackInfo feedbackInfo = new FeedbackInfo();
        try {
            feedbackInfo = feedbackMapper.getFeedbackById(feedbackId);
        } catch (Exception e) {
            log.error("获取思想反馈详细信息失败", e);
        }
        return feedbackInfo;
    }

}
