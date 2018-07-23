package com.zqu.pa.dao.exam;

import com.zqu.pa.entity.exam.ExamRoleExample;
import com.zqu.pa.entity.exam.ExamRoleKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExamRoleMapper {
    long countByExample(ExamRoleExample example);

    int deleteByExample(ExamRoleExample example);

    int deleteByPrimaryKey(ExamRoleKey key);

    int insert(ExamRoleKey record);

    int insertSelective(ExamRoleKey record);

    List<ExamRoleKey> selectByExample(ExamRoleExample example);

    int updateByExampleSelective(@Param("record") ExamRoleKey record, @Param("example") ExamRoleExample example);

    int updateByExample(@Param("record") ExamRoleKey record, @Param("example") ExamRoleExample example);
}