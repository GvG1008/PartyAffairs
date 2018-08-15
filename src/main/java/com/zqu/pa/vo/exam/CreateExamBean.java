package com.zqu.pa.vo.exam;

import java.util.List;

import com.zqu.pa.entity.exam.ExamInfo;
import com.zqu.pa.entity.exam.ExamInfoReview;

public class CreateExamBean {
    
    private ExamInfo examInfo; //考试基本信息
    
    private ExamInfoReview examInfoReview; //单/多选题分数
    
    private List<String> userID; //考试参考人员用户ID数组
    
    private Integer categoryID; //考试对应题库分类ID
    
    /*{
    "examInfo": {
        "examTitle": "考试标题啊啊啊啊啊",
        "startTime": 1530587844000,
        "endTime": 1531019844000,
        "examPeriod": 30,
        "singleQuantity": 10,
        "multipleQuantity": 10,
        "passScore": 60
    },
    "examInfoReview": {
        "singleScore": 2,
        "multipleScore": 4
    },
    "userID": [
        123,
        456
    ],
    "categoryID": 1
    }*/

    public Integer getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Integer categoryID) {
        this.categoryID = categoryID;
    }

    public ExamInfo getExamInfo() {
        return examInfo;
    }

    public void setExamInfo(ExamInfo examInfo) {
        this.examInfo = examInfo;
    }

    public ExamInfoReview getExamInfoReview() {
        return examInfoReview;
    }

    public void setExamInfoReview(ExamInfoReview examInfoReview) {
        this.examInfoReview = examInfoReview;
    }

    public List<String> getUserID() {
        return userID;
    }

    public void setUserID(List<String> userID) {
        this.userID = userID;
    }

}
