package com.zqu.pa.entity.exam;

import org.springframework.stereotype.Component;

@Component("examScore")
public class ExamScore extends ExamScoreKey {
    private Integer score;

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}