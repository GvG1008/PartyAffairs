package com.zqu.pa.vo.study;

import java.util.List;

import com.zqu.pa.entity.study.StudyLabel;

public class StudyDocumentVO1 {
    private Integer documentId;
    private String documentTitle;
    private String documentIntroduction;
    private String coverImg;
    private String filePath;
    private String updateTime;
    private String uploadUser;
    private Integer downloadTimes;
    private List<StudyLabel> studyLabels;
    public StudyDocumentVO1() {
        super();
    }
    public StudyDocumentVO1(Integer documentId, String documentTitle, String documentIntroduction, String coverImg,
            String filePath, String updateTime, String uploadUser, Integer downloadTimes,
            List<StudyLabel> studyLabels) {
        super();
        this.documentId = documentId;
        this.documentTitle = documentTitle;
        this.documentIntroduction = documentIntroduction;
        this.coverImg = coverImg;
        this.filePath = filePath;
        this.updateTime = updateTime;
        this.uploadUser = uploadUser;
        this.downloadTimes = downloadTimes;
        this.studyLabels = studyLabels;
    }
    public Integer getDocumentId() {
        return documentId;
    }
    public void setDocumentId(Integer documentId) {
        this.documentId = documentId;
    }
    public String getDocumentTitle() {
        return documentTitle;
    }
    public void setDocumentTitle(String documentTitle) {
        this.documentTitle = documentTitle;
    }
    public String getDocumentIntroduction() {
        return documentIntroduction;
    }
    public void setDocumentIntroduction(String documentIntroduction) {
        this.documentIntroduction = documentIntroduction;
    }
    public String getCoverImg() {
        return coverImg;
    }
    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg;
    }
    public String getFilePath() {
        return filePath;
    }
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
    public String getUpdateTime() {
        return updateTime;
    }
    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
    public String getUploadUser() {
        return uploadUser;
    }
    public void setUploadUser(String uploadUser) {
        this.uploadUser = uploadUser;
    }
    public Integer getDownloadTimes() {
        return downloadTimes;
    }
    public void setDownloadTimes(Integer downloadTimes) {
        this.downloadTimes = downloadTimes;
    }
    public List<StudyLabel> getStudyLabels() {
        return studyLabels;
    }
    public void setStudyLabels(List<StudyLabel> studyLabels) {
        this.studyLabels = studyLabels;
    }
    @Override
    public String toString() {
        return "StudyDocumentVO1 [documentId=" + documentId + ", documentTitle=" + documentTitle
                + ", documentIntroduction=" + documentIntroduction + ", coverImg=" + coverImg + ", filePath=" + filePath
                + ", updateTime=" + updateTime + ", uploadUser=" + uploadUser + ", downloadTimes=" + downloadTimes
                + ", studyLabels=" + studyLabels + "]";
    }
    
}
