package com.zqu.pa.controller.feedback;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zqu.pa.common.ServerResponse;
import com.zqu.pa.entity.feedback.Feedback;
import com.zqu.pa.entity.feedback.FeedbackType;
import com.zqu.pa.service.feedback.FeedbackService;
import com.zqu.pa.vo.feedback.FeedbackInfo;
import com.zqu.pa.vo.feedback.PageOfFeedback;
import com.zqu.pa.vo.userInfo.UserBasicInfo;

@Controller
@RequestMapping("/feedback")
public class FeedbackController {

	// 获取日志记录器Logger，名字为本类类名
	private static Logger log = LoggerFactory.getLogger(FeedbackController.class);

	@Autowired
	FeedbackService feedbackService;

	/**
	 * 管理员获取本支部用户的思想反馈列表
	 */
	@ResponseBody
	@RequestMapping(value = "/listFeedback", method = RequestMethod.GET)
	public ServerResponse getFeedbackList() {
		UserBasicInfo basicInfo = (UserBasicInfo) SecurityUtils.getSubject().getSession().getAttribute("basicInfo");
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

		return ServerResponse.createBySuccess("获取思想反馈列表成功", feedbackInfoList);
	}

	/**
	 * 添加思想反馈
	 */
	@ResponseBody
	@RequestMapping(value = "/insertFeedback", method = RequestMethod.POST)
	public ServerResponse insertFeedback(@RequestBody Feedback feedback) {
		UserBasicInfo basicInfo = (UserBasicInfo) SecurityUtils.getSubject().getSession().getAttribute("basicInfo");
		if (basicInfo == null) {
			log.error("用户未登录");
			return ServerResponse.createByErrorMessage("用户未登录");
		}
		feedback.setUserId(basicInfo.getUserId());
		try {
			feedbackService.insertFeedback(feedback);
		} catch (Exception e) {
			return ServerResponse.createByErrorMessage("添加思想反馈失败!");
		}
		return ServerResponse.createBySuccessMessage("添加思想反馈成功!");
	}

	/**
	 * 获取反馈类型
	 */
	@ResponseBody
	@RequestMapping(value = "/getFeedbackType", method = RequestMethod.GET)
	public ServerResponse getFeedbackTypeList() {
		List<FeedbackType> feedbackTypeList = new ArrayList<FeedbackType>();
		try {
			feedbackTypeList = feedbackService.getFeedbackTypeList();
		} catch (Exception e) {
			return ServerResponse.createByErrorMessage("获取思想反馈类型失败!");
		}
		return ServerResponse.createBySuccess("获取思想反馈类型成功!", feedbackTypeList);
	}

	/**
	 * 根据id获取思想反馈详细信息
	 */
	@ResponseBody
	@RequestMapping(value = "/getFeedbackById", method = RequestMethod.GET)
	public ServerResponse getFeedbackById(@RequestParam(value = "feedbackId") int feedbackId) {
		FeedbackInfo feedbackInfo = new FeedbackInfo();
		try {
			feedbackInfo = feedbackService.getFeedback(feedbackId);
		} catch (Exception e) {
			return ServerResponse.createByErrorMessage("获取思想反馈详细信息失败!");
		}
		return ServerResponse.createBySuccess("获取思想反馈详细信息成功!", feedbackInfo);
	}

	/**
	 * 分页获取个人思想反馈列表
	 */
	@ResponseBody
	@RequestMapping(value = "/getMyFeedbackList/{pageNum}/{num}", method = RequestMethod.GET)
	public ServerResponse getMyFeedbackList(@PathVariable(value = "pageNum") int pageNum,
			@PathVariable(value = "num") int num) {
		PageOfFeedback pageOfFeedback = new PageOfFeedback();
		UserBasicInfo basicInfo = (UserBasicInfo) SecurityUtils.getSubject().getSession().getAttribute("basicInfo");
		if (basicInfo == null) {
			log.error("用户未登录");
			return ServerResponse.createByErrorMessage("用户未登录");
		}
		String userId = basicInfo.getUserId();
		try {
			pageOfFeedback = feedbackService.getPageOfFeedback(pageNum, num, userId);
		} catch (Exception e) {
			return ServerResponse.createByErrorMessage("获取个人思想反馈列表失败!");
		}
		return ServerResponse.createBySuccess("获取思想反馈列表成功", pageOfFeedback);
	}
}
