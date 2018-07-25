package com.zqu.pa.dao.study;

import com.zqu.pa.entity.study.StudyLabel;

public interface StudyLabelMapper {
    int deleteByPrimaryKey(Integer labelId);

    int insert(StudyLabel record);

    int insertSelective(StudyLabel record);

    StudyLabel selectByPrimaryKey(Integer labelId);

    int updateByPrimaryKeySelective(StudyLabel record);

    int updateByPrimaryKey(StudyLabel record);
}