package cn.com.dbridge.lifecare.framework.dto.web;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/**
 * 
 * @ClassName:  DistrictDTO
 * @Description:区DTO
 * @author: 陈健飞
 * @date:   2018年12月27日 下午12:56:06
 * 
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
@Data
public class DistrictDTO implements Serializable {
    /**
     * 区编号
     */
    @ApiModelProperty(value = "区编号")
    private Integer districtId;

    /**
     * 区名称
     */
    @ApiModelProperty(value = "区名称")
    private String districtName;

    /**
     * 市编号
     */
    @ApiModelProperty(value = "市编号") 
    private Integer cityId;

    /**
     * t_district
     */
    private static final long serialVersionUID = 1L;
}