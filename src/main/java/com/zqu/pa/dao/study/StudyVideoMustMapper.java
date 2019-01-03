package com.zqu.pa.dao.study;

import org.apache.ibatis.annotations.Param;

import com.zqu.pa.entity.study.StudyVideoMust;

public interface StudyVideoMustMapper {

    int insert(StudyVideoMust record);
    
    Integer selectIsAlready(@Param("userId")String userId,@Param("videoId")Integer videoId);
    
    int setVideoMustAlready(@Param("userId")String userId,@Param("videoId")Integer videoId);
}
