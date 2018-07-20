package com.zqu.pa.vo.exam;

import java.util.List;

public class ResponsePaperInfo {

    private List<Questions> question;
    
    private Integer score;

    public List<Questions> getQuestion() {
        return question;
    }

    public void setQuestion(List<Questions> question) {
        this.question = question;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
class Questions {
    
    public Integer questionId;
    
    public List<Integer> userAnswer;
}
