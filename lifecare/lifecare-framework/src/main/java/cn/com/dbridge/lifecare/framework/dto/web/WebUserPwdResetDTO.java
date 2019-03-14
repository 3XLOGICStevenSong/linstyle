package cn.com.dbridge.lifecare.framework.dto.web;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/**
 * 
 * @ClassName:  WebUserPwdResetDTO
 * @Description:密码重置DTO
 * @author: 王林江
 * @date:   2019年01月11日 10:13:18
 * 
 * @Copyright: 2019 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
@Data
public class WebUserPwdResetDTO implements Serializable {

    /**
     * 登录名
     */
    @ApiModelProperty(value = "登录名")
    @NotBlank(message = "登录名设定不正：登录名不能为空")
    @Length(min = 0, max = 16, message = "登录名设定不正：登录名长度不能大于16位")
    @Pattern(regexp = "^([A-Za-z0-9]{1,})?$", message = "登录名设定不正：登录名必须为大小字母或数字")
    private String userNumber;

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