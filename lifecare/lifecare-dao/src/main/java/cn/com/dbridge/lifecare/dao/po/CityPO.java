package cn.com.dbridge.lifecare.dao.po;

import java.io.Serializable;

import lombok.Data;
/**
 * 
 * @ClassName:  CityPO
 * @Description: 城市信息PO
 * @author: 陈健飞
 * @date:   2019年1月15日 下午7:09:22
 * 
 * @Copyright: 2019 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
@Data
public class CityPO implements Serializable {
    /**
     * 市编号
     */
    private Integer cityId;

    /**
     * 市名称
     */
    private String cityName;

    /**
     * 邮编
     */
    private String zipCode;

    /**
     * 电话区号
     */
    private String areaCode;

    /**
     * 省编号
     */
    private Integer provinceId;

    /**
     * t_city
     */
    private static final long serialVersionUID = 1L;

}