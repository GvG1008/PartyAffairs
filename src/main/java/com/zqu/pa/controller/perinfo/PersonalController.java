package com.zqu.pa.controller.perinfo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zqu.pa.common.ServerResponse;
import com.zqu.pa.entity.perinfo.UserPartyInfo;
import com.zqu.pa.entity.perinfo.UserPersonInfo;

@Controller
@RequestMapping("/userinfo")
public class PersonalController {
    
    /**
     * 获取该session用户所有个人信息
     * @return
     */
    @ResponseBody
    @RequestMapping("/person")
    public ServerResponse<UserPersonInfo> getUserPerInfo(){
        
        UserPersonInfo info = new UserPersonInfo();

        //此处获取session里用户userId
        //通过id获取个人信息
        
        if(info==null)
            return ServerResponse.createByErrorMessage("获取用户个人信息失败");
        return ServerResponse.createBySuccess("获取用户个人信息成功", info);
    }
    
    /**
     * 获取该session用户所有个人党员信息
     * @return
     */
    @ResponseBody
    @RequestMapping("/party")
    public ServerResponse<UserPartyInfo> getUserPartyInfo(){
        
        UserPartyInfo info = new UserPartyInfo();

        //此处获取session里用户userId
        //通过id获取党员信息
        
        if(info==null)
            return ServerResponse.createByErrorMessage("获取用户个人信息失败");
        return ServerResponse.createBySuccess("获取用户个人信息成功", info);
    }
    

}
