package com.zqu.pa.vo.exam;

import java.util.List;

public class Question {

    private Integer questionId;

    private String questionContent;

    private List<String> choice;
    
    private List<Integer> answer;
    
    private List<Integer> userAnswer;
    
    private boolean correct;

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    public List<String> getChoice() {
        return choice;
    }

    public void setChoice(List<String> choice) {
        this.choice = choice;
    }

    public List<Integer> getAnswer() {
        return answer;
    }

    public void setAnswer(List<Integer> answer) {
        this.answer = answer;
    }

    public List<Integer> getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(List<Integer> userAnswer) {
        this.userAnswer = userAnswer;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }
    
}
