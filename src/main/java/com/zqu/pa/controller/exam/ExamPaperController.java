package com.zqu.pa.controller.exam;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zqu.pa.common.ServerResponse;
import com.zqu.pa.entity.exam.ExamPaper;
import com.zqu.pa.service.exam.ExamPaperService;
import com.zqu.pa.vo.exam.Paper;

@Controller
@RequestMapping("/exampaper")
public class ExamPaperController {
    
    @Autowired
    private ExamPaperService examPaperService;
    
    /**
     * 返回随机生成的试卷内容
     * @param examId 管理员发布考试对应的考试ID
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/{examId}", method=RequestMethod.GET)
    public ServerResponse<Paper> getExamPaper(@PathVariable Integer examId) {
        
        Paper paper = examPaperService.getExamPaper(examId);
        ServerResponse<Paper> result = ServerResponse.createBySuccess(paper);
        return result;      
    }
    
    /**
     * 接收前端计算后的考试成绩和试卷信息
     * @param examId 考试ID
     * @param score  成绩
     * @param examPaper 试卷信息，前端只传入题目ID（questionId）和考生所选答案（userAnswer）
     * @return 返回及格分数线和目前考试最高分数
     */
    @ResponseBody
    @RequestMapping(value="/{examId}/{score}", method=RequestMethod.POST)
    public ServerResponse paperScore(@PathVariable Integer examId, @PathVariable Integer score,
            @RequestBody List<ExamPaper> examPaper) {
        
        Map<String, Integer> responseScore = examPaperService.updatePaperScore(examId, score, examPaper);
        return ServerResponse.createBySuccess(responseScore);
    }
}
