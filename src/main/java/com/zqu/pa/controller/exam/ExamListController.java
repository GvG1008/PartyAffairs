package com.zqu.pa.controller.exam;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zqu.pa.common.ResponseCode;
import com.zqu.pa.common.ServerResponse;
import com.zqu.pa.entity.exam.ExamInfo;
import com.zqu.pa.service.exam.ExamListService;
import com.zqu.pa.vo.exam.ResponseExamList;
import com.zqu.pa.vo.exam.ResponseNowExamList;
import com.zqu.pa.vo.userInfo.UserBasicInfo;

@Controller
@RequestMapping("/examlist")
public class ExamListController {

    @Autowired
    private ExamListService examListService;
    
    @ResponseBody
    @RequestMapping("/finish")
    public ServerResponse getFinishedExamList() {
        
        examListService.setExamFinish();
        List<ResponseExamList> list = examListService.getFinishExamList();
        if (list == null)
            return ServerResponse.createByError();
        return ServerResponse.createBySuccess(list);
    }
    
    @ResponseBody
    @RequestMapping("/unfinish")
    public ServerResponse getUnFinishedExamList() {
        
        examListService.setExamFinish();
        List<ResponseNowExamList> list = examListService.getUnFinishExamList();
        if (list == null)
            return ServerResponse.createByError();
        return ServerResponse.createBySuccess(list);
    }
    
    @ResponseBody
    @RequestMapping("/{examId}")
    public ServerResponse<ResponseNowExamList> getExamInfo(@PathVariable Integer examId) {
        
        UserBasicInfo basicInfo = (UserBasicInfo)SecurityUtils.getSubject().getSession().getAttribute("basicInfo");
        String userId = basicInfo.getUserId();
        ExamInfo e = examListService.getExamInfo(examId);
        if (e == null)
            return ServerResponse.createByError();
        ResponseNowExamList rnel = examListService.getResponseNowExamList(e, userId);
        if (rnel == null)
            return ServerResponse.createByError();
        return ServerResponse.createBySuccess(rnel);
    }
}
