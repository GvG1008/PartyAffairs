package com.zqu.pa.service.exam;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

import com.zqu.pa.common.ServerResponse;
import com.zqu.pa.vo.exam.ResponseQuestionBank;

public interface QuestionBankService {

    ResponseQuestionBank getUploadQuestion(MultipartFile file, Integer categoryId);
    
    /**
     * 读取文件中第一个sheet（单选题）和第二个sheet（多选题）内容，存入ResponseQuestionBank类返回
     * @param file 上传题库的Excel文件
     * @param categoryId 此份题库对应的分类ID
     * @return 读取文件格式化为ResponseQuestionBank类的数据
     */
    ServerResponse analyseFile(MultipartFile file, Integer categoryId);
    
    //将题库信息存入数据库
    Integer saveQuestionBank(ResponseQuestionBank responseQuestionBank);
}
