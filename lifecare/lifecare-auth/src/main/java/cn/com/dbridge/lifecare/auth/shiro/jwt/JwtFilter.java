package cn.com.dbridge.lifecare.auth.shiro.jwt;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import com.auth0.jwt.exceptions.TokenExpiredException;

import cn.com.dbridge.lifecare.framework.constant.Constant;
import cn.com.dbridge.lifecare.framework.exception.CustomSignatureVerificationException;
import cn.com.dbridge.lifecare.framework.util.ErrorMsgUtils;
import cn.com.dbridge.lifecare.framework.util.JedisUtil;
import cn.com.dbridge.lifecare.framework.util.JwtUtil;
import cn.com.dbridge.lifecare.framework.util.common.PropertiesUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @ClassName:  JwtFilter
 * @Description: JWT 过滤器
 * @author: 陈健飞
 * @date:   2019年1月5日 下午3:36:03
 * 
 * @Copyright: 2019 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
@Slf4j
public class JwtFilter extends BasicHttpAuthenticationFilter {
    /**
     * 
     * Title: isAccessAllowed
     * Description: 未被Shiro拦截的请求会执行这个方法
     * @param request 
     * @param response
     * @param mappedValue
     * @return
     * @see org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter#isAccessAllowed(javax.servlet.ServletRequest, javax.servlet.ServletResponse, java.lang.Object)
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request,ServletResponse response, Object mappedValue) {
        if (this.isLoginAttempt(request, response)) {
            try {
                this.executeLogin(request, response); 
            } catch (Exception e) {
                Throwable throwable = e.getCause();
               if (throwable != null && throwable instanceof TokenExpiredException) {
                    if (this.refreshToken(request, response)) {
                        return true;
                    } else {
                    	ErrorMsgUtils.response401(request, response,"密钥已失效，请用新的密钥登录");
                    }
                } else if (throwable != null && throwable instanceof CustomSignatureVerificationException) {
                	ErrorMsgUtils.response401(request, response, "Token Invalid");
                } else {
                    if (throwable != null) {
                        log.info(throwable.getMessage());
                    }
                }
                return false;
            }
        }
        return true;
    }
    /**
     * 
     * Title: onAccessDenied
     * Description:处理未经身份验证的请求。
     * @param request
     * @param response
     * @return
     * @throws Exception
     * @see org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter#onAccessDenied(javax.servlet.ServletRequest, javax.servlet.ServletResponse)
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        this.sendChallenge(request, response);
        return false;
    }

    @Override
    protected boolean sendChallenge(ServletRequest request, ServletResponse response) {
        if (log.isDebugEnabled()) {
            log.debug("Authentication required: sending 401 Authentication challenge response.");
        }
        HttpServletResponse httpResponse = WebUtils.toHttp(response);
        httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        return false;
    }
    /**
     * 
     * Title: isLoginAttempt
     * Description:确定传入请求是否尝试登录
     * @param request
     * @param response
     * @return false:通过用户名、密码登录 true:不是通过用户名、密码登录请求
     * @see org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter#isLoginAttempt(javax.servlet.ServletRequest, javax.servlet.ServletResponse)
     */
    @Override
    protected boolean isLoginAttempt(ServletRequest request,ServletResponse response) {
        String token = this.getAuthzHeader(request);
        return token !=null;
    }
    /**
     * 
     * Title: executeLogin
     * Description:通过Token方式执行登陆
     * @param request
     * @param response
     * @return
     * @throws Exception
     * @see org.apache.shiro.web.filter.authc.AuthenticatingFilter#executeLogin(javax.servlet.ServletRequest, javax.servlet.ServletResponse)
     */
    @Override
    protected boolean executeLogin(ServletRequest request,ServletResponse response) throws Exception {
    	log.debug("执行executeLogin方法，接口通过Token方式进行登录");
        JwtToken token = new JwtToken(this.getAuthzHeader(request));
        this.getSubject(request, response).login(token);
        return true;
    }
    /**
     * 
     * @Title: refreshToken
     * @Description: 刷新Token
     * @param request
     * @param response
     * @return
     */
    private boolean refreshToken(ServletRequest request,ServletResponse response) {
    	log.debug("执行refreshToken");
        String token = this.getAuthzHeader(request);
        String account = JwtUtil.getClaim(token, Constant.ACCOUNT);
        if (JedisUtil.exists(Constant.PREFIX_SHIRO_REFRESH_TOKEN + account)) {
            String currentTimeMillisRedis = JedisUtil .getObject(Constant.PREFIX_SHIRO_REFRESH_TOKEN + account).toString();
            String timeInToken = JwtUtil.getClaim(token, Constant.CURRENT_TIME_MILLIS);
            if (timeInToken.equals(currentTimeMillisRedis)) {
            	log.debug("执行refreshToken时,token里解析的时间戳{},Redis中账户{}，的时间戳：{}相等[OK]",timeInToken,account,currentTimeMillisRedis);
            	String currentTimeMillis = String.valueOf(System.currentTimeMillis());
                PropertiesUtil.readProperties("config.properties");
                String refreshTokenExpireTime = PropertiesUtil.getProperty("refreshTokenExpireTime");
                JedisUtil.setObject(Constant.PREFIX_SHIRO_REFRESH_TOKEN + account,currentTimeMillis,Integer.parseInt(refreshTokenExpireTime));
                log.debug("执行refreshToken的时候更新refreshTokenExpireTime,在Redis中失效时间调整为：{}秒",refreshTokenExpireTime);
                token = JwtUtil.sign(account, currentTimeMillis);
                JwtToken jwtToken = new JwtToken(token);
                this.getSubject(request, response).login(jwtToken);
                HttpServletResponse httpServletResponse = (HttpServletResponse) response;
                httpServletResponse.setHeader("Authorization", token);
                httpServletResponse.setHeader("Access-Control-Expose-Headers","Authorization");
                return true;
            }
        }
        log.debug("执行refreshToken时,token:{},account:{}在缓存中不存在[非法]",token,account);
        //非法用户
        return false;
    }
}
