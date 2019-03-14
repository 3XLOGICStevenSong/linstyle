package com.djb.ylt.user.dto;

import java.util.Date;

public class ScheduleDateDTO {

	private Date startDate;

	private String startTime;

	private String endTime;

	private Integer patientNum;
	
	private Integer scheduleId;
	
	private Integer appointNum;

	/**
	 * 返回startDate的值
	 * @return Date startDate的值
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * 设置startDate的值
	 * @param  startDate startDate的值
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	
	/**
	 * 返回patientNum的值
	 * @return Integer patientNum的值
	 */
	public Integer getPatientNum() {
		return patientNum;
	}

	/**
	 * 设置patientNum的值
	 * @param  patientNum patientNum的值
	 */
	public void setPatientNum(Integer patientNum) {
		this.patientNum = patientNum;
	}

	/**
	 * 返回scheduleId的值
	 * @return Integer scheduleId的值
	 */
	public Integer getScheduleId() {
		return scheduleId;
	}

	/**
	 * 设置scheduleId的值
	 * @param  scheduleId scheduleId的值
	 */
	public void setScheduleId(Integer scheduleId) {
		this.scheduleId = scheduleId;
	}

	/**
	 * 返回startTime的值
	 * @return String startTime的值
	 */
	public String getStartTime() {
		return startTime;
	}

	/**
	 * 设置startTime的值
	 * @param  startTime startTime的值
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	/**
	 * 返回endTime的值
	 * @return String endTime的值
	 */
	public String getEndTime() {
		return endTime;
	}

	/**
	 * 设置endTime的值
	 * @param  endTime endTime的值
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	/**
	 * 返回appointNum的值
	 * @return Integer appointNum的值
	 */
	public Integer getAppointNum() {
		return appointNum;
	}

	/**
	 * 设置appointNum的值
	 * @param  appointNum appointNum的值
	 */
	public void setAppointNum(Integer appointNum) {
		this.appointNum = appointNum;
	}

	
}