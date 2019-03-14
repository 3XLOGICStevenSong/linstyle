/**
 * All rights Reserved, Designed By dbridge.com.cn
 * @Title:  PageDTO.java
 * @Package lifecare.framework.base
 * @Description:    分页查询参数模型 
 * @author: 陈健飞 
 * @date:   2018年12月3日 上午9:40:23
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
 * @ClassName:  PageDTO
 * @Description: 分页查询参数模型 
 * @author: 陈健飞
 * @date:   2018年12月3日 上午9:40:23
 * 
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageDTO {
    @ApiModelProperty(value = "当前页数")
    private Integer offset;
    @ApiModelProperty(value = "每页显示记录数")
    private Integer limit;
}
