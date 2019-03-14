/**
 * All rights Reserved, Designed By dbridge.com.cn
 * @Title:  Result.java
 * @Package lifecare.framework.base
 * @Description:    分页操作返回对象模型
 * @author: 陈健飞 
 * @date:   2018年11月22日 下午1:54:49
 * @version V1.0
 * @Copyright: 2018 dbridge.com.cn Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
package cn.com.dbridge.jtraining.framework.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName:  Result
 * @Description: 分页操作返回对象模型
 * @author: 陈健飞
 * @date:   2018年11月22日 下午1:54:49
 * 
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResult<T> {
    @ApiModelProperty(value = "状态码")
    private Integer statusCode;
    @ApiModelProperty(value = "当前页数")
    private int offset;
    @ApiModelProperty(value = "每页显示记录数")
    private int limit;
    @ApiModelProperty(value = "接口名称")
    private String name;
    private T data;
}
