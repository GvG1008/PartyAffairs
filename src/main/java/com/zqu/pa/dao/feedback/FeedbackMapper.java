package com.zqu.pa.dao.feedback;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zqu.pa.entity.feedback.Feedback;
import com.zqu.pa.entity.feedback.FeedbackType;
import com.zqu.pa.vo.feedback.FeedbackInfo;

public interface FeedbackMapper {
	
	void insertFeedback(Feedback feedback);
	
	List<FeedbackInfo> getFeedbackList(@Param("branchId") int branchId);
	
	List<FeedbackType> getFeedbackType();
	
	FeedbackInfo getFeedbackById(@Param("feedbackId") int feedbackId);
	
	Integer getTotalFeedbackByUserId(@Param("userId") String userId);
	
	List<FeedbackInfo> getPageOfFeedbackListLimited(@Param("userId") String userId, @Param("index") int index , @Param("num") int num);
}
