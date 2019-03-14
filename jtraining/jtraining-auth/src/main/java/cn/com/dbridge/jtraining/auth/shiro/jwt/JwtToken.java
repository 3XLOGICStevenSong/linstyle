package cn.com.dbridge.jtraining.auth.shiro.jwt;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * 
 * @ClassName:  JwtToken
 * @Description:JwtToken
 * @author: 陈健飞
 * @date:   2018年12月5日 上午10:36:19
 * 
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved.
 *	注：このコンテンツはBJ Information Technology Co.、Ltd.が社内でのみ流通させており、他の商業目的で漏洩することは禁止されています。
 */
public class JwtToken implements AuthenticationToken {
    private static final long serialVersionUID = -3573313547079142883L;
    private String token;

    public JwtToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
