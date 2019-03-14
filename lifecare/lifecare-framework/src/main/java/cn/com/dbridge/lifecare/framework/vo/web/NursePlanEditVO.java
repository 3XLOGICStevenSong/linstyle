package cn.com.dbridge.lifecare.framework.vo.web;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName:NursePlanEditVO
 * @Description:TODO
 * @author:郭健
 * @date:2019年1月9日 下午4:52:44
 * @Copyright: 2019 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目的
 */
@Data
@JsonInclude(value=Include.NON_NULL)
public class NursePlanEditVO {
    /**
     * 服务方案编号
     */
    @ApiModelProperty(value = "服务方案编号")
    private Integer nursePlanId;
    /**
     * 后台用户主键
     */
    @ApiModelProperty(value = "后台用户主键")
    private Integer backendPersonId;
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
     * 客户性别
     */
    @ApiModelProperty(value = "客户性别")
    private Byte customSex;
    /**
     * 客户年龄
     */
    @ApiModelProperty(value = "客户年龄")
    private Integer customAge;
    /**
     * 客户星级
     */
    @ApiModelProperty(value = "客户星级")
    private String customUserLevel;
    /**
     * 服务方案类型(1:生活助理 2:老龄照护 3:临床护理)
     */
    @ApiModelProperty(value = "服务方案类型(1:生活助理 2:老龄照护 3:临床护理)")
    private Byte nursePlanType;
    /**
     * 服务方案标题
     */
    @ApiModelProperty(value = "服务方案标题")
    private String nursePlanTitle;
    /**
     * 服务方案开始日期
     */
    @ApiModelProperty(value = "服务方案开始日期")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date nursePlanBeginDate;
    /**
     * 服务方案内容
     */
    @ApiModelProperty(value = "服务方案内容")
    private String nursePlanContent;
    /**
     * 制定人员工号
     */
    @ApiModelProperty(value = "制定人员工号")
    private String backendPersonUserNumber;
    /**
     * 制定人员姓名
     */
    @ApiModelProperty(value = "制定人员姓名")
    private String backendPersonRealName;
    /**
     * 制定日期
     */
    @ApiModelProperty(value = "制定日期")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date draftDate;
    /**
     * 使用状态 0:启动 1：停用
     */
    @ApiModelProperty(value = "使用状态 0:启动 1：停用")
    private Byte useType;
    /**
     * 录入员工号
     */
    @ApiModelProperty(value = "录入员工号")
    private String enteringPersonUserNumber;
    /**
     * 录入员姓名
     */
    @ApiModelProperty(value = "录入员姓名")
    private String enteringPersonRealName;
}