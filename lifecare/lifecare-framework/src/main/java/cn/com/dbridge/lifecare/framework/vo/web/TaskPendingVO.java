
package cn.com.dbridge.lifecare.framework.vo.web;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 
 * @ClassName:  RoleResultVO
 * @Description:待处理订单VO
 * @author: linh
 * @date:   2019年1月4日 上午11:03:32
 * 
 * @Copyright: 2019 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
@Data
@JsonInclude(value = Include.NON_NULL)
public class TaskPendingVO {
    /**
     * 主键
     */
    @ApiModelProperty(value = "待处理订单数")
    private Integer pendingCnt;

}
