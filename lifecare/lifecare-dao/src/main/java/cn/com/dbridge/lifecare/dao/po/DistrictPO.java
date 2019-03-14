package cn.com.dbridge.lifecare.dao.po;

import java.io.Serializable;

import lombok.Data;
/**
 * 
 * @ClassName:  DistrictPO
 * @Description:区域PO
 * @author: 陈健飞
 * @date:   2019年1月15日 下午7:10:11
 * 
 * @Copyright: 2019 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
@Data
public class DistrictPO implements Serializable {
    /**
     * 区编号
     */
    private Integer districtId;

    /**
     * 区名称
     */
    private String districtName;

    /**
     * 市编号
     */
    private Integer cityId;

    /**
     * t_district
     */
    private static final long serialVersionUID = 1L;

}