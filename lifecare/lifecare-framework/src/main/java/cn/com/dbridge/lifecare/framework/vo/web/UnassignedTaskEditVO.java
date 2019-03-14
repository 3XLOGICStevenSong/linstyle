package cn.com.dbridge.lifecare.framework.vo.web;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName:UnassignedTaskEditVO
 * @Description:未分配任务编辑时回显数据
 * @author:郭健
 * @date:2019年1月7日 下午2:55:22
 * @Copyright: 2019 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目的
 */
@Data
@JsonInclude(value=Include.NON_NULL)
public class UnassignedTaskEditVO {
    /**
     *  订单主键
     */
	@ApiModelProperty(value = "订单主键")
    private Integer id;
    /**
     * 任务编号
     */
	@ApiModelProperty(value = "任务编号")
    private String orderNo;
    /**
     *  客户主键
     */
	@ApiModelProperty(value = "客户主键")
    private Integer customId;
    /**
     * 客户编号
     */
	@ApiModelProperty(value = "客户编号")
    private String userNumber;
    /**
     * 客户姓名
     */
	@ApiModelProperty(value = "客户姓名")
    private String realName;
    /**
     * 客户手机号
     */
	@ApiModelProperty(value = "客户手机号")
    private String mobile;
    /**
     * 客户星级
     */
	@ApiModelProperty(value = "客户星级")
    private String userLevel;
    /**
     * 客户年龄
     */
	@ApiModelProperty(value = "客户年龄")
    private Integer age;
    /**
     * 客户性别
     */
	@ApiModelProperty(value = "客户性别")
    private Byte sex;
    /**
     * 预约地址
     */
	@ApiModelProperty(value = "预约地址")
    private String orderAddr;
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
    @ApiModelProperty(value = "预约开始时间")
    @JsonFormat(timezone = "GMT+8", pattern = "HH:mm")
    private Date orderBeginTime;
    /**
     * 预约结束时间
     */
    @ApiModelProperty(value = "预约结束时间")
    @JsonFormat(timezone = "GMT+8", pattern = "HH:mm")
    private Date orderEndTime;
    /**
     * 服务类别编码
     */
    @ApiModelProperty(value = "服务类别编码")
    private String serviceCategoryCode;
    /**
     * 服务内容
     */
    @ApiModelProperty(value = "服务内容")
    private String content;
    /**
     * 预约备注
     */
    @ApiModelProperty(value = "预约备注")
    private String remark;
    /**
     * 取消服务(订单状态(1：待分配 2:待完成 3.已完成 4.取消))
     */
    @ApiModelProperty(value = "取消服务(订单状态(1：待分配 2:待完成 3.已完成 4.取消))")
    private Byte orderStatus;
    /**
     *  服务取消原因
     */
    @ApiModelProperty(value = "服务取消原因")
    private String cancelReson;
}
