package com.zqu.pa.dao.exam;

import com.zqu.pa.entity.exam.ExamUserExample;
import com.zqu.pa.entity.exam.ExamUserKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExamUserMapper {
    long countByExample(ExamUserExample example);

    int deleteByExample(ExamUserExample example);

    int deleteByPrimaryKey(ExamUserKey key);

    int insert(ExamUserKey record);

    int insertSelective(ExamUserKey record);

    List<ExamUserKey> selectByExample(ExamUserExample example);

    int updateByExampleSelective(@Param("record") ExamUserKey record, @Param("example") ExamUserExample example);

    int updateByExample(@Param("record") ExamUserKey record, @Param("example") ExamUserExample example);
}