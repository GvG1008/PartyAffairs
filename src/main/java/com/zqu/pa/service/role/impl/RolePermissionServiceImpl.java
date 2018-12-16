package com.zqu.pa.service.role.impl;

import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zqu.pa.common.ServerResponse;
import com.zqu.pa.dao.role.RoleMapper;
import com.zqu.pa.dao.role.RolePermissionMapper;
import com.zqu.pa.entity.role.Permission;
import com.zqu.pa.entity.role.Role;
import com.zqu.pa.service.role.RolePermissionService;

@Service
public class RolePermissionServiceImpl implements RolePermissionService {

    //默认身份roleId:1 普通党员
    public static final int DEFAULT_ROLE = 1;
    
    @Autowired
    RoleMapper roleDao;
    
    @Autowired
    RolePermissionMapper permissionDao;
    
    @Override
    public ServerResponse getRoleList() {
        List<Role> roleList = roleDao.selectRole();
        if(roleList == null || roleList.size()==0) {
            return ServerResponse.createByErrorMessage("获取失败");
        }
        
        return ServerResponse.createBySuccess(roleList);
    }

    @Override
    public ServerResponse insertRole(String roleName) {
        boolean result = roleDao.insertRole(roleName)>0;
        if(!result) {
            return ServerResponse.createByErrorMessage("添加失败");
        }
        return ServerResponse.createBySuccessMessage("添加成功");
    }

    @Transactional
    @Override
    public ServerResponse deleteRole(Integer roleId) {
        //先将原该身份默认
        roleDao.updateRole(roleId, Integer.valueOf(DEFAULT_ROLE));
        //将要删除的身份的权限删除
        boolean result1 = permissionDao.deleteRolePermissions(roleId)>=0;
        //删除身份
        boolean result2 = roleDao.deleteRole(roleId)>0;
        if(!result1||!result2) {
            throw new RuntimeException();
        }
        return ServerResponse.createBySuccessMessage("删除成功");
    }

    @Override
    public ServerResponse getUserRole(String userId) {
        Role role = roleDao.getUserRole(userId);
        if(role == null) {
            return ServerResponse.createByErrorMessage("获取指定用户身份失败");
        }
        return ServerResponse.createBySuccess(role);
    }

    @Override
    public ServerResponse updateUserRole(Integer roleId, String userId) {
        boolean result = roleDao.updateUserRole(roleId, userId)>0;
        if(!result) {
            return ServerResponse.createByErrorMessage("修改用户身份失败");
        }
        return ServerResponse.createBySuccessMessage("修改用户身份成功");
    }

    @Override
    public ServerResponse getRolePermissionList(Integer roleId) {
        List<Permission> permissionList = permissionDao.selectPermission(roleId);
        if(permissionList == null) {
            return ServerResponse.createByErrorMessage("获取失败");
        }else if(permissionList.size() == 0 ) {
            return ServerResponse.createBySuccess("权限为空", permissionList);
        }
        return ServerResponse.createBySuccess("获取权限列表成功", permissionList);
    }

}
