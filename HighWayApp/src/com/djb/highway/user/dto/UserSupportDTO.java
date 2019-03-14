package com.djb.highway.user.dto;

import com.djb.highway.framework.dto.PageDTO;

public class UserSupportDTO extends PageDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2649667952492299655L;

	private Integer us_id;

	private Integer u_id;

	private Integer udp_id;

	private UserDTO userDTO;

	public Integer getUs_id() {
		return us_id;
	}

	public void setUs_id(Integer us_id) {
		this.us_id = us_id;
	}

	public Integer getU_id() {
		return u_id;
	}

	public void setU_id(Integer u_id) {
		this.u_id = u_id;
	}

	public Integer getUdp_id() {
		return udp_id;
	}

	public void setUdp_id(Integer udp_id) {
		this.udp_id = udp_id;
	}

	public UserDTO getUserDTO() {
		return userDTO;
	}

	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}

}