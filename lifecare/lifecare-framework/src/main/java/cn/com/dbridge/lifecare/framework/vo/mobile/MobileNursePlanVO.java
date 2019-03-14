package cn.com.dbridge.lifecare.framework.vo.mobile;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/**
 * 
 * @ClassName:NursePlanVO
 * @Description:护理计划VO
 * @author:陈健飞
 * @date:2018年12月27日 下午12:54:15
 * 
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目的
 */
@Data
@JsonInclude(value=Include.NON_NULL)
public class MobileNursePlanVO implements Serializable {
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
     * 服务人员主键
     */
    @ApiModelProperty(value = "服务人员主键")
    private Integer servicePersonId;

    /**
     * 护理方案标题
     */
    @ApiModelProperty(value = "护理方案标题")
    private String nursePlanTitle;

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
     * 护理方案内容
     */
    @ApiModelProperty(value = "护理方案内容")
    private byte[] nursePlanContent;

    /**
     * t_nurse_plan
     */
    private static final long serialVersionUID = 1L;
}