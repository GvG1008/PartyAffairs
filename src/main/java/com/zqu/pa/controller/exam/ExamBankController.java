package com.zqu.pa.controller.exam;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.collect.Lists;
import com.zqu.pa.common.ServerResponse;
import com.zqu.pa.dao.exam.QuestionBankMapper;
import com.zqu.pa.service.exam.QuestionBankService;
import com.zqu.pa.utils.FTPUtil;
import com.zqu.pa.vo.exam.Paper;
import com.zqu.pa.vo.exam.ResponseQuestionBank;

@Controller
@RequestMapping("/exambank")
public class ExamBankController {

    @Autowired
    private QuestionBankService questionBankService;
    
    /**
     * 接收用户上传的题库Excel文件，遍历其中的内容返回给前端显示，实现预览
     * @param categoryId 分类ID
     * @param file 用户上传的题库Excel文件
     * @return 文件中的数据封装为ResponseQuestionBank类
     */
    @ResponseBody
    @RequestMapping("/upload/{categoryId}")
    public ServerResponse upload(@PathVariable Integer categoryId, @RequestParam("file") MultipartFile file) {
   
        return questionBankService.analyseFile(file, categoryId);
    }
    
    /**
     * 前端预览后将原数据返回存入数据库
     * @param rqb 题库信息
     * @return 存储数据库是否成功
     */
    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ServerResponse saveQuestionBank(@RequestBody ResponseQuestionBank rqb) {
   
        int i = 0;
        try {
            i = questionBankService.saveQuestionBank(rqb);
        } catch(Exception e) {
            e.printStackTrace();
            return ServerResponse.createByError();
        }
        if (i == 1) {
        	return ServerResponse.createBySuccess();
        }            
        else {
        	return ServerResponse.createByError();  
        }                         
    }
    
    /**
     * 根据考试题库分类ID获取下面所有题目信息
     * @param categoryId 考试题库分类ID
     * @return Paper
     */
    @ResponseBody
    @RequestMapping(value = "/{categoryId}", method = RequestMethod.GET)
    public ServerResponse<Paper> getQuestionBank(@PathVariable Integer categoryId) {
        
        Paper paper = new Paper();
        try {
            paper = questionBankService.getQuestionBank(categoryId);
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.createByError();
        }
        if (paper == null) {
            return ServerResponse.createByError();
        }
        return ServerResponse.createBySuccess(paper);
    }

}
