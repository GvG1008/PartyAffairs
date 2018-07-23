package com.zqu.pa.service.userlogin.impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.zqu.pa.dao.userlogin.UserDao;
import com.zqu.pa.entity.partybranch.PartyBranch;
import com.zqu.pa.entity.userlogin.User;
import com.zqu.pa.service.userlogin.UserService;
import com.zqu.pa.vo.userInfo.UserBasicInfo;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    
    /**
     * 测试
     */
    @Override
    public User getUser() {

        return userDao.getUser();
    }

    /**
     * 根据输入的用户名和密码返回User
     */
    @Override
    public User userlogin(String userId){
       
        return userDao.getUserLogin(userId);
    }

    @Override
    public List<String> getPermissionMenu(int roleId) {
        
        return userDao.getRolePermission(roleId);
    }

/*    @Override
    public String getUserRealName(String userId) {
        
        return userDao.getRealName(userId);
    }

    @Override
    public PartyBranch getPartyBranch(String userId) {
        
        return userDao.getPartyBranch(userId);
    }*/

    @Override
    public UserBasicInfo getUserBasicInfo(String userId) {
        UserBasicInfo userBasicInfo = new UserBasicInfo();
        userBasicInfo = userDao.getUserBasicInfo(userId);
        return userBasicInfo;
    }

}
