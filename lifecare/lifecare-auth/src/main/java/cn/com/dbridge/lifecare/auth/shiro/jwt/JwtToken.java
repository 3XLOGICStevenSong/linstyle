package cn.com.dbridge.lifecare.auth.shiro.jwt;

import org.apache.shiro.authc.AuthenticationToken;
/**
 * 
 * @ClassName:  JwtToken
 * @Description: JWT Token
 * @author: 陈健飞
 * @date:   2019年1月5日 下午3:36:03
 * 
 * @Copyright: 2019 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
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
