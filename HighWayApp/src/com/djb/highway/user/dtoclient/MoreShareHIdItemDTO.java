package com.djb.highway.user.dtoclient;

public class MoreShareHIdItemDTO {

	private Integer udp_id;

	private String u_name;

	private String udp_deploy_time;

	private String deploy_msg;

	private String udp_pic_path;
	// 赞的数量
	private String zanCount;
	// 路段名称
	private String section_name;
	// 高速id
	private Integer h_id;
	
	/**
	 * 事件类型
	 */
	private String eventName;

	/**
	 * 范围 :西江街-白塔堡
	 */
	private String direction;

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public Integer getUdp_id() {
		return udp_id;
	}

	public void setUdp_id(Integer udp_id) {
		this.udp_id = udp_id;
	}

	public String getU_name() {
		return u_name;
	}

	public void setU_name(String u_name) {
		this.u_name = u_name;
	}

	public String getUdp_deploy_time() {
		return udp_deploy_time;
	}

	public void setUdp_deploy_time(String udp_deploy_time) {
		this.udp_deploy_time = udp_deploy_time;
	}

	public String getDeploy_msg() {
		return deploy_msg;
	}

	public void setDeploy_msg(String deploy_msg) {
		this.deploy_msg = deploy_msg;
	}

	public String getUdp_pic_path() {
		return udp_pic_path;
	}

	public void setUdp_pic_path(String udp_pic_path) {
		this.udp_pic_path = udp_pic_path;
	}

	public String getZanCount() {
		return zanCount;
	}

	public void setZanCount(String zanCount) {
		this.zanCount = zanCount;
	}

	public String getSection_name() {
		return section_name;
	}

	public void setSection_name(String section_name) {
		this.section_name = section_name;
	}

	public Integer getH_id() {
		return h_id;
	}

	public void setH_id(Integer h_id) {
		this.h_id = h_id;
	}}

	