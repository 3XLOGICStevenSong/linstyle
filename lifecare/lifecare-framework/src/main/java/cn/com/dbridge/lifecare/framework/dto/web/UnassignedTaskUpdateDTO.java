package cn.com.dbridge.lifecare.framework.dto.web;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName:WebUnassignedUpdateDTO
 * @Description:TODO
 * @author:郭健
 * @date:2019年1月7日 下午4:32:59
 * @Copyright: 2019 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目的
 */
@Data
public class UnassignedTaskUpdateDTO {
    /**
     * 订单主键
     */
    @ApiModelProperty(value = "订单主键")
    private Integer id;
    /**
     * 客户主键
     */
    @ApiModelProperty(value = "客户主键")
    private Integer customId;
    /**
     * 后台用户主键
     */
    @ApiModelProperty(value = "后台用户主键")
    private Integer backendPersonId;
    /**
     * 性别要求
     */
    @ApiModelProperty(value = "性别要求(0:不限 1:男 2:女)")
    private Byte sexRequire;
    /**
     *预约日期
     */
    @ApiModelProperty(value = "预约日期")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @NotNull(message = "预约日期不能为空")
    private Date orderDate;
    /**
     * 预约开始时间
     */
    @ApiModelProperty(value = "预约开始时间")
    @JsonFormat(timezone = "GMT+8", pattern = "HH:mm")
    @NotNull(message = "预约开始时间不能为空")
    private Date orderBeginTime;
    /**
     * 预约结束时间
     */
    @ApiModelProperty(value = "预约结束时间")
    @JsonFormat(timezone = "GMT+8", pattern = "HH:mm")
    @NotNull(message = "预约结束时间不能为空")
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
    @NotBlank(message = "服务内容不能为空")
    @Pattern(regexp = "^(?=.*\\S).+$", message = "服务内容不能全为空格")
    @Length(min = 0, max = 4000, message = "服务内容长度不能大于4000字")
    private String content;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    @Length(min = 0, max = 500, message = "备注长度不能大于500字")
    private String remark;
    /**
     * 取消服务
     */
    @ApiModelProperty(value = "取消服务")
    private Byte orderStatus;
    /**
     * 服务取消原因
     */
    @ApiModelProperty(value = "服务取消原因")
    @NotBlank(message = "取消服务原因不能为空")
    @Pattern(regexp = "^(?=.*\\S).+$", message = "取消服务原因不能全为空格")
    @Length(min = 0, max = 500, message = "取消服务原因长度不能大于500字")
    private String cancelReson;
}
