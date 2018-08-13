package com.zqu.pa.service.exam;

import com.zqu.pa.common.ServerResponse;
import com.zqu.pa.entity.exam.ExamInfo;
import com.zqu.pa.entity.exam.ExamInfoReview;

public interface ExamInfoService {

    //创建一场考试
    ServerResponse createExamInfo(ExamInfo examInfo, ExamInfoReview examInfoReview);
}
