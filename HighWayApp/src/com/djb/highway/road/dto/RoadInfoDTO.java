package com.djb.highway.road.dto;

import java.util.List;

import com.djb.highway.framework.dto.BaseDTO;

public class RoadInfoDTO extends BaseDTO {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3544711239082338470L;
	private int roadId;
	private boolean markFlag;
	private String roadName;
	private String plazCodeStart;
	private String plazCodeEnd;
	private Integer user_id;
	private String road_type;

	public String getRoad_type() {
		return road_type;
	}

	public void setRoad_type(String road_type) {
		this.road_type = road_type;
	}

	private List<RoadInfoItemDTO> list;

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getRoadId() {
		return roadId;
	}

	public void setRoadId(int roadId) {
		this.roadId = roadId;
	}

	public String getRoadName() {
		return roadName;
	}

	public void setRoadName(String roadName) {
		this.roadName = roadName;
	}

	public List<RoadInfoItemDTO> getList() {
		return list;
	}

	public void setList(List<RoadInfoItemDTO> list) {
		this.list = list;
	}

	public boolean isMarkFlag() {
		return markFlag;
	}

	public void setMarkFlag(boolean markFlag) {
		this.markFlag = markFlag;
	}

	public String getPlazCodeStart() {
		return plazCodeStart;
	}

	public void setPlazCodeStart(String plazCodeStart) {
		this.plazCodeStart = plazCodeStart;
	}

	public String getPlazCodeEnd() {
		return plazCodeEnd;
	}

	public void setPlazCodeEnd(String plazCodeEnd) {
		this.plazCodeEnd = plazCodeEnd;
	}

}
