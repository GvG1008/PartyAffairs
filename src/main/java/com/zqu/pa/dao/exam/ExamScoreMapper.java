package com.zqu.pa.dao.exam;

import com.zqu.pa.entity.exam.ExamScore;
import com.zqu.pa.entity.exam.ExamScoreExample;
import com.zqu.pa.entity.exam.ExamScoreKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExamScoreMapper {
    long countByExample(ExamScoreExample example);

    int deleteByExample(ExamScoreExample example);

    int deleteByPrimaryKey(ExamScoreKey key);

    int insert(ExamScore record);

    int insertSelective(ExamScore record);

    List<ExamScore> selectByExample(ExamScoreExample example);

    ExamScore selectByPrimaryKey(ExamScoreKey key);

    int updateByExampleSelective(@Param("record") ExamScore record, @Param("example") ExamScoreExample example);

    int updateByExample(@Param("record") ExamScore record, @Param("example") ExamScoreExample example);

    int updateByPrimaryKeySelective(ExamScore record);

    int updateByPrimaryKey(ExamScore record);
}