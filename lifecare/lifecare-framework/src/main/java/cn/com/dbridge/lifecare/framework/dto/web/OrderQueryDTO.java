package cn.com.dbridge.lifecare.framework.dto.web;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/**
 * 
 * @ClassName:  OrderQueryDTO
 * @Description:订单查询DTO
 * @author: 陈健飞
 * @date:   2018年12月27日 下午12:57:13
 * 
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
@Data
public class OrderQueryDTO implements Serializable {
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    private Integer id;

    /**
     * 订单编号、任务编号
     */
    @ApiModelProperty(value = "订单编号、任务编号")
    private String orderNo;

    /**
     * 客户主键
     */
    @ApiModelProperty(value = "客户主键")
    private Integer customId;

    /**
     * 服务人员主键
     */
    @ApiModelProperty(value = "服务人员主键")
    private Integer servicePersonId;

    /**
     * 服务类别编号
     */
    @ApiModelProperty(value = "服务类别编号")
    private Integer serviceCategoryId;

    /**
     * 性别要求(0:不限 1:男 2:女)
     */
    @ApiModelProperty(value = "性别要求(0:不限 1:男 2:女)")
    private Byte sexRequire;

    /**
     * 预约地址
     */
    @ApiModelProperty(value = "预约地址")
    private String orderAddr;

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
     * 服务内容
     */
    @ApiModelProperty(value = "服务内容")
    private String contenta;

    /**
     * 预约备注
     */
    @ApiModelProperty(value = "预约备注")
    private String remark;

    /**
     * 服务开始时间
     */
    @ApiModelProperty(value = "服务开始时间")
    @JsonFormat(timezone = "GMT+8", pattern = "HH:mm:ss")
    private Date serviceBeginTime;

    /**
     * 服务结束时间
     */
    @ApiModelProperty(value = "服务结束时间")
    @JsonFormat(timezone = "GMT+8", pattern = "HH:mm:ss")
    private Date serviceEndTime;

    /**
     * 服务时长
     */
    @ApiModelProperty(value = "服务时长")
    private String serviceDuration;

    /**
     * 服务描述
     */
    @ApiModelProperty(value = "服务描述")
    private String serviceDesc;

    /**
     * 订单状态(1：待分配 2:待完成 3.已完成 4.取消)
     */
    @ApiModelProperty(value = "订单状态(1：待分配 2:待完成 3.已完成 4.取消)")
    private Byte orderStatus;

    /**
     * 服务取消原因
     */
    @ApiModelProperty(value = "服务取消原因")
    private String cancelReson;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    private Integer createBy;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /**
     * 更新人
     */
    @ApiModelProperty(value = "更新人")
    private Integer updateBy;

    /**
     * t_order
     */
    private static final long serialVersionUID = 1L;
}