package com.zqu.pa.dao.study;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zqu.pa.entity.study.StudyDocument;

public interface StudyDocumentMapper {
    int deleteByPrimaryKey(Integer documentId);

    int insert(StudyDocument record);

    int insertSelective(StudyDocument record);
    
    String getUserNameByUserId(String userId);

    StudyDocument selectByPrimaryKey(Integer documentId);
    
    List<StudyDocument> selectMustPutonByUserId(@Param("index")int index,@Param("num")int num,@Param("userId")String userId);
    
    int selectCountMustPutonByUserId(String userId);
    
    List<StudyDocument> selectAll();
    
    List<StudyDocument> selectPutOn(@Param("index")int index,@Param("num")int num);
    
    int selectCountPutOn();

    List<StudyDocument> selectPutOff();
    
    int selectCountMustPutonByLabelId(HashMap map);
    
    List<StudyDocument> selectPutonByLabelId(HashMap map);
    
    List<StudyDocument> selectMustPutonByLabelId(HashMap map);
    
    int selectCountPutonByLabelId(List<Integer> idList);
    
    int selectDocumentIdByFilePath(String filePath);

    int updateByPrimaryKeySelective(StudyDocument record);

    int updateByPrimaryKey(StudyDocument record);
}