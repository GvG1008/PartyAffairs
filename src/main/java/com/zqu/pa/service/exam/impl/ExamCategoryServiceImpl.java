package com.zqu.pa.service.exam.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zqu.pa.dao.exam.ExamCategoryMapper;
import com.zqu.pa.entity.exam.ExamCategory;
import com.zqu.pa.entity.exam.ExamCategoryExample;
import com.zqu.pa.service.exam.ExamCategoryService;
import com.zqu.pa.vo.exam.ResponseExamCategory;

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
    public List<ResponseExamCategory> getExamCategory() {
        
        ExamCategoryExample example = new ExamCategoryExample();
        List<ExamCategory> listExamCategory = examCategoryMapper.selectByExample(example);
        List<ResponseExamCategory> result = new ArrayList<>();
        int singleQuantity = 0, multipleQuantity = 0;
        for (ExamCategory ec : listExamCategory) {
            singleQuantity = examCategoryMapper.sumSingleQuantity(ec);
            multipleQuantity = examCategoryMapper.sumMultipleQuantity(ec);
            ResponseExamCategory rec = new ResponseExamCategory();
            rec.setCategoryId(ec.getCategoryId());
            rec.setCategoryName(ec.getCategoryName());
            rec.setSingleQuantity(singleQuantity);
            rec.setMultipleQuantity(multipleQuantity);
            result.add(rec);
        }
        return result;
    } 
}
