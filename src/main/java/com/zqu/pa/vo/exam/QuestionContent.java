package com.zqu.pa.vo.exam;

import java.util.List;

public class QuestionContent {

    private String questionContent; //问题内容

    private List<String> choice; //选项集合
    
    private List<Integer> answer; //答案集合，1对应A，2对应B...

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
}
