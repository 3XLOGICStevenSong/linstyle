package com.djb.highway.user.dtoclient;

import java.util.List;

import com.djb.highway.framework.dto.BaseDTO;
import com.djb.highway.road.dtoclient.BaseClientDTO;

public class PhoneClientDTO extends BaseClientDTO {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2818942151241855130L;
	private List<PhoneDetailDTO> list;

	public List<PhoneDetailDTO> getList() {
		return list;
	}

	public void setList(List<PhoneDetailDTO> list) {
		this.list = list;
	}

}