package com.zqu.pa.dao.study;

import java.util.List;

import com.zqu.pa.entity.study.StudyDocumentMust;

public interface StudyDocumentMustMapper {
    int insert(StudyDocumentMust record);
    
    List<Integer> selectDocumentIdByUserId(String userId);
}
