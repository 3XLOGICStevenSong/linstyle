package com.djb.ylt.medicine.dto;

import java.util.Date;

import com.djb.ylt.framework.dto.PageDTO;



public class PrescriptionInfoDTO  extends PageDTO {

    /**
     * 
     */
    private static final long serialVersionUID = 4704413406884565027L;

    private Integer prescription_id;

    private Date prescription_start_time;

    private Date prescription_end_time;

    private String prescription_hospital;

    private String patient_sex;

    private String patient_age;

    private Integer illness_id;

    private String illness_name;

    private String doctor_name;

    private String patient_name;

    private String prescription_memo;
    
    private Integer patientId;

    private Date create_time;

    private Date last_modify_time;

    private Integer create_user;

    private Integer last_modify_user;

    private Integer version;
    
    private String prescription_name;

    public Integer getPrescription_id() {
        return prescription_id;
    }

    public void setPrescription_id(Integer prescription_id) {
        this.prescription_id = prescription_id;
    }

   

    public Date getPrescription_end_time() {
        return prescription_end_time;
    }

    public void setPrescription_end_time(Date prescription_end_time) {
        this.prescription_end_time = prescription_end_time;
    }

    public String getPrescription_hospital() {
        return prescription_hospital;
    }

    public void setPrescription_hospital(String prescription_hospital) {
        this.prescription_hospital = prescription_hospital;
    }

    public String getPatient_sex() {
        return patient_sex;
    }

    public void setPatient_sex(String patient_sex) {
        this.patient_sex = patient_sex;
    }

    public String getPatient_age() {
        return patient_age;
    }

    public void setPatient_age(String patient_age) {
        this.patient_age = patient_age;
    }

    public Integer getIllness_id() {
        return illness_id;
    }

    public void setIllness_id(Integer illness_id) {
        this.illness_id = illness_id;
    }

    public String getIllness_name() {
        return illness_name;
    }

    public void setIllness_name(String illness_name) {
        this.illness_name = illness_name;
    }

    public String getDoctor_name() {
        return doctor_name;
    }

    public void setDoctor_name(String doctor_name) {
        this.doctor_name = doctor_name;
    }

    public String getPatient_name() {
        return patient_name;
    }

    public void setPatient_name(String patient_name) {
        this.patient_name = patient_name;
    }

    public String getPrescription_memo() {
        return prescription_memo;
    }

    public void setPrescription_memo(String prescription_memo) {
        this.prescription_memo = prescription_memo;
    }

    
    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getLast_modify_time() {
        return last_modify_time;
    }

    public void setLast_modify_time(Date last_modify_time) {
        this.last_modify_time = last_modify_time;
    }

    public Integer getCreate_user() {
        return create_user;
    }

    public void setCreate_user(Integer create_user) {
        this.create_user = create_user;
    }

    public Integer getLast_modify_user() {
        return last_modify_user;
    }

    public void setLast_modify_user(Integer last_modify_user) {
        this.last_modify_user = last_modify_user;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getPrescription_name() {
        return prescription_name;
    }

    public void setPrescription_name(String prescription_name) {
        this.prescription_name = prescription_name;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

  

    public Date getPrescription_start_time() {
        return prescription_start_time;
    }

    public void setPrescription_start_time(Date prescription_start_time) {
        this.prescription_start_time = prescription_start_time;
    }

	/**
	 * 返回patientId的值
	 * @return Integer patientId的值
	 */
	public Integer getPatientId() {
		return patientId;
	}

	/**
	 * 设置patientId的值
	 * @param  patientId patientId的值
	 */
	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}


}
