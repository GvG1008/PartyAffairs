package com.zqu.pa.controller.exam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zqu.pa.common.ServerResponse;
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
}
