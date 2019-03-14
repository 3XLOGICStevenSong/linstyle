package cn.com.dbridge.lifecare.framework.exception;

/**
 * 
 * @ClassName:  CustomUnauthorizedException
 * @Description:自定义401无权限异常
 * @author: 陈健飞
 * @date:   2018年12月5日 上午10:34:18
 * 
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
public class CustomUnauthorizedException extends RuntimeException {

	private static final long serialVersionUID = -5616945239523562917L;

	public CustomUnauthorizedException(String msg){
        super(msg);
    }

    public CustomUnauthorizedException() {
        super();
    }
}
