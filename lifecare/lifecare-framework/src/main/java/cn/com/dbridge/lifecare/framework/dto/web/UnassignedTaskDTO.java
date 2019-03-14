package cn.com.dbridge.lifecare.framework.dto.web;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName:UnassignedTaskDTO
 * @Description:未分配任务查询输入
 * @author:郭健
 * @date:2019年1月7日 上午10:10:20
 * @Copyright: 2019 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目的
 */
@Data
public class UnassignedTaskDTO {
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
     *  客户编号
     */
	@ApiModelProperty(value = "客户编号")
	@Length(min = 0, max = 8, message = "客户编号长度不能大于8位")
    private String userNumber;
    /**
     * 最小预约日期
     */
    @ApiModelProperty(value = "最小预约日期")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date orderDateFrom;
    /**
     * 最小预约日期
     */
    @ApiModelProperty(value = "最小预约日期")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date orderDateTo;
    /**
     * 客户姓名
     */
    @ApiModelProperty(value = "客户姓名")
    @Length(min = 0, max = 8, message = "客户姓名长度不能大于8字")
    private String realName;
    /**
     * 最小预约开始时间
     */
    @ApiModelProperty(value = "最小预约开始时间")
    @JsonFormat(timezone = "GMT+8", pattern = "HH:mm")
    private Date orderBeginTimeFrom;
    /**
     * 最大预约开始时间
     */
    @ApiModelProperty(value = "最大预约开始时间")
    @JsonFormat(timezone = "GMT+8", pattern = "HH:mm")
    private Date orderBeginTimeTo;
    /**
     * 客户生日
     */
    @ApiModelProperty(value = "客户生日")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date birthday;
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
     * 客户手机号
     */
    @ApiModelProperty(value = "客户手机号")
    @Length(min = 0, max = 11, message = "客户手机号长度必须为11位")
    @Pattern(regexp = "^(([0-9]{1,})?)|(([ ]{1,})?)$", message = "客户手机号必须为数字")
    private String mobile;
    /**
     * 客户身份证号
     */
    @ApiModelProperty(value = "客户身份证号")
    @Length(min = 0, max = 18, message = "客户身份证号长度必须为18位")
    private String idCard;

    private Integer offset;
    private Integer limit;
}
