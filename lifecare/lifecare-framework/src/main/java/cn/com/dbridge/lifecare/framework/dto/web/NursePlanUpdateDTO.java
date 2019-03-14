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
 * @ClassName:NursePlanUpdateDTO
 * @Description:TODO
 * @author:郭健
 * @date:2019年1月9日 下午6:47:59
 * @Copyright: 2019 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目的
 */
@Data
public class NursePlanUpdateDTO {
    /**
     * 服务方案编号
     */
    @ApiModelProperty(value = "服务方案编号")
    private Integer nursePlanId;
    /**
     * 服务方案类型
     */
    @ApiModelProperty(value = "服务方案类型")
    private Byte nursePlanType;
    /**
     * 服务方案标题
     */
    @ApiModelProperty(value = "服务方案标题")
    @NotBlank(message = "照护方案标题不能为空")
    @Pattern(regexp = "^(?=.*\\S).+$", message = "服务方案标题不能为全空格")
    @Length(min = 0, max = 50, message = "服务方案标题长度不能大于50字")
    private String nursePlanTitle;
    /**
     * 方案开始日期
     */
    @ApiModelProperty(value = "方案开始日期")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @NotNull(message = "方案开始日期不能为空")
    private Date nursePlanBeginDate;
    /**
     * 服务方案内容
     */
    @ApiModelProperty(value = "服务方案内容")
    @NotBlank(message = "服务方案内容不能为空")
    @Pattern(regexp = "^(?=.*\\S).+$", message = "服务方案内容不能全为空格")
    @Length(min = 0, max = 4000, message = "服务方案内容长度不能大于4000字")
    private String nursePlanContent;
    /**
     * 制定人员主键
     */
    @ApiModelProperty(value = "制定人员主键")
    @NotNull(message = "制定人员工号不能为空")
    private Integer backendPersonId;
    /**
     * 制定日期
     */
    @ApiModelProperty(value = "制定日期")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @NotNull(message = " 制定日期不能为空")
    private Date draftDate;
    /**
     * 使用状态 0:启动 1：停用
     */
    @ApiModelProperty(value = "使用状态 0:启动 1：停用")
    private Byte useType;
    /**
     * 录入员编号
     */
    @ApiModelProperty(value = "录入员编号")
    private Integer enteringPersonId;
}
