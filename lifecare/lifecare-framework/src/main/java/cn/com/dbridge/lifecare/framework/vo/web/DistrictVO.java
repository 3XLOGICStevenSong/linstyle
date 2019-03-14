package cn.com.dbridge.lifecare.framework.vo.web;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModelProperty;
/**
 * 
 * @ClassName:  DistrictVO
 * @Description:区VO
 * @author: 陈健飞
 * @date:   2018年12月27日 下午12:53:49
 * 
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
@JsonInclude(value=Include.NON_NULL)
public class DistrictVO implements Serializable {
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

    /**
     * 区编号
     * @return district_id 区编号
     */
    public Integer getDistrictId() {
        return districtId;
    }

    /**
     * 区编号
     * @param districtId 区编号
     */
    public void setDistrictId(Integer districtId) {
        this.districtId = districtId;
    }

    /**
     * 区名称
     * @return district_name 区名称
     */
    public String getDistrictName() {
        return districtName;
    }

    /**
     * 区名称
     * @param districtName 区名称
     */
    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    /**
     * 市编号
     * @return city_id 市编号
     */
    public Integer getCityId() {
        return cityId;
    }

    /**
     * 市编号
     * @param cityId 市编号
     */
    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", districtId=").append(districtId);
        sb.append(", districtName=").append(districtName);
        sb.append(", cityId=").append(cityId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}