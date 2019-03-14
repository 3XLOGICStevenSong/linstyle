/**
 * All rights Reserved, Designed By dbridge.com.cn
 * @Title:  ErrorField.java
 * @Package cn.com.dbridge.lifecare.framework.base
 * @Description:    错误属性模型 
 * @author: 陈健飞 
 * @date:   2018年12月7日 上午9:08:48
 * @version V1.0
 * @Copyright: 2018 dbridge.com.cn Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
package cn.com.dbridge.lifecare.framework.base;

import lombok.Data;

/**
 * @ClassName:  ErrorField
 * @Description: 错误属性模型
 * @author: 陈健飞
 * @date:   2018年12月7日 上午9:08:48
 * 
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
@Data
public class ErrorField {
    private String field;
    private String message;
}
