package com.djb.ylt.user.dtoclient;


import java.util.List;

import com.djb.ylt.framework.dto.BaseClientDTO;





public class PatientClientDTO  extends BaseClientDTO{
	

	private Integer patientId;

    private String name;

    private String birth;

    private String ageUnit;

    private String sex;
    
    private String patientPic;

    private String symptonName;

    private String appointTime;
    		
    private String  symptonDescribe;
    
    private Integer appointId;

    private String recordsType;
    
    private Integer recordsId;

    private String depName;

    private String inquiryStatus;
    
    private String tel;
    
    private String telNum;
    
    private String medicalEatenHistory;

    private String allergyHistory;

    private String illnessHistory;
    
    private List<RecordsClientDTO> recordsList ;
    
    private String payStatus;
    
    private String createTime;
    
    private String userCode;
    
    private String qaTime;
	/**;
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

	/**
	 * 返回name的值
	 * @return String name的值
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置name的值
	 * @param  name name的值
	 */
	public void setName(String name) {
		this.name = name;
	}

	
	/**
	 * 返回ageUnit的值
	 * @return String ageUnit的值
	 */
	public String getAgeUnit() {
		return ageUnit;
	}

	/**
	 * 设置ageUnit的值
	 * @param  ageUnit ageUnit的值
	 */
	public void setAgeUnit(String ageUnit) {
		this.ageUnit = ageUnit;
	}

	/**
	 * 返回sex的值
	 * @return String sex的值
	 */
	public String getSex() {
		return sex;
	}

	/**
	 * 设置sex的值
	 * @param  sex sex的值
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * 返回patientPic的值
	 * @return String patientPic的值
	 */
	public String getPatientPic() {
		return patientPic;
	}

	/**
	 * 设置patientPic的值
	 * @param  patientPic patientPic的值
	 */
	public void setPatientPic(String patientPic) {
		this.patientPic = patientPic;
	}

	/**
	 * 返回symptonName的值
	 * @return String symptonName的值
	 */
	public String getSymptonName() {
		return symptonName;
	}

	/**
	 * 设置symptonName的值
	 * @param  symptonName symptonName的值
	 */
	public void setSymptonName(String symptonName) {
		this.symptonName = symptonName;
	}

	/**
	 * 返回appointTime的值
	 * @return String appointTime的值
	 */
	public String getAppointTime() {
		return appointTime;
	}

	/**
	 * 设置appointTime的值
	 * @param  appointTime appointTime的值
	 */
	public void setAppointTime(String appointTime) {
		this.appointTime = appointTime;
	}

	/**
	 * 返回symptonDescribe的值
	 * @return String symptonDescribe的值
	 */
	public String getSymptonDescribe() {
		return symptonDescribe;
	}

	/**
	 * 设置symptonDescribe的值
	 * @param  symptonDescribe symptonDescribe的值
	 */
	public void setSymptonDescribe(String symptonDescribe) {
		this.symptonDescribe = symptonDescribe;
	}

	
	/**
	 * 返回recordsType的值
	 * @return String recordsType的值
	 */
	public String getRecordsType() {
		return recordsType;
	}

	/**
	 * 设置recordsType的值
	 * @param  recordsType recordsType的值
	 */
	public void setRecordsType(String recordsType) {
		this.recordsType = recordsType;
	}

	
	/**
	 * 返回birth的值
	 * @return String birth的值
	 */
	public String getBirth() {
		return birth;
	}

	/**
	 * 设置birth的值
	 * @param  birth birth的值
	 */
	public void setBirth(String birth) {
		this.birth = birth;
	}

	/**
	 * 返回recordsId的值
	 * @return Integer recordsId的值
	 */
	public Integer getRecordsId() {
		return recordsId;
	}

	/**
	 * 设置recordsId的值
	 * @param  recordsId recordsId的值
	 */
	public void setRecordsId(Integer recordsId) {
		this.recordsId = recordsId;
	}

