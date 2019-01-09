package com.zqu.pa.vo.report;

import java.util.Date;

import com.zqu.pa.utils.DateToString;

public class ReportInfo {
	
	 private int reportId;
	    
	 private int branchId;
	 
	 private String userId;
	 
	 private String userName;
	 
	 private String title;
	 
	 private String date;

	public int getReportId() {
		return reportId;
	}

	public void setReportId(int reportId) {
		this.reportId = reportId;
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
}
