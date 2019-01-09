package com.zqu.pa.controller.feedback;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zqu.pa.common.ServerResponse;
import com.zqu.pa.entity.feedback.Feedback;
import com.zqu.pa.service.feedback.FeedbackService;
import com.zqu.pa.vo.feedback.FeedbackInfo;
import com.zqu.pa.vo.userInfo.UserBasicInfo;


@Controller
@RequestMapping("/feedback")
public class FeedbackController {
	
	//获取日志记录器Logger，名字为本类类名
    private static Logger log = LoggerFactory.getLogger(FeedbackController.class);
    
    @Autowired
    FeedbackService feedbackService;
    
    /**
	 * 添加思想汇报
	 */
	@ResponseBody
	@RequestMapping(value = "/listFeedback", method = RequestMethod.GET)
	public ServerResponse InsertReport() {
		UserBasicInfo basicInfo = (UserBasicInfo)SecurityUtils.getSubject().getSession().getAttribute("basicInfo");
        if (basicInfo == null) {
            log.error("用户未登录");
            return ServerResponse.createByErrorMessage("用户未登录");
        }
        int branchId = basicInfo.getBranchId();
        		
		List<FeedbackInfo> feedbackInfoList = new ArrayList<FeedbackInfo>();
		try {
			feedbackInfoList = feedbackService.getFeedbackInfoList(branchId);

		} catch (Exception e) {
			return ServerResponse.createByErrorMessage("获取思想反馈失败!");
		}

		return ServerResponse.createBySuccess("获取思想反馈列表成功",feedbackInfoList);
	}

}
