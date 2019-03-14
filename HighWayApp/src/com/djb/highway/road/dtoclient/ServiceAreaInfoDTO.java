package com.djb.highway.road.dtoclient;

import java.util.List;

import com.djb.highway.framework.dto.BaseDTO;

public class ServiceAreaInfoDTO extends BaseClientDTO {


	private List<ServiceAreaClientDTO> eventList;

	public List<ServiceAreaClientDTO> getEventList() {
		return eventList;
	}

	public void setEventList(List<ServiceAreaClientDTO> eventList) {
		this.eventList = eventList;
	}

}
