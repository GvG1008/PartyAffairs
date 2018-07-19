package com.zqu.pa.dao;

import java.util.List;

import com.zqu.pa.entity.User;

public interface UserDao {

    User getUser();

    User getUserLogin(String userId);
    
    /**
     * 根据身份ID获取权限列表
     * @param roleId
     * @return
     */
    List<String> getRolePermission(int roleId);
}