	/**
	 * 返回depName的值
	 * @return String depName的值
	 */
	public String getDepName() {
		return depName;
	}

	/**
	 * 设置depName的值
	 * @param  depName depName的值
	 */
	public void setDepName(String depName) {
		this.depName = depName;
	}

	/**
	 * 返回inquiryStatus的值
	 * @return String inquiryStatus的值
	 */
	public String getInquiryStatus() {
		return inquiryStatus;
	}

	/**
	 * 设置inquiryStatus的值
	 * @param  inquiryStatus inquiryStatus的值
	 */
	public void setInquiryStatus(String inquiryStatus) {
		this.inquiryStatus = inquiryStatus;
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
	 * 返回recordsList的值
	 * @return List<RecordsClientDTO> recordsList的值
	 */
	public List<RecordsClientDTO> getRecordsList() {
		return recordsList;
	}

	/**
	 * 设置recordsList的值
	 * @param  recordsList recordsList的值
	 */
	public void setRecordsList(List<RecordsClientDTO> recordsList) {
		this.recordsList = recordsList;
	}

	/**
	 * 返回tel的值
	 * @return String tel的值
	 */
	public String getTel() {
		return tel;
	}

	/**
	 * 设置tel的值
	 * @param  tel tel的值
	 */
	public void setTel(String tel) {
		this.tel = tel;
	}

	/**
	 * 返回createTime的值
	 * @return String createTime的值
	 */
	public String getCreateTime() {
		return createTime;
	}

	/**
	 * 设置createTime的值
	 * @param  createTime createTime的值
	 */
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	/**
	 * 返回telNum的值
	 * @return String telNum的值
	 */
	public String getTelNum() {
		return telNum;
	}

	/**
	 * 设置telNum的值
	 * @param  telNum telNum的值
	 */
	public void setTelNum(String telNum) {
		this.telNum = telNum;
	}

	/**
	 * 返回medicalEatenHistory的值
	 * @return String medicalEatenHistory的值
	 */
	public String getMedicalEatenHistory() {
		return medicalEatenHistory;
	}

	/**
	 * 设置medicalEatenHistory的值
	 * @param  medicalEatenHistory medicalEatenHistory的值
	 */
	public void setMedicalEatenHistory(String medicalEatenHistory) {
		this.medicalEatenHistory = medicalEatenHistory;
	}

	/**
	 * 返回allergyHistory的值
	 * @return String allergyHistory的值
	 */
	public String getAllergyHistory() {
		return allergyHistory;
	}

	/**
	 * 设置allergyHistory的值
	 * @param  allergyHistory allergyHistory的值
	 */
	public void setAllergyHistory(String allergyHistory) {
		this.allergyHistory = allergyHistory;
	}

	/**
	 * 返回illnessHistory的值
	 * @return String illnessHistory的值
	 */
	public String getIllnessHistory() {
		return illnessHistory;
	}

	/**
	 * 设置illnessHistory的值
	 * @param  illnessHistory illnessHistory的值
	 */
	public void setIllnessHistory(String illnessHistory) {
		this.illnessHistory = illnessHistory;
	}

	/**
	 * 返回payStatus的值
	 * @return String payStatus的值
	 */
	public String getPayStatus() {
		return payStatus;
	}

	/**
	 * 设置payStatus的值
	 * @param  payStatus payStatus的值
	 */
	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}

	/**
	 * 返回userCode的值
	 * @return String userCode的值
	 */
	public String getUserCode() {
		return userCode;
	}

	/**
	 * 设置userCode的值
	 * @param  userCode userCode的值
	 */
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	/**
	 * 返回qaTime的值
	 * @return String qaTime的值
	 */
	public String getQaTime() {
		return qaTime;
	}

	/**
	 * 设置qaTime的值
	 * @param  qaTime qaTime的值
	 */
	public void setQaTime(String qaTime) {
		this.qaTime = qaTime;
	}


    
}