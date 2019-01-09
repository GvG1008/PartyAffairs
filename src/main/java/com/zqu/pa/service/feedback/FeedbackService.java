package com.zqu.pa.service.feedback;

import java.util.List;

import com.zqu.pa.entity.feedback.Feedback;
import com.zqu.pa.entity.feedback.FeedbackType;
import com.zqu.pa.vo.feedback.FeedbackInfo;

public interface FeedbackService {
	void insertFeedback(Feedback feedback);
	
	List<FeedbackInfo> getFeedbackInfoList(int branchId);
	
	List<FeedbackType> getFeedbackTypeList();
}
