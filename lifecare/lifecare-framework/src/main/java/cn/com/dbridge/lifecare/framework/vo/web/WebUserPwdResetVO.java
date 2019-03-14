package cn.com.dbridge.lifecare.framework.vo.web;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/**
 * 
 * @ClassName:  WebUserPwdResetVO
 * @Description:重置密码VO
 * @author: 王林江
 * @date:   2019年01月14日 16:13:18
 * 
 * @Copyright: 2019 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
@JsonInclude(value=Include.NON_NULL)
@Data
public class WebUserPwdResetVO implements Serializable {

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码")
    private String password;

    /**
    * t_user
    */
    private static final long serialVersionUID = 1L;

}