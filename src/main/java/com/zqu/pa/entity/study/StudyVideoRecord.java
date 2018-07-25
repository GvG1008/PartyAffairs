package com.zqu.pa.entity.study;

import java.util.Date;

public class StudyVideoRecord {
    private Integer videoId;

    private String userId;

    private Float schedule;

    private Date updatetime;

    public StudyVideoRecord(Integer videoId, String userId, Float schedule, Date updatetime) {
        this.videoId = videoId;
        this.userId = userId;
        this.schedule = schedule;
        this.updatetime = updatetime;
    }

    public StudyVideoRecord() {
        super();
    }

    public Integer getVideoId() {
        return videoId;
    }

    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Float getSchedule() {
        return schedule;
    }

    public void setSchedule(Float schedule) {
        this.schedule = schedule;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}