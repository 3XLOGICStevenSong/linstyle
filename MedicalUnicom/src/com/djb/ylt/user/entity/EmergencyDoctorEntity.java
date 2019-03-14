package com.djb.ylt.user.entity;

import java.util.Date;

import com.djb.ylt.framework.entity.PageModel;

public class EmergencyDoctorEntity extends PageModel {

	/**
	 * serialVersionUID:用一句话描述这个变量表示什么。
	 */
	private static final long serialVersionUID = -4065135404395538838L;

	private Integer edId;

	private Integer edDocid;

	private String edUsertel;

	private String edStatus;

	private Date workDate;

	public Integer getEdId() {
		return edId;
	}

	public void setEdId(Integer edId) {
		this.edId = edId;
	}

	public Integer getEdDocid() {
		return edDocid;
	}

	public void setEdDocid(Integer edDocid) {
		this.edDocid = edDocid;
	}

	public String getEdUsertel() {
		return edUsertel;
	}

	public void setEdUsertel(String edUsertel) {
		this.edUsertel = edUsertel;
	}

	public String getEdStatus() {
		return edStatus;
	}

	public void setEdStatus(String edStatus) {
		this.edStatus = edStatus;
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
		EmergencyDoctorEntity other = (EmergencyDoctorEntity) that;
		return (this.getEdId() == null ? other.getEdId() == null : this.getEdId().equals(other.getEdId()))
				&& (this.getEdDocid() == null ? other.getEdDocid() == null
						: this.getEdDocid().equals(other.getEdDocid()))
				&& (this.getEdUsertel() == null ? other.getEdUsertel() == null
						: this.getEdUsertel().equals(other.getEdUsertel()))
				&& (this.getEdStatus() == null ? other.getEdStatus() == null
						: this.getEdStatus().equals(other.getEdStatus()));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getEdId() == null) ? 0 : getEdId().hashCode());
		result = prime * result + ((getEdDocid() == null) ? 0 : getEdDocid().hashCode());
		result = prime * result + ((getEdUsertel() == null) ? 0 : getEdUsertel().hashCode());
		result = prime * result + ((getEdStatus() == null) ? 0 : getEdStatus().hashCode());
		return result;
	}

	/**
	 * 返回workDate的值
	 * 
	 * @return Date workDate的值
	 */
	public Date getWorkDate() {
		return workDate;
	}

	/**
	 * 设置workDate的值
	 * 
	 * @param workDate
	 *            workDate的值
	 */
	public void setWorkDate(Date workDate) {
		this.workDate = workDate;
	}
}