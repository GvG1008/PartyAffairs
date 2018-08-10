package com.zqu.pa.service.exam;

import java.util.List;

import com.zqu.pa.common.ServerResponse;
import com.zqu.pa.entity.exam.ExamInfo;
import com.zqu.pa.entity.exam.ExamInfoReview;
import com.zqu.pa.vo.exam.ResponseExamList;
import com.zqu.pa.vo.exam.ResponseNowExamList;

public interface ExamListService {

    //获得用户已经完成的考试列表
    List<ResponseExamList> getFinishExamList();
    
    //根据当前时间，改变考试状态finish
    void setExamFinish();
    
    //根据用户id获取考试id
    List<Integer> listExamUser(String userId);
    
    //判断用户某次考试是否及格
    Integer isPass(Integer examId, String userId, Integer passScore);
    
    //获取成绩
    Integer getScore(Integer examId, String userId);
    
    //获取正在进行中的考试列表
    List<ResponseNowExamList> getUnFinishExamList();

    //返回考试详情
    ResponseNowExamList getResponseNowExamList(Integer examId);
    
    //根据examId获取考试信息ExamInfo
    ExamInfo getExamInfo(Integer examId);
    
    //根据examId获取考试审核表ExamInfoReview
    ExamInfoReview getExamInfoReview(Integer examId);
}
