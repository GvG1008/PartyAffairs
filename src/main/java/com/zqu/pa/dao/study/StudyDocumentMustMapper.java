package com.zqu.pa.dao.study;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zqu.pa.entity.study.StudyDocumentMust;

public interface StudyDocumentMustMapper {
    int insert(StudyDocumentMust record);
    
    Integer selectIsAlready(@Param("userId")String userId,@Param("documentId")Integer documentId);
    
    List<Integer> selectDocumentIdByUserId(String userId);
    
    int setDocumentMustAlready(@Param("userId")String userId,@Param("documentId")Integer documentId);
}
