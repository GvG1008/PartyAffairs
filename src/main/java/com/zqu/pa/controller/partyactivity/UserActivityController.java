package com.zqu.pa.controller.partyactivity;

import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Maps;
import com.zqu.pa.common.ServerResponse;
import com.zqu.pa.service.partyactivity.UserActivityService;
import com.zqu.pa.vo.partyactivity.PageOfList;
import com.zqu.pa.vo.userInfo.UserBasicInfo;

@Controller
@RequestMapping("/partyActivity")
public class UserActivityController {

    @Autowired
    UserActivityService userActivityService;
    
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
     * 用户点击我要报名，返回是否可以报名的信息
     * @param activityId
     * @return
     */
    @ResponseBody
    @RequestMapping("/apply/{activityId}")
    public ServerResponse applyActivity(@PathVariable(value="activityId") int activityId) {
        
        Map info = userActivityService.getApplyResult(activityId);
        if(info==null)
            return ServerResponse.createByError();
        if((int)info.get("State")!=1)
            return ServerResponse.createByErrorMessage((String)info.get("Msg"));//返回错误信息包括无权限，已报名等
        
        //返回成功信息为未报名，当前可以报名
        return ServerResponse.createBySuccessMessage((String)info.get("Msg"));
    }
}
