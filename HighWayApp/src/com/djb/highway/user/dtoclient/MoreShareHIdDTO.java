package com.djb.highway.user.dtoclient;

import java.util.List;

import com.djb.highway.road.dtoclient.BaseClientDTO;

public class MoreShareHIdDTO extends BaseClientDTO {
	/**
     * 
     */
	private static final long serialVersionUID = -378848295830417701L;

	private List<MoreShareHIdItemDTO> list;

	public List<MoreShareHIdItemDTO> getList() {
		return list;
	}

	public void setList(List<MoreShareHIdItemDTO> list) {
		this.list = list;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}