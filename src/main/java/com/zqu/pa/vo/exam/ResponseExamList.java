package com.zqu.pa.vo.exam;

public class ResponseExamList {

    private Integer examId; //考试ID
    
    private String examTitle; //考试标题
    
    private Integer score; //考生成绩
    
    private Integer pass = -1; //考试是否及格（-1：未参加考试；0：不及格；1：及格）
    
    private String startTime; //考试开始时间
    
    private String endTime; //考试结束时间

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

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getPass() {
        return pass;
    }

    public void setPass(Integer pass) {
        this.pass = pass;
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
       
}
