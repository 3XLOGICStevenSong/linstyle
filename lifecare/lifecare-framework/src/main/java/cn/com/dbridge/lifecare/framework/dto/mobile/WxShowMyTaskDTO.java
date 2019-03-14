package cn.com.dbridge.lifecare.framework.dto.mobile;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 
 * @ClassName:  WxShowMyTaskDTO
 * @Description:微信查看我的任务DTO
 * @author: 陈健飞
 * @date:   2019年1月9日 上午9:42 
 * @Copyright: 2019 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
@Data
public class WxShowMyTaskDTO {
    /**
     * 订单编号
     */
    @ApiModelProperty(value = "订单编号")
    private Integer taskId;
    /**
     * 用户编号
     */
    @ApiModelProperty(value = "用户编号")
    private Integer userId;
    
    /**
     * 类别 0：任务池 1:我的任务
     */
    @ApiModelProperty(value = "类别")
    private String type = "1";
}
