package com.zqu.pa.controller.partyactivity;

import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Maps;
import com.zqu.pa.common.ServerResponse;
import com.zqu.pa.entity.partyactivity.PartyActivity;
import com.zqu.pa.service.partyactivity.UserActivityService;
import com.zqu.pa.vo.partyactivity.ActivityInfo;
import com.zqu.pa.vo.partyactivity.PageOfList;
import com.zqu.pa.vo.partyactivity.UserApplyInfo;
import com.zqu.pa.vo.userInfo.UserBasicInfo;

@Controller
@RequestMapping("/partyActivity")
public class UserActivityController {

    @Autowired
    UserActivityService userActivityService;
    
    /**
     * 用户获取相应活动列表
     * @param page
     * @param num
     * @return
     */
    @ResponseBody
    @RequestMapping("/menu/{pageNum}/{num}")
    public ServerResponse<PageOfList> getMenuList(@PathVariable(value="pageNum") int page,
            @PathVariable(value="num") int num) {
        
        //获取当前session里的当前用户所属党支部
        UserBasicInfo basicInfo = (UserBasicInfo)SecurityUtils.getSubject().getSession().getAttribute("basicInfo");
        if(basicInfo==null)
            return ServerResponse.createByErrorMessage("无法获取当前session信息");
        
        PageOfList info = new PageOfList();
        
        info = userActivityService.getMenuInfo(page,num,basicInfo.getBranchId());
        
        if(page==0)
            return ServerResponse.createByErrorMessage("页数不合法");
        else if(info.getList()==null||info.getPageNum()<=0||info.getPageNum()<=0
                ||info.getPageNum()<=0)
            return ServerResponse.createByErrorMessage("获取失败");
        return ServerResponse.createBySuccess("获取活动列表成功", info);
    }
    
    /**
     * 用户查看活动详情，返回详细信息
     * @param activityId
     * @return
     */
    @ResponseBody
    @RequestMapping("/info/{activityId}")
    public ServerResponse getActivityInfo(@PathVariable(value="activityId")Integer activityId) {
        if(activityId==null)
            return ServerResponse.createByErrorMessage("活动ID为空");
        
        ActivityInfo info = userActivityService.getActivityInfo(activityId);
        if(info==null)
            return ServerResponse.createByErrorMessage("该活动信息不存在");
        return ServerResponse.createBySuccess("获取活动详情成功", info);
    }
    
    /**
     * 用户点击我要报名，返回是否可以报名的信息
     * @param activityId
     * @return
     */
    @ResponseBody
    @RequestMapping("/applyInfo/{activityId}")
    public ServerResponse applyActivity(@PathVariable(value="activityId") int activityId) {
        
        Map info = userActivityService.getApplyResult(activityId);
        if(info==null)
            return ServerResponse.createByError();
        if((int)info.get("State")!=1)
            return ServerResponse.createByErrorMessage((String)info.get("Msg"));//返回错误信息包括无权限，已报名等
        
        //返回成功信息为未报名，当前可以报名
        return ServerResponse.createBySuccessMessage((String)info.get("Msg"));
    }
    
    /**
     * 活动报名提交表单信息接口
     * @param activityId
     * @param phoneNum
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/apply",method=RequestMethod.POST)
    public ServerResponse applyActivityInfo(@RequestParam(value="activityId")Integer activityId, 
            @RequestParam(value="phoneNum")String phoneNum) {
        if(activityId==null)
            return ServerResponse.createByErrorMessage("活动ID不存在");
        //判断手机号合法性
        String reg = "^[0-9]+(.[0-9]+)?$";
        if(phoneNum==null||!phoneNum.matches(reg))
            return ServerResponse.createByErrorMessage("联系方式为空或格式非法");
        
        //
        String Msg = userActivityService.applyActivity(activityId,phoneNum);
        if(Msg==null||!Msg.equals("报名成功,等待审核结果"))
            return ServerResponse.createByErrorMessage(Msg);
        return ServerResponse.createBySuccessMessage(Msg);
    }
    
    /**
     * 用户获取自己的所有报名状态信息，未分页
     * @return
     */
    @ResponseBody
    @RequestMapping("/applyAllInfo")
    public ServerResponse allApplyInfo() {
        String userId = (String)SecurityUtils.getSubject().getSession().getAttribute("userId");
        if(userId==null)
            return ServerResponse.createByErrorMessage("无法获取当前session信息");
        
        List<UserApplyInfo> info = userActivityService.getUserApplyInfo(userId);
        if(info==null||info.size()==0)
            return ServerResponse.createByErrorMessage("暂无报名信息");
        
        return ServerResponse.createBySuccess("获取成功", info);
    }
    
    /**
     * 用户撤销报名信息，当活动满足allowDelete=1时可以进行该操作
     * @param activityId
     * @return
     */
    @ResponseBody
    @RequestMapping("/deletApply/{activityId}")
    public ServerResponse deleteApply(@PathVariable(value="activityId")Integer activityId) {
        String userId = (String)SecurityUtils.getSubject().getSession().getAttribute("userId");
        if(userId==null)
            return ServerResponse.createByErrorMessage("无法获取当前session信息");
        
        String Msg = userActivityService.deleteApply(activityId,userId);
        if(Msg==null||!Msg.equals("撤销报名成功!"))
            return ServerResponse.createByErrorMessage(Msg);
        return ServerResponse.createBySuccessMessage(Msg);
    }
}
