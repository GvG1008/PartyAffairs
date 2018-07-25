package com.zqu.pa.dao.study;

import com.zqu.pa.entity.study.StudyDocumentLabel;

public interface StudyDocumentLabelMapper {
    int deleteByPrimaryKey(Integer orderby);

    int insert(StudyDocumentLabel record);

    int insertSelective(StudyDocumentLabel record);

    StudyDocumentLabel selectByPrimaryKey(Integer orderby);

    int updateByPrimaryKeySelective(StudyDocumentLabel record);

    int updateByPrimaryKey(StudyDocumentLabel record);
}