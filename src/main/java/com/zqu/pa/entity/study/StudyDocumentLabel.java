package com.zqu.pa.entity.study;

public class StudyDocumentLabel {
    private Integer orderby;

    private Integer documentId;

    private Integer labelId;

    public StudyDocumentLabel(Integer orderby, Integer documentId, Integer labelId) {
        this.orderby = orderby;
        this.documentId = documentId;
        this.labelId = labelId;
    }

    public StudyDocumentLabel() {
        super();
    }

    public Integer getOrderby() {
        return orderby;
    }

    public void setOrderby(Integer orderby) {
        this.orderby = orderby;
    }

    public Integer getDocumentId() {
        return documentId;
    }

    public void setDocumentId(Integer documentId) {
        this.documentId = documentId;
    }

    public Integer getLabelId() {
        return labelId;
    }

    public void setLabelId(Integer labelId) {
        this.labelId = labelId;
    }

    @Override
    public String toString() {
        return "StudyDocumentLabel [orderby=" + orderby + ", documentId=" + documentId + ", labelId=" + labelId + "]";
    }
    
}