package com.djb.ylt.main_page.dto;

import com.djb.ylt.framework.dto.BaseDTO;

/**
 * @date 2016年12月13日 下午6:04:10
 * @author chengming
 * 内容：android端首页轮播图的request参数pojo类
 * 修改履历：2016年12月13日 下午6:04:10 chengming 
 */
public class CarouselDTO extends BaseDTO {
	private static final long serialVersionUID = -4737954181773645953L;
	private Integer carouselId;

    private Integer roleId;

    private String carouselUrl;
    
    private String carouselHtml;
    
    public String getCarouselHtml() {
		return carouselHtml;
	}

	public void setCarouselHtml(String carouselHtml) {
		this.carouselHtml = carouselHtml;
	}

	public Integer getCarouselId() {
        return carouselId;
    }

    public void setCarouselId(Integer carouselId) {
        this.carouselId = carouselId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getCarouselUrl() {
        return carouselUrl;
    }

    public void setCarouselUrl(String carouselUrl) {
        this.carouselUrl = carouselUrl == null ? null : carouselUrl.trim();
    }
}
