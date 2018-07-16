package com.zqu.pa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zqu.pa.dao.UserDao;
import com.zqu.pa.entity.User;
import com.zqu.pa.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    
    @Override
    public User getUser() {
        // TODO Auto-generated method stub
        return userDao.getUser();
    }

    
}
