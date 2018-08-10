package com.zqu.pa.dao.study;

import java.util.List;
import java.util.Map;

import com.zqu.pa.entity.study.StudyVideo;
import com.zqu.pa.entity.study.StudyVideoRecord;

public interface StudyVideoMapper {
    int deleteByPrimaryKey(Integer videoId);

    int insert(StudyVideo record);

    int insertSelective(StudyVideo record);

    StudyVideo selectByPrimaryKey(Integer videoId);
    
    List<StudyVideo> selectPutonByLabelId(List<Integer> list);
    
    List<StudyVideo> selectMustPutonByUserId(String userId);
    
    List<StudyVideo> selectPuton();
    
    float selectScheduleByVideoIdAndUserId(StudyVideoRecord record);
    
    int selectVideoIdByVideoPath(String videoPath);

    int updateByPrimaryKeySelective(StudyVideo record);

    int updateByPrimaryKey(StudyVideo record);
}