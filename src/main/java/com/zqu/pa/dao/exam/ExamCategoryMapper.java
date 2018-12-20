package com.zqu.pa.dao.exam;

import com.zqu.pa.entity.exam.ExamCategory;
import com.zqu.pa.entity.exam.ExamCategoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExamCategoryMapper {
    long countByExample(ExamCategoryExample example);

    int deleteByExample(ExamCategoryExample example);

    int deleteByPrimaryKey(Integer categoryId);

    int insert(ExamCategory record);

    int insertSelective(ExamCategory record);

    List<ExamCategory> selectByExample(ExamCategoryExample example);

    ExamCategory selectByPrimaryKey(Integer categoryId);

    int updateByExampleSelective(@Param("record") ExamCategory record, @Param("example") ExamCategoryExample example);

    int updateByExample(@Param("record") ExamCategory record, @Param("example") ExamCategoryExample example);

    int updateByPrimaryKeySelective(ExamCategory record);

    int updateByPrimaryKey(ExamCategory record);
    
    //统计某题库的单选题总数
    int sumSingleQuantity(ExamCategory record);
    
    //统计某题库的单选题总数
    int sumMultipleQuantity(ExamCategory record);
}