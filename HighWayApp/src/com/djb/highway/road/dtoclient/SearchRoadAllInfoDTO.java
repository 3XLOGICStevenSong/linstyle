package com.djb.highway.road.dtoclient;

import java.util.List;

public class SearchRoadAllInfoDTO extends BaseClientDTO {

	private boolean markFlag;

	private String money;

	private String time;

	private String length;
	private List<SearchRoadInfoDTO> stationInfoData;

	public List<SearchRoadInfoDTO> getStationInfoData() {
		return stationInfoData;
	}

	public void setStationInfoData(List<SearchRoadInfoDTO> stationInfoData) {
		this.stationInfoData = stationInfoData;
	}

	public boolean isMarkFlag() {
		return markFlag;
	}

	public void setMarkFlag(boolean markFlag) {
		this.markFlag = markFlag;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

}