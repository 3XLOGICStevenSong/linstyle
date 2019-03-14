package com.djb.ylt.docFollow.entity;

import com.djb.ylt.framework.entity.PageModel;
import com.djb.ylt.user.entity.PatientEntity;

public class DocFollowEntity  extends PageModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1153939949501472489L;
	
	private Integer concernId;

	private Integer fId;

    private Integer patientId;

    private Integer doctorId;

    private String fFlag;
    
    private PatientEntity patientEntity;
    
    private Float commentGrade;
    
    private String methodFlg;

	private String sortType;
	
	private Double minTotal;
	
	private String followFlag;
	
	private Integer appointId;

	public Integer getfId() {
		return fId;
	}

	public void setfId(Integer fId) {
		this.fId = fId;
	}

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public Integer getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}

	public String getfFlag() {
		return fFlag;
	}

	public void setfFlag(String fFlag) {
		this.fFlag = fFlag;
	}

	public Float getCommentGrade() {
		return commentGrade;
	}

	public void setCommentGrade(Float commentGrade) {
		this.commentGrade = commentGrade;
	}

	public String getMethodFlg() {
		return methodFlg;
	}

	public void setMethodFlg(String methodFlg) {
		this.methodFlg = methodFlg;
	}

	public String getSortType() {
		return sortType;
	}

	public void setSortType(String sortType) {
		this.sortType = sortType;
	}

	public Double getMinTotal() {
		return minTotal;
	}

	public void setMinTotal(Double minTotal) {
		this.minTotal = minTotal;
	}

	public PatientEntity getPaitentEntity() {
		return patientEntity;
	}

	public void setPaitentEntity(PatientEntity patientEntity) {
		this.patientEntity = patientEntity;
	}

	public Integer getConcernId() {
		return concernId;
	}

	public void setConcernId(Integer concernId) {
		this.concernId = concernId;
	}
	
	public String getFollowFlag() {
		return followFlag;
	}

	public void setFollowFlag(String followFlag) {
		this.followFlag = followFlag;
	}

	public Integer getAppointId() {
		return appointId;
	}

	public void setAppointId(Integer appointId) {
		this.appointId = appointId;
	}
}
