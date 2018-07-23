package com.zqu.pa.dao.exam;

import com.zqu.pa.entity.exam.ExamInfoCategoryExample;
import com.zqu.pa.entity.exam.ExamInfoCategoryKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExamInfoCategoryMapper {
    long countByExample(ExamInfoCategoryExample example);

    int deleteByExample(ExamInfoCategoryExample example);

    int deleteByPrimaryKey(ExamInfoCategoryKey key);

    int insert(ExamInfoCategoryKey record);

    int insertSelective(ExamInfoCategoryKey record);

    List<ExamInfoCategoryKey> selectByExample(ExamInfoCategoryExample example);

    int updateByExampleSelective(@Param("record") ExamInfoCategoryKey record, @Param("example") ExamInfoCategoryExample example);

    int updateByExample(@Param("record") ExamInfoCategoryKey record, @Param("example") ExamInfoCategoryExample example);
}