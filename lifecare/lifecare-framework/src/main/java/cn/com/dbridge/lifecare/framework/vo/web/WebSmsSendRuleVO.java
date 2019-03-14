package cn.com.dbridge.lifecare.framework.vo.web;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/**
 * 
 * @ClassName:  WebSmsSendRuleVO
 * @Description:短息推送规则VO
 * @author: 王林江
 * @date:   2019年01月11日 下午09:55:13
 * 
 * @Copyright: 2019 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
@JsonInclude(value=Include.NON_NULL)
@Data
public class WebSmsSendRuleVO implements Serializable {
    /**
     * 推送标识1(0:都不发、1：客户发 2：服务人员发 3：都发)
     */
    @ApiModelProperty(value = "推送标识1(0:都不发、1：客户发 2：服务人员发 3：都发)")
    private Byte sendFlag1;

    /**
     * 分钟1
     */
    @ApiModelProperty(value = "分钟1")
    private Integer minutes1;

    /**
     * 推送标识2(0:都不发、1：客户发 2：服务人员发 3：都发)
     */
    @ApiModelProperty(value = "推送标识2(0:都不发、1：客户发 2：服务人员发 3：都发)")
    private Byte sendFlag2;

    /**
     * 分钟1
     */
    @ApiModelProperty(value = "分钟2")
    private Integer minutes2;


    /**
     * t_sms_send_rule
     */
    private static final long serialVersionUID = 1L;
}