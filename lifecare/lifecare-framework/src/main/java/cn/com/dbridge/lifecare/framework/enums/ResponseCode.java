/**
 * All rights Reserved, Designed By dbridge.com.cn
 * 
 * @Title: DynamicErrorCode.java
 * @Package cn.com.dbridge.lifecare.framework.enums
 * @Description: 自定义错误码枚举
 * @author: 陈健飞
 * @date: 2018年12月7日 上午9:12:05
 * @version V1.0
 * @Copyright: 2018 dbridge.com.cn Inc. All rights reserved.
 *             注意：本内容仅限于必捷必信息技术有限公司 内部传阅，禁止外泄以及用于其他的商业目
 */
package cn.com.dbridge.lifecare.framework.enums;

/**
 * @ClassName:  DynamicErrorCode
 * @Description: 自定义错误码枚举
 * @author: 陈健飞
 * @date:   2018年12月7日 上午9:12:05
 * 
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
public enum ResponseCode {
	REQUEST_SUCCESS(200,"Request Successful"),
	PERMISSION_ERROR(401,"身份认证错误"),
	NOT_FOUND(404,"Not Found"),
	DATA_CHECK_ERROR(511, "Data Check Error"),
	SERVICE_ERROR(512, "Service Error"),
    SERVER_ERROR(500, "Server Error"),
    WECHAT_MP_ERROR(513, "WECHAT MP ERROR");
    public final int value;
    public final String name;
    ResponseCode(int value, String name) {
        this.value = value;
        this.name = name;
    }
}
