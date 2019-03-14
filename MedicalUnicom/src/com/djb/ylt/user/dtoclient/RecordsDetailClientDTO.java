package com.djb.ylt.user.dtoclient;


import com.djb.ylt.framework.dto.BaseClientDTO;

public class RecordsDetailClientDTO extends BaseClientDTO {

	/**
	 * serialVersionUID:用一句话描述这个变量表示什么。
	 * 
	 * 1.医生ID 2.医生头像 3.医生姓名 4.医生科室 5.医生职称名 6.医生简介 7.套餐价格 8.套餐剩余有效天数 9.套餐剩余视频问诊次数
	 * 10.套餐剩余电话问诊次数
	 */


	private Integer doctorId;

	private String name;

	private String positional;

	private String headPic;

	private String hospitalName;

	private String introduction;

	private String healDisease;

	private Integer serviceCount;

	private String departmentName;

	private Integer recordsId;

	private Integer appointId;

	private String inquiryStatus;

	private String appointTime;

	private String symptomName;
	
	private String analysis;
	
	private String guidance;
	
	private String symptonDescribe;
	
	private String createTime;
	
	private String recordsType;
	
	private Float commentGrade;
	
	private String upFlag;
	
	private Integer scheduleId;
	
	private String commentFlag;
	
	private String dcName;
	/**
	 * 
	 * 返回doctorId的值
	 * @return Integer doctorId的值
	 */
	public Integer getDoctorId() {
		return doctorId;
	}

	/**
	 * 设置doctorId的值
	 * @param  doctorId doctorId的值
	 */
	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
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
	 * 返回positional的值
	 * @return String positional的值
	 */
	public String getPositional() {
		return positional;
	}

	/**
	 * 设置positional的值
	 * @param  positional positional的值
	 */
	public void setPositional(String positional) {
		this.positional = positional;
	}

	/**
	 * 返回headPic的值
	 * @return String headPic的值
	 */
	public String getHeadPic() {
		return headPic;
	}

	/**
	 * 设置headPic的值
	 * @param  headPic headPic的值
	 */
	public void setHeadPic(String headPic) {
		this.headPic = headPic;
	}

	/**
	 * 返回hospitalName的值
	 * @return String hospitalName的值
	 */
	public String getHospitalName() {
		return hospitalName;
	}

	/**
	 * 设置hospitalName的值
	 * @param  hospitalName hospitalName的值
	 */
	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	/**
	 * 返回introduction的值
	 * @return String introduction的值
	 */
	public String getIntroduction() {
		return introduction;
	}

	/**
	 * 设置introduction的值
	 * @param  introduction introduction的值
	 */
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	/**
	 * 返回healDisease的值
	 * @return String healDisease的值
	 */
	public String getHealDisease() {
		return healDisease;
	}

	/**
	 * 设置healDisease的值
	 * @param  healDisease healDisease的值
	 */
	public void setHealDisease(String healDisease) {
		this.healDisease = healDisease;
	}

	/**
	 * 返回serviceCount的值
	 * @return Integer serviceCount的值
	 */
	public Integer getServiceCount() {
		return serviceCount;
	}

	/**
	 * 设置serviceCount的值
	 * @param  serviceCount serviceCount的值
	 */
	public void setServiceCount(Integer serviceCount) {
		this.serviceCount = serviceCount;
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
	 * 返回commentGrade的值
	 * @return Float commentGrade的值
	 */
	public Float getCommentGrade() {
		return commentGrade;
	}

	/**
	 * 设置commentGrade的值
	 * @param  commentGrade commentGrade的值
	 */
	public void setCommentGrade(Float commentGrade) {
		this.commentGrade = commentGrade;
	}

	/**
	 * 返回upFlag的值
	 * @return String upFlag的值
	 */
	public String getUpFlag() {
		return upFlag;
	}

	/**
	 * 设置upFlag的值
	 * @param  upFlag upFlag的值
	 */
	public void setUpFlag(String upFlag) {
		this.upFlag = upFlag;
	}

	/**
	 * 返回scheduleId的值
	 * @return Integer scheduleId的值
	 */
	public Integer getScheduleId() {
		return scheduleId;
	}

	/**
	 * 设置scheduleId的值
	 * @param  scheduleId scheduleId的值
	 */
	public void setScheduleId(Integer scheduleId) {
		this.scheduleId = scheduleId;
	}

	/**
	 * 返回commentFlag的值
	 * @return String commentFlag的值
	 */
	public String getCommentFlag() {
		return commentFlag;
	}

	/**
	 * 设置commentFlag的值
	 * @param  commentFlag commentFlag的值
	 */
	public void setCommentFlag(String commentFlag) {
		this.commentFlag = commentFlag;
	}

	/**
	 * 返回dcName的值
	 * @return String dcName的值
	 */
	public String getDcName() {
		return dcName;
	}

	/**
	 * 设置dcName的值
	 * @param  dcName dcName的值
	 */
	public void setDcName(String dcName) {
		this.dcName = dcName;
	}
	
	
}