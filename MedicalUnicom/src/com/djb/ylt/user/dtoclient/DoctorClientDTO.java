package com.djb.ylt.user.dtoclient;

import com.djb.ylt.framework.dto.BaseClientDTO;

public class DoctorClientDTO extends BaseClientDTO {

	private Integer doctorId;

	private Integer userId;

	private String name;

	private String sex;

	private String age;

	private String positional;

	private String cardNum;

	private String certificateNum;

	private String certificatePic;

	private String headPic;

	private String hospitalName;

	private String introduction;

	private String bankOwner;

	private String bankNum;

	private String bankName;

	private String healDisease;

	private Integer serviceCount;

	private String grade;
	private String symptomName;
	private String departmentId;

	private String departmentName;

	private String dcName;

	private Integer dcId;

	private String followFlag;

	private Integer followCount;

	private String minTotal;
	
	private Float commentGrade;
	
	private Integer scheduleFlag;
	
	private String serviceType;

	public Integer getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPositional() {
		return positional;
	}

	public void setPositional(String positional) {
		this.positional = positional;
	}

	public String getCardNum() {
		return cardNum;
	}

	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}

	public String getCertificateNum() {
		return certificateNum;
	}

	public void setCertificateNum(String certificateNum) {
		this.certificateNum = certificateNum;
	}

	public String getCertificatePic() {
		return certificatePic;
	}

	public void setCertificatePic(String certificatePic) {
		this.certificatePic = certificatePic;
	}

	public String getHeadPic() {
		return headPic;
	}

	public void setHeadPic(String headPic) {
		this.headPic = headPic;
	}

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getBankOwner() {
		return bankOwner;
	}

	public void setBankOwner(String bankOwner) {
		this.bankOwner = bankOwner;
	}

	public String getBankNum() {
		return bankNum;
	}

	public void setBankNum(String bankNum) {
		this.bankNum = bankNum;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getHealDisease() {
		return healDisease;
	}

	public void setHealDisease(String healDisease) {
		this.healDisease = healDisease;
	}

	public Integer getServiceCount() {
		return serviceCount;
	}

	public void setServiceCount(Integer serviceCount) {
		this.serviceCount = serviceCount;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	/**
	 * 返回symptomName的值
	 * 
	 * @return String symptomName的值
	 */
	public String getSymptomName() {
		return symptomName;
	}

	/**
	 * 设置symptomName的值
	 * 
	 * @param symptomName
	 *            symptomName的值
	 */
	public void setSymptomName(String symptomName) {
		this.symptomName = symptomName;
	}

	/**
	 * 返回departmentName的值
	 * 
	 * @return String departmentName的值
	 */
	public String getDepartmentName() {
		return departmentName;
	}

	/**
	 * 设置departmentName的值
	 * 
	 * @param departmentName
	 *            departmentName的值
	 */
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	/**
	 * 返回age的值
	 * 
	 * @return String age的值
	 */
	public String getAge() {
		return age;
	}

	/**
	 * 设置age的值
	 * 
	 * @param age
	 *            age的值
	 */
	public void setAge(String age) {
		this.age = age;
	}

	/**
	 * 返回dcName的值
	 * 
	 * @return String dcName的值
	 */
	public String getDcName() {
		return dcName;
	}

	/**
	 * 设置dcName的值
	 * 
	 * @param dcName
	 *            dcName的值
	 */
	public void setDcName(String dcName) {
		this.dcName = dcName;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public Integer getDcId() {
		return dcId;
	}

	public void setDcId(Integer dcId) {
		this.dcId = dcId;
	}

	/**
	 * 返回followFlag的值
	 * 
	 * @return String followFlag的值
	 */
	public String getFollowFlag() {
		return followFlag;
	}

	/**
	 * 设置followFlag的值
	 * 
	 * @param followFlag
	 *            followFlag的值
	 */
	public void setFollowFlag(String followFlag) {
		this.followFlag = followFlag;
	}

	/**
	 * 返回followCount的值
	 * 
	 * @return Integer followCount的值
	 */
	public Integer getFollowCount() {
		return followCount;
	}

	/**
	 * 设置followCount的值
	 * 
	 * @param followCount
	 *            followCount的值
	 */
	public void setFollowCount(Integer followCount) {
		this.followCount = followCount;
	}

	/**
	 * 返回minTotal的值
	 * @return String minTotal的值
	 */
	public String getMinTotal() {
		return minTotal;
	}

	/**
	 * 设置minTotal的值
	 * @param  minTotal minTotal的值
	 */
	public void setMinTotal(String minTotal) {
		this.minTotal = minTotal;
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
	 * 返回serviceType的值
	 * @return String serviceType的值
	 */
	public String getServiceType() {
		return serviceType;
	}

	/**
	 * 设置serviceType的值
	 * @param  serviceType serviceType的值
	 */
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	/**
	 * 返回scheduleFlag的值
	 * @return Integer scheduleFlag的值
	 */
	public Integer getScheduleFlag() {
		return scheduleFlag;
	}

	/**
	 * 设置scheduleFlag的值
	 * @param  scheduleFlag scheduleFlag的值
	 */
	public void setScheduleFlag(Integer scheduleFlag) {
		this.scheduleFlag = scheduleFlag;
	}

}