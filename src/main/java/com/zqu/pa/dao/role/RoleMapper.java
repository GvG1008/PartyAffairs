package com.zqu.pa.dao.role;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zqu.pa.vo.perinfo.Role;
import com.zqu.pa.vo.role.RoleInfo;

public interface RoleMapper {
    
    List<RoleInfo> selectRole();
    
    Role getUserRole(@Param("userId")String userId);
    
    int insertRole(@Param("roleName")String roleName);
    
    int deleteRole(@Param("roleId")Integer roleId);
    
    int updateRole(@Param("oldRole")Integer oldRole, @Param("newRole")Integer newRole);
    
    int updateUserRole(@Param("roleId")Integer roleId, @Param("userId")String userId);
}
