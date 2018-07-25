package com.zqu.pa.entity.study;

public class StudyVideoLabel {
    private Integer orderby;

    private Integer videoId;

    private Integer labelId;

    public StudyVideoLabel(Integer orderby, Integer videoId, Integer labelId) {
        this.orderby = orderby;
        this.videoId = videoId;
        this.labelId = labelId;
    }

    public StudyVideoLabel() {
        super();
    }

    public Integer getOrderby() {
        return orderby;
    }

    public void setOrderby(Integer orderby) {
        this.orderby = orderby;
    }

    public Integer getVideoId() {
        return videoId;
    }

    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }

    public Integer getLabelId() {
        return labelId;
    }

    public void setLabelId(Integer labelId) {
        this.labelId = labelId;
    }
}