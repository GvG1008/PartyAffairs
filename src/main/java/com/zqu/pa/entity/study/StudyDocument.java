package com.zqu.pa.entity.study;

import java.util.Date;

public class StudyDocument {
    private Integer documentId;

    private String documentTitle;

    private String documentIntroduction;

    private String coverImg;

    private String filePath;

    private String userId;

    private Date createtime;

    private Date updatetime;

    public StudyDocument(Integer documentId, String documentTitle, String documentIntroduction, String coverImg, String filePath, String userId, Date createtime, Date updatetime) {
        this.documentId = documentId;
        this.documentTitle = documentTitle;
        this.documentIntroduction = documentIntroduction;
        this.coverImg = coverImg;
        this.filePath = filePath;
        this.userId = userId;
        this.createtime = createtime;
        this.updatetime = updatetime;
    }

    public StudyDocument() {
        super();
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
        this.documentTitle = documentTitle == null ? null : documentTitle.trim();
    }

    public String getDocumentIntroduction() {
        return documentIntroduction;
    }

    public void setDocumentIntroduction(String documentIntroduction) {
        this.documentIntroduction = documentIntroduction == null ? null : documentIntroduction.trim();
    }

    public String getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg == null ? null : coverImg.trim();
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath == null ? null : filePath.trim();
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