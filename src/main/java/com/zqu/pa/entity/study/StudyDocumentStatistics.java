package com.zqu.pa.entity.study;

import java.util.Date;

public class StudyDocumentStatistics {
    private Integer documentId;

    private String userId;

    private Integer times;

    private Date createtime;

    private Date updatetime;

    public StudyDocumentStatistics(Integer documentId, String userId, Integer times, Date createtime, Date updatetime) {
        this.documentId = documentId;
        this.userId = userId;
        this.times = times;
        this.createtime = createtime;
        this.updatetime = updatetime;
    }

    public StudyDocumentStatistics() {
        super();
    }

    public Integer getDocumentId() {
        return documentId;
    }

    public void setDocumentId(Integer documentId) {
        this.documentId = documentId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getTimes() {
        return times;
    }

    public void setTimes(Integer times) {
        this.times = times;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}