package cn.com.dbridge.lifecare.framework.dto.web;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/**
 * 
 * @ClassName:  ProvinceDTO
 * @Description:省DTO
 * @author: 陈健飞
 * @date:   2018年12月27日 下午12:57:22
 * 
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
@Data
public class ProvinceQueryDTO implements Serializable {
    /**
     * 省编号
     */
    @ApiModelProperty(value = "省编号")
    private Integer provinceId;

    /**
     * 省名称
     */
    @ApiModelProperty(value = "省名称")
    private String provinceName;

    /**
     * t_province
     */
    private static final long serialVersionUID = 1L;
}