package cn.com.dbridge.jtraining.framework.exception;

/**
 * 
 * @ClassName:  CustomException
 * @Description:自定义异常
 * @author: 陈健飞
 * @date:   2018年12月5日 上午10:34:00
 * 
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
public class CustomException extends RuntimeException {

    private static final long serialVersionUID = -7032280241409667512L;

    public CustomException(String msg) {
        super(msg);
    }

    public CustomException() {
        super();
    }
}
