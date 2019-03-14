package com.djb.ylt.user.entity;

import java.util.Date;

public class GraphicEntity {
    private Long graphicId;

    private Integer appointId;

    private String graphicContent;

    private String graphicType;

    private Date recordTime;

    private String deleteFlag;

    private String role;

    private Integer doctorId;

    private Integer patientId;
    
    private Date  maxTime;
    
    private Date createTime;
    
    private Date graphicTime;

    public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getGraphicId() {
        return graphicId;
    }

    public void setGraphicId(Long graphicId) {
        this.graphicId = graphicId;
    }


    public String getGraphicContent() {
        return graphicContent;
    }

    public void setGraphicContent(String graphicContent) {
        this.graphicContent = graphicContent;
    }

    public String getGraphicType() {
        return graphicType;
    }

    public void setGraphicType(String graphicType) {
        this.graphicType = graphicType;
    }

  

    public String getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
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
        GraphicEntity other = (GraphicEntity) that;
        return (this.getGraphicId() == null ? other.getGraphicId() == null : this.getGraphicId().equals(other.getGraphicId()))
            && (this.getAppointId() == null ? other.getAppointId() == null : this.getAppointId().equals(other.getAppointId()))
            && (this.getGraphicContent() == null ? other.getGraphicContent() == null : this.getGraphicContent().equals(other.getGraphicContent()))
            && (this.getGraphicType() == null ? other.getGraphicType() == null : this.getGraphicType().equals(other.getGraphicType()))
            && (this.getRecordTime() == null ? other.getRecordTime() == null : this.getRecordTime().equals(other.getRecordTime()))
            && (this.getDeleteFlag() == null ? other.getDeleteFlag() == null : this.getDeleteFlag().equals(other.getDeleteFlag()))
            && (this.getRole() == null ? other.getRole() == null : this.getRole().equals(other.getRole()))
            && (this.getDoctorId() == null ? other.getDoctorId() == null : this.getDoctorId().equals(other.getDoctorId()))
            && (this.getPatientId() == null ? other.getPatientId() == null : this.getPatientId().equals(other.getPatientId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getGraphicId() == null) ? 0 : getGraphicId().hashCode());
        result = prime * result + ((getAppointId() == null) ? 0 : getAppointId().hashCode());
        result = prime * result + ((getGraphicContent() == null) ? 0 : getGraphicContent().hashCode());
        result = prime * result + ((getGraphicType() == null) ? 0 : getGraphicType().hashCode());
        result = prime * result + ((getRecordTime() == null) ? 0 : getRecordTime().hashCode());
        result = prime * result + ((getDeleteFlag() == null) ? 0 : getDeleteFlag().hashCode());
        result = prime * result + ((getRole() == null) ? 0 : getRole().hashCode());
        result = prime * result + ((getDoctorId() == null) ? 0 : getDoctorId().hashCode());
        result = prime * result + ((getPatientId() == null) ? 0 : getPatientId().hashCode());
        return result;
    }

	/**
	 * 返回recordTime的值
	 * @return Date recordTime的值
	 */
	public Date getRecordTime() {
		return recordTime;
	}

	/**
	 * 设置recordTime的值
	 * @param  recordTime recordTime的值
	 */
	public void setRecordTime(Date recordTime) {
		this.recordTime = recordTime;
	}

	/**
	 * 返回appointId的值
	 * @return Integer appointId的值
	 */
	public Integer getAppointId() {
		return appointId;
	}

	/**
	 * 设置appointId的值
	 * @param  appointId appointId的值
	 */
	public void setAppointId(Integer appointId) {
		this.appointId = appointId;
	}

	/**
	 * 返回maxTime的值
	 * @return Date maxTime的值
	 */
	public Date getMaxTime() {
		return maxTime;
	}

	/**
	 * 设置maxTime的值
	 * @param  maxTime maxTime的值
	 */
	public void setMaxTime(Date maxTime) {
		this.maxTime = maxTime;
	}

	/**
	 * 返回graphicTime的值
	 * @return Date graphicTime的值
	 */
	public Date getGraphicTime() {
		return graphicTime;
	}

	/**
	 * 设置graphicTime的值
	 * @param  graphicTime graphicTime的值
	 */
	public void setGraphicTime(Date graphicTime) {
		this.graphicTime = graphicTime;
	}
	
	
}