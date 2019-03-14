package com.djb.ylt.user.dtoclient;

import java.util.Date;

import com.djb.ylt.framework.dto.BaseClientDTO;



public class AppointInquiryClientDTO extends BaseClientDTO{
	
    /**
	 * serialVersionUID:用一句话描述这个变量表示什么。
	 */


	private Integer appointId;

    private Integer patientId;

    private Integer doctorId;

    private Integer packageId;

    private Integer depClassId;

    private Integer depId;

    private String symptonIdList;

    private Date createTime;

    public Integer getAppointId() {
        return appointId;
    }

    public void setAppointId(Integer appointId) {
        this.appointId = appointId;
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

    public Integer getPackageId() {
        return packageId;
    }

    public void setPackageId(Integer packageId) {
        this.packageId = packageId;
    }

    public Integer getDepClassId() {
        return depClassId;
    }

    public void setDepClassId(Integer depClassId) {
        this.depClassId = depClassId;
    }

    public Integer getDepId() {
        return depId;
    }

    public void setDepId(Integer depId) {
        this.depId = depId;
    }

    public String getSymptonIdList() {
        return symptonIdList;
    }

    public void setSymptonIdList(String symptonIdList) {
        this.symptonIdList = symptonIdList;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }



}