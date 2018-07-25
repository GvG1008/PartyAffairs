package com.zqu.pa.entity.study;

import java.util.Date;

public class StudyVideo {
    private Integer videoId;

    private String videoTitle;

    private String videoIntroduction;

    private String coverImg;

    private String videoPath;

    private String userId;

    private Date createtime;

    private Date updatetime;

    public StudyVideo(Integer videoId, String videoTitle, String videoIntroduction, String coverImg, String videoPath, String userId, Date createtime, Date updatetime) {
        this.videoId = videoId;
        this.videoTitle = videoTitle;
        this.videoIntroduction = videoIntroduction;
        this.coverImg = coverImg;
        this.videoPath = videoPath;
        this.userId = userId;
        this.createtime = createtime;
        this.updatetime = updatetime;
    }

    public StudyVideo() {
        super();
    }

    public Integer getVideoId() {
        return videoId;
    }

    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }

    public String getVideoTitle() {
        return videoTitle;
    }

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle == null ? null : videoTitle.trim();
    }

    public String getVideoIntroduction() {
        return videoIntroduction;
    }

    public void setVideoIntroduction(String videoIntroduction) {
        this.videoIntroduction = videoIntroduction == null ? null : videoIntroduction.trim();
    }

    public String getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg == null ? null : coverImg.trim();
    }

    public String getVideoPath() {
        return videoPath;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath == null ? null : videoPath.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
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