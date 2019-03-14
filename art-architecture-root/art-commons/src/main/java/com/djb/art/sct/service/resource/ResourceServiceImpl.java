package com.djb.art.sct.service.resource;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.djb.art.sct.model.Menu;
import com.djb.art.sct.model.Resource;
import com.djb.art.sct.service.ResourceService;

public class ResourceServiceImpl implements ResourceService {

	@Autowired
	private ResourceRepository resourceRepository;
	
	@Override
	public Set<String> getPermissionsByUserLoginCode(String loginCode) {
		Set<String> ret = new HashSet<String>();
		List<String> tmp = resourceRepository.selectStringPermissionsByUserLoginCode(loginCode);
		for(String item : tmp) {
			ret.add(item);
		}
		return ret;
	}

	@Override
	public List<Menu> getMenusByUserLoginCode(String userLoginCode) {
		return resourceRepository.selectMenusByUserLoginCode(userLoginCode);
	}

	@Override
	public Integer createResource(Resource resource) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer updateResource(Resource resource) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer disableResource(Integer resourceId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Resource getResourceByResourceId(Integer resourceId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Resource> getResources() {
		// TODO Auto-generated method stub
		return null;
	}

}
