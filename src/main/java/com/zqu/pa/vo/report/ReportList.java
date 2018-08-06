package com.zqu.pa.vo.report;

import java.util.Date;

import com.zqu.pa.utils.DateToString;

public class ReportList {
	
	//思想报告ID
	private Integer id;
	//思想报告标题
    private String title;
    //思想报告提交日期
    private String date;
    
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
		this.date = DateToString.getDateString("yyyy/MM/dd", date);;
	}
    

}
