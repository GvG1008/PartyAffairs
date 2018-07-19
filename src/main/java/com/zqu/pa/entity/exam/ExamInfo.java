package com.zqu.pa.entity.exam;

import org.springframework.stereotype.Component;

@Component("examInfo")
public class ExamInfo {
    private Integer examId;

    private Integer branchId;

    private String examTitle;

    private Long startTime;

    private Long endTime;

    private Integer examPeriod;

    private Integer singleQuantity;

    private Integer multipleQuantity;

    private Integer passScore;

    private Integer roleId;

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
        this.examTitle = examTitle == null ? null : examTitle.trim();
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

    public Integer getPassScore() {
        return passScore;
    }

    public void setPassScore(Integer passScore) {
        this.passScore = passScore;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}