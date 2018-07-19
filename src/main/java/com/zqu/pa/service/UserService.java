package com.zqu.pa.service;

import java.util.List;

import com.zqu.pa.entity.User;

public interface UserService {

    User getUser();
    User userlogin(String userId);
    /**
     * 根据身份ID获取权限列表
     * @param roleId
     * @return
     */
    List<String> getPermissionMenu(int roleId);
}
