package com.zqu.pa.dao.exam;

import com.zqu.pa.entity.exam.QuestionBank;
import com.zqu.pa.entity.exam.QuestionBankExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface QuestionBankMapper {
    long countByExample(QuestionBankExample example);

    int deleteByExample(QuestionBankExample example);

    int deleteByPrimaryKey(Integer questionId);

    int insert(QuestionBank record);

    int insertSelective(QuestionBank record);

    List<QuestionBank> selectByExample(QuestionBankExample example);

    QuestionBank selectByPrimaryKey(Integer questionId);

    int updateByExampleSelective(@Param("record") QuestionBank record, @Param("example") QuestionBankExample example);

    int updateByExample(@Param("record") QuestionBank record, @Param("example") QuestionBankExample example);

    int updateByPrimaryKeySelective(QuestionBank record);

    int updateByPrimaryKey(QuestionBank record);
}