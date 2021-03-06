package com.zqu.pa.entity.exam;

import org.springframework.stereotype.Component;

@Component("examScoreKey")
public class ExamScoreKey {
    private Integer examId;

    private String userId;

    public Integer getExamId() {
        return examId;
    }

    public void setExamId(Integer examId) {
        this.examId = examId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }
}