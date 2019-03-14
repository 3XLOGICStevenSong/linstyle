package com.djb.highway.user.dtoclient;

import java.util.List;

import com.djb.highway.road.dtoclient.BaseClientDTO;

public class MoreShareDTO extends BaseClientDTO {
	/**
     * 
     */
	private static final long serialVersionUID = -378848295830417701L;

	private List<MoreShareItemDTO> list;

	public List<MoreShareItemDTO> getList() {
		return list;
	}

	public void setList(List<MoreShareItemDTO> list) {
		this.list = list;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}