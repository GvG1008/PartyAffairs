package com.zqu.pa.service.report;

import java.util.List;

import com.zqu.pa.entity.report.Report;
import com.zqu.pa.vo.report.PageOfReport;

public interface ReportService {
	
	//提交思想报告
	String InsertReport(Report report);
	//分页查询思想汇报列表
	PageOfReport getPageOfReport(int page, int num);
	//查询思想报告
	Report getReportDetailById(int report_id);
	//根据条件查询思想报告
	List<Report> queryReport(Report report);

}
