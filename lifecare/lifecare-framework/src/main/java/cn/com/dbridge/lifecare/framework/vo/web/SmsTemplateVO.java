package cn.com.dbridge.lifecare.framework.vo.web;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModelProperty;
/**
 * 
 * @ClassName:  SmsTemplateVO
 * @Description:短信模板VO
 * @author: 陈健飞
 * @date:   2018年12月27日 下午12:55:27
 * 
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
@JsonInclude(value=Include.NON_NULL)
public class SmsTemplateVO implements Serializable {
    /**
     * 短信模板编号
     */
    @ApiModelProperty(value = "短信模板编号")
    private Integer smsTemplateId;

    /**
     * 短信模板描述
     */
    @ApiModelProperty(value = "短信模板描述")
    private String smsTemplateDesc;

    /**
     * 短信模板内容
     */
    @ApiModelProperty(value = "短信模板内容")
    private String smsTemplateContent;

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
     * t_sms_template
     */
    private static final long serialVersionUID = 1L;

    /**
     * 短信模板编号
     * @return sms_template_id 短信模板编号
     */
    public Integer getSmsTemplateId() {
        return smsTemplateId;
    }

    /**
     * 短信模板编号
     * @param smsTemplateId 短信模板编号
     */
    public void setSmsTemplateId(Integer smsTemplateId) {
        this.smsTemplateId = smsTemplateId;
    }

    /**
     * 短信模板描述
     * @return sms_template_desc 短信模板描述
     */
    public String getSmsTemplateDesc() {
        return smsTemplateDesc;
    }

    /**
     * 短信模板描述
     * @param smsTemplateDesc 短信模板描述
     */
    public void setSmsTemplateDesc(String smsTemplateDesc) {
        this.smsTemplateDesc = smsTemplateDesc;
    }

    /**
     * 短信模板内容
     * @return sms_template_content 短信模板内容
     */
    public String getSmsTemplateContent() {
        return smsTemplateContent;
    }

    /**
     * 短信模板内容
     * @param smsTemplateContent 短信模板内容
     */
    public void setSmsTemplateContent(String smsTemplateContent) {
        this.smsTemplateContent = smsTemplateContent;
    }

    /**
     * 创建时间
     * @return create_time 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 创建人
     * @return create_by 创建人
     */
    public Integer getCreateBy() {
        return createBy;
    }

    /**
     * 创建人
     * @param createBy 创建人
     */
    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    /**
     * 更新时间
     * @return update_time 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新时间
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 更新人
     * @return update_by 更新人
     */
    public Integer getUpdateBy() {
        return updateBy;
    }

    /**
     * 更新人
     * @param updateBy 更新人
     */
    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", smsTemplateId=").append(smsTemplateId);
        sb.append(", smsTemplateDesc=").append(smsTemplateDesc);
        sb.append(", smsTemplateContent=").append(smsTemplateContent);
        sb.append(", createTime=").append(createTime);
        sb.append(", createBy=").append(createBy);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateBy=").append(updateBy);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}