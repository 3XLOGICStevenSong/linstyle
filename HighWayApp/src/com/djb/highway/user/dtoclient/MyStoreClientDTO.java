package com.djb.highway.user.dtoclient;

import java.util.List;

import com.djb.highway.road.dtoclient.BaseClientDTO;

public class MyStoreClientDTO extends BaseClientDTO {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2818942151241855130L;
	private List<MyStoreDetailDTO> list;

	private String our_version_time;

	public List<MyStoreDetailDTO> getList() {
		return list;
	}

	public void setList(List<MyStoreDetailDTO> list) {
		this.list = list;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getOur_version_time() {
		return our_version_time;
	}

	public void setOur_version_time(String our_version_time) {
		this.our_version_time = our_version_time;
	}

}
