package com.djb.art.sct.service.resource;

import java.util.List;

import com.djb.art.cms.annotations.MybatisRepository;
import com.djb.art.sct.model.Menu;
import com.djb.art.sct.model.Role;

@MybatisRepository
interface ResourceRepository {
	
	public Integer insertRole(Role user);
	
	public List<String> selectStringPermissionsByUserLoginCode(String loginCode);
	
	public List<Menu> selectMenusByUserLoginCode(String userLoginCode);
	
}
