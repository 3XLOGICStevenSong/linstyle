package com.djb.ylt.main_page.entity;

import com.djb.ylt.framework.entity.PageModel;

/**
 * @date 2016年12月13日 下午6:08:12
 * @author chengming 内容：android端首页轮播图dao pojo类 
 * 修改履历：2016年12月13日 下午6:08:12 chengming
 */
public class CarouselEntity extends PageModel {
	private Integer carouselId;

	private Integer roleId;

	private String carouselUrl;
	// 关联查询role表
	private RoleEntity roleEntity;

	//修改履历：2016年12月22日 下午1:53:29 chengming  start
	private String carouselHtml;
	public String getCarouselHtml() {
		return carouselHtml;
	}

	public void setCarouselHtml(String carouselHtml) {
		this.carouselHtml = carouselHtml;
	}

	//修改履历：2016年12月22日 下午1:53:29 chengming  end
	public RoleEntity getRoleEntity() {
		return roleEntity;
	}

	public void setRoleEntity(RoleEntity roleEntity) {
		this.roleEntity = roleEntity;
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