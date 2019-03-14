package cn.com.dbridge.lifecare.framework.vo.mobile;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName:MobileTaskVO
 * @Description:TODO
 * @author:郭健
 * @date:2019年1月4日 上午8:52:56
 * @Copyright: 2019 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目的
 */
@Data
public class MobileTaskVO {
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    private Integer id;


    /**
     * 名称(客户名称、服务人员名称、后台用户名称)
     */
    @ApiModelProperty(value = "名称(客户名称、服务人员名称、后台用户名称)")
    private String realName;

    /**
     * 手机
     */
    @ApiModelProperty(value = "手机")
    private String mobile;

    /**
     * 预约地址
     */
    @ApiModelProperty(value = "预约地址")
    private String orderAddr;

    /**
     * 服务内容
     */
    @ApiModelProperty(value = "服务内容")
    private String contenta;

    /**
     * 预约订单时长
     */
    @ApiModelProperty(value = "预约订单时长")
    private String orderDuration;

    /**
     * 预约日期
     */
    @ApiModelProperty(value = "预约日期")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date orderDate;

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
     * 订单编号、任务编号
     */
    @ApiModelProperty(value = "订单编号、任务编号")
    private String orderNo;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

}
