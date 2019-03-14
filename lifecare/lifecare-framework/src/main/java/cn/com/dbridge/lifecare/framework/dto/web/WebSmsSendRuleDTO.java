package cn.com.dbridge.lifecare.framework.dto.web;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/**
 * 
 * @ClassName:  WebSmsSendRuleDTO
 * @Description:短息推送规则DTO
 * @author: 王林江
 * @date:   2019年01月11日 09:57:42
 * 
 * @Copyright: 2019 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
@Data
public class WebSmsSendRuleDTO implements Serializable {

    /**
     * 推送标识1(0:都不发、1：客户发 2：服务人员发 3：都发)
     */
    @ApiModelProperty(value = "推送标识1(0:都不发、1：客户发 2：服务人员发 3：都发)")
    @Range(min = 0, max = 3, message = "推送标识1设定不正：推送标识1只能设定为0:都不发、1：客户发 2：服务人员发 3：都发")
    private Byte sendFlag1;

    /**
     * 分钟1
     */
    @ApiModelProperty(value = "分钟1")
    @Range(min = 0, max = 599, message = "提醒时间1设定不正：提醒时间1要大于0小时并且小于10小时")
    private Integer minutes1;

    /**
     * 推送标识2(0:都不发、1：客户发 2：服务人员发 3：都发)
     */
    @ApiModelProperty(value = "推送标识2(0:都不发、1：客户发 2：服务人员发 3：都发)")
    @Range(min = 0, max = 3, message = "推送标识2设定不正：推送标识2只能设定为0:都不发、1：客户发 2：服务人员发 3：都发")
    private Byte sendFlag2;

    /**
     * 分钟1
     */
    @ApiModelProperty(value = "分钟2")
    @Range(min = 0, max = 5999, message = "提醒时间2设定不正：提醒时间2要大于0小时并且小于100小时")
    private Integer minutes2;

    /**
     * 登录用户ID
     */
    @ApiModelProperty(value = "登录用户ID")
    @NotNull(message = "登录用户ID设定不正：登录用户ID必须入力")
    private Integer loginUserId;

    /**
     * t_sms_send_rule
     */
    private static final long serialVersionUID = 1L;
}