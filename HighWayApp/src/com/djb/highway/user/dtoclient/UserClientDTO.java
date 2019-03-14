package com.djb.highway.user.dtoclient;

import java.util.List;

import com.djb.highway.framework.dto.BaseDTO;
import com.djb.highway.road.dtoclient.BaseClientDTO;

public class UserClientDTO extends BaseClientDTO {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2818942151241855130L;
	 private Integer u_id;
	 private String  u_code;
	private String   u_name;
	public Integer getU_id() {
		return u_id;
	}
	public void setU_id(Integer u_id) {
		this.u_id = u_id;
	}
	public String getU_code() {
		return u_code;
	}
	public void setU_code(String u_code) {
		this.u_code = u_code;
	}
	public String getU_name() {
		return u_name;
	}
	public void setU_name(String u_name) {
		this.u_name = u_name;
	}
}

	