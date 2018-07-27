package com.zqu.pa.dao.study;

import com.zqu.pa.entity.study.StudyDocument;

public interface StudyDocumentMapper {
    int deleteByPrimaryKey(Integer documentId);

    int insert(StudyDocument record);

    int insertSelective(StudyDocument record);

    StudyDocument selectByPrimaryKey(Integer documentId);

    int updateByPrimaryKeySelective(StudyDocument record);

    int updateByPrimaryKey(StudyDocument record);
}