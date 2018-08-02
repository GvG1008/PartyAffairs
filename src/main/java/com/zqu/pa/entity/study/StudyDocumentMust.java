package com.zqu.pa.entity.study;

public class StudyDocumentMust {
    private int documentId;
    private String userId;
    public StudyDocumentMust() {
        super();
        // TODO Auto-generated constructor stub
    }
    public StudyDocumentMust(int documentId, String userId) {
        super();
        this.documentId = documentId;
        this.userId = userId;
    }
    public int getDocumentId() {
        return documentId;
    }
    public void setDocumentId(int documentId) {
        this.documentId = documentId;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    @Override
    public String toString() {
        return "StudyDocumentMust [documentId=" + documentId + ", userId=" + userId + "]";
    }
    
}
