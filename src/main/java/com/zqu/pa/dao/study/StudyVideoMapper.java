package com.zqu.pa.dao.study;

import java.util.List;

import com.zqu.pa.entity.study.StudyVideo;

public interface StudyVideoMapper {
    int deleteByPrimaryKey(Integer videoId);

    int insert(StudyVideo record);

    int insertSelective(StudyVideo record);

    StudyVideo selectByPrimaryKey(Integer videoId);
    
    List<StudyVideo> selectPuton();
    
    int selectVideoIdByVideoPath(String videoPath);

    int updateByPrimaryKeySelective(StudyVideo record);

    int updateByPrimaryKey(StudyVideo record);
}