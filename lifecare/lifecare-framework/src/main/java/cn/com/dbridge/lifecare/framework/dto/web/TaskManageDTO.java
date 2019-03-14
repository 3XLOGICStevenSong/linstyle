package cn.com.dbridge.lifecare.framework.dto.web;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName:TaskManageDTO
 * @Description:TODO
 * @author:郭健
 * @date:2019年1月14日 下午4:08:00
 * @Copyright: 2019 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目的
 */
@Data
public class TaskManageDTO implements Serializable{
	private static final long serialVersionUID = -1003497268776481576L;
	/**
     * 任务编号
     */
	@ApiModelProperty(value = "任务编号")
	@Length(min = 0, max = 12, message = "任务编号长度不能大于12位")
    private String orderNo;
    /**
     * 服务类别
     */
	@ApiModelProperty(value = "服务类别")
    private List<String> serviceCategoryCodes;
    /**
     * 客户编号
     */
	@ApiModelProperty(value = "客户编号")
	@Length(min = 0, max = 8, message = "客户编号长度不能大于8位")
    private String customUserNumber;
    /**
     * 服务人员工号
     */
	@ApiModelProperty(value = "服务人员工号")
	@Length(min = 0, max = 16, message = "服务人员工号长度不能大于16位")
    private String servicePersonUserNumber;
    /**
     * 客户姓名
     */
	@ApiModelProperty(value = "客户姓名")
	@Length(min = 0, max = 8, message = "客户姓名长度不能大于8字")
    private String customRealName;
    /**
     * 服务人员姓名
     */
	@ApiModelProperty(value = "服务人员姓名")
	@Length(min = 0, max = 8, message = "服务人员姓名长度不能大于8字")
    private String servicePersonRealName;
    /**
     * 客户生日
     */
	@ApiModelProperty(value = "客户生日")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date customBirthday;
    /**
     * 服务人员生日
     */
	@ApiModelProperty(value = "服务人员生日")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date servicePersonBirthday;
    /**
     * 客户身份证号
     */
	@ApiModelProperty(value = "客户身份证号")
	@Length(min = 0, max = 18, message = "客户身份证号长度必须为18位")
    private String customIdCard;
    /**
     * 服务人员身份证号
     */
	@ApiModelProperty(value = "服务人员身份证号")
	@Length(min = 0, max = 18, message = "服务人员身份证号长度必须为18位")
    private String servicePersonIdCard;
    /**
     * 客户手机号
     */
	@ApiModelProperty(value = "客户手机号")
	@Length(min = 0, max = 11, message = "客户手机号长度必须为11位")
    @Pattern(regexp = "^(([0-9]{1,})?)|(([ ]{1,})?)$", message = "客户手机号必须为数字")
    private String customMobile;
    /**
     * 服务人员手机号
     */
	@ApiModelProperty(value = "服务人员手机号")
	@Length(min = 0, max = 11, message = "服务人员手机号长度必须为11位")
    @Pattern(regexp = "^(([0-9]{1,})?)|(([ ]{1,})?)$", message = "服务人员手机号必须为数字")
    private String servicePersonMobile;
    /**
     * 任务状态
     */
	@ApiModelProperty(value = "任务状态")
    private List<Integer> orderStatus;
    /**
     * 预约开始日期
     */
	@ApiModelProperty(value = "预约开始日期")
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date orderDateFrom;
    /**
     * 预约结束日期
     */
	@ApiModelProperty(value = "预约结束日期")
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date orderDateTo;
    /**
     * 最小预约时长
     */
	@ApiModelProperty(value = "最小预约时长")
	@Pattern(regexp = "^[1-9]\\d*$", message = "最小预约时长必须为数字")
    private Integer orderDurationFrom;
    /**
     * 最大预约时长
     */
	@ApiModelProperty(value = "最大预约时长")
	@Pattern(regexp = "^[1-9]\\d*$", message = "最大预约时长必须为数字")
    private Integer orderDurationTo;
    /**
     * 最小预约开始时间
     */
	@ApiModelProperty(value = "最小预约开始时间 格式HH:mm")
    @JsonFormat(timezone = "GMT+8", pattern = "HH:mm")
    private Date orderBeginTimeFrom;
    /**
     * 最大预约开始时间
     */
	@ApiModelProperty(value = "最大预约开始时间 格式HH:mm")
    @JsonFormat(timezone = "GMT+8", pattern = "HH:mm")
    private Date orderBeginTimeTo;
    /**
     * 异常状态任务(0:否 1:是 2:全选或全不选)
     */
	@ApiModelProperty(value = "异常状态任务(0:否 1:是 2:全选或全不选)")
    private List<Integer> abnormalOrder;

    private Integer offset;
    private Integer limit;
}
