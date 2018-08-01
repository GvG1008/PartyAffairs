package com.zqu.pa.controller.exam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zqu.pa.common.ServerResponse;
import com.zqu.pa.entity.exam.ExamCategory;
import com.zqu.pa.service.exam.ExamCategoryService;

@Controller
@RequestMapping("/examcategory")
public class ExamCategoryController {

    @Autowired
    private ExamCategoryService examCategoryService;
    
    /**
     * 获取全部题库分类
     * @return ExamCategory类集合
     */
    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ServerResponse getCategory() {
        
        return ServerResponse.createBySuccess(examCategoryService.getExamCategory());
    }
    
    /**
     * 新增一种题库分类
     * @param categoryName 题库分类名称
     * @return 插入成功与否
     */
    @ResponseBody
    @RequestMapping(value = "/{categoryName}", method = RequestMethod.POST)
    public ServerResponse createCategory(@PathVariable String categoryName) {
        
        int result = examCategoryService.createCategory(categoryName);
        if (result > 0)
            return ServerResponse.createBySuccess();
        return ServerResponse.createByError();
    } 
}
