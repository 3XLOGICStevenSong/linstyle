/**
 * All rights Reserved, Designed By dbridge.com.cn
 * @Title:  Result.java
 * @Package lifecare.framework.base
 * @Description:    返回对象封装类 
 * @author: 陈健飞 
 * @date:   2018年11月22日 下午1:54:49
 * @version V1.0
 * @Copyright: 2018 dbridge.com.cn Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
package cn.com.dbridge.jtraining.framework.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName:  Result
 * @Description: 返回对象封装类
 * @author: 陈健飞
 * @date:   2018年11月22日 下午1:54:49
 * 
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
@Data
public class Result<T> {

    @ApiModelProperty(value = "状态码")
    private Integer statusCode;
    @ApiModelProperty(value = "描述信息")
    private String msg;
    @ApiModelProperty(value = "返回数据")
    private T data;

    public Result() {
    }
    public Result(Integer statusCode, String msg) {
        super();
        this.statusCode = statusCode;
        this.msg = msg;
    }
    public Result(Integer statusCode, String msg, T data) {
        this(statusCode, msg);
        this.data = data;
    }
}
