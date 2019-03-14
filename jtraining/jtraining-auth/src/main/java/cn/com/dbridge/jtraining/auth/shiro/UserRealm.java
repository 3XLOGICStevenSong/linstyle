package cn.com.dbridge.jtraining.auth.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.dbridge.jtraining.auth.common.Constant;
import cn.com.dbridge.jtraining.auth.shiro.jwt.JwtToken;
import cn.com.dbridge.jtraining.auth.util.JwtUtil;
import cn.com.dbridge.jtraining.dao.po.UserPO;
import cn.com.dbridge.jtraining.dao.respository.UserPOMapper;
import cn.com.dbridge.jtraining.framework.util.JedisUtil;
import cn.com.dbridge.jtraining.framework.util.common.StringUtil;

/**
 * @ClassName:  UserRealm
 * @Description:カスタムレルム
 * @author: 陈健飞
 * @date:   2018年12月5日 上午10:35:50
 * 
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved.
 *	注：このコンテンツはBJ Information Technology Co.、Ltd.が社内でのみ流通させており、他の商業目的で漏洩することは禁止されています。
 */
@Service
public class UserRealm extends AuthenticatingRealm {

    @Autowired
    private UserPOMapper userPOMapper;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    /**
     * ユーザー名が正しいかどうか, 例外をスローする際のエラー
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {
        String token = (String) auth.getCredentials();
        String account = JwtUtil.getClaim(token, Constant.ACCOUNT);
        if (StringUtil.isBlank(account)) {
            throw new AuthenticationException("トークンアカウントが空です");
        }
        UserPO userPO = userPOMapper.selectByPrimaryKey(account);
        if (userPO == null) {
            throw new AuthenticationException("アカウントが存在しません");
        }
        if(JwtUtil.verify(token) && JedisUtil.exists(Constant.PREFIX_SHIRO_REFRESH_TOKEN + account)){
            String currentTimeMillisRedis = JedisUtil.getObject(Constant.PREFIX_SHIRO_REFRESH_TOKEN + account).toString();
            if(JwtUtil.getClaim(token, Constant.CURRENT_TIME_MILLIS).equals(currentTimeMillisRedis)){
                return new SimpleAuthenticationInfo(token, token, "userRealm");
            }
        }
        throw new AuthenticationException("トークンは期限切れです");
    }
}
