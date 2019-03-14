package com.djb.art.cms.security;


import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.djb.art.sct.model.User;
import com.djb.art.sct.service.ResourceService;
import com.djb.art.sct.service.RoleService;
import com.djb.art.sct.service.UserService;

public class UserRealm extends AuthorizingRealm {

	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private ResourceService permissionService;
	
	/**
	 * 授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		
		String loginCode = (String)principals.getPrimaryPrincipal();
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		authorizationInfo.setRoles(this.roleService.getRolesByUserLoginCode(loginCode));
		authorizationInfo.setStringPermissions(this.permissionService.getPermissionsByUserLoginCode(loginCode));
		return authorizationInfo;
		
	}

	/**
	 * 认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
		String loginCode = (String)authcToken.getPrincipal();
		User user = this.userService.getUserByLoginCode(loginCode);
		
		// 账户不存在
		if(user == null) {
			throw new UnknownAccountException();
        }
		
		// 账户异常，被锁定
		if(Boolean.FALSE.equals(user.getStatus())) {
			throw new LockedAccountException(); //帐号锁定
        }
		
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
			user.getLoginCode(),
			user.getPassword(),
			ByteSource.Util.bytes(user.getCredentialsSalt()),
			getName()
        );
		
		return authenticationInfo;
	}


}
