package com.zqu.pa.controller.exam;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
     * @return ResponseExamCategory类集合
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
    
    /**
     * 修改题库分类名称
     * @param categoryId 题库ID
     * @param categoryName 题库新名称
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/{categoryId}", method = RequestMethod.PUT)
    public ServerResponse updateCategory(@PathVariable Integer categoryId, @RequestParam(value = "categoryName", required = true) String categoryName) {
        
        ExamCategory e = new ExamCategory();
        e.setCategoryId(categoryId);
        e.setCategoryName(categoryName);
        int result = examCategoryService.updateCategroy(e);
        if (result > 0)
            return ServerResponse.createBySuccess();
        return ServerResponse.createByError();
    }
    
    /**
     * 删除题库分类（目前只删除了题库分类表exam_category，并没有删除题库中的题目）
     * @param categoryId 题库ID
     * @return
     */
    @RequiresPermissions("examcategory:delete")
    @ResponseBody
    @RequestMapping(value="/{categoryId}", method = RequestMethod.DELETE)
    public ServerResponse removeCategory(@PathVariable Integer categoryId) {
        
        int result = examCategoryService.removeCategory(categoryId);
        if (result > 0)
            return ServerResponse.createBySuccess();
        return ServerResponse.createByError();
    }
    
}
