package com.zqu.pa.service.exam.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zqu.pa.dao.exam.AnswerMapper;
import com.zqu.pa.dao.exam.ChoiceMapper;
import com.zqu.pa.dao.exam.ExamInfoCategoryMapper;
import com.zqu.pa.dao.exam.ExamInfoMapper;
import com.zqu.pa.dao.exam.ExamPaperMapper;
import com.zqu.pa.dao.exam.ExamScoreMapper;
import com.zqu.pa.dao.exam.QuestionBankMapper;
import com.zqu.pa.dao.exam.QuestionExamCategoryMapper;
import com.zqu.pa.entity.exam.Answer;
import com.zqu.pa.entity.exam.AnswerExample;
import com.zqu.pa.entity.exam.Choice;
import com.zqu.pa.entity.exam.ChoiceExample;
import com.zqu.pa.entity.exam.ExamInfo;
import com.zqu.pa.entity.exam.ExamInfoCategoryExample;
import com.zqu.pa.entity.exam.ExamInfoCategoryKey;
import com.zqu.pa.entity.exam.ExamPaper;
import com.zqu.pa.entity.exam.ExamPaperExample;
import com.zqu.pa.entity.exam.ExamScore;
import com.zqu.pa.entity.exam.ExamScoreKey;
import com.zqu.pa.entity.exam.QuestionBank;
import com.zqu.pa.entity.exam.QuestionExamCategoryExample;
import com.zqu.pa.entity.exam.QuestionExamCategoryKey;
import com.zqu.pa.service.exam.ExamPaperService;
import com.zqu.pa.vo.exam.Paper;
import com.zqu.pa.vo.exam.Question;
import com.zqu.pa.vo.userInfo.UserBasicInfo;

@Service
public class ExamPaperServiceImpl implements ExamPaperService {

    @Autowired
    private Paper paper;
    
    @Autowired
    private QuestionBank questionBank;   
    
    @Autowired
    private ExamInfo examInfo;
    
    @Autowired
    private ExamScoreKey examScoreKey;
    
    @Autowired
    private ExamScore examScore;
    
    @Autowired
    private QuestionBankMapper questionBankMapper;
    
    @Autowired
    private ExamInfoMapper examInfoMapper;
    
    @Autowired
    private ChoiceMapper choiceMapper;
    
    @Autowired
    private AnswerMapper answerMapper;
    
    @Autowired
    private ExamScoreMapper examScoreMapper;
    
    @Autowired
    private ExamPaperMapper examPaperMapper;
    
    @Autowired
    private ExamInfoCategoryMapper examInfoCategoryMapper;
    
    @Autowired
    private QuestionExamCategoryMapper questionExamCategoryMapper;
    
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
        
        //将分钟转换为秒数
        Integer examPeriod = examInfo.getExamPeriod();
        examPeriod *= 60; 
        paper.setExamPeriod(examPeriod);
        paper.setSingleQuantity(singleQuantity);
        paper.setMultipleQuantity(multipleQuantity);
        
        //此次考试对应的分类id
        List<Integer> listCategory = listExamCategory(examId);
        //找出此次考试所有分类下的所有question_id
        List<Integer> listQuestionId = listCategoryQuestionId(listCategory);
        
        //type = 0:单选题
        //type = 1:多选题
        Integer type = 0;
        paper.setSingleQuestion(getQuestion(branchId, singleQuantity, type, listQuestionId));
        type = 1;
        paper.setMultipleQuestion(getQuestion(branchId, multipleQuantity, type, listQuestionId));
        
