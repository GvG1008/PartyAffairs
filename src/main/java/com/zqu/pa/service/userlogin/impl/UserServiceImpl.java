package com.zqu.pa.service.userlogin.impl;

import java.util.List;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zqu.pa.common.UserLoginException;
import com.zqu.pa.dao.userlogin.UserDao;
import com.zqu.pa.entity.User;
import com.zqu.pa.service.userlogin.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    
    @Override
    public User getUser() {
        // TODO Auto-generated method stub
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

    
}
