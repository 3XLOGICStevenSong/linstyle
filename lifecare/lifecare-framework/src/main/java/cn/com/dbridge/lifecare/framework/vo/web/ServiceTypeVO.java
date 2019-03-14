package cn.com.dbridge.lifecare.framework.vo.web;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModelProperty;
/**
 * 
 * @ClassName:  ServiceTypeVO
 * @Description:服务类别VO
 * @author: 陈健飞
 * @date:   2018年12月27日 下午12:55:02
 * 
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
@JsonInclude(value=Include.NON_NULL)
public class ServiceTypeVO implements Serializable {
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

    /**
     * 服务类别编号
     * @return service_category_id 服务类别编号
     */
    public Integer getServiceCategoryId() {
        return serviceCategoryId;
    }

    /**
     * 服务类别编号
     * @param serviceCategoryId 服务类别编号
     */
    public void setServiceCategoryId(Integer serviceCategoryId) {
        this.serviceCategoryId = serviceCategoryId;
    }

    /**
     * 服务类别名称
     * @return service_category_name 服务类别名称
     */
    public String getServiceCategoryName() {
        return serviceCategoryName;
    }

    /**
     * 服务类别名称
     * @param serviceCategoryName 服务类别名称
     */
    public void setServiceCategoryName(String serviceCategoryName) {
        this.serviceCategoryName = serviceCategoryName;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", serviceCategoryId=").append(serviceCategoryId);
        sb.append(", serviceCategoryName=").append(serviceCategoryName);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}