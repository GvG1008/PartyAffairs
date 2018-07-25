package com.zqu.pa.controller.perinfo;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zqu.pa.common.ServerResponse;
import com.zqu.pa.entity.perinfo.UserPartyInfo;
import com.zqu.pa.entity.perinfo.UserPersonInfo;
import com.zqu.pa.service.perinfo.UserInfoService;

@Controller
@RequestMapping("/userInfo")
public class PersonalController {
    
    @Autowired
    UserInfoService userInfoService;
    
    /**
     * 获取该session用户所有个人信息
     * @return
     */
    @ResponseBody
    @RequestMapping("/personInfo")
    public ServerResponse<UserPersonInfo> getUserPerInfo(){
        
        UserPersonInfo info = new UserPersonInfo();

        //此处获取session里用户userId
        String userId = (String)SecurityUtils.getSubject().getSession().getAttribute("userId");
        if(userId==null)
            return ServerResponse.createByErrorMessage("登录已失效");

        //通过id获取个人信息
        info = userInfoService.getUserPersonInfo(userId);
        
        if(info==null)
            return ServerResponse.createByErrorMessage("获取用户个人信息失败");
        return ServerResponse.createBySuccess("获取用户个人信息成功", info);
    }
    
    /**
     * 获取该session用户所有个人党员信息
     * @return
     */
    @ResponseBody
    @RequestMapping("/partyInfo")
    public ServerResponse<UserPartyInfo> getUserPartyInfo(){
        
        UserPartyInfo info = new UserPartyInfo();

        //此处获取session里用户userId
        String userId = (String)SecurityUtils.getSubject().getSession().getAttribute("userId");
        if(userId==null)
            return ServerResponse.createByErrorMessage("登录已失效");

        //通过id获取党员信息
        info = userInfoService.getUserPartyInfo(userId);
        
        if(info==null)
            return ServerResponse.createByErrorMessage("获取用户个人信息失败");
        return ServerResponse.createBySuccess("获取用户个人信息成功", info);
    }

    /**
     * 用户修改自己的个人信息
     * @param info 接收表单UserPersonInfo信息
     * @return
     */
    @ResponseBody
    @RequestMapping("/updatePerson")
    public ServerResponse updateUserPersonInfo(UserPersonInfo info) {
       
        //获取当前session里的userId
        String userId = (String)SecurityUtils.getSubject().getSession().getAttribute("userId");

        //如果数据注入的ID不是当前session里的userId,返回错误
        if(userId==null||info.getUserId()==null||!userId.equals(info.getUserId()))
            return ServerResponse.createByErrorMessage("操作失败");
        else {
            info.setCheckState(null);
            info.setCheckId(null);
            info.setCreateId(null);
            info.setCreateTime(null);
            info.setImgHead(null);
            info.setLastTime(null);
            info.setName(null);
            info.setSex(null);
        }
        
        //修改信息
        if(userInfoService.updateByUserPerson(info)==0)
            return ServerResponse.createByErrorMessage("修改个人信息失败");
        
        return ServerResponse.createBySuccessMessage("修改成功");
    }
    
    /**
     * 用户修改自己的部分党员信息
     * @param info 接收表单UserPartyInfo信息
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateParty")
    public ServerResponse updateUserPartyInfo(UserPartyInfo info) {
        
        //获取当前session里的userId
        String userId = (String)SecurityUtils.getSubject().getSession().getAttribute("userId");
        //如果数据注入的ID不是当前session里的userId,返回错误
        if(userId==null||info.getUserId()==null||!userId.equals(info.getUserId()))
            return ServerResponse.createByErrorMessage("操作失败");
        else {
            //将用户不可修改的一些党员信息设为null
            //..
        }
        
        //修改信息
        if(userInfoService.updateByUserParty(info)==0)
            return ServerResponse.createByErrorMessage("修改党员信息失败");
        return ServerResponse.createBySuccessMessage("修改成功");
    }
}
