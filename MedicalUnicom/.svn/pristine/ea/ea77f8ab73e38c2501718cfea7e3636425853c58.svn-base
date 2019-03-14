package com.djb.ylt.user.entity;

import com.djb.ylt.framework.entity.PageModel;

public class PatientScheduleEntity  extends  PageModel{
	
    /**
	 * serialVersionUID:用一句话描述这个变量表示什么。
	 */
	private static final long serialVersionUID = -6076339906820020343L;

	private Integer psId;

    private Integer scheduleId;

    private Integer patientId;

    private String psFlag;

    private Integer doctorId;

    public Integer getPsId() {
        return psId;
    }

    public void setPsId(Integer psId) {
        this.psId = psId;
    }

    public Integer getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Integer scheduleId) {
        this.scheduleId = scheduleId;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public String getPsFlag() {
        return psFlag;
    }

    public void setPsFlag(String psFlag) {
        this.psFlag = psFlag;
    }

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
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
        PatientScheduleEntity other = (PatientScheduleEntity) that;
        return (this.getPsId() == null ? other.getPsId() == null : this.getPsId().equals(other.getPsId()))
            && (this.getScheduleId() == null ? other.getScheduleId() == null : this.getScheduleId().equals(other.getScheduleId()))
            && (this.getPatientId() == null ? other.getPatientId() == null : this.getPatientId().equals(other.getPatientId()))
            && (this.getPsFlag() == null ? other.getPsFlag() == null : this.getPsFlag().equals(other.getPsFlag()))
            && (this.getDoctorId() == null ? other.getDoctorId() == null : this.getDoctorId().equals(other.getDoctorId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getPsId() == null) ? 0 : getPsId().hashCode());
        result = prime * result + ((getScheduleId() == null) ? 0 : getScheduleId().hashCode());
        result = prime * result + ((getPatientId() == null) ? 0 : getPatientId().hashCode());
        result = prime * result + ((getPsFlag() == null) ? 0 : getPsFlag().hashCode());
        result = prime * result + ((getDoctorId() == null) ? 0 : getDoctorId().hashCode());
        return result;
    }
}