        return paper;
    }
    
    @Override
    public List<Integer> listExamCategory(Integer examId) {
        
        ExamInfoCategoryExample example = new ExamInfoCategoryExample();
        example.createCriteria().andExamIdEqualTo(examId);
        List<ExamInfoCategoryKey> temp = examInfoCategoryMapper.selectByExample(example);
        List<Integer> listCategory = new ArrayList<Integer>();
        for (int i = 0; i < temp.size(); i++)
            listCategory.add(temp.get(i).getCategoryId());
        return listCategory;
    }
    
    @Override
    public List<Integer> listCategoryQuestionId(List<Integer> listCategory) {
        
        QuestionExamCategoryExample example = new QuestionExamCategoryExample();
        example.createCriteria().andCategoryIdIn(listCategory);
        List<QuestionExamCategoryKey> temp = questionExamCategoryMapper.selectByExample(example);
        List<Integer> listQuestionId = new ArrayList<Integer>();
        for (int i = 0; i < temp.size(); i++)
            listQuestionId.add(temp.get(i).getQuestionId());
        return listQuestionId;
    }

    @Override
    public List<Question> getQuestion(Integer branchId, Integer quantity, Integer type, List<Integer> listQuestionId) {

        //TODO 错误检测，返回值为空时，题库数量不足，题目、选项、答案不一致
        
        List<Question> question = new ArrayList<Question>();
        //随机选择获得题库信息
        List<QuestionBank> listQuestionBank = questionBankMapper.selectRand(branchId, quantity, type, listQuestionId);
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

        AnswerExample example = new AnswerExample();
        example.createCriteria().andQuestionIdIn(questionId);
        List<Answer> answer = answerMapper.selectByExample(example);
        return answer;
    }

    @Transactional
    @Override
    public Map<String, Integer> updatePaperScore(Integer examId, Integer score, List<ExamPaper> examPaper) {

        UserBasicInfo basicInfo = (UserBasicInfo)SecurityUtils.getSubject().getSession().getAttribute("basicInfo");
        String userId = basicInfo.getUserId();
        if (userId == null) {
            
        }
        Map<String, Integer> responseScore = new HashMap<>();       
        
        //前端接收ExamPaper只有两个字段，将剩余字段补全
        for(ExamPaper e : examPaper) {
            e.setExamId(examId);
            e.setUserId(userId);
        }
        Integer topScore = getPaperScore(examId, userId);
        //查无成绩，说明第一次考试，保存成绩和试卷
        if(topScore == null) {
            topScore = score;      
            if (insertExamPaper(examPaper) > 0) {
                //System.out.println("插入试卷成功");
            } else {
                //TODO 插入试卷失败处理
            }
            if (insertExamScore(score, examId, userId) > 0) {
                //System.out.println("插入成绩成功");
            } else {
              //TODO 插入成绩失败处理
            }          
        }
        //本次考试高于历史考试最高分，更新最高成绩和对应试卷
        //低于等于历史考试最高分，不更新成绩和试卷（即此次考试不保存）
        else if (score > topScore) {
            topScore = score;
            if (updateExamPaper(examPaper, userId, examId) > 0) {
                System.out.println("更新试卷成功");
            } else {
                //TODO 更新试卷失败处理
            }
            if (updateExamScore(score, examId, userId) > 0) {
                System.out.println("更新成绩成功");
            } else {
                //TODO 更新成绩失败处理
            }
            topScore = score;
        } 

        //及格分数线
        Integer passScore = getPassScore(examId);
        responseScore.put("passScore", passScore);
        responseScore.put("topScore", topScore);
        return responseScore;
    }

    @Override
    public Integer getPaperScore(Integer examId, String userId) {

        examScoreKey.setExamId(examId);
        examScoreKey.setUserId(userId);
        examScore = examScoreMapper.selectByPrimaryKey(examScoreKey);
        if (examScore == null)
            return null;
        return examScore.getScore();
    }

    @Override
    public Integer insertExamPaper(List<ExamPaper> examPaper) {
        
        return examPaperMapper.insertList(examPaper);
    }

    @Override
    public Integer insertExamScore(Integer score, Integer examId, String userId) {
        
        ExamScore es = new ExamScore();
        es.setExamId(examId);
        es.setScore(score);        
        es.setUserId(userId);
        return examScoreMapper.insertSelective(es);
    }

    @Override
    public Integer getPassScore(Integer examId) {

        return examInfoMapper.selectPassScore(examId);
    }

    @Transactional
    @Override
    public Integer updateExamPaper(List<ExamPaper> examPaper, String userId, Integer examId) {
        
        //删除原试卷
        ExamPaperExample example = new ExamPaperExample();
        example.createCriteria().andUserIdEqualTo(userId).andExamIdEqualTo(examId);
        examPaperMapper.deleteByExample(example);
        
        //插入试卷
        return insertExamPaper(examPaper);
    }

    @Override
    public Integer updateExamScore(Integer score, Integer examId, String userId) {

        ExamScore e = new ExamScore();
        e.setExamId(examId);
        e.setUserId(userId);
        e.setScore(score);
        return examScoreMapper.updateByPrimaryKeySelective(e);
    }

}
