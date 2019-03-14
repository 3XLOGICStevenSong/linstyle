package cn.com.dbridge.lifecare.framework.dto.web;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/**
 * 
 * @ClassName:LoginDTO
 * @Description:登陆DTO
 * @author:陈健飞
 * @date:2018年12月27日 下午12:56:32
 * 
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目的
 */
@Data
public class LoginDTO implements Serializable {

    /**
     * 编号(客户编号、服务人员编号、后台用户编号)
     */
    @ApiModelProperty(value = "编号(客户编号、服务人员编号、后台用户编号)")
    private String userNumber;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码")
    private String password;
    
    /**
     * openId
     */
    @ApiModelProperty(value = "openId")
    private String openId;
    /**
     * 微信昵称
     */
    @ApiModelProperty(value = "nickName")
    private String nickName;

    /**
     * 来源 用于标识调用接口来源
     */
    @ApiModelProperty(value = "sourceType")
    private String sourceType;
    /**
     * t_user
     */
    private static final long serialVersionUID = 1L;
}