package cn.com.dbridge.lifecare.framework.vo.mobile;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName:MobileWaitingServiceVO
 * @Description:TODO
 * @author:郭健
 * @date:2019年1月4日 上午11:16:50
 * @Copyright: 2019 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目的
 */
public class MobileWaitingServiceVO {
    /**
     * 客户主键
     */
    @ApiModelProperty(value = "客户主键")
    private Integer customId;

    /**
     * 服务类别名称
     */
    @ApiModelProperty(value = "服务类别名称")
    private String serviceCategoryName;

    /**
     * 服务类别颜色
     */
    @ApiModelProperty(value = "服务类别颜色")
    private String serviceCategoryColor;

    /**
     * 名称(客户名称、服务人员名称、后台用户名称)
     */
    @ApiModelProperty(value = "名称(客户名称、服务人员名称、后台用户名称)")
    private String realName;

    /**
     * 预约开始时间
     */
    @ApiModelProperty(value = "预约开始时间")
    private Date orderBeginTime;

    /**
     * 预约结束时间
     */
    @ApiModelProperty(value = "预约结束时间")
    private Date orderEndTime;

    /**
     * 订单状态(1：待分配 2:待完成 3.已完成 4.取消)
     */
    @ApiModelProperty(value = "订单状态(1：待分配 2:待完成 3.已完成 4.取消)")
    private Byte orderStatus;
}
