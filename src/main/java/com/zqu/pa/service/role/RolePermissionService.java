package com.zqu.pa.service.role;

import java.util.List;

import com.zqu.pa.common.ServerResponse;

public interface RolePermissionService {

    //获取所有身份列表
    ServerResponse getRoleList();

    //新增一个身份
    ServerResponse insertRole(String roleName);

    //删除一个身份，并将原该身份改为普通党员身份
    ServerResponse deleteRole(Integer roleId);

    //根据用户ID返回身份
    ServerResponse getUserRole(String userId);

    //修改用户身份类型
    ServerResponse updateUserRole(Integer roleId, String userId);

    //获取指定身份的所有权限列表
    ServerResponse getRolePermissionList(Integer roleId);

    //修改权限列表
    ServerResponse updateRolePermissionList(Integer roleId, List<Integer> permissionList);
    
    //获取所有权限列表
    ServerResponse getAllPermissionList();

    //获取所有管理权限的账号列表
    ServerResponse getAdminUserList();
}
