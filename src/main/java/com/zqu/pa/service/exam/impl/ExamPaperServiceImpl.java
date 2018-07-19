package com.zqu.pa.service.exam.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zqu.pa.dao.exam.AnswerMapper;
import com.zqu.pa.dao.exam.ChoiceMapper;
import com.zqu.pa.dao.exam.ExamInfoMapper;
import com.zqu.pa.dao.exam.QuestionBankMapper;
import com.zqu.pa.entity.exam.Answer;
import com.zqu.pa.entity.exam.AnswerExample;
import com.zqu.pa.entity.exam.Choice;
import com.zqu.pa.entity.exam.ChoiceExample;
import com.zqu.pa.entity.exam.ExamInfo;
import com.zqu.pa.entity.exam.QuestionBank;
import com.zqu.pa.service.exam.ExamPaperService;
import com.zqu.pa.vo.exam.Paper;
import com.zqu.pa.vo.exam.Question;

@Service
public class ExamPaperServiceImpl implements ExamPaperService {

    @Autowired
    private Paper paper;
    
    @Autowired
    private QuestionBank questionBank;   
    
    @Autowired
    private ExamInfo examInfo;
    
    @Autowired
    private QuestionBankMapper questionBankMapper;
    
    @Autowired
    private ExamInfoMapper examInfoMapper;
    
    @Autowired
    private ChoiceMapper choiceMapper;
    
    @Autowired
    private AnswerMapper answerMapper;
    
    @Override
    public Paper getExamPaper(Integer examId) {
        
        //根据examId查找考试信息表examInfo
        examInfo = examInfoMapper.selectByPrimaryKey(examId);
        //党支部ID
        Integer branchId = examInfo.getBranchId();
        //单选题数量
        Integer singleQuantity = examInfo.getSingleQuantity();
        //多选题数量
        Integer multipleQuantity = examInfo.getMultipleQuantity();
        
        paper.setExamPeriod(examInfo.getExamPeriod());
        paper.setSingleQuantity(singleQuantity);
        paper.setMultipleQuantity(multipleQuantity);

        //type = 0:单选题
        //type = 1:多选题
        Integer type = 0;
        paper.setSingleQuestion(getQuestion(branchId, singleQuantity, type));
        type = 1;
        paper.setMultipleQuestion(getQuestion(branchId, multipleQuantity, type));
        
        return paper;
    }

    @Override
    public List<Question> getQuestion(Integer branchId, Integer quantity, Integer type) {

        //TODO 错误检测，返回值为空时，题库数量不足，题目、选项、答案不一致
        
        List<Question> question = new ArrayList<Question>();
        //随机选择获得题库信息
        List<QuestionBank> listQuestionBank = questionBankMapper.selectRand(branchId, quantity, type);
        //获取的题目ID集合
        List<Integer> listQuestionID = new ArrayList<Integer>();
        for (int i = 0; i < listQuestionBank.size(); i++)
            listQuestionID.add(listQuestionBank.get(i).getQuestionId());
        //根据题目ID集合查找选项集合
        List<Choice> choice = listChoice(listQuestionID);
        //根据题目ID集合查找答案集合
        List<Answer> answer = listAnswer(listQuestionID);
        
        //将题目集合、选项集合、答案集合根据题目ID合并成Question类集合
        Integer questionID = 0;
        for (int i = 0; i < listQuestionBank.size(); i++) {
            questionID = listQuestionBank.get(i).getQuestionId();
            List<String> listChoice = new ArrayList<String>();
            List<Integer> listAnswer = new ArrayList<Integer>();
            for (int j = 0; j < choice.size(); j++) {              
                if (questionID == choice.get(j).getQuestionId())
                    listChoice.add(choice.get(j).getChoiceContent());
            }
            for (int k = 0; k < answer.size(); k++) {               
                if (questionID == answer.get(k).getQuestionId())
                    listAnswer.add(answer.get(k).getChoice());
            }
            Question q = new Question();
            q.setQuestionId(questionID);
            q.setQuestionContent(listQuestionBank.get(i).getQuestionContent());
            q.setChoice(listChoice);
            q.setAnswer(listAnswer);
            question.add(q);
           
        }
      
        return question;
    }

    @Override
    public List<Choice> listChoice(List<Integer> questionId) {
        
        ChoiceExample example = new ChoiceExample();
        example.createCriteria().andQuestionIdIn(questionId);
        //example.setOrderByClause("choice asc");
        List<Choice> choice = choiceMapper.selectByExample(example);
        return choice;
    }

    @Override
    public List<Answer> listAnswer(List<Integer> questionId) {
        // TODO Auto-generated method stub
        AnswerExample example = new AnswerExample();
        example.createCriteria().andQuestionIdIn(questionId);
        List<Answer> answer = answerMapper.selectByExample(example);
        return answer;
    }

}
