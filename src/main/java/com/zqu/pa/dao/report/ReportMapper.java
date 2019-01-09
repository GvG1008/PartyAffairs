package com.zqu.pa.dao.report;

import com.zqu.pa.entity.report.Report;
import com.zqu.pa.entity.report.ReportExample;
import com.zqu.pa.vo.report.ReportInfo;
import com.zqu.pa.vo.report.ReportList;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ReportMapper {
    long countByExample(ReportExample example);

    int deleteByExample(ReportExample example);

    int deleteByPrimaryKey(Integer reportId);

    int insert(Report record);

    int insertSelective(Report record);

    List<Report> selectByExampleWithBLOBs(ReportExample example);

    List<Report> selectByExample(ReportExample example);

    Report selectByPrimaryKey(Integer reportId);

    int updateByExampleSelective(@Param("record") Report record, @Param("example") ReportExample example);

    int updateByExampleWithBLOBs(@Param("record") Report record, @Param("example") ReportExample example);

    int updateByExample(@Param("record") Report record, @Param("example") ReportExample example);

    int updateByPrimaryKeySelective(Report record);

    int updateByPrimaryKeyWithBLOBs(Report record);

    int updateByPrimaryKey(Report record);
    //查询个人思想报告列表
	List<ReportList> getPageOfReportListLimited(@Param("userId") String userId, @Param("index") int index , @Param("num") int num);
	//查询思想汇报详情
	Report getReportInfo(@Param("userId") String userId, @Param("report_id") int report_id);
	
	/**
	 *
	 * @Description: 获取思想报告列表
	 *
	 * @param:
	 * @return:
	 * @author: huanrong.chen
	 * @date: 2019/1/9
	 */
	List<ReportInfo> getReportInfoList(@Param("branchId") int branchId);
}