package com.djb.art.sct.service;


import com.djb.art.sct.model.User;

public interface UserService {

	// 通过账户名获取用户
	public User getUserByLoginCode(String loginCode);
	
	public User getUserByUserId(Integer userId);
	
	// 创建用户
	public Integer createUser(User user);
	
	// 更新用户登录的ip地址
	public Integer updateLoginIp(Integer userId, String ip);
	
	// 更改密码
	public Integer changePassword(Integer useId, String oldPwd, String newPwd);
	
	// 禁用账户
	public Integer disablesUser(Integer useId);
	
	// 获取当前登录用户
	public User getCurrentUser();
	
	// 获取当前登录用户的账户名
	public String getCurrentUserLoginCode();
	
}
