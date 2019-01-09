package com.zqu.pa.vo.feedback;

import java.util.Date;

import com.zqu.pa.utils.DateToString;

public class FeedbackInfo {
	private int feedbackId;

	private int branchId;

	private String userId;

	private String userName;

	private String title;

	private String date;
	
	private String feedbackName;

	public int getFeedbackId() {
		return feedbackId;
	}

	public void setFeedbackId(int feedbackId) {
		this.feedbackId = feedbackId;
	}

	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = DateToString.getDateString("yyyy/MM/dd HH:mm:ss", date);
	} 

	public String getFeedbackName() {
		return feedbackName;
	}

	public void setFeedbackName(String feedbackName) {
		this.feedbackName = feedbackName;
	}
	
}
