package com.djb.highway.road.dto;

import java.util.Date;

import com.djb.highway.road.entity.HighWayEntity;

public class HighWayDTO {
	private Integer h_id;

	private String h_code;

	private String h_name;

	private String start_stake_id;

	private String end_stake_id;

	private String h_desc;

	private String length;

	private String h_type;

	private String h_status;

	private String plaz_list;

	private Date create_time;

	private Date update_time;

	public Integer getH_id() {
		return h_id;
	}

	public void setH_id(Integer h_id) {
		this.h_id = h_id;
	}

	public String getH_code() {
		return h_code;
	}

	public void setH_code(String h_code) {
		this.h_code = h_code;
	}

	public String getH_name() {
		return h_name;
	}

	public void setH_name(String h_name) {
		this.h_name = h_name;
	}

	public String getStart_stake_id() {
		return start_stake_id;
	}

	public void setStart_stake_id(String start_stake_id) {
		this.start_stake_id = start_stake_id;
	}

	public String getEnd_stake_id() {
		return end_stake_id;
	}

	public void setEnd_stake_id(String end_stake_id) {
		this.end_stake_id = end_stake_id;
	}

	public String getH_desc() {
		return h_desc;
	}

	public void setH_desc(String h_desc) {
		this.h_desc = h_desc;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public String getH_type() {
		return h_type;
	}

	public void setH_type(String h_type) {
		this.h_type = h_type;
	}

	public String getH_status() {
		return h_status;
	}

	public void setH_status(String h_status) {
		this.h_status = h_status;
	}

	public String getPlaz_list() {
		return plaz_list;
	}

	public void setPlaz_list(String plaz_list) {
		this.plaz_list = plaz_list;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public Date getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}

}