package com.zqu.pa.vo.exam;

import java.util.List;

public class ResponseQuestionBank {

    private Integer branchId; //党支部ID
    
    private Integer categoryId; //分类ID
    
    private Integer userId; //用户ID
    
    private List<QuestionContent> singleQuestion; //单选题集合
    
    private List<QuestionContent> multipleQuestion; //多选题集合

    public Integer getBranchId() {
        return branchId;
    }

    public void setBranchId(Integer branchId) {
        this.branchId = branchId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public List<QuestionContent> getSingleQuestion() {
        return singleQuestion;
    }

    public void setSingleQuestion(List<QuestionContent> singleQuestion) {
        this.singleQuestion = singleQuestion;
    }

    public List<QuestionContent> getMultipleQuestion() {
        return multipleQuestion;
    }

    public void setMultipleQuestion(List<QuestionContent> multipleQuestion) {
        this.multipleQuestion = multipleQuestion;
    }
}