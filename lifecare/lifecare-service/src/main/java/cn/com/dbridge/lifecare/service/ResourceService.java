package cn.com.dbridge.lifecare.service;

import java.util.Set;

import cn.com.dbridge.lifecare.dao.po.ResourcePO;
import cn.com.dbridge.lifecare.framework.vo.web.MenuResultVO;
import cn.com.dbridge.lifecare.framework.vo.web.ResourceResultVO;



public interface ResourceService {
	
	public Set<String> getPermissionsByUserLoginCode(String userLoginCode);
	
    public MenuResultVO getMenusByUserNumber(String userNumber);
	
    public Integer createResource(ResourcePO resource);
	
    public Integer updateResource(ResourcePO resource);
	
	public Integer disableResource(Integer resourceId);
	
    public ResourcePO getResourceByResourceId(Integer resourceId);
	
    public ResourceResultVO getResources(Integer roleId);
	
}
