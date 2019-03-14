package com.djb.ylt.user.dtoclient;


import com.djb.ylt.framework.dto.BaseClientDTO;

public class RecordsDetailInfoDTO extends BaseClientDTO {

	/**
	 * serialVersionUID:用一句话描述这个变量表示什么。
	 * 
	 * 1.医生ID 2.医生头像 3.医生姓名 4.医生科室 5.医生职称名 6.医生简介 7.套餐价格 8.套餐剩余有效天数 9.套餐剩余视频问诊次数
	 * 10.套餐剩余电话问诊次数
	 */


	private Integer patientId;

	private String name;

	private String sex;

	private String birth;

	private String  patientPic;

	private String departmentName;

	private Integer recordsId;

	private Integer appointId;

	private String inquiryStatus;

	private String appointTime;

	private String symptomName;
	
	private String analysis;
	
	private String guidance;
	
	private String symptonDescribe;

	 private String recordsType;
	 
	private String createTime;
	
	private String telNum;
	
	private String inquiryPic;
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
	 * 返回departmentName的值
	 * @return String departmentName的值
	 */
	public String getDepartmentName() {
		return departmentName;
	}

	/**
	 * 设置departmentName的值
	 * @param  departmentName departmentName的值
	 */
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
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
	 * 返回symptomName的值
	 * @return String symptomName的值
	 */
	public String getSymptomName() {
		return symptomName;
	}

	/**
	 * 设置symptomName的值
	 * @param  symptomName symptomName的值
	 */
	public void setSymptomName(String symptomName) {
		this.symptomName = symptomName;
	}

	/**
	 * 返回analysis的值
	 * @return String analysis的值
	 */
	public String getAnalysis() {
		return analysis;
	}

	/**
	 * 设置analysis的值
	 * @param  analysis analysis的值
	 */
	public void setAnalysis(String analysis) {
		this.analysis = analysis;
	}

	/**
	 * 返回guidance的值
	 * @return String guidance的值
	 */
	public String getGuidance() {
		return guidance;
	}

	/**
	 * 设置guidance的值
	 * @param  guidance guidance的值
	 */
	public void setGuidance(String guidance) {
		this.guidance = guidance;
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
	 * 返回inquiryPic的值
	 * @return String inquiryPic的值
	 */
	public String getInquiryPic() {
		return inquiryPic;
	}

	/**
	 * 设置inquiryPic的值
	 * @param  inquiryPic inquiryPic的值
	 */
	public void setInquiryPic(String inquiryPic) {
		this.inquiryPic = inquiryPic;
	}
	
}