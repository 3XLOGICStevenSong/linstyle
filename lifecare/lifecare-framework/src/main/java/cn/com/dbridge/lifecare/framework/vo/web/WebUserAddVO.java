package cn.com.dbridge.lifecare.framework.vo.web;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/**
 * 
 * @ClassName:  WebUserAddVO
 * @Description:添加用户管理信息返回VO
 * @author: 王林江
 * @date:   2019年01月09日 09:13:18
 * 
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
@JsonInclude(value=Include.NON_NULL)
@Data
public class WebUserAddVO implements Serializable {

    /**
     * 用户主键
     */
    @ApiModelProperty(value = "用户主键")
    private Integer userId;

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
     * 名称(客户名称、服务人员名称、后台用户名称)
     */
    @ApiModelProperty(value = "名称(客户名称、服务人员名称、后台用户名称)")
    private String realName;

    /**
     * t_user
     */
    private static final long serialVersionUID = 1L;

}