package com.djb.highway.road.entity;

import java.util.Date;

import com.djb.highway.framework.entity.BaseEntity;

public class HighWayEntity extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5628431451560940527L;

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

	@Override
	public boolean equals(Object that) {
		if (this == that) {
			return true;
		}
		if (that == null) {
			return false;
		}
		if (getClass() != that.getClass()) {
			return false;
		}
		HighWayEntity other = (HighWayEntity) that;
		return (this.getH_id() == null ? other.getH_id() == null : this
				.getH_id().equals(other.getH_id()))
				&& (this.getH_code() == null ? other.getH_code() == null : this
						.getH_code().equals(other.getH_code()))
				&& (this.getH_name() == null ? other.getH_name() == null : this
						.getH_name().equals(other.getH_name()))
				&& (this.getStart_stake_id() == null ? other
						.getStart_stake_id() == null : this.getStart_stake_id()
						.equals(other.getStart_stake_id()))
				&& (this.getEnd_stake_id() == null ? other.getEnd_stake_id() == null
						: this.getEnd_stake_id()
								.equals(other.getEnd_stake_id()))
				&& (this.getH_desc() == null ? other.getH_desc() == null : this
						.getH_desc().equals(other.getH_desc()))
				&& (this.getLength() == null ? other.getLength() == null : this
						.getLength().equals(other.getLength()))
				&& (this.getH_type() == null ? other.getH_type() == null : this
						.getH_type().equals(other.getH_type()))
				&& (this.getH_status() == null ? other.getH_status() == null
						: this.getH_status().equals(other.getH_status()))
				&& (this.getPlaz_list() == null ? other.getPlaz_list() == null
						: this.getPlaz_list().equals(other.getPlaz_list()))
				&& (this.getCreate_time() == null ? other.getCreate_time() == null
						: this.getCreate_time().equals(other.getCreate_time()))
				&& (this.getUpdate_time() == null ? other.getUpdate_time() == null
						: this.getUpdate_time().equals(other.getUpdate_time()));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((getH_id() == null) ? 0 : getH_id().hashCode());
		result = prime * result
				+ ((getH_code() == null) ? 0 : getH_code().hashCode());
		result = prime * result
				+ ((getH_name() == null) ? 0 : getH_name().hashCode());
		result = prime
				* result
				+ ((getStart_stake_id() == null) ? 0 : getStart_stake_id()
						.hashCode());
		result = prime
				* result
				+ ((getEnd_stake_id() == null) ? 0 : getEnd_stake_id()
						.hashCode());
		result = prime * result
				+ ((getH_desc() == null) ? 0 : getH_desc().hashCode());
		result = prime * result
				+ ((getLength() == null) ? 0 : getLength().hashCode());
		result = prime * result
				+ ((getH_type() == null) ? 0 : getH_type().hashCode());
		result = prime * result
				+ ((getH_status() == null) ? 0 : getH_status().hashCode());
		result = prime * result
				+ ((getPlaz_list() == null) ? 0 : getPlaz_list().hashCode());
		result = prime
				* result
				+ ((getCreate_time() == null) ? 0 : getCreate_time().hashCode());
		result = prime
				* result
				+ ((getUpdate_time() == null) ? 0 : getUpdate_time().hashCode());
		return result;
	}
}