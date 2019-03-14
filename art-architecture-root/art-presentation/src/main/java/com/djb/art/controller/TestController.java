package com.djb.art.controller;


import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.djb.art.sct.model.User;
import com.djb.art.sct.service.UserService;


@RestController
public class TestController {
	
	@Autowired
	private UserService userService;
	
	@RequiresPermissions("fc:*")
	@GetMapping("~resource/fc/test")
	public String getFcTestString(){
		return "fc test ok!";
	}
	
	@RequiresPermissions("user:*")
	@GetMapping("~resource/management/user/test")
	public String getUserTestString(){
		return "test";
	}
	
	@RequiresPermissions("user:create")
	@GetMapping("~resource/management/user")
	public User CreateUserTest(){
		User user = new User();
		user.setLoginCode("user");
		user.setPassword("ba3253876aed6bc22d4a6ff53d8406c6ad864195ed144ab5c87621b6c233b548baeae6956df346ec8c17f5ea10f35ee3cbc514797ed7ddd3145464e2a0bab413");
		user.setUserName("普通用户");
		userService.createUser(user);
		return userService.getUserByUserId(user.getBackUserId());
	}

}
