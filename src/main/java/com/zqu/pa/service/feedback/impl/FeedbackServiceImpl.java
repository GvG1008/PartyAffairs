package com.zqu.pa.service.feedback.impl;

import java.util.ArrayList;
import java.util.Date;
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
import com.zqu.pa.vo.feedback.PageOfFeedback;

@Service("feedbackService")
public class FeedbackServiceImpl implements FeedbackService{
	
	//获取日志记录器Logger，名字为本类类名
    private static Logger log = LoggerFactory.getLogger(FeedbackService.class);
	
	@Autowired
	FeedbackMapper feedbackMapper;

	@Override
	public void insertFeedback(Feedback feedback) {
	    try {
	        feedback.setDate(new Date());
            feedbackMapper.insertFeedback(feedback);
        } catch (Exception e) {
            log.error("添加思想反馈失败", e);
            throw e;
        }
	}

	@Override
	public List<FeedbackInfo> getFeedbackInfoList(int branchId) {
		List<FeedbackInfo> feedbackInfoList = new ArrayList<FeedbackInfo>();
		try {
			feedbackInfoList = feedbackMapper.getFeedbackList(branchId);
		} catch (Exception e) {
			log.error("获取思想反馈列表失败", e);
			throw e;
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
            throw e;
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
            throw e;
        }
        return feedbackInfo;
    }

	@Override
	public PageOfFeedback getPageOfFeedback(int page, int num, String userId) {
		PageOfFeedback pageOfFeedback = new PageOfFeedback();
		
		try {
			// 总记录数
			int totalInfoNum = feedbackMapper.getTotalFeedbackByUserId(userId);
			pageOfFeedback.setTotalInfoNum(totalInfoNum);
			// 总页数
	        int totalPageNum = (int)(totalInfoNum+num-1)/num;
	        pageOfFeedback.setTotalPageNum(totalPageNum);
	        
	        if(totalPageNum<page)
	            page = totalPageNum;
	        pageOfFeedback.setPageNum(page);
	        
	        List<FeedbackInfo> list = new ArrayList<FeedbackInfo>();
	        if (totalInfoNum <=0) {
	        	pageOfFeedback.setList(list);
	        	return pageOfFeedback;
	        }
	        
	        //limit index,num  从第index+1条记录开始，num条记录
	        if(page>=1){
	        	 int index = (page-1)*num;
	        	 pageOfFeedback.setList(feedbackMapper.getPageOfFeedbackListLimited(userId, index, num));
	        }
		} catch (Exception e) {			
			log.error("获取个人思想反馈列表失败", e);
			throw e;
		}
		
		return pageOfFeedback;
	}

}
