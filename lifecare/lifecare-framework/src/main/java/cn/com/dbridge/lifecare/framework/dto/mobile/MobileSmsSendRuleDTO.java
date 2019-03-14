package cn.com.dbridge.lifecare.framework.dto.mobile;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/**
 * 
 * @ClassName:SmsSendRuleDTO
 * @Description:短息推送规则DTO
 * @author:陈健飞
 * @date:2018年12月27日 下午12:57:42
 * 
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目的
 */
@Data
public class MobileSmsSendRuleDTO implements Serializable {
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    private Integer id;

    /**
     * 推送客户
     */
    @ApiModelProperty(value = "推送客户")
    private String sendUser;

    /**
     * 推送服务人员
     */
    @ApiModelProperty(value = "推送服务人员")
    private String sendServiceUse;

    /**
     * 分钟
     */
    @ApiModelProperty(value = "分钟")
    private String minutes;

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
     * t_sms_send_rule
     */
    private static final long serialVersionUID = 1L;
}