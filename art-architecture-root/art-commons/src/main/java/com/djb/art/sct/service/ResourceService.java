package com.djb.art.sct.service;

import java.util.List;
import java.util.Set;

import com.djb.art.sct.model.Menu;
import com.djb.art.sct.model.Resource;

public interface ResourceService {
	
	public Set<String> getPermissionsByUserLoginCode(String userLoginCode);
	
	public List<Menu> getMenusByUserLoginCode(String userLoginCode);
	
	public Integer createResource(Resource resource);
	
	public Integer updateResource(Resource resource);
	
	public Integer disableResource(Integer resourceId);
	
	public Resource getResourceByResourceId(Integer resourceId);
	
	public List<Resource> getResources();
	
}
