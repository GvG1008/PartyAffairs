package com.zqu.pa.dao.role;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zqu.pa.entity.role.Permission;

public interface RolePermissionMapper {
    
    List<Permission> selectPermission(@Param("roleId")Integer roleId);
    
    List<Permission> selectAllPermission();
    
    int deleteRolePermissions(@Param("roleId")Integer roleId);
    
    int insertRolePermissions(@Param("roleId")Integer roleId, @Param("permissions")List<Integer> permissions);
    
}
