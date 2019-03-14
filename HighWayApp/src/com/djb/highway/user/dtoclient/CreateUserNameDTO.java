package com.djb.highway.user.dtoclient;


import com.djb.highway.road.dtoclient.BaseClientDTO;

public class CreateUserNameDTO extends BaseClientDTO {

	private static final long serialVersionUID = 1L;

	private String userCode;

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

}
