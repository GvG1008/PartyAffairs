package com.zqu.pa.controller.report;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zqu.pa.common.ServerResponse;
import com.zqu.pa.entity.report.Report;
import com.zqu.pa.service.report.ReportService;
import com.zqu.pa.vo.report.PageOfReport;

@Controller
@RequestMapping("/report")
public class ReportController {
	
	@Autowired
	ReportService reportService;
	
	/**
	 * 根据条件查询所有的报告
	 * */
	@ResponseBody
    @RequestMapping(value="/queryReports",method=RequestMethod.GET)
    public ServerResponse selectReport(@RequestBody Report report) {

		List<Report> lists = reportService.queryReport(report);
		return ServerResponse.createBySuccess("查询成功", lists);
		
	}
	
	/**
	 * 添加思想汇报
	 * */
	@ResponseBody
    @RequestMapping(value="/insertReport",method=RequestMethod.POST)
    public ServerResponse InsertReport(@RequestBody Report report) {
        String Msg;
        try {
            Msg = reportService.InsertReport(report);
            
        }catch (Exception e) {
            return ServerResponse.createByErrorMessage("提交思想报告失败!");
        }
        
        if(Msg==null)
            return ServerResponse.createByErrorMessage("提交思想报告失败!");
        if(!Msg.equals("提交思想报告成功"))
            return ServerResponse.createByErrorMessage(Msg);
        return ServerResponse.createBySuccessMessage(Msg);
    }
    
	
	/**
	 * 查询个人思想汇报列表
	 * */
	 @ResponseBody
    @RequestMapping(value="/myReports/{pageNum}/{num}")
    public ServerResponse<PageOfReport> getPersonalReportsList(@PathVariable(value="pageNum") int page,@PathVariable(value="num") int num) {
		PageOfReport pageOfReport = null;
	        
        //传入的页数与当前页显示的条数,page为页数，num为每页条数
	 	pageOfReport = reportService.getPageOfReport(page,num);
        
        if(page==0){
        	return ServerResponse.createByErrorMessage("404notfound");
        	
        }else if(pageOfReport == null){
        	
        	return ServerResponse.createByErrorMessage("获取失败");
        	
        }else if(pageOfReport.getList()==null||pageOfReport.getPageNum()<=0
        		||pageOfReport.getTotalInfoNum()<=0||pageOfReport.getTotalPageNum()<=0){
        	
        	return ServerResponse.createByErrorMessage("获取失败");
        }
           
       return ServerResponse.createBySuccess("获取思想汇报列表成功", pageOfReport);
	 }
	 
	 /**
	     * 显示思想汇报详细内容页面的信息
	     * @param report_id
	     * @return
	     */
	    @ResponseBody
	    @RequestMapping("/detail/{report_id}")
	    public ServerResponse<Report> getReportDetailById(@PathVariable int report_id){
	        
	        Report report = reportService.getReportDetailById(report_id);
	        
	        if(report!=null)
	            return ServerResponse.createBySuccess("获取思想汇报成功", report);
	        else
	            return ServerResponse.createByErrorMessage("获取思想汇报失败");
	    }
	
}
