package com.djb.art.sct.service.role;

import java.util.List;

import com.djb.art.cms.annotations.MybatisRepository;
import com.djb.art.sct.model.Role;

@MybatisRepository
interface RoleRepository {
	
	public List<String> selectStringRolesByUserLoginCode(String loginCode);
	
	public Integer insertRole(Role role);
	
	public Integer updateRole(Role role);
	
	public Integer disableRoleByRoleId(Integer roleId);
	
	public Role getRoleByRoleId(Integer roleId);
	
	public List<Role> getRoles();
	
}
