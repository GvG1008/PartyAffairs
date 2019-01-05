package com.zqu.pa.dao.role;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zqu.pa.entity.perinfo.UserPartyInfo;
import com.zqu.pa.entity.perinfo.UserPersonInfo;
import com.zqu.pa.entity.role.Permission;
import com.zqu.pa.vo.perinfo.Branch;
import com.zqu.pa.vo.perinfo.Role;
import com.zqu.pa.vo.userInfo.AdminUserInfo;
import com.zqu.pa.vo.userInfo.UserLoginInfo;

public interface RolePermissionMapper {
    
    List<Permission> selectPermission(@Param("roleId")Integer roleId);
    
    List<Permission> selectAllPermission();
    
    int deleteRolePermissions(@Param("roleId")Integer roleId);
    
    int insertRolePermissions(@Param("roleId")Integer roleId, @Param("permissions")List<Integer> permissions);
    
    //查询具有管理权限的角色
    List<Integer> selectAdminRoleIdList(@Param("permissionName")String permissionName);
    
    List<AdminUserInfo> selectAdminUserList(@Param("roleIds")List<Integer> roleIds);
    
    //获取所有的党支部
    List<Branch> getAllbranch();
    
    //获取拥有管理员权限的角色
    List<Role> getAdminRoleList();
    
    //录入管理员的信息
    int insertAdminUserLoginInfo(UserLoginInfo info);
    
    //录入管理员的信息
    int insertAdminUserPartyInfo(UserPartyInfo info);
    
    //录入管理员的信息
    int insertAdminUserPersonInfo(UserPersonInfo info);
    
    //检测账号ID是否存在
    Integer CheckUserIdSure(@Param(value="userId")String userId);
}
