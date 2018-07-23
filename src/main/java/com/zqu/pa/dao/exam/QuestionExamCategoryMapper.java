package com.zqu.pa.dao.exam;

import com.zqu.pa.entity.exam.QuestionExamCategoryExample;
import com.zqu.pa.entity.exam.QuestionExamCategoryKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface QuestionExamCategoryMapper {
    long countByExample(QuestionExamCategoryExample example);

    int deleteByExample(QuestionExamCategoryExample example);

    int deleteByPrimaryKey(QuestionExamCategoryKey key);

    int insert(QuestionExamCategoryKey record);

    int insertSelective(QuestionExamCategoryKey record);

    List<QuestionExamCategoryKey> selectByExample(QuestionExamCategoryExample example);

    int updateByExampleSelective(@Param("record") QuestionExamCategoryKey record, @Param("example") QuestionExamCategoryExample example);

    int updateByExample(@Param("record") QuestionExamCategoryKey record, @Param("example") QuestionExamCategoryExample example);
}