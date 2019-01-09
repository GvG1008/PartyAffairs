package com.zqu.pa.vo.meeting;

import com.zqu.pa.entity.meeting.Meeting;

/*多返回党支部名称 发布人姓名 头像*/
public class MeetingInfo2 {
    private Integer meetingId;

    private Integer branchId;
    
    private String branchName;

    private String title;

    private String date;

    private String creatorId;

    private String headImg;
    
    private String creatorName;
    
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLastTime() {
        return lastTime;
    }

    public void setLastTime(String lastTime) {
        this.lastTime = lastTime;
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
    
    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }
    public void setMeeting(Meeting meeting) {
        this.branchId = meeting.getBranchId();
        this.click = meeting.getClick();
        this.content = meeting.getContent();
        this.coverpath = meeting.getCoverpath();
        this.creatorId = meeting.getCreatorId();
        this.date = meeting.getDate();
        this.lastTime = meeting.getLastTime();
        this.meetingId = meeting.getMeetingId();
        this.state = meeting.getState();
        this.title = meeting.getTitle();
        this.video = meeting.getVideo();
    }
}
