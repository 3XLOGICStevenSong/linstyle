package cn.com.dbridge.lifecare.framework.constant;

/**
 * 
 * @ClassName:  Constant
 * @Description: 常量
 * @author: 陈健飞
 * @date:   2019年1月5日 上午11:40:18
 * 
 * @Copyright: 2019 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
public class Constant {

    /**
     * redis-key-前缀-shiro:cache:重写Shiro的cache
     */
    public final static String PREFIX_SHIRO_CACHE = "lifecare:shiro:cache:";

    /**
     * redis-key-前缀-shiro:refresh_token:用于Token处理
     */
    public final static String PREFIX_SHIRO_REFRESH_TOKEN = "lifecare:shiro:refresh_token:";

    /**
     * JWT-account:存储Token的account属性
     */
    public final static String ACCOUNT = "account";

    /**
     * JWT-currentTimeMillis:存储Token的当前时间戳
     */
    public final static String CURRENT_TIME_MILLIS = "currentTimeMillis";

    /**
     * PASSWORD_MAX_LEN
     */
    public static final Integer PASSWORD_MAX_LEN = 20;

}
