package com.zqu.pa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zqu.pa.entity.User;
import com.zqu.pa.service.UserService;

@Controller
public class UserController {

    @Autowired
    private UserService userService;
    
    @ResponseBody
    @RequestMapping("/user")
    public User getUser() {
        return userService.getUser();
    }
}
