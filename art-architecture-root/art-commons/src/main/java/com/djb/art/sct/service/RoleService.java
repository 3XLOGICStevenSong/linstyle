package com.djb.art.sct.service;

import java.util.List;
import java.util.Set;

import com.djb.art.sct.model.Role;

public interface RoleService {
	
	public Set<String> getRolesByUserLoginCode(String userLoginCode);
	
	public Integer createRole(Role role);
	
	public Integer disableRoleByRoleId(Integer roleId);
	
	public List<Role> getRoles();
	
}
