package com.zqu.pa.service.exam;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.zqu.pa.entity.exam.Answer;
import com.zqu.pa.entity.exam.Choice;
import com.zqu.pa.entity.exam.ExamInfoCategoryKey;
import com.zqu.pa.entity.exam.ExamPaper;
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
     * @param category 分类对应的所有题目id
     * @return
     */
    List<Question> getQuestion(Integer branchId, Integer quantity, Integer type, List<Integer> listQuestionId);
    
    //查出此次考试对应的分类列表id
    List<Integer> listExamCategory(Integer examId);
    
    //查出对应分类列表id下的所有question_id
    List<Integer> listCategoryQuestionId(List<Integer> listCategory);
       
    //根据一个question_id集合查找对应所有选项
    List<Choice> listChoice(List<Integer> questionId);
    
    //根据一个question_id集合查找对应所有答案
    List<Answer> listAnswer(List<Integer> questionId);
    
    /**
     * 将考生此次成绩与以往成绩对比，如果高于以往分数则更新成绩和试卷，低于则不保存此次成绩
     * 对于管理员发布的一次考试考生可以多次进行考试，只保留最高分数和对应的试卷
     * @param examId 考试ID
     * @param score 此次考试成绩
     * @param examPaper 此次试卷信息
     * @return
     */
    Map<String, Integer> updatePaperScore(Integer examId, Integer score,
            List<ExamPaper> examPaper);
    
    //根据examId和用户ID获取考试成绩
    Integer getPaperScore(Integer examId, String userId);
    
    //第一次考试，将试卷信息存入数据库
    Integer insertExamPaper(List<ExamPaper> examPaper);
    
    //第一次考试，将考试成绩存入数据库
    Integer insertExamScore(Integer score, Integer examId, String userId);
    
    //获取考试及格分数线
    Integer getPassScore(Integer examId);
    
    //XXX 可以对原试卷question_id和user_answer字段进行更行操作
    //更新保存考生此次试卷（先删除原先试卷，再执行插入操作）
    Integer updateExamPaper(List<ExamPaper> examPaper, String userId, Integer examId);
    
    //更新成绩
    Integer updateExamScore(Integer score, Integer examId, String userId);
}
