package cn.com.dbridge.lifecare.framework.vo.web;

import java.io.Serializable;


/**
 * 
 * @ClassName:  ResourcePO
 * @Description: 资源PO
 * @author: linh
 * @date:   2018年12月26日 下午1:05:50
 * 
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
public class ResourceVO implements Serializable {


    /**
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
     */
    private static final long serialVersionUID = 957878591829432822L;
    /**
     * 
     */
    private Integer resourceId;
	
	private Integer parentId;
	
	private String resourceName;
	
	private String resourceType;
	
	private String url;
	
	private String permission;
	
	private Integer showOrder;
	
	private Boolean status;
	
	
	public Integer getResourceId() {
		return resourceId;
	}
	public void setResourceId(Integer resourceId) {
		this.resourceId = resourceId;
	}
	public String getResourceName() {
		return resourceName;
	}
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	public String getResourceType() {
		return resourceType;
	}
	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getPermission() {
		return permission;
	}
	public void setPermission(String permission) {
		this.permission = permission;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatu(Boolean status) {
		this.status = status;
	}

	public Integer getShowOrder() {
		return showOrder;
	}
	
	public void setShowOrder(Integer showOrder) {
		this.showOrder = showOrder;
	}
	
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
}
