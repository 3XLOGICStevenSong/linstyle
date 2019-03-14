package com.djb.ylt.docFollow.dto;

import java.util.List;

import com.djb.ylt.framework.dto.BaseDTO;

public class DocFollowDTO   extends BaseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4739402585277050484L;

	private Integer fId;

    private Integer patientId;

    private Integer doctorId;

    private String fFlag;

    private String methodFlg;

	private String sortType;
	
	private String patientTel;
	
	private List<FollowDocDTO> doctorList;

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

	public List<FollowDocDTO> getDoctorList() {
		return doctorList;
	}

	public void setDoctorList(List<FollowDocDTO> doctorList) {
		this.doctorList = doctorList;
	}

	public String getPatientTel() {
		return patientTel;
	}

	public void setPatientTel(String patientTel) {
		this.patientTel = patientTel;
	}
}
