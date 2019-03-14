package com.djb.highway.road.dtoclient;

import java.util.Date;

public class RoadControlInfoClientDTO {

	private Long road_id;

	private String road_name;

	private String control_scope;

	private String control_direction;

	private String control_reason;

	private String control_mode;

	private String rci_info;

	private Date start_time;

	private Date end_time;

	private Date update_time;

	private Integer rci_longitude;

	private Integer rci_latitude;

	private Integer rci_type;

	public Long getRoad_id() {
		return road_id;
	}

	public void setRoad_id(Long road_id) {
		this.road_id = road_id;
	}

	public String getRoad_name() {
		return road_name;
	}

	public void setRoad_name(String road_name) {
		this.road_name = road_name;
	}

	public String getControl_scope() {
		return control_scope;
	}

	public void setControl_scope(String control_scope) {
		this.control_scope = control_scope;
	}

	public String getControl_direction() {
		return control_direction;
	}

	public void setControl_direction(String control_direction) {
		this.control_direction = control_direction;
	}

	public String getControl_reason() {
		return control_reason;
	}

	public void setControl_reason(String control_reason) {
		this.control_reason = control_reason;
	}

	public String getControl_mode() {
		return control_mode;
	}

	public void setControl_mode(String control_mode) {
		this.control_mode = control_mode;
	}

	public String getRci_info() {
		return rci_info;
	}

	public void setRci_info(String rci_info) {
		this.rci_info = rci_info;
	}

	public Date getStart_time() {
		return start_time;
	}

	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}

	public Date getEnd_time() {
		return end_time;
	}

	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}

	public Date getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}

	public Integer getRci_longitude() {
		return rci_longitude;
	}

	public void setRci_longitude(Integer rci_longitude) {
		this.rci_longitude = rci_longitude;
	}

	public Integer getRci_latitude() {
		return rci_latitude;
	}

	public void setRci_latitude(Integer rci_latitude) {
		this.rci_latitude = rci_latitude;
	}

	public Integer getRci_type() {
		return rci_type;
	}

	public void setRci_type(Integer rci_type) {
		this.rci_type = rci_type;
	}

}