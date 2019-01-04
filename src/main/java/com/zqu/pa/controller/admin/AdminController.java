package com.zqu.pa.controller.admin;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zqu.pa.common.ServerResponse;
import com.zqu.pa.service.role.RolePermissionService;
import com.zqu.pa.vo.userInfo.UserBasicInfo;

@Controller
@RequestMapping("/adminManager")
public class AdminController {
    
    @Autowired
    RolePermissionService rolePermissionService;
    
    @ResponseBody
    @RequestMapping(value="/userlist", method=RequestMethod.GET)
    public ServerResponse getuserList() {
        UserBasicInfo basicInfo = (UserBasicInfo)SecurityUtils.getSubject().getSession().getAttribute("basicInfo");
        //获取当前登录人的身份
        Integer role = basicInfo.getRoleId();
        if( role == null && role != 0 )
            return ServerResponse.createByErrorMessage("无权限");
        return rolePermissionService.getAdminUserList();
    }
}
