package com.djb.ylt.user.dto;

import com.djb.ylt.framework.dto.PageDTO;


public class FollowInterestDTO extends PageDTO{
    /**
	 * serialVersionUID:用一句话描述这个变量表示什么。
	 */
	private static final long serialVersionUID = -6448697843249831491L;

	private Integer fId;

    private Integer patientId;

    private Integer doctorId;

    private String fFlag;

    private String methodFlg;

	private String sortType;
	
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

	/**
	 * 返回methodFlg的值
	 * @return String methodFlg的值
	 */
	public String getMethodFlg() {
		return methodFlg;
	}

	/**
	 * 设置methodFlg的值
	 * @param  methodFlg methodFlg的值
	 */
	public void setMethodFlg(String methodFlg) {
		this.methodFlg = methodFlg;
	}

	/**
	 * 返回sortType的值
	 * @return String sortType的值
	 */
	public String getSortType() {
		return sortType;
	}

	/**
	 * 设置sortType的值
	 * @param  sortType sortType的值
	 */
	public void setSortType(String sortType) {
		this.sortType = sortType;
	}

   
}