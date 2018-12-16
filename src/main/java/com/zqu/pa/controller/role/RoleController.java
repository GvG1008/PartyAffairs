package com.zqu.pa.controller.role;

import org.apache.commons.lang3.StringUtils;
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

@Controller
@RequestMapping("/role")
public class RoleController {
    
    @Autowired
    RolePermissionService rolePermissionService;
    
    /**
     * 获取所有身份列表
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
     * 修改用户身份类型
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
    @RequestMapping(value="/permission/{roleId}")
    public ServerResponse getRolePermissionList(@PathVariable(value="roleId") Integer roleId) {
        if(roleId == null)
            return ServerResponse.createByErrorMessage("参数错误");
        return rolePermissionService.getRolePermissionList(roleId);
    }
}
