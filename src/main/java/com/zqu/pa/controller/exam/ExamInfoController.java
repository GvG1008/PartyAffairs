package com.zqu.pa.controller.exam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zqu.pa.common.ServerResponse;
import com.zqu.pa.entity.exam.ExamInfo;
import com.zqu.pa.entity.exam.ExamInfoReview;
import com.zqu.pa.service.exam.ExamInfoService;

@Controller
@RequestMapping("/examinfo")
public class ExamInfoController {

    @Autowired
    private ExamInfoService examInfoService;
    
    /**
     * 创建一场考试信息
     * @param examInfo 考试基本信息
     * @param examInfoReview 单/多选题分数
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ServerResponse createExamInfo(ExamInfo examInfo, ExamInfoReview examInfoReview) {
        
        ServerResponse result = null;
        try {
            result = examInfoService.createExamInfo(examInfo, examInfoReview);
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.createByError();
        }
        return result;
    }
    
}
