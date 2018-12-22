package com.zqu.pa.service.exam;

import java.util.List;

import com.zqu.pa.common.ServerResponse;
import com.zqu.pa.vo.exam.ExamScoreDetail;
import com.zqu.pa.vo.exam.ExamScoreList;

public interface ExamScoreService {

    ServerResponse<List<ExamScoreList>> listExamScore();
    
    ServerResponse<List<ExamScoreDetail>> listExamScoreDetail(Integer examID);
}
