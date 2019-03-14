package cn.com.dbridge.lifecare.framework.vo.mobile;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName:MobileMyTaskVO
 * @Description:TODO
 * @author:郭健
 * @date:2019年1月3日 下午12:58:43
 * @Copyright: 2019 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目的
 */
@Data
public class MobileMyTaskVO {
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    private Integer id;

    /**
     * 服务类别名称
     */
    @ApiModelProperty(value = "服务类别名称")
    private String serviceCategoryName;

    /**
     * 服务类颜色
     */
    @ApiModelProperty(value = "服务类颜色")
    private String serviceCategoryColor;
    
    /**
     * 名称(客户名称、服务人员名称、后台用户名称)
     */
    @ApiModelProperty(value = "名称(客户名称、服务人员名称、后台用户名称)")
    private String realName;

    /**
     * 客户主键
     */
    @ApiModelProperty(value = "客户主键")
    private Integer customId;

    /**
     * 预约开始时间
     */
    @ApiModelProperty(value = "预约开始时间")
    @JsonFormat(timezone = "GMT+8", pattern = "HH:mm:ss")
    private Date orderBeginTime;

    /**
     * 预约结束时间
     */
    @ApiModelProperty(value = "预约结束时间")
    @JsonFormat(timezone = "GMT+8", pattern = "HH:mm:ss")
    private Date orderEndTime;

    /**
     * 订单状态(1：待分配 2:待完成 3.已完成 4.取消)
     */
    @ApiModelProperty(value = "订单状态(1：待分配 2:待完成 3.已完成 4.取消)")
    private Byte orderStatus;

    /**
     * 查看标志
     */
    @ApiModelProperty(value = "查看标志")
    private String seeFlag;
}
