package com.djb.ylt.user.entity;

import java.util.Date;

import com.djb.ylt.framework.entity.PageModel;

public class DoctorCommentEntity extends PageModel{
	
	
	
    /**
	 * serialVersionUID:用一句话描述这个变量表示什么。
	 */
	private static final long serialVersionUID = 6703644259965332401L;

	private Integer commentId;

    private Integer doctorId;

    private Integer patientId;

    private Integer recordsId;

    private Float grade;

    private String content;

    private String deleteFlg;

    private String evalationType;

    private Date evalationTime;

    private String anonymousFlag;

    private Date createTime;

    private Date updateTime;

    private PatientEntity patientEntity;
    
    private Integer appointId; 
    
    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
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

    public Integer getRecordsId() {
        return recordsId;
    }

    public void setRecordsId(Integer recordsId) {
        this.recordsId = recordsId;
    }

    public Float getGrade() {
        return grade;
    }

    public void setGrade(Float grade) {
        this.grade = grade;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDeleteFlg() {
        return deleteFlg;
    }

    public void setDeleteFlg(String deleteFlg) {
        this.deleteFlg = deleteFlg;
    }

    public String getEvalationType() {
        return evalationType;
    }

    public void setEvalationType(String evalationType) {
        this.evalationType = evalationType;
    }

    public Date getEvalationTime() {
        return evalationTime;
    }

    public void setEvalationTime(Date evalationTime) {
        this.evalationTime = evalationTime;
    }

    public String getAnonymousFlag() {
        return anonymousFlag;
    }

    public void setAnonymousFlag(String anonymousFlag) {
        this.anonymousFlag = anonymousFlag;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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
        DoctorCommentEntity other = (DoctorCommentEntity) that;
        return (this.getCommentId() == null ? other.getCommentId() == null : this.getCommentId().equals(other.getCommentId()))
            && (this.getDoctorId() == null ? other.getDoctorId() == null : this.getDoctorId().equals(other.getDoctorId()))
            && (this.getPatientId() == null ? other.getPatientId() == null : this.getPatientId().equals(other.getPatientId()))
            && (this.getRecordsId() == null ? other.getRecordsId() == null : this.getRecordsId().equals(other.getRecordsId()))
            && (this.getGrade() == null ? other.getGrade() == null : this.getGrade().equals(other.getGrade()))
            && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()))
            && (this.getDeleteFlg() == null ? other.getDeleteFlg() == null : this.getDeleteFlg().equals(other.getDeleteFlg()))
            && (this.getEvalationType() == null ? other.getEvalationType() == null : this.getEvalationType().equals(other.getEvalationType()))
            && (this.getEvalationTime() == null ? other.getEvalationTime() == null : this.getEvalationTime().equals(other.getEvalationTime()))
            && (this.getAnonymousFlag() == null ? other.getAnonymousFlag() == null : this.getAnonymousFlag().equals(other.getAnonymousFlag()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCommentId() == null) ? 0 : getCommentId().hashCode());
        result = prime * result + ((getDoctorId() == null) ? 0 : getDoctorId().hashCode());
        result = prime * result + ((getPatientId() == null) ? 0 : getPatientId().hashCode());
        result = prime * result + ((getRecordsId() == null) ? 0 : getRecordsId().hashCode());
        result = prime * result + ((getGrade() == null) ? 0 : getGrade().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
        result = prime * result + ((getDeleteFlg() == null) ? 0 : getDeleteFlg().hashCode());
        result = prime * result + ((getEvalationType() == null) ? 0 : getEvalationType().hashCode());
        result = prime * result + ((getEvalationTime() == null) ? 0 : getEvalationTime().hashCode());
        result = prime * result + ((getAnonymousFlag() == null) ? 0 : getAnonymousFlag().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }

	/**
	 * 返回patientEntity的值
	 * @return PatientEntity patientEntity的值
	 */
	public PatientEntity getPatientEntity() {
		return patientEntity;
	}

	/**
	 * 设置patientEntity的值
	 * @param  patientEntity patientEntity的值
	 */
	public void setPatientEntity(PatientEntity patientEntity) {
		this.patientEntity = patientEntity;
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
    
}