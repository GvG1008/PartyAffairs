package com.zqu.pa.dao.study;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zqu.pa.entity.study.StudyVideo;
import com.zqu.pa.entity.study.StudyVideoRecord;

public interface StudyVideoMapper {
    int deleteByPrimaryKey(Integer videoId);

    int insert(StudyVideo record);

    int insertSelective(StudyVideo record);

    StudyVideo selectByPrimaryKey(Integer videoId);
    
    List<StudyVideo> selectPutonByLabelId(HashMap map);
    
    List<StudyVideo> selectPutonMustByLabelId(HashMap map);
    
    int selectCountPutonByLabelId(List<Integer> list);
    
    int selectCountPutonMustByLabelId(HashMap map);
    
    List<StudyVideo> selectMustPutonByUserId(@Param("index")int index,@Param("num")int num,@Param("userId")String userId);
    
    int selectCountMustPutonByUserId(String userId);
    
    List<StudyVideo> selectPuton(@Param("index")int index,@Param("num")int num);
    
    int selectCountPuton();
    
    float selectScheduleByVideoIdAndUserId(StudyVideoRecord record);
    
    int selectVideoIdByVideoPath(String videoPath);

    int updateByPrimaryKeySelective(StudyVideo record);

    int updateByPrimaryKey(StudyVideo record);
}