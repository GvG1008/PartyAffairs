package com.zqu.pa.controller.exam;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    
    /**
     * 用户获取已结束考试列表
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/finish", method = RequestMethod.GET)
    public ServerResponse getFinishedExamList() {
        
        examListService.setExamFinish();
        List<ResponseExamList> list = examListService.getFinishExamList();
        if (list == null)
            return ServerResponse.createByError();
        return ServerResponse.createBySuccess(list);
    }
    
    /**
     * 用户获取未完成考试列表
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/unfinish", method = RequestMethod.GET)
    public ServerResponse getUnFinishedExamList() {
        
        examListService.setExamFinish();
        List<ResponseNowExamList> list = examListService.getUnFinishExamList();
        if (list == null)
            return ServerResponse.createByError();
        return ServerResponse.createBySuccess(list);
    }
    
    /**
     * 获取考试详细信息
     * @param examId 考试ID
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/{examId}", method = RequestMethod.GET)
    public ServerResponse<ResponseNowExamList> getExamInfo(@PathVariable Integer examId) {
        
        ResponseNowExamList rnel = null;
        try {
            rnel = examListService.getResponseNowExamList(examId);
        } catch(Exception e) {
            return ServerResponse.createByError();
        }
        if (rnel == null)
            return ServerResponse.createByError();
        return ServerResponse.createBySuccess(rnel);
    }
}
