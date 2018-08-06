package com.zqu.pa.entity.report;

import java.util.Date;

import com.zqu.pa.utils.DateToString;

public class Report {
    private Integer reportId;

    private String userId;

    private String title;

    private String date;

    private String content;

    public Integer getReportId() {
        return reportId;
    }

    public void setReportId(Integer reportId) {
        this.reportId = reportId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = DateToString.getDateString("yyyy/MM/dd", date);;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}