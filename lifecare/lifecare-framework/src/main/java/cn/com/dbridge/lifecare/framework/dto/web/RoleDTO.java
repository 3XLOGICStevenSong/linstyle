package cn.com.dbridge.lifecare.framework.dto.web;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 
 * @ClassName:  RolePO
 * @Description:TODO(这里用一句话描述这个类的作用)
 * @author: linh
 * @date:   2018年12月26日 下午1:05:26
 * 
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
@Data
public class RoleDTO {

    @ApiModelProperty(value = "角色ID")
    private Integer roleId;

    @ApiModelProperty(value = "角色名称")
    private String roleName;

    @ApiModelProperty(value = "描述")
    private String memo;
    @ApiModelProperty(value = "状态")
    private String status;
}
