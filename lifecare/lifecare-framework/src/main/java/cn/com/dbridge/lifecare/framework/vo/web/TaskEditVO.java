package cn.com.dbridge.lifecare.framework.vo.web;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName:TaskEditVO
 * @Description:TODO
 * @author:郭健
 * @date:2019年1月14日 下午4:39:23
 * @Copyright: 2019 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目的
 */
@Data
@JsonInclude(value=Include.NON_NULL)
public class TaskEditVO {
    /**
     * 订单主键
     */
	@ApiModelProperty(value = "订单主键")
    private Integer id;
    /**
     * 任务编号
     */
	@ApiModelProperty(value = "任务编号")
    private String orderNo;
    /**
     * 客户主键
     */
	@ApiModelProperty(value = "客户主键")
    private Integer customId;
    /**
     * 客户编号
     */
	@ApiModelProperty(value = "客户编号")
    private String customUserNumber;
    /**
     * 客户姓名
     */
	@ApiModelProperty(value = "客户姓名")
    private String customRealName;
    /**
     * 客户手机号
     */
	@ApiModelProperty(value = "客户手机号")
    private String customMobile;
    /**
     * 客户星级
     */
	@ApiModelProperty(value = "客户星级")
    private String customUserLevel;
	/**
	 * 客户年龄
	 */
	@ApiModelProperty(value = "客户年龄")
	private Integer customAge;
	/**
	 * 客户性别
	 */
	@ApiModelProperty(value = "客户性别")
	private String customSex;
    /**
     * 预约地址
     */
	@ApiModelProperty(value = "预约地址")
    private String orderAddr;
    /**
     * 性别要求
     */
	@ApiModelProperty(value = "性别要求")
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
     * 服务人员主键
     */
    @ApiModelProperty(value = "服务人员主键")
    private Integer servicePersonId;
    /**
     * 服务人员工号
     */
    @ApiModelProperty(value = "服务人员工号")
    private String servicePersonUserNumber;
    /**
     * 服务人员姓名
     */
    @ApiModelProperty(value = "服务人员姓名")
    private String servicePersonRealName;
    /**
     * 服务人员手机号
     */
    @ApiModelProperty(value = "服务人员手机号")
    private String servicePersonMobile;
    /**
     * 服务人员星级
     */
    @ApiModelProperty(value = "服务人员星级")
    private String servicePersonUserLevel;   
    /**
     * 服务人员年龄
     */
    @ApiModelProperty(value = "服务人员年龄")
    private Integer servicePersonAge; 
    /**
     * 服务人员性别
     */
    @ApiModelProperty(value = "服务人员性别")
    private String servicePersonSex;  
    /**
     * 服务开始时间
     */
    @ApiModelProperty(value = "服务开始时间")
    @JsonFormat(timezone = "GMT+8", pattern = "HH:mm")
    private Date serviceBeginTime;
    /**
     * 服务结束时间
     */
    @ApiModelProperty(value = "服务结束时间")
    @JsonFormat(timezone = "GMT+8", pattern = "HH:mm")
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
     * 取消服务
     */
    @ApiModelProperty(value = "取消服务")
    private Byte orderStatus;
    /**
     * 服务取消原因
     */
    @ApiModelProperty(value = "服务取消原因")
    private String cancelReson;
}
