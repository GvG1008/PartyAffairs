package com.zqu.pa.dao.study;

import java.util.List;

import com.zqu.pa.entity.study.StudyLabel;

public interface StudyLabelMapper {
    int deleteByPrimaryKey(Integer labelId);

    int insert(StudyLabel record);

    int insertSelective(StudyLabel record);

    StudyLabel selectByPrimaryKey(Integer labelId);
    
    List<StudyLabel> selectAll();
    
    List<StudyLabel> selectByDocumentId(Integer documentId);
    
    List<StudyLabel> selectByVideoId(Integer videoId);

    int updateByPrimaryKeySelective(StudyLabel record);

    int updateByPrimaryKey(StudyLabel record);
}