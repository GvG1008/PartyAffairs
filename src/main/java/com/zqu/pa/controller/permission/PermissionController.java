package com.zqu.pa.controller.permission;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zqu.pa.common.ServerResponse;
import com.zqu.pa.service.role.RolePermissionService;

@Controller
@RequestMapping("/permission")
public class PermissionController {
	
	@Autowired
    RolePermissionService rolePermissionService;

	@ResponseBody
    @RequestMapping(value="/list",method=RequestMethod.GET)
	public ServerResponse getAllPermissionList() {
		return rolePermissionService.getAllPermissionList();
	}
}
