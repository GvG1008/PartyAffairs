package com.zqu.pa.vo.exam;

import java.util.List;

import org.springframework.stereotype.Component;

@Component("paper")
public class Paper {

    private Integer singleQuantity;
    
    private Integer singleScore;

    private List<Question> singleQuestion;
    
    private Integer multipleQuantity;
    
    private Integer multipleScore;
    
    private List<Question> multipleQuestion;
    
    private Integer examPeriod;

    public Integer getSingleQuantity() {
        return singleQuantity;
    }

    public void setSingleQuantity(Integer singleQuantity) {
        this.singleQuantity = singleQuantity;
    }

    public List<Question> getSingleQuestion() {
        return singleQuestion;
    }

    public void setSingleQuestion(List<Question> singleQuestion) {
        this.singleQuestion = singleQuestion;
    }

    public Integer getMultipleQuantity() {
        return multipleQuantity;
    }

    public void setMultipleQuantity(Integer multipleQuantity) {
        this.multipleQuantity = multipleQuantity;
    }

    public List<Question> getMultipleQuestion() {
        return multipleQuestion;
    }

    public void setMultipleQuestion(List<Question> multipleQuestion) {
        this.multipleQuestion = multipleQuestion;
    }

    public Integer getExamPeriod() {
        return examPeriod;
    }

    public void setExamPeriod(Integer examPeriod) {
        this.examPeriod = examPeriod;
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
    
    
}
