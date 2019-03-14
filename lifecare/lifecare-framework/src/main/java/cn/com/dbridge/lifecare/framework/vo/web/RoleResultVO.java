
package cn.com.dbridge.lifecare.framework.vo.web;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @ClassName:  RoleResultVO
 * @Description:TODO(这里用一句话描述这个类的作用)
 * @author: linh
 * @date:   2019年1月4日 上午11:03:32
 * 
 * @Copyright: 2019 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(value = Include.NON_NULL)
public class RoleResultVO {
    @ApiModelProperty(value = "状态码")
    private Integer statusCode;
    @ApiModelProperty(value = "当前页数")
    private int offset;
    @ApiModelProperty(value = "每页显示记录数")
    private int limit;
    @ApiModelProperty(value = "接口名称")
    private String name;
    private List<RoleVO> rows;
    @ApiModelProperty(value = "总数")
    private Long total;
  


}
