package com.djb.art.sct.service.role;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.djb.art.sct.model.Role;
import com.djb.art.sct.service.RoleService;

public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository roleRepository;
	@Override
	public Set<String> getRolesByUserLoginCode(String loginCode) {
		Set<String> ret = new HashSet<String>();
		List<String> tmp = roleRepository.selectStringRolesByUserLoginCode(loginCode);
		for(String item : tmp) {
			ret.add(item);
		}
		return ret;
	}

	@Override
	public Integer createRole(Role role) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer disableRoleByRoleId(Integer roleId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Role> getRoles() {
		// TODO Auto-generated method stub
		return null;
	}

}
