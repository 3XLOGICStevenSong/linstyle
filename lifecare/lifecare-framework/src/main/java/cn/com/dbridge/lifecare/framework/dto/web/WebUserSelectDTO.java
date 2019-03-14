package cn.com.dbridge.lifecare.framework.dto.web;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/**
 * 
 * @ClassName:  WebUserSelectDTO
 * @Description:服务池选择服务人员DTO
 * @author: 王林江
 * @date:   2019年01月05日 09:13:18
 * 
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
@Data
public class WebUserSelectDTO implements Serializable {

    /**
     * 用户主键
     */
    @ApiModelProperty(value = "用户主键")
    @NotNull(message = "用户ID设定不正：用户ID必须入力")
    private Integer userId;

    /**
     * 订单主键
     */
    @ApiModelProperty(value = "订单主键")
    @NotNull(message = "订单主键设定不正：订单主键必须入力")
    private Integer id;

    /**
     * 登录用户ID
     */
    @ApiModelProperty(value = "登录用户ID")
    @NotNull(message = "登录用户ID设定不正：登录用户ID必须入力")
    private Integer loginUserId;

    /**
     * t_user
     */
    private static final long serialVersionUID = 1L;
}