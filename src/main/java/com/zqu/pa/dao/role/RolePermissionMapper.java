package com.zqu.pa.dao.role;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zqu.pa.entity.role.Permission;
import com.zqu.pa.vo.userInfo.AdminUserInfo;

public interface RolePermissionMapper {
    
    List<Permission> selectPermission(@Param("roleId")Integer roleId);
    
    List<Permission> selectAllPermission();
    
    int deleteRolePermissions(@Param("roleId")Integer roleId);
    
    int insertRolePermissions(@Param("roleId")Integer roleId, @Param("permissions")List<Integer> permissions);
    
    //查询具有管理权限的角色
    List<Integer> selectAdminRoleIdList(@Param("permissionName")String permissionName);
    
    List<AdminUserInfo> selectAdminUserList(@Param("roleIds")List<Integer> roleIds);
}
