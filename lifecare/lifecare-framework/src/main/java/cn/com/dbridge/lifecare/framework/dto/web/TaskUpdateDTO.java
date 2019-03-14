package cn.com.dbridge.lifecare.framework.dto.web;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName:TaskUpdateDTO
 * @Description:TODO
 * @author:郭健
 * @date:2019年1月14日 下午7:20:06
 * @Copyright: 2019 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目的
 */
@Data
public class TaskUpdateDTO {
    /**
     * 订单主键
     */
    @ApiModelProperty(value = "订单主键")
    private Integer id;
    /**
     * 预约地址
     */
    @ApiModelProperty(value = "预约地址")
    private String orderAddr;
    /**
     * 后台用户主键
     */
    @ApiModelProperty(value = "后台用户主键")
    private Integer backendPersonId;
    /**
     * 性别要求(0:不限 1:男 2:女)
     */
    @ApiModelProperty(value = "性别要求(0:不限 1:男 2:女)")
    private Byte sexRequire;
    /**
     * 预约日期
     */
    @ApiModelProperty(value = "预约日期")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date orderDate;
    /**
     * 预约开始时间
     */
    @ApiModelProperty(value = "预约开始时间 格式HH:mm")
    @JsonFormat(timezone = "GMT+8", pattern = "HH:mm")
    private Date orderBeginTime;
    /**
     * 预约结束时间
     */
    @ApiModelProperty(value = "预约结束时间 格式HH:mm")
    @JsonFormat(timezone = "GMT+8", pattern = "HH:mm")
    private Date orderEndTime;
    /**
     * 服务类别
     */
    @ApiModelProperty(value = "服务类别 例如：A B C)")
    private String serviceCategoryCode;
    /**
     * 服务内容
     */
    @ApiModelProperty(value = "服务内容")
    private String content;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
    /**
     * 服务人员主键
     */
    @ApiModelProperty(value = "服务人员主键")
    private Integer servicePersonId;
    /**
     * 服务开始时间
     */
    @ApiModelProperty(value = "服务开始时间 格式HH:mm")
    @JsonFormat(timezone = "GMT+8", pattern = "HH:mm")
    private Date serviceBeginTime;
    /**
     * 服务结束时间
     */
    @ApiModelProperty(value = "服务结束时间 格式HH:mm")
    @JsonFormat(timezone = "GMT+8", pattern = "HH:mm")
    private Date serviceEndTime;
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
}
