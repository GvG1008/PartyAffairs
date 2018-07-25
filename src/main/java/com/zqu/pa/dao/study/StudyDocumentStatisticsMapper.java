package com.zqu.pa.dao.study;

import com.zqu.pa.entity.study.StudyDocumentStatistics;

public interface StudyDocumentStatisticsMapper {
    int insert(StudyDocumentStatistics record);

    int insertSelective(StudyDocumentStatistics record);
}