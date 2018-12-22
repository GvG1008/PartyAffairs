package com.zqu.pa.vo.exam;

public class ExamScoreList {

    private Integer examId; //考试ID

    private Integer branchId; //党支部ID

    private String examTitle; //考试标题

    private Long startTime; //考试开始时间

    private Long endTime; //考试结束时间
    
    private String stringStartTime; //考试开始时间
    
    private String stringEndTime; //考试结束时间
    
    private Integer passScore; //及格分数
    
    private Integer examNumbers; //考试人数
    
    private Integer passNumbers; //及格人数
    
    private Integer notTakeExamNumbers; //还未参加考试人数

    public Integer getExamId() {
        return examId;
    }

    public void setExamId(Integer examId) {
        this.examId = examId;
    }

    public Integer getBranchId() {
        return branchId;
    }

    public void setBranchId(Integer branchId) {
        this.branchId = branchId;
    }

    public String getExamTitle() {
        return examTitle;
    }

    public void setExamTitle(String examTitle) {
        this.examTitle = examTitle;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public String getStringStartTime() {
        return stringStartTime;
    }

    public void setStringStartTime(String stringStartTime) {
        this.stringStartTime = stringStartTime;
    }

    public String getStringEndTime() {
        return stringEndTime;
    }

    public void setStringEndTime(String stringEndTime) {
        this.stringEndTime = stringEndTime;
    }

    public Integer getPassScore() {
        return passScore;
    }

    public void setPassScore(Integer passScore) {
        this.passScore = passScore;
    }

    public Integer getExamNumbers() {
        return examNumbers;
    }

    public void setExamNumbers(Integer examNumbers) {
        this.examNumbers = examNumbers;
    }

    public Integer getPassNumbers() {
        return passNumbers;
    }

    public void setPassNumbers(Integer passNumbers) {
        this.passNumbers = passNumbers;
    }

    public Integer getNotTakeExamNumbers() {
        return notTakeExamNumbers;
    }

    public void setNotTakeExamNumbers(Integer notTakeExamNumbers) {
        this.notTakeExamNumbers = notTakeExamNumbers;
    }
    
}
