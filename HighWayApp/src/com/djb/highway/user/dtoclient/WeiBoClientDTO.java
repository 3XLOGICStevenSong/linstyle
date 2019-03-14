package com.djb.highway.user.dtoclient;



import java.util.List;

import com.djb.highway.road.dtoclient.BaseClientDTO;

public class WeiBoClientDTO extends BaseClientDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<WeiBoDetailDTO> list;

	public List<WeiBoDetailDTO> getList() {
		return list;
	}

	public void setList(List<WeiBoDetailDTO> list) {
		this.list = list;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
