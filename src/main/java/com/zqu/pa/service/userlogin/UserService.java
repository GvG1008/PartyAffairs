package com.zqu.pa.service.userlogin;

import java.util.List;

import com.zqu.pa.entity.userlogin.User;

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
