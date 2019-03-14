package com.djb.ylt.user.dtoclient;

import java.util.Date;

import com.djb.ylt.framework.dto.BaseClientDTO;

public class InqueryViewClientDTO extends BaseClientDTO {

	/**
	 * serialVersionUID:用一句话描述这个变量表示什么。
	 */

	private Integer packageId;

	private String name;

	private Integer doctorId;

	private Integer count;

	private String unit;

	private String qaTime;

	private String timeUnit;

	private String typeId;

	private Byte telCount;

	private String telUnit;

	private String total;

	private Date effectTime;

	private String positional;

	private String headPic;

	private Integer serviceCount;

	private String grade;

	private Integer departmentId;

	private String healDisease;

	private Integer symptomId;

	private Float commentGrade;

	private String hospitalName;

	private String departmentName;

	private String dcName;

	private String followFlag;

	private String minTotal;

	private Integer scheduleFlag;

	private String serviceType;

	private String departmentClassName;
	

	
	

	// 是否关注
	public int getAppointCount() {
		return appointCount;
	}

	public void setAppointCount(int appointCount) {
		this.appointCount = appointCount;
	}

	private int appointCount;

	private String appointTime;

	public String getAppointTime() {
		return appointTime;
	}

	public void setAppointTime(String appointTime) {
		this.appointTime = appointTime;
	}

	public String getDepartmentClassName() {
		return departmentClassName;
	}

	public void setDepartmentClassName(String departmentClassName) {
		this.departmentClassName = departmentClassName;
	}

	public Integer getPackageId() {
		return packageId;
	}

	public void setPackageId(Integer packageId) {
		this.packageId = packageId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getQaTime() {
		return qaTime;
	}

	public void setQaTime(String qaTime) {
		this.qaTime = qaTime;
	}

	public String getTimeUnit() {
		return timeUnit;
	}

	public void setTimeUnit(String timeUnit) {
		this.timeUnit = timeUnit;
	}

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public Byte getTelCount() {
		return telCount;
	}

	public void setTelCount(Byte telCount) {
		this.telCount = telCount;
	}

	public String getTelUnit() {
		return telUnit;
	}

	public void setTelUnit(String telUnit) {
		this.telUnit = telUnit;
	}

	public Date getEffectTime() {
		return effectTime;
	}

	public void setEffectTime(Date effectTime) {
		this.effectTime = effectTime;
	}

	/**
	 * 返回symptomId的值
	 * 
	 * @return Integer symptomId的值
	 */
	public Integer getSymptomId() {
		return symptomId;
	}

	/**
	 * 设置symptomId的值
	 * 
	 * @param symptomId
	 *            symptomId的值
	 */
	public void setSymptomId(Integer symptomId) {
		this.symptomId = symptomId;
	}

	/**
	 * 返回grade的值
	 * 
	 * @return String grade的值
	 */
	public String getGrade() {
		return grade;
	}

	/**
	 * 设置grade的值
	 * 
	 * @param grade
	 *            grade的值
	 */
	public void setGrade(String grade) {
		this.grade = grade;
	}

	/**
	 * 返回commentGrade的值
	 * 
	 * @return Float commentGrade的值
	 */
	public Float getCommentGrade() {
		return commentGrade;
	}

	/**
	 * 设置commentGrade的值
	 * 
	 * @param commentGrade
	 *            commentGrade的值
	 */
	public void setCommentGrade(Float commentGrade) {
		this.commentGrade = commentGrade;
	}

	/**
	 * 返回positional的值
	 * 
	 * @return String positional的值
	 */
	public String getPositional() {
		return positional;
	}

	/**
	 * 设置positional的值
	 * 
	 * @param positional
	 *            positional的值
	 */
	public void setPositional(String positional) {
		this.positional = positional;
	}

	/**
	 * 返回headPic的值
	 * 
	 * @return String headPic的值
	 */
	public String getHeadPic() {
		return headPic;
	}

	/**
	 * 设置headPic的值
	 * 
	 * @param headPic
	 *            headPic的值
	 */
	public void setHeadPic(String headPic) {
		this.headPic = headPic;
	}

	/**
	 * 返回serviceCount的值
	 * 
	 * @return Integer serviceCount的值
	 */
	public Integer getServiceCount() {
		return serviceCount;
	}

	/**
	 * 设置serviceCount的值
	 * 
	 * @param serviceCount
	 *            serviceCount的值
	 */
	public void setServiceCount(Integer serviceCount) {
		this.serviceCount = serviceCount;
	}

	/**
	 * 返回departmentId的值
	 * 
	 * @return Integer departmentId的值
	 */
	public Integer getDepartmentId() {
		return departmentId;
	}

	/**
	 * 设置departmentId的值
	 * 
	 * @param departmentId
	 *            departmentId的值
	 */
	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	/**
	 * 返回healDisease的值
	 * 
	 * @return String healDisease的值
	 */
	public String getHealDisease() {
		return healDisease;
	}

	/**
	 * 设置healDisease的值
	 * 
	 * @param healDisease
	 *            healDisease的值
	 */
	public void setHealDisease(String healDisease) {
		this.healDisease = healDisease;
	}

	/**
	 * 设置total的值
	 * 
	 * @param total
	 *            total的值
	 */
	public void setTotal(String total) {
		this.total = total;
	}

	/**
	 * 返回hospitalName的值
	 * 
	 * @return String hospitalName的值
	 */
	public String getHospitalName() {
		return hospitalName;
	}

	/**
	 * 设置hospitalName的值
	 * 
	 * @param hospitalName
	 *            hospitalName的值
	 */
	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	/**
	 * 返回total的值
	 * 
	 * @return String total的值
	 */
	public String getTotal() {
		return total;
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
	 * 返回minTotal的值
	 * 
	 * @return String minTotal的值
	 */
	public String getMinTotal() {
		return minTotal;
	}

	/**
	 * 设置minTotal的值
	 * 
	 * @param minTotal
	 *            minTotal的值
	 */
	public void setMinTotal(String minTotal) {
		this.minTotal = minTotal;
	}

	/**
	 * 返回scheduleFlag的值
	 * 
	 * @return Integer scheduleFlag的值
	 */
	public Integer getScheduleFlag() {
		return scheduleFlag;
	}

	/**
	 * 设置scheduleFlag的值
	 * 
	 * @param scheduleFlag
	 *            scheduleFlag的值
	 */
	public void setScheduleFlag(Integer scheduleFlag) {
		this.scheduleFlag = scheduleFlag;
	}

	/**
	 * 返回serviceType的值
	 * 
	 * @return String serviceType的值
	 */
	public String getServiceType() {
		return serviceType;
	}

	/**
	 * 设置serviceType的值
	 * 
	 * @param serviceType
	 *            serviceType的值
	 */
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
}