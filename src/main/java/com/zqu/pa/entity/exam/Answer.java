package com.zqu.pa.entity.exam;

import org.springframework.stereotype.Component;

@Component("answer")
public class Answer {
    private Integer answerId;

    private Integer questionId;

    private Integer choice;

    public Integer getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Integer answerId) {
        this.answerId = answerId;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public Integer getChoice() {
        return choice;
    }

    public void setChoice(Integer choice) {
        this.choice = choice;
    }
}