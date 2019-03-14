package com.djb.highway.road.dtoclient;

import java.util.List;

public class LNUniqueListDTO extends BaseClientDTO {

	private List<LNUniqueDTO> LNUniqueInfoList;

	public List<LNUniqueDTO> getLNUniqueInfoList() {
		return LNUniqueInfoList;
	}

	public void setLNUniqueInfoList(List<LNUniqueDTO> lNUniqueInfoList) {
		LNUniqueInfoList = lNUniqueInfoList;
	}

}
