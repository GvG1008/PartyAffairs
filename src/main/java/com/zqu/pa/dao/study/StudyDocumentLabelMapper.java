package com.zqu.pa.dao.study;

import java.util.List;
import java.util.Map;

import com.zqu.pa.entity.study.StudyDocumentLabel;

public interface StudyDocumentLabelMapper {
    int deleteByPrimaryKey(Integer orderby);

    int insert(StudyDocumentLabel record);

    int insertSelective(StudyDocumentLabel record);

    StudyDocumentLabel selectByPrimaryKey(Integer orderby);
    
    List<Integer> selectDocumentIdByLabels(List list);

    int updateByPrimaryKeySelective(StudyDocumentLabel record);

    int updateByPrimaryKey(StudyDocumentLabel record);
}