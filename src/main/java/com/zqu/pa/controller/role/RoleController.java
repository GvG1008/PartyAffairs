package com.zqu.pa.controller.role;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.sql.dialect.oracle.ast.expr.OracleSizeExpr.Unit;
import com.zqu.pa.common.ServerResponse;
import com.zqu.pa.entity.perinfo.UserPersonInfo;
import com.zqu.pa.service.role.RolePermissionService;
import com.zqu.pa.vo.userInfo.UserBasicInfo;

@Controller
@RequestMapping("/role")
public class RoleController {
    
    @Autowired
    RolePermissionService rolePermissionService;
    
    /**
     * 获取所有身份列表，以及对应人数
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/list", method=RequestMethod.GET)
    public ServerResponse getRoleList() {
        
        return rolePermissionService.getRoleList();
    }
    
    /**
     * 添加一个新的身份
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/insert", method=RequestMethod.POST)
    public ServerResponse insertRole(@RequestParam("roleName")String roleName) {
        roleName = StringUtils.deleteWhitespace(roleName);
        if(StringUtils.isEmpty(roleName)) {
            return ServerResponse.createByErrorMessage("输入的字符串不合法");
        }
        return rolePermissionService.insertRole(roleName);
    }
    
    /**
     * 删除一个身份，将原该身份人员改为默认身份
     * @param roleId
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/delete", method=RequestMethod.POST)
    public ServerResponse deleteRole(@RequestParam("roleId")Integer roleId) {
        if(roleId!=null) {
            return rolePermissionService.deleteRole(roleId);
        }
        return ServerResponse.createByErrorMessage("操作失败");
    }
    
    /**
     * 根据用户ID返回身份
     * @param userId
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/{userId}", method=RequestMethod.GET)
    public ServerResponse getUserRole(@PathVariable(value="userId") String userId){
        
        if(userId==null)
            return ServerResponse.createByErrorMessage("获取指定用户身份失败");
        
        return rolePermissionService.getUserRole(userId);
    }
    
    /**
     * 修改用户身份角色类型
     * @param roleId
     * @param userId
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/updateUser", method=RequestMethod.POST)
    public ServerResponse getUserRole(@RequestParam("roleId")Integer roleId,@RequestParam("userId") String userId){
        
        if(userId==null||roleId==null)
            return ServerResponse.createByErrorMessage("修改失败");
        
        return rolePermissionService.updateUserRole(roleId,userId);
    }
    
    /**
     * 查看该身份ID所有权限列表
     * @param roleId
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/permission/{roleId}",method=RequestMethod.GET)
    public ServerResponse getRolePermissionList(@PathVariable(value="roleId") Integer roleId) {
        if(roleId == null)
            return ServerResponse.createByErrorMessage("参数错误");
        return rolePermissionService.getRolePermissionList(roleId);
    }
    

    /**
     * 修改指定角色身份ID的权限列表
     * @param roleId
     * @param permissionId
     * @return
     */
    @RequiresPermissions("permission:update")
    @ResponseBody
    @RequestMapping(value="/update_role_permission")
    public ServerResponse getRolePermissionList(@RequestParam(value = "roleId")Integer roleId,
            @RequestParam(value = "permissionId") Integer[] permissionId) {
        if(roleId == 0)
            return ServerResponse.createByErrorMessage("最高权限的权限不可修改");
        UserBasicInfo basicInfo = (UserBasicInfo)SecurityUtils.getSubject().getSession().getAttribute("basicInfo");
        //获取当前登录人的身份
        Integer role = basicInfo.getRoleId();
        if( role == null )
            return ServerResponse.createByErrorMessage("无法获取当前登录信息");
        List<Integer> permissionList = new ArrayList<Integer>();
        for(Integer p : permissionId) {
            permissionList.add(p);
        }

        return rolePermissionService.updateRolePermissionList(roleId,permissionList);
    }
}
