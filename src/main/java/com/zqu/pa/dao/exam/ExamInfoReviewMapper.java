package com.zqu.pa.dao.exam;

import com.zqu.pa.entity.exam.ExamInfoReview;
import com.zqu.pa.entity.exam.ExamInfoReviewExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExamInfoReviewMapper {
    long countByExample(ExamInfoReviewExample example);

    int deleteByExample(ExamInfoReviewExample example);

    int deleteByPrimaryKey(Integer examId);

    int insert(ExamInfoReview record);

    int insertSelective(ExamInfoReview record);

    List<ExamInfoReview> selectByExample(ExamInfoReviewExample example);

    ExamInfoReview selectByPrimaryKey(Integer examId);

    int updateByExampleSelective(@Param("record") ExamInfoReview record, @Param("example") ExamInfoReviewExample example);

    int updateByExample(@Param("record") ExamInfoReview record, @Param("example") ExamInfoReviewExample example);

    int updateByPrimaryKeySelective(ExamInfoReview record);

    int updateByPrimaryKey(ExamInfoReview record);
}