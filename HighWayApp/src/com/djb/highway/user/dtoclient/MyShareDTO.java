package com.djb.highway.user.dtoclient;

import java.util.List;

import com.djb.highway.road.dtoclient.BaseClientDTO;

public class MyShareDTO extends BaseClientDTO {
	/**
     * 
     */
	private static final long serialVersionUID = -378848295830417701L;

	// 版本时间戳
	//private String udp_version_time;

	private List<UserDeployPicClearDTO> userDeployPicDTOs;

	public List<UserDeployPicClearDTO> getUserDeployPicDTOs() {
		return userDeployPicDTOs;
	}

//	public String getUdp_version_time() {
//		return udp_version_time;
//	}
//
//	public void setUdp_version_time(String udp_version_time) {
//		this.udp_version_time = udp_version_time;
//	}

	public void setUserDeployPicDTOs(
			List<UserDeployPicClearDTO> userDeployPicDTOs) {
		this.userDeployPicDTOs = userDeployPicDTOs;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}