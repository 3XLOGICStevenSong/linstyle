package cn.com.dbridge.lifecare.framework.util;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import cn.com.dbridge.lifecare.framework.constant.Constant;
import cn.com.dbridge.lifecare.framework.exception.CustomException;
import cn.com.dbridge.lifecare.framework.exception.CustomSignatureVerificationException;
import cn.com.dbridge.lifecare.framework.util.common.Base64ConvertUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @ClassName:  JwtUtil
 * @Description:JWT工具类
 * @author: 陈健飞
 * @date:   2019年1月19日 上午9:30:04
 * 
 * @Copyright: 2019 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
@Component
@Slf4j
public class JwtUtil {

    /**
     * 过期时间改为从配置文件获取
     */
    private static String accessTokenExpireTime;

    /**
     * JWT认证加密私钥(Base64加密)
     */
    private static String encryptJWTKey;

    @Value("${accessTokenExpireTime}")
    public void setAccessTokenExpireTime(String accessTokenExpireTime) {
        JwtUtil.accessTokenExpireTime = accessTokenExpireTime;
    }

    @Value("${encryptJWTKey}")
    public void setEncryptJWTKey(String encryptJWTKey) {
        JwtUtil.encryptJWTKey = encryptJWTKey;
    }

    /**
     * 
     * @Title: verify
     * @Description: 校验token是否正确
     * @param token
     * @return boolean 是否正确
     */
    public static boolean verify(String token) {
        try {
            // 帐号加JWT私钥解密
            String secret = getClaim(token, Constant.ACCOUNT)
                    + Base64ConvertUtil.decode(encryptJWTKey);
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm).build();
            verifier.verify(token);
            return true;
        } catch (UnsupportedEncodingException e) {
            log.error("JWTToken认证解密出现UnsupportedEncodingException异常:"
                    + e.getMessage());
            throw new CustomException(
                    "JWTToken认证解密出现UnsupportedEncodingException异常:"
                            + e.getMessage());
        } catch(SignatureVerificationException e) {
            throw new CustomSignatureVerificationException();
        }
    }

    /**
    * 
    * @Title: getClaim
    * @Description: 获得Token中的信息无需secret解密也能获得
    * @param token
    * @param claim
    * @return
    */
    public static String getClaim(String token, String claim) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            // 只能输出String类型，如果是其他类型返回null
            return jwt.getClaim(claim).asString();
        } catch (JWTDecodeException e) {
            log.error(
                    "解密Token中的公共信息出现JWTDecodeException异常:" + e.getMessage());
            throw new JWTDecodeException(
                    "解密Token中的公共信息出现JWTDecodeException异常:" + e.getMessage());
        }
    }
    /**
     * 
     * @Title: sign
     * @Description: 生成签名
     * @param account 帐号
     * @param currentTimeMillis
     * @return
     */
    public static String sign(String account, String currentTimeMillis) {
        try {
            // 帐号加JWT私钥加密
            String secret = account + Base64ConvertUtil.decode(encryptJWTKey);
            // 此处过期时间是以毫秒为单位，所以乘以1000
            Date date = new Date(System.currentTimeMillis()
                    + Long.parseLong(accessTokenExpireTime) * 1000);
            Algorithm algorithm = Algorithm.HMAC256(secret);
            // 附带account帐号信息
            return JWT.create().withClaim("account", account)
                    .withClaim("currentTimeMillis", currentTimeMillis)
                    .withExpiresAt(date).sign(algorithm);
        } catch (UnsupportedEncodingException e) {
            log.error("JWTToken加密出现UnsupportedEncodingException异常:"
                    + e.getMessage());
            throw new CustomException(
                    "JWTToken加密出现UnsupportedEncodingException异常:"
                            + e.getMessage());
        }
    }
}
