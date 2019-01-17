package com.zqu.pa.service.feedback;

import java.util.List;

import com.zqu.pa.entity.feedback.Feedback;
import com.zqu.pa.entity.feedback.FeedbackType;
import com.zqu.pa.vo.feedback.FeedbackInfo;
import com.zqu.pa.vo.feedback.PageOfFeedback;

public interface FeedbackService {
	
	/**
	 * 	添加思想汇报
	 * @param feedback
	 */
	void insertFeedback(Feedback feedback);
	
	/**
	 * 	管理员获取本支部用户的思想反馈列表
	 * @param branchId
	 * @return
	 */
	List<FeedbackInfo> getFeedbackInfoList(int branchId);
	
	/**
	 * 	获取反馈类型
	 * @return
	 */
	List<FeedbackType> getFeedbackTypeList();
	
	/**
	 * 	获取思想反馈详细信息
	 * @param feedbackId
	 * @return
	 */
	FeedbackInfo getFeedback(int feedbackId);
	
	/**
	 * 	分页获取个人思想反馈列表
	 * @param page
	 * @param num
	 * @return
	 */
	PageOfFeedback getPageOfFeedback (int page, int num, String userId);
}
