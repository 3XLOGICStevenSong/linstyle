package cn.com.dbridge.lifecare.framework.dto.web;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/**
 * 
 * @ClassName:  NursePlanDTO
 * @Description:护理计划DTO
 * @author: 陈健飞
 * @date:   2018年12月27日 下午12:56:42
 * 
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
@Data
public class NursePlanDTO implements Serializable {
    /**
     * 护理方案编号
     */
    @ApiModelProperty(value = "护理方案编号")
    private Integer nursePlanId;

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
     * 护理方案内容
     */
    @ApiModelProperty(value = "护理方案内容")
    private String nursePlanContent;

    /**
     * 护理方案标题
     */
    @ApiModelProperty(value = "护理方案标题")
    private String nursePlanTitle;
   
    /**
     * 护理方案开始日期
     */
    @ApiModelProperty(value = "护理方案开始日期")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date nursePlanBeginDate;

    /**
     * 方案状态(0:草稿 1:发布)
     */
    @ApiModelProperty(value = "方案状态(0:草稿 1:发布)")
    private Byte nursePlanStatus;
   
    /**
     * 制定日期
     */
    @ApiModelProperty(value = "制定日期")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date draftDate;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm")
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
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm")
    private Date updateTime;

    /**
     * 更新人
     */
    @ApiModelProperty(value = "更新人")
    private Integer updateBy;

    /**
     * t_nurse_plan
     */
    private static final long serialVersionUID = 1L;
}