package com.zqu.pa.service.exam;

import java.util.List;

import com.zqu.pa.entity.exam.ExamCategory;
import com.zqu.pa.vo.exam.ResponseExamCategory;

public interface ExamCategoryService {

    //创建一个题库分类
    int createCategory(String categoryName);
    
    //获取全部题库分类
    List<ResponseExamCategory> getExamCategory();
}
