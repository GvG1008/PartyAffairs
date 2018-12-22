package com.zqu.pa.controller.exam;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zqu.pa.common.ServerResponse;
import com.zqu.pa.service.exam.ExamScoreService;
import com.zqu.pa.vo.exam.ExamScoreDetail;
import com.zqu.pa.vo.exam.ExamScoreList;
import com.zqu.pa.vo.exam.ResponseExamList;

@Controller
@RequestMapping("/examscore")
public class ExamScoreController {

    @Autowired
    private ExamScoreService examScoreService;
    
    /**
     * 考试成绩情况列表
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ServerResponse<List<ExamScoreList>> listExamScore() {
        
        ServerResponse<List<ExamScoreList>> list = null;
        try {
            list = examScoreService.listExamScore();
        } catch(Exception e) {
            e.printStackTrace();
            return ServerResponse.createByError();
        }
        return list;
    }
    
    /**
     * 获取考试成绩详情（每个参考人员成绩详情）
     * @param examID 考试ID
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/{examID}", method = RequestMethod.GET)
    public ServerResponse<List<ExamScoreDetail>> listExamScoreDetail(@PathVariable Integer examID) {
        
        ServerResponse<List<ExamScoreDetail>> list = null;
        try {
            list = examScoreService.listExamScoreDetail(examID);
        } catch(Exception e) {
            e.printStackTrace();
            return ServerResponse.createByError();
        }
        return list;
    }
}
