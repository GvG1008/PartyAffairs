package com.zqu.pa.vo.study;

import java.util.List;

import com.zqu.pa.entity.study.StudyLabel;

public class StudyVideoVO3 {

    private int videoId;
    private String videoTitle;
    private String videoIntroduction;
    private String coverImg;
    private String videoPath;
    private String uploadUser;
    private String updateTime;
    private List<StudyLabel> studyLabels;
    private Integer already;

    public StudyVideoVO3() {
        super();
    }

    public StudyVideoVO3(int videoId, String videoTitle, String videoIntroduction, String coverImg, String videoPath,
            String uploadUser, String updateTime, List<StudyLabel> studyLabels, Integer already) {
        super();
        this.videoId = videoId;
        this.videoTitle = videoTitle;
        this.videoIntroduction = videoIntroduction;
        this.coverImg = coverImg;
        this.videoPath = videoPath;
        this.uploadUser = uploadUser;
        this.updateTime = updateTime;
        this.studyLabels = studyLabels;
        this.already = already;
    }

    public int getVideoId() {
        return videoId;
    }

    public void setVideoId(int videoId) {
        this.videoId = videoId;
    }

    public String getVideoTitle() {
        return videoTitle;
    }

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }

    public String getVideoIntroduction() {
        return videoIntroduction;
    }

    public void setVideoIntroduction(String videoIntroduction) {
        this.videoIntroduction = videoIntroduction;
    }

    public String getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg;
    }

    public String getVideoPath() {
        return videoPath;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }

    public String getUploadUser() {
        return uploadUser;
    }

    public void setUploadUser(String uploadUser) {
        this.uploadUser = uploadUser;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public List<StudyLabel> getStudyLabels() {
        return studyLabels;
    }

    public void setStudyLabels(List<StudyLabel> studyLabels) {
        this.studyLabels = studyLabels;
    }

    public Integer getAlready() {
        return already;
    }

    public void setAlready(Integer already) {
        this.already = already;
    }

    @Override
    public String toString() {
        return "StudyVideoVO2 [videoId=" + videoId + ", videoTitle=" + videoTitle + ", videoIntroduction="
                + videoIntroduction + ", coverImg=" + coverImg + ", videoPath=" + videoPath + ", uploadUser="
                + uploadUser + ", updateTime=" + updateTime + ", studyLabels=" + studyLabels + ", already= " + already + "]";
    }
    
    
}
