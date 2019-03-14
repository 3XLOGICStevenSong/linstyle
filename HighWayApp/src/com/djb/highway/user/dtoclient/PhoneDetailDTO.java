package com.djb.highway.user.dtoclient;


import com.djb.highway.road.dtoclient.BaseClientDTO;

@SuppressWarnings("serial")
public class PhoneDetailDTO extends BaseClientDTO {

	/**
	 * 当前用户id
	 */
	private Integer userId;
	private Integer p_id;
	/**
	 * 备注
	 */
	private String name;

	/**
	 * 电话
	 */
	private String tel;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public Integer getP_id() {
		return p_id;
	}

	public void setP_id(Integer p_id) {
		this.p_id = p_id;
	}

}
