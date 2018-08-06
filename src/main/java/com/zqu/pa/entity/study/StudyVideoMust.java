package com.zqu.pa.entity.study;

public class StudyVideoMust {
    private int videoId;
    private String userId;
    public StudyVideoMust() {
        super();
        // TODO Auto-generated constructor stub
    }
    public StudyVideoMust(int videoId, String userId) {
        super();
        this.videoId = videoId;
        this.userId = userId;
    }
    public int getVideoId() {
        return videoId;
    }
    public void setVideoId(int videoId) {
        this.videoId = videoId;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    @Override
    public String toString() {
        return "StudyVideoMust [videoId=" + videoId + ", userId=" + userId + "]";
    }
    
}
