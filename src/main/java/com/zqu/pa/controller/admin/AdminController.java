package com.zqu.pa.controller.admin;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zqu.pa.common.Const;
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
    
    /**
     * 在录入管理员账号时获取党支部选项
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/getBranchList",method=RequestMethod.GET)
    public ServerResponse getBranchList() {
        UserBasicInfo basicInfo = (UserBasicInfo)SecurityUtils.getSubject().getSession().getAttribute("basicInfo");
        //获取当前登录人的身份
        Integer role = basicInfo.getRoleId();
        if( role == null && role != 0 )
            return ServerResponse.createByErrorMessage("无权限");
        return rolePermissionService.getBranchList();
    }
    
    
    /**
     * 在录入管理员账号时获取管理员角色选项
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/getRoleList",method=RequestMethod.GET)
    public ServerResponse getRoleList() {
        UserBasicInfo basicInfo = (UserBasicInfo)SecurityUtils.getSubject().getSession().getAttribute("basicInfo");
        //获取当前登录人的身份
        Integer role = basicInfo.getRoleId();
        if( role == null && role != 0 )
            return ServerResponse.createByErrorMessage("无权限");
        return rolePermissionService.getAdminRoleList();
    }
    
    /**
     * 录入管理员账号
     * @param userId
     * @param password 若密码传值为空，则密码默认为userId
     * @param roleId
     * @param branchId
     * @param name
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/insert", method=RequestMethod.POST)
    public ServerResponse insertAdminUser(@RequestParam(value="userId")String userId, @RequestParam(value="password",required=false)String password,
            @RequestParam(value="roleId")Integer roleId, @RequestParam(value="branchId")Integer branchId,
            @RequestParam(value="name")String name) {

        return rolePermissionService.insertAdmin(userId,password,roleId,branchId,name);
    }
    
    /**
     * 判断是否有工作台权限，权限管理权限
     * @return 2为拥有两者，1为只拥有管理员工作台，0为没有权限
     */
    @ResponseBody
    @RequestMapping(value="/checkAdmin", method=RequestMethod.GET)
    public ServerResponse checkAdmin(){
        Subject currentUser = SecurityUtils.getSubject();
        UserBasicInfo basicInfo = (UserBasicInfo)currentUser.getSession().getAttribute("basicInfo");
        //获取当前登录
        if(basicInfo==null) {
            return ServerResponse.createBySuccess("", 0);
        }
        String permission1 = Const.ADMIN_PERMISSION_NAME;
        String permission2 = Const.ADMIN_PERMISSION_UPDATE_NAME;
        boolean permitted1 = currentUser.isPermitted(permission1);// 判断是否有权限
        boolean permitted2 = currentUser.isPermitted(permission2);// 判断是否有权限
        if(permitted1&&!permitted2) {
            return ServerResponse.createBySuccess("工作台权限", 1);
        }else if(permitted2){
            //拥有权限管理权限，则拥有工作台权限
            return ServerResponse.createBySuccess("权限管理", 2);
        }else {
            //无管理权限
            return ServerResponse.createBySuccess("", 0);
        }
    }
}
