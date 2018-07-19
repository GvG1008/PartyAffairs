package com.zqu.pa.dao.exam;

import com.zqu.pa.entity.exam.Choice;
import com.zqu.pa.entity.exam.ChoiceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ChoiceMapper {
    long countByExample(ChoiceExample example);

    int deleteByExample(ChoiceExample example);

    int deleteByPrimaryKey(Integer choiceId);

    int insert(Choice record);

    int insertSelective(Choice record);

    List<Choice> selectByExample(ChoiceExample example);

    Choice selectByPrimaryKey(Integer choiceId);

    int updateByExampleSelective(@Param("record") Choice record, @Param("example") ChoiceExample example);

    int updateByExample(@Param("record") Choice record, @Param("example") ChoiceExample example);

    int updateByPrimaryKeySelective(Choice record);

    int updateByPrimaryKey(Choice record);
}