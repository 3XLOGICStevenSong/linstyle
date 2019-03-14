package com.djb.highway.road.dtoclient;

import java.util.List;

/**
 * 类说明
 * 
 * @author LiWm E-mail:
 * @version 创建时间：2014年8月27日 下午5:56:36
 */
public class RoadDTO extends BaseClientDTO{

	/**
	 * 默认
	 */
	private static final long serialVersionUID = 1L;
	
	private List<RoadDTO> listRoadEvent;
	
	/**
	 * 事件id
	 */
	private Integer event_id;
	
	/**
	 * 封闭原因
	 */
	private String road_reason;
	
	/**
	 * 高速名称
	 */
	private String highway_name;

	/**
	 * 开始时间
	 */
	private String start_time;

	/**
	 * 高速code
	 */
	private String high_code;

	/**
	 * 管制范围
	 */
	private String control_scope;

	/**
	 * 单向封闭或者双向封闭
	 */
	private String road_type;

	/**
	 * 预计结束时间
	 */
	private String end_time;

	public String getRoad_reason() {
		return road_reason;
	}

	public void setRoad_reason(String road_reason) {
		this.road_reason = road_reason;
	}

	public String getHighway_name() {
		return highway_name;
	}

	public void setHighway_name(String highway_name) {
		this.highway_name = highway_name;
	}

	public String getStart_time() {
		return start_time;
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

	public String getHigh_code() {
		return high_code;
	}

	public void setHigh_code(String high_code) {
		this.high_code = high_code;
	}

	public String getControl_scope() {
		return control_scope;
	}

	public void setControl_scope(String control_scope) {
		this.control_scope = control_scope;
	}

	public String getRoad_type() {
		return road_type;
	}

	public void setRoad_type(String road_type) {
		this.road_type = road_type;
	}

	public String getEnd_time() {
		return end_time;
	}

	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}

	public List<RoadDTO> getListRoadEvent() {
		return listRoadEvent;
	}

	public void setListRoadEvent(List<RoadDTO> listRoadEvent) {
		this.listRoadEvent = listRoadEvent;
	}

	public Integer getEvent_id() {
		return event_id;
	}

	public void setEvent_id(Integer event_id) {
		this.event_id = event_id;
	}

	
}
