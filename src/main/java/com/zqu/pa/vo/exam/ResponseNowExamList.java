package com.zqu.pa.vo.exam;

public class ResponseNowExamList extends ResponseExamList {

    private String startTime;
    
    private String endTime;
    
    private Integer status;
    
    private Integer passScore;
    
    private Integer examPeriod;

    private Integer singleQuantity;

    private Integer multipleQuantity;
    
    private Integer singleScore;
    
    private Integer multipleScore;

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
