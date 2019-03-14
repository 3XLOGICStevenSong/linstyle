package com.djb.ylt.user.entity;

import java.util.Date;
import java.util.List;

import com.djb.ylt.framework.entity.BaseEntity;
import com.djb.ylt.user.dto.DoctorScheduleDTO;

public class DoctorScheduleEntity extends BaseEntity {

	/**
	 * serialVersionUID:用一句话描述这个变量表示什么。
	 */
	private static final long serialVersionUID = 4444507110551669058L;

	private Integer scheduleId;

	private Integer doctorId;

	private Date startDate;

	private Date startTime;

	private String timeInterval;

	private String appointStatus;

	private Integer patientId;

	private String status;

	private Date createTime;

	private Date updateTime;

	private String[] startDateArgs;

	private Date endDate;

	private Integer patientNum;

	private Integer appointNum;

	private Date endTime;
	
	
	
	private List<DoctorScheduleDTO> scheduleDTOList;
	public Integer getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(Integer scheduleId) {
		this.scheduleId = scheduleId;
	}

	public Integer getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}

	public String getTimeInterval() {
		return timeInterval;
	}

	public void setTimeInterval(String timeInterval) {
		this.timeInterval = timeInterval;
	}

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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
		DoctorScheduleEntity other = (DoctorScheduleEntity) that;
		return (this.getScheduleId() == null ? other.getScheduleId() == null
				: this.getScheduleId().equals(other.getScheduleId()))
				&& (this.getDoctorId() == null ? other.getDoctorId() == null
						: this.getDoctorId().equals(other.getDoctorId()))
				&& (this.getStartDate() == null ? other.getStartDate() == null
						: this.getStartDate().equals(other.getStartDate()))
				&& (this.getStartTime() == null ? other.getStartTime() == null
						: this.getStartTime().equals(other.getStartTime()))
				&& (this.getTimeInterval() == null ? other.getTimeInterval() == null
						: this.getTimeInterval().equals(other.getTimeInterval()))
				&& (this.getAppointStatus() == null ? other.getAppointStatus() == null
						: this.getAppointStatus().equals(other.getAppointStatus()))
				&& (this.getPatientId() == null ? other.getPatientId() == null
						: this.getPatientId().equals(other.getPatientId()))
				&& (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
				&& (this.getCreateTime() == null ? other.getCreateTime() == null
						: this.getCreateTime().equals(other.getCreateTime()))
				&& (this.getUpdateTime() == null ? other.getUpdateTime() == null
						: this.getUpdateTime().equals(other.getUpdateTime()));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getScheduleId() == null) ? 0 : getScheduleId().hashCode());
		result = prime * result + ((getDoctorId() == null) ? 0 : getDoctorId().hashCode());
		result = prime * result + ((getStartDate() == null) ? 0 : getStartDate().hashCode());
		result = prime * result + ((getStartTime() == null) ? 0 : getStartTime().hashCode());
		result = prime * result + ((getTimeInterval() == null) ? 0 : getTimeInterval().hashCode());
		result = prime * result + ((getAppointStatus() == null) ? 0 : getAppointStatus().hashCode());
		result = prime * result + ((getPatientId() == null) ? 0 : getPatientId().hashCode());
		result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
		result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
		result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
		return result;
	}

	/**
	 * 返回appointStatus的值
	 * 
	 * @return String appointStatus的值
	 */
	public String getAppointStatus() {
		return appointStatus;
	}

	/**
	 * 设置appointStatus的值
	 * 
	 * @param appointStatus
	 *            appointStatus的值
	 */
	public void setAppointStatus(String appointStatus) {
		this.appointStatus = appointStatus;
	}

	/**
	 * 返回updateTime的值
	 * 
	 * @return Date updateTime的值
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * 设置updateTime的值
	 * 
	 * @param updateTime
	 *            updateTime的值
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * 返回startTime的值
	 * 
	 * @return Date startTime的值
	 */
	public Date getStartTime() {
		return startTime;
	}

	/**
	 * 设置startTime的值
	 * 
	 * @param startTime
	 *            startTime的值
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	/**
	 * 返回startDateArgs的值
	 * 
	 * @return String[] startDateArgs的值
	 */
	public String[] getStartDateArgs() {
		return startDateArgs;
	}

	/**
	 * 设置startDateArgs的值
	 * 
	 * @param startDateArgs
	 *            startDateArgs的值
	 */
	public void setStartDateArgs(String[] startDateArgs) {
		this.startDateArgs = startDateArgs;
	}

	/**
	 * 返回startDate的值
	 * 
	 * @return Date startDate的值
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * 设置startDate的值
	 * 
	 * @param startDate
	 *            startDate的值
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**

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

	/**
	 * 返回endTime的值
	 * @return Date endTime的值
	 */
	public Date getEndTime() {
		return endTime;
	}

	/**
	 * 设置endTime的值
	 * @param  endTime endTime的值
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	/**
	 * 返回scheduleDTOList的值
	 * @return List<DoctorScheduleDTO> scheduleDTOList的值
	 */
	public List<DoctorScheduleDTO> getScheduleDTOList() {
		return scheduleDTOList;
	}

	/**
	 * 设置scheduleDTOList的值
	 * @param  scheduleDTOList scheduleDTOList的值
	 */
	public void setScheduleDTOList(List<DoctorScheduleDTO> scheduleDTOList) {
		this.scheduleDTOList = scheduleDTOList;
	}

	/**
	 * 返回endDate的值
	 * @return Date endDate的值
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * 设置endDate的值
	 * @param  endDate endDate的值
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	
}