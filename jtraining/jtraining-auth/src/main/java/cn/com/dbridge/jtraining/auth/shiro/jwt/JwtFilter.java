package cn.com.dbridge.jtraining.auth.shiro.jwt;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;

import com.auth0.jwt.exceptions.TokenExpiredException;

import cn.com.dbridge.jtraining.auth.common.Constant;
import cn.com.dbridge.jtraining.auth.util.JwtUtil;
import cn.com.dbridge.jtraining.framework.exception.CustomSignatureVerificationException;
import cn.com.dbridge.jtraining.framework.util.ErrorMsgUtils;
import cn.com.dbridge.jtraining.framework.util.JedisUtil;
import cn.com.dbridge.jtraining.framework.util.common.PropertiesUtil;
import lombok.extern.slf4j.Slf4j;
/**
 * 
 * @ClassName:  JwtFilter
 * @Description: JwtFilterフィルタ
 * @author: 陈健飞
 * @date:   2018年12月5日 上午10:36:02
 * 
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved.
 *	注：このコンテンツはBJ Information Technology Co.、Ltd.が社内でのみ流通させており、他の商業目的で漏洩することは禁止されています。
 */
@Slf4j
public class JwtFilter extends BasicHttpAuthenticationFilter {

    /**
     * 
     * Title: isAccessAllowed
     * Description: アクセスが許可されているかどうかを判断する
     * @param request
     * @param response
     * @param mappedValue
     * @return
     * @see org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter#isAccessAllowed(javax.servlet.ServletRequest, javax.servlet.ServletResponse, java.lang.Object)
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request,ServletResponse response, Object mappedValue) {
        // ユーザーがログインしたいかどうかを判断する
        if (this.isLoginAttempt(request, response)) { 
            try {
                //ShiroのUserRealmにログインする
                this.executeLogin(request, response); 
            } catch (Exception e) {
                // アプリケーションの例外を取得します
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
     * 書き換えについて:
     * 親クラスのメソッドを比較できる、単にexecuteLoginメソッド呼び出しを削除する
     * 削除されない場合、doGetAuthenticationInfoメソッドは周期的に呼び出されます。
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        this.sendChallenge(request, response);
        return false;
    }

    /**
     * 承認フィールドがヘッダーに含まれていて、トークンログイン認証が承認されているかどうかを確認します。
     */
    @Override
    protected boolean isLoginAttempt(ServletRequest request,ServletResponse response) {
        String token = this.getAuthzHeader(request);
        return token != null;
    }

    /**
     * AccessTokenのログイン認証と認証を実行する
     */
    @Override
    protected boolean executeLogin(ServletRequest request,ServletResponse response) throws Exception {
        JwtToken token = new JwtToken(this.getAuthzHeader(request));
        this.getSubject(request, response).login(token);
        return true;
    }

    /**
     * 
     * 期限切れでない場合は、新しいAccessTokenを返し、通常のアクセスを継続します。
     */
    
    /**
     * 
     * @Title: refreshToken
     * @Description: ここでは、RefreshTokenがリフレッシュされてRefreshTokenが期限切れになったかどうかを判断します。
     *                 期限切れでない場合は、新しいAccessTokenを返し、通常のアクセスを継続します。
     * @param request ServletRequest
     * @param response ServletResponse
     * @return
     */
    private boolean refreshToken(ServletRequest request,ServletResponse response) {
        String token = this.getAuthzHeader(request);
        String account = JwtUtil.getClaim(token, Constant.ACCOUNT);
        if (JedisUtil.exists(Constant.PREFIX_SHIRO_REFRESH_TOKEN + account)) {
            String currentTimeMillisRedis = JedisUtil .getObject(Constant.PREFIX_SHIRO_REFRESH_TOKEN + account).toString();
            if (JwtUtil.getClaim(token, Constant.CURRENT_TIME_MILLIS).equals(currentTimeMillisRedis)) {
                String currentTimeMillis = String.valueOf(System.currentTimeMillis());
                PropertiesUtil.readProperties("config.properties");
                String refreshTokenExpireTime = PropertiesUtil.getProperty("refreshTokenExpireTime");
                JedisUtil.setObject(Constant.PREFIX_SHIRO_REFRESH_TOKEN + account,currentTimeMillis,Integer.parseInt(refreshTokenExpireTime));
                token = JwtUtil.sign(account, currentTimeMillis);
                JwtToken jwtToken = new JwtToken(token);
                this.getSubject(request, response).login(jwtToken);
                HttpServletResponse httpServletResponse = (HttpServletResponse) response;
                httpServletResponse.setHeader("Authorization", token);
                httpServletResponse.setHeader("Access-Control-Expose-Headers","Authorization");
                return true;
            }
        }
        return false;
    }
}
