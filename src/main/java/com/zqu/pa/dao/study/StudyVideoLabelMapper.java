package com.zqu.pa.dao.study;

import com.zqu.pa.entity.study.StudyVideoLabel;

public interface StudyVideoLabelMapper {
    int deleteByPrimaryKey(Integer orderby);

    int insert(StudyVideoLabel record);

    int insertSelective(StudyVideoLabel record);

    StudyVideoLabel selectByPrimaryKey(Integer orderby);

    int updateByPrimaryKeySelective(StudyVideoLabel record);

    int updateByPrimaryKey(StudyVideoLabel record);
}