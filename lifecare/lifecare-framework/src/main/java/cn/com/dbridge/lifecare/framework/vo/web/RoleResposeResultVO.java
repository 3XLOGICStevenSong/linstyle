package cn.com.dbridge.lifecare.framework.vo.web;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@AllArgsConstructor
@NoArgsConstructor
public class RoleResposeResultVO {


    private List<RoleResposibilityVO> rows;

    @ApiModelProperty(value = "状态码")
    private Integer statusCode;
    @ApiModelProperty(value = "接口名称")
    private String msg;
}
