package com.zqu.pa.controller.partyactivity;

import java.util.Date;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zqu.pa.common.ServerResponse;
import com.zqu.pa.entity.partyactivity.PartyActivity;
import com.zqu.pa.service.partyactivity.ActivityManageService;
import com.zqu.pa.utils.DateUtil;
import com.zqu.pa.utils.StringTimeToLong;
import com.zqu.pa.vo.partyactivity.ActivityManageMenu;
import com.zqu.pa.vo.userInfo.UserBasicInfo;

@Controller
@RequestMapping("/pActivityManage")
public class PActivityManageController {

    @Autowired
    ActivityManageService activityManageService;
    
    /**
     * 管理员获取对应党支部的活动所有列表信息
     * @return
     */
    @ResponseBody
    @RequestMapping("/list")
    public ServerResponse getActivityList() {
        
        //获取当前session里的当前用户信息
        UserBasicInfo basicInfo = (UserBasicInfo)SecurityUtils.getSubject().getSession().getAttribute("basicInfo");
        if(basicInfo==null)
            return ServerResponse.createByErrorMessage("无法获取当前session信息");
        
        List<ActivityManageMenu> info = activityManageService.getActivityMenuList(basicInfo.getBranchId());
        if(info==null)
            return ServerResponse.createByErrorMessage("查询列表失败");
        
        return ServerResponse.createBySuccess("查询列表成功", info);
    }
    

    /**
     * 管理员创建新活动表
     * 
     * @param branchId 活动所属党支部ID
     * @param name 活动名称
     * @param content 活动内容
     * @param num 活动限制参加人数
     * @param address 活动地点
     * @param releaseUnit 发布单位
     * @param roleIds 限制参加人员的角色身份 checkbox
     * @param reStar 报名开始时间
     * @param reEnd  报名结束时间
     * @param acStar 活动开始时间
     * @param acEnd  活动借宿时间
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/insertActivity",method=RequestMethod.POST)
    public ServerResponse insertActivity(@RequestParam(value="branchId")Integer branchId, @RequestParam(value="name")String name,
            @RequestParam(value="content")String content, @RequestParam(value="num")Integer num, @RequestParam(value="address")String address,
            @RequestParam(value="release_unit")String releaseUnit, @RequestParam(value="roleId")Integer[] roleIds,
            @RequestParam(value="registrationStart")String reStar,@RequestParam(value="registrationEnd")String reEnd, 
            @RequestParam(value="activityStart")String acStar,@RequestParam(value="activityEnd")String acEnd) {
        
        //获取当前session里的当前用户信息
        UserBasicInfo basicInfo = (UserBasicInfo)SecurityUtils.getSubject().getSession().getAttribute("basicInfo");
        if(basicInfo==null)
            return ServerResponse.createByErrorMessage("无法获取当前session信息");
        
        if(basicInfo.getBranchId()!=0&&basicInfo.getBranchId()!=branchId)
            return ServerResponse.createByErrorMessage("不能创建其他所属党支部的活动");
        
        if(name==null||content==null||num==null||roleIds==null||reStar==null||reEnd==null||acStar==null||acEnd==null)
            return ServerResponse.createByErrorMessage("填写数据不能为空");
        
        PartyActivity PA = new PartyActivity();
        PA.setName(name);
        PA.setContent(content);
        PA.setNum(num);
        if(address==null||address.equals(""))
            PA.setAddress(null);
        else
            PA.setAddress(address);
        if(releaseUnit==null||releaseUnit.equals(""))
            releaseUnit = basicInfo.getBranchName();//默认单位为党支部名
        if(basicInfo.getBranchId()==0)
            releaseUnit = "活动发布员";
        PA.setReleaseUnit(releaseUnit);
        Long registrationStart = StringTimeToLong.convertTimeToLong(reStar);//将时间"yyyy-MM-dd hh:mm:ss"字符串转换为long型
        Long registrationEnd = StringTimeToLong.convertTimeToLong(reEnd);
        Long activityStart = StringTimeToLong.convertTimeToLong(acStar);
        Long activityEnd = StringTimeToLong.convertTimeToLong(acEnd);
        PA.setRegistrationStart(registrationStart);
        PA.setRegistrationEnd(registrationEnd);
        PA.setActivityStart(activityStart);
        PA.setActivityEnd(activityEnd);
        PA.setBranchId(branchId);
        PA.setCreateTime(new Date());
        PA.setCreateId(basicInfo.getUserId());
        
        String Msg;
        try {
            Msg = activityManageService.insertActivity(PA,roleIds);
        }catch (Exception e) {
            return ServerResponse.createByErrorMessage("创建失败");
        }
        
        return ServerResponse.createBySuccessMessage(Msg);
    }
}
