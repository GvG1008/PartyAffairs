package com.zqu.pa.controller.exam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zqu.pa.dao.exam.ChoiceMapper;
import com.zqu.pa.entity.exam.Choice;

@Controller
@RequestMapping("/exampaper")
public class ExamPaperController {

    @Autowired
    private ChoiceMapper c;
    @ResponseBody
    @RequestMapping("")
    public Choice getChoice() {
        
        System.out.println("ExamPaperController");
        return c.selectByPrimaryKey(1);       
    }
}
