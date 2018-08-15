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
    
    //获取未审核的考试信息列表
    List<AdminExamInfoList> unreviewExamInfo();
    
    //考试通过审核
    ServerResponse reviewExamInfo(Integer examId);
}
