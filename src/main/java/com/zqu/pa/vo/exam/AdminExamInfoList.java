package com.zqu.pa.vo.exam;

import java.util.List;

public class AdminExamInfoList {

    private Integer examId; //考试ID
    
    private String examTitle; //考试标题
    
    private Integer branchId; //党支部ID
    
    private String branchName; //党支部名称
    
    private String createId; //创建人ID
    
    private String createName; //创建人名字
 
    private String startTime; //考试开始时间
    
    private String endTime; //考试结束时间
    
    private Integer status; //考试状态（-1：考试已过期；0：考试还未开始；1：考试正在进行）
    
    private Integer passScore; //及格分数
    
    private Integer examPeriod; //考试时长（分钟）

    private Integer singleQuantity; //单选题数量

    private Integer multipleQuantity; //多选题数量
    
    private Integer singleScore; //单选题分数
    
    private Integer multipleScore; //多选题分数
    
    private List<String> listUserID; //考试参与人员Id
    
    private List<String> listUserName; //考试参与人员名字

    public Integer getExamId() {
        return examId;
    }

    public void setExamId(Integer examId) {
        this.examId = examId;
    }

    public String getExamTitle() {
        return examTitle;
    }

    public void setExamTitle(String examTitle) {
        this.examTitle = examTitle;
    }

    public Integer getBranchId() {
        return branchId;
    }

    public void setBranchId(Integer branchId) {
        this.branchId = branchId;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getCreateId() {
        return createId;
    }

    public void setCreateId(String createId) {
        this.createId = createId;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
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

    public List<String> getListUserID() {
        return listUserID;
    }

    public void setListUserID(List<String> listUserID) {
        this.listUserID = listUserID;
    }

    public List<String> getListUserName() {
        return listUserName;
    }

    public void setListUserName(List<String> listUserName) {
        this.listUserName = listUserName;
    }
}
