package cn.com.dbridge.lifecare.framework.vo.mobile;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/**
 * 
 * @ClassName:ServiceTypeVO
 * @Description:服务类别VO
 * @author:陈健飞
 * @date:2018年12月27日 下午12:55:02
 * 
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目的
 */
@Data
@JsonInclude(value=Include.NON_NULL)
public class MobileServiceTypeVO implements Serializable {
    /**
     * 服务类别编号
     */
    @ApiModelProperty(value = "服务类别编号")
    private Integer serviceCategoryId;

    /**
     * 服务类别名称
     */
    @ApiModelProperty(value = "服务类别名称")
    private String serviceCategoryName;

    /**
     * t_service_type
     */
    private static final long serialVersionUID = 1L;
}