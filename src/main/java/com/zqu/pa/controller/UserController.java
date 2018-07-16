package com.zqu.pa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zqu.pa.common.ServerResponse;
import com.zqu.pa.entity.User;
import com.zqu.pa.service.UserService;

@Controller
public class UserController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private User user;
    
    @ResponseBody
    @RequestMapping("/user")
    public User getUser() {
        return userService.getUser();
    }
    
    @ResponseBody
    @RequestMapping("/user1")
    public ServerResponse<User> getStatusUser() {
        user = userService.getUser();
        //ServerResponse<User> serverResponse = ServerResponse.createBySuccess("成功信息", user);
        ServerResponse<User> serverResponse = ServerResponse.createByErrorCodeMessage(404, "错误信息");
        return serverResponse;
    }
}
