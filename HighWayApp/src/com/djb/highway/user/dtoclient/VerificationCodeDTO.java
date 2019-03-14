package com.djb.highway.user.dtoclient;


import com.djb.highway.road.dtoclient.BaseClientDTO;

public class VerificationCodeDTO extends BaseClientDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -513513924185210787L;
	private String verificationCode;

	public String getVerificationCode() {
		return verificationCode;
	}

	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}

}