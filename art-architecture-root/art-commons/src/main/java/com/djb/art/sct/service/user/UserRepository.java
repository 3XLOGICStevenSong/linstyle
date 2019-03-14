package com.djb.art.sct.service.user;

import com.djb.art.cms.annotations.MybatisRepository;
import com.djb.art.sct.model.User;

@MybatisRepository
interface UserRepository {
	
	public Integer insertUser(User user);
	
	public User selectUserByLoginCode(String loginCode);
	
	public User selectUserByUserId(Integer userId);
	
	public Integer updateUser(User user);
	
	public Integer disableUserByUserId(Integer userId);
	
}
