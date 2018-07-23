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
    
    /**
     * 根据党支部随机查找题库对应数量的题目
     * @param brandId  党支部ID
     * @param quantity 题目数量
     * @param type     题目类型（单选/多选）
     * @return
     */
    List<QuestionBank> selectRand(@Param("branchId") Integer branchId, 
            @Param("quantity") Integer quantity, @Param("type") Integer type, 
            @Param("questionId") List<Integer> questionId);
}