package com.zqu.pa.entity.feedback;

import java.util.Date;

import com.zqu.pa.utils.DateToString;

public class Feedback {
	private int feedbackId;
	private String userId;
	private String title;
	private String content;
	private String date;
	private int feedbackType;
	
	public int getFeedbackId() {
		return feedbackId;
	}
	public void setFeedbackId(int feedbackId) {
		this.feedbackId = feedbackId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getDate() {
		return date;
	}
	public void setDate(Date date) {
        this.date = DateToString.getDateString("yyyy/MM/dd", date);;
    }
	public int getFeedbackType() {
		return feedbackType;
	}
	public void setFeedbackType(int feedbackType) {
		this.feedbackType = feedbackType;
	}
	
	
}
