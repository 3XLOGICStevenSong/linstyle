package com.djb.highway.road.dtoclient;

public class ProclDTO {

	/**
	 * 显示名称
	 */
	private String sectionName;

	/**
	 * 发布时间
	 */
	private String deploy_time;

	/**
	 * 图片路径
	 */
	private String pic_path;

	/**
	 * 桩号
	 */
	private String stake_id;

	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}

	public String getDeploy_time() {
		return deploy_time;
	}

	public void setDeploy_time(String deploy_time) {
		this.deploy_time = deploy_time;
	}

	public String getPic_path() {
		return pic_path;
	}

	public void setPic_path(String pic_path) {
		this.pic_path = pic_path;
	}

	public String getStake_id() {
		return stake_id;
	}

	public void setStake_id(String stake_id) {
		this.stake_id = stake_id;
	}

}
