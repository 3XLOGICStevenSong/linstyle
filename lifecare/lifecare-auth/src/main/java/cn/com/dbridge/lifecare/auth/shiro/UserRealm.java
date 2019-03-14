package cn.com.dbridge.lifecare.auth.shiro;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auth0.jwt.exceptions.TokenExpiredException;

import cn.com.dbridge.lifecare.auth.shiro.jwt.JwtToken;
import cn.com.dbridge.lifecare.dao.po.UserPO;
import cn.com.dbridge.lifecare.dao.respository.UserPOMapper;
import cn.com.dbridge.lifecare.framework.constant.Constant;
import cn.com.dbridge.lifecare.framework.enums.ResponseCode;
import cn.com.dbridge.lifecare.framework.util.JedisUtil;
import cn.com.dbridge.lifecare.framework.util.JwtUtil;
import cn.com.dbridge.lifecare.framework.util.common.StringUtil;
import cn.com.dbridge.lifecare.service.ResourceService;
import cn.com.dbridge.lifecare.service.RoleService;
/**
 * @ClassName:  UserRealm
 * @Description: 自定义Ream
 * @author: 陈健飞
 * @date:   2018年12月7日 上午9:12:05
 * 
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
@Service
@Slf4j
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserPOMapper userPOMapper;
    @Autowired
    private RoleService roleService;

    @Autowired
    private ResourceService permissionService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {
        //获取请求的Token
    	String token = (String) auth.getCredentials();
        log.debug("token:{}",token);
    	//获取Token中的account信息
    	String account = JwtUtil.getClaim(token, Constant.ACCOUNT);
        
    	//如果Token中的账户信息为空，则为无权访问
    	if (StringUtil.isBlank(account)) {
            throw new AuthenticationException(ResponseCode.PERMISSION_ERROR.name);
        }
    	
    	//根据Token中的account信息查询数据库中的用户信息，如果没有对应记录，则为无权限
        UserPO userPO = new UserPO();
        userPO.setUserNumber(account);
        if (null == userPOMapper.selectByUser(userPO)) {
            throw new AuthenticationException(ResponseCode.PERMISSION_ERROR.name);
        }
        
        //如果Token合法、在Redis中存在、时间合法
        if(JwtUtil.verify(token) && JedisUtil.exists(Constant.PREFIX_SHIRO_REFRESH_TOKEN + account)){
            String currentTimeMillisRedis = JedisUtil.getObject(Constant.PREFIX_SHIRO_REFRESH_TOKEN + account).toString();
            //Token中account的时间戳和Redis中Token的时间戳相等则为合法。
            if(JwtUtil.getClaim(token, Constant.CURRENT_TIME_MILLIS).equals(currentTimeMillisRedis)){
            	return new SimpleAuthenticationInfo(account, token, "userRealm");
            }
        }
        //Token过期
        throw new TokenExpiredException(ResponseCode.PERMISSION_ERROR.name);
    }

    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //获取Token
    	String token = (String) principals.getPrimaryPrincipal();
        
    	//获取Token中的account信息
    	String account = JwtUtil.getClaim(token, Constant.ACCOUNT);
        
    	//对该账户授予角色
    	SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(this.roleService.getRolesByUserLoginCode(account));
        
        //对该账户授予权限
        authorizationInfo.setStringPermissions(this.permissionService.getPermissionsByUserLoginCode(account));
        return authorizationInfo;
    }
}
