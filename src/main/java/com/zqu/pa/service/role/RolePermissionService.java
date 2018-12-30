package com.zqu.pa.service.role;

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

}