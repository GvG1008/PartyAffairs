package com.zqu.pa.service.role.impl;

import java.util.List;

import javax.management.RuntimeErrorException;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zqu.pa.common.Const;
import com.zqu.pa.common.ServerResponse;
import com.zqu.pa.dao.role.RoleMapper;
import com.zqu.pa.dao.role.RolePermissionMapper;
import com.zqu.pa.entity.role.Permission;
import com.zqu.pa.entity.role.Role;
import com.zqu.pa.realm.UserRealm;
import com.zqu.pa.service.role.RolePermissionService;
import com.zqu.pa.vo.role.RoleInfo;
import com.zqu.pa.vo.userInfo.AdminUserInfo;

@Service
public class RolePermissionServiceImpl implements RolePermissionService {

    //默认身份roleId:1 普通党员
    public static final int DEFAULT_ROLE = 1;
    
    @Autowired 
    private UserRealm userRealm;
    
    @Autowired
    RoleMapper roleDao;
    
    @Autowired
    RolePermissionMapper permissionDao;
    
    @Override
    public ServerResponse getRoleList() {
        List<RoleInfo> roleList = roleDao.selectRole();
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
        /**
         * 在权限修改后
         * 在service调用此方法UserRealm.clearCached()
         * 可以及时清空用户缓存
         * 达到权限修改立即生效的目的
         */
        userRealm.clearCached();
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

    @Transactional
    @Override
    public ServerResponse updateRolePermissionList(Integer roleId, List<Integer> permissionList) {
        //删除所有权限
        boolean result = permissionDao.deleteRolePermissions(roleId)>=0;
        //添加新的所有权限
        if(!result)
            return ServerResponse.createByErrorMessage("操作失败");
        if(permissionList.size()!=0) {
            result = permissionDao.insertRolePermissions(roleId, permissionList)>0;
            if(!result) {
                throw new RuntimeException("操作失败，回滚");
            }
        }

        /**
         * 在权限修改后
         * 在service调用此方法UserRealm.clearCached()
         * 可以及时清空用户缓存
         * 达到权限修改立即生效的目的
         */
        userRealm.clearCached();
        return ServerResponse.createBySuccessMessage("修改权限成功");
    }

	@Override
	public ServerResponse getAllPermissionList() {

		List<Permission> permissionList = permissionDao.selectAllPermission();
		if(permissionList == null || permissionList.size()==0) {
			return ServerResponse.createByErrorMessage("获取失败");
	    }

		return ServerResponse.createBySuccess(permissionList);
	}

    @Override
    public ServerResponse getAdminUserList() {
        //获取拥有管理权限字段的角色ID
        List<Integer> roleIds = permissionDao.selectAdminRoleIdList(Const.ADMIN_PERMISSION_NAME);
        if(roleIds == null ) {
            return ServerResponse.createByErrorMessage("查询失败");
        }
        if(roleIds.size()==0) {
            return ServerResponse.createByErrorMessage("管理员为空");
        }
        List<AdminUserInfo> info = permissionDao.selectAdminUserList(roleIds);
        if(info == null) {
            return ServerResponse.createByErrorMessage("查询失败");
        }
        if(info.size()==0) {
            ServerResponse.createBySuccessMessage("管理员账号为空");
        }
        return ServerResponse.createBySuccess("获取成功", info);
    }

}
