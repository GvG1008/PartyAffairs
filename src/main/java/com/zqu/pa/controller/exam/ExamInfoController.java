package com.zqu.pa.controller.exam;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zqu.pa.common.ServerResponse;
import com.zqu.pa.entity.exam.ExamInfo;
import com.zqu.pa.entity.exam.ExamInfoReview;
import com.zqu.pa.service.exam.ExamInfoService;
import com.zqu.pa.vo.exam.AdminExamInfoList;
import com.zqu.pa.vo.exam.CreateExamBean;
import com.zqu.pa.vo.exam.ResponseNowExamList;

@Controller
@RequestMapping("/examinfo")
public class ExamInfoController {

    @Autowired
    private ExamInfoService examInfoService;
    
    /**
     * 创建一场考试信息
     * @param createExamBean 
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ServerResponse createExamInfo(@RequestBody CreateExamBean createExamBean) {
        
        ServerResponse result = null;
        try {
            result = examInfoService.createExamInfo(createExamBean);
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.createByError();
        }
        return result;
    }
    
    /**
     * 取出登录管理员账号相同党支部的所有考试信息
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/lists", method = RequestMethod.GET)
    public ServerResponse<List<AdminExamInfoList>> listExamInfo() {
        
        List<AdminExamInfoList> result = null;
        try {
            //所有考试信息
            Integer review = -1;
            result = examInfoService.listExamInfo(review);
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.createByError();
        }
        return ServerResponse.createBySuccess(result);
    }
    
    /**
     * 取出登录管理员账号相同党支部的未审核的考试信息
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/lists/unreview", method = RequestMethod.GET)
    public ServerResponse<List<AdminExamInfoList>> unreviewExamInfo() {
        
        List<AdminExamInfoList> result = null;
        try {
            //未审核考试信息
            Integer review = 0;
            result = examInfoService.listExamInfo(review);
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.createByError();
        }
        return ServerResponse.createBySuccess(result);
    }
    
    /**
     * 考试审核通过，将字段review置为1
     * @param listExamId 考试ID数组
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/review", method = RequestMethod.PUT)
    public ServerResponse reviewExamInfo(@RequestBody List<Integer> listExamId) {

        return examInfoService.reviewExamInfo(listExamId);
    }
    
    /**
     * 删除考试信息（当前只删除exam_info和exam_info_category表）
     * @param examId 要删除的考试ID
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/{examId}", method = RequestMethod.DELETE)
    public ServerResponse removeExamInfo(@PathVariable Integer examId) {

        return examInfoService.removeExamInfo(examId);
    }
    
}
