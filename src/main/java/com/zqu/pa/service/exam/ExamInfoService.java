package com.zqu.pa.service.exam;

import java.util.List;

import com.zqu.pa.common.ServerResponse;
import com.zqu.pa.entity.exam.ExamInfo;
import com.zqu.pa.entity.exam.ExamInfoReview;
import com.zqu.pa.vo.exam.AdminExamInfoList;
import com.zqu.pa.vo.exam.CreateExamBean;
import com.zqu.pa.vo.exam.ResponseNowExamList;

public interface ExamInfoService {

    //创建一场考试
    ServerResponse createExamInfo(CreateExamBean createExamBean);
    
    //管理员获取考试信息列表（-1：全部；0：未审核考试信息）
    List<AdminExamInfoList> listExamInfo(Integer review);
    
    //考试通过审核
    ServerResponse reviewExamInfo(List<Integer> listExamId);
    
    //根据考试ID删除考试
    ServerResponse removeExamInfo(Integer examId);
}
