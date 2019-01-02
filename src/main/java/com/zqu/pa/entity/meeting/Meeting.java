package com.zqu.pa.entity.meeting;

import java.util.Date;

import com.zqu.pa.utils.DateToString;

public class Meeting {
    private Integer meetingId;

    private Integer branchId;

    private String title;

    private String date;

    private String creatorId;

    private Integer state;

    private String lastTime;

    private Integer click;

    private String coverpath;

    private String content;
    
    private String video;

    public Integer getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(Integer meetingId) {
        this.meetingId = meetingId;
    }

    public Integer getBranchId() {
        return branchId;
    }

    public void setBranchId(Integer branchId) {
        this.branchId = branchId;
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
        this.date = DateToString.getDateString("yyyy/MM/dd HH:mm:ss", date);
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId == null ? null : creatorId.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = DateToString.getDateString("yyyy/MM/dd HH:mm:ss", lastTime);
    }

    public Integer getClick() {
        return click;
    }

    public void setClick(Integer click) {
        this.click = click;
    }

    public String getCoverpath() {
        return coverpath;
    }

    public void setCoverpath(String coverpath) {
        this.coverpath = coverpath == null ? null : coverpath.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
    
    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video == null ? null : video.trim();
    }
}