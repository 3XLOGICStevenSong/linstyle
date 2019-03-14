package cn.com.dbridge.lifecare.framework.dto.mobile;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 
 * @ClassName:MobileCityDTO
 * @Description:市DTO
 * @author:郭健
 * @date:2018年12月28日 下午3:38:34
 * 
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved.
 *  注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目的
 */
@Data
public class MobileCityDTO implements Serializable {
    /**
     * 市编号
     */
    @ApiModelProperty(value = "市编号")
    private Integer cityId;

    /**
     * 市名称
     */
    @ApiModelProperty(value = "市名称")
    private String cityName;

    /**
     * 邮编
     */
    @ApiModelProperty(value = "邮编")
    private String zipCode;

    /**
     * 电话区号
     */
    @ApiModelProperty(value = "电话区号")
    private String areaCode;

    /**
     * 省编号
     */
    @ApiModelProperty(value = "省编号")
    private Integer provinceId;

    /**
     * t_city
     */
    private static final long serialVersionUID = 1L;
}
