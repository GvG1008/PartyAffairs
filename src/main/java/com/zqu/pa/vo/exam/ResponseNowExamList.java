package com.zqu.pa.vo.exam;

public class ResponseNowExamList extends ResponseExamList {

    private String startTime; //考试开始时间
    
    private String endTime; //考试结束时间
    
    private Integer status; //考试状态（-1：考试已过期；0：考试还未开始；1：考试正在进行）
    
    private Integer passScore; //及格分数
    
    private Integer examPeriod; //考试时长

    private Integer singleQuantity; //单选题数量

    private Integer multipleQuantity; //多选题数量
    
    private Integer singleScore; //单选题分数
    
    private Integer multipleScore; //多选题分数

    public Integer getSingleScore() {
        return singleScore;
    }

    public void setSingleScore(Integer singleScore) {
        this.singleScore = singleScore;
    }

    public Integer getMultipleScore() {
        return multipleScore;
    }

    public void setMultipleScore(Integer multipleScore) {
        this.multipleScore = multipleScore;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getPassScore() {
        return passScore;
    }

    public void setPassScore(Integer passScore) {
        this.passScore = passScore;
    }

    public Integer getExamPeriod() {
        return examPeriod;
    }

    public void setExamPeriod(Integer examPeriod) {
        this.examPeriod = examPeriod;
    }

    public Integer getSingleQuantity() {
        return singleQuantity;
    }

    public void setSingleQuantity(Integer singleQuantity) {
        this.singleQuantity = singleQuantity;
    }

    public Integer getMultipleQuantity() {
        return multipleQuantity;
    }

    public void setMultipleQuantity(Integer multipleQuantity) {
        this.multipleQuantity = multipleQuantity;
    }
}
