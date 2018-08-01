package com.zqu.pa.service.exam.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zqu.pa.dao.exam.ExamCategoryMapper;
import com.zqu.pa.entity.exam.ExamCategory;
import com.zqu.pa.entity.exam.ExamCategoryExample;
import com.zqu.pa.service.exam.ExamCategoryService;

@Service
public class ExamCategoryServiceImpl implements ExamCategoryService {

    @Autowired
    private ExamCategoryMapper examCategoryMapper;
    
    @Override
    public int createCategory(String categoryName) {
        
        ExamCategory e = new ExamCategory();
        e.setCategoryName(categoryName);
        return examCategoryMapper.insert(e);
    }

    @Override
    public List<ExamCategory> getExamCategory() {
        
        ExamCategoryExample example = new ExamCategoryExample();
        return examCategoryMapper.selectByExample(example);
    } 
}
