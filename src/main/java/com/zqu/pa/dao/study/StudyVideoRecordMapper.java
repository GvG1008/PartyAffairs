package com.zqu.pa.dao.study;

import com.zqu.pa.entity.study.StudyVideoRecord;

public interface StudyVideoRecordMapper {
    int insert(StudyVideoRecord record);

    int insertSelective(StudyVideoRecord record);
}