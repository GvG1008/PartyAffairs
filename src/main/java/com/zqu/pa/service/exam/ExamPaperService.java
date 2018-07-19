package com.zqu.pa.service.exam;

import java.util.List;

import com.zqu.pa.entity.exam.Answer;
import com.zqu.pa.entity.exam.Choice;
import com.zqu.pa.vo.exam.Paper;
import com.zqu.pa.vo.exam.Question;

public interface ExamPaperService {

    /**
     * 根据考试ID查找考试信息，随机组卷后封装成Paper
     * @param examId 管理员发布考试对应的考试ID
     * @return
     */
    Paper getExamPaper(Integer examId);
    
    /**
     * 根据党支部和题目数量取单选或多选题库封装成Question集合
     * @param branchId 党支部ID
     * @param quantity 要取的题目数量
     * @param type     题目类型(0:单选；1:多选)
     * @return
     */
    List<Question> getQuestion(Integer branchId, Integer quantity, Integer type);
       
    //根据一个question_id集合查找对应所有选项
    List<Choice> listChoice(List<Integer> questionId);
    
    //根据一个question_id集合查找对应所有答案
    List<Answer> listAnswer(List<Integer> questionId);
}
