package com.djb.art.controller;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.djb.art.sct.model.Menu;
import com.djb.art.sct.model.User;
import com.djb.art.sct.service.ResourceService;
import com.djb.art.sct.service.RoleService;
import com.djb.art.sct.service.UserService;
import com.fasterxml.jackson.annotation.JsonView;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@RestController
public class CurrentUserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private ResourceService resourceService;
	
	@GetMapping("~resource/current/user")
	@JsonView(User.WithoutPasswordView.class)
	public User getCurrentUser() {
		return userService.getCurrentUser();
	}
	
	@GetMapping("~resource/current/user/menus")
	public JSONArray getCurrentUserMenus(){
		List<Menu> list= resourceService.getMenusByUserLoginCode(userService.getCurrentUserLoginCode());
		return buildMenuTree(list, 0);
	}
	
	private JSONArray buildMenuTree(List<Menu> menus, Integer root) {
		JSONArray ret = new JSONArray();
		List<Menu> list = menus.stream().filter(item->item.getParentId()==root).collect(Collectors.toList());
		for(Menu item : list) {
			ret.add(buildMenuItem(item, buildMenuTree(menus, item.getId())));
		}
		return ret;
	}
	
	private JSONObject buildMenuItem(Menu menu, JSONArray childern) {
		if (null == menu) return null;
		JSONObject ret = new JSONObject();
		ret.put("id", menu.getId());
		ret.put("name", menu.getName());
		ret.put("url", menu.getUrl());
		if (null != childern && childern.size() > 0)
			ret.put("children", childern);
		return ret;
	}
	
	@GetMapping("~resource/current/user/roles")
	public Set<String> getCurrentUserRoles(){
		return roleService.getRolesByUserLoginCode(userService.getCurrentUserLoginCode());
	}
	
	@GetMapping("~resource/current/user/permissions")
	public Set<String> getCurrentUserPermissions(){
		return resourceService.getPermissionsByUserLoginCode(userService.getCurrentUserLoginCode());
	}
	
}
