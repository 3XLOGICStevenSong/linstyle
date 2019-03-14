package cn.com.dbridge.lifecare.framework.dto.mobile;

import cn.com.dbridge.lifecare.framework.enums.TaskStatusEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 
 * @ClassName:  WxWaitingServiceDTO
 * @Description:微信获取待服务订单用户信息
 * @author: 陈健飞
 * @date:   2019年1月9日 上午9:42 
 * @Copyright: 2019 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
@Data
public class WxWaitingServiceDTO {
    /**
     * 服务人员编号
     */
    @ApiModelProperty(value = "服务人员编号")
    private Integer servicePersonId;
    /**
     * 订单状态，默认待完成状态(2:待完成  3:已完成)
     */
    private String orderStatus=TaskStatusEnum.TO_BE_COMPLETED.value;
    
    /**
     * 分页偏移量
     */
    @ApiModelProperty(value = "分页偏移量)")
    private Integer offset;
    /**
     * 每页显示记录数
     */
    @ApiModelProperty(value = "每页显示记录数")
    private Integer limit;
}
