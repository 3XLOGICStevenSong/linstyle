package com.djb.ylt.user.dto;

import java.util.Date;



import com.djb.ylt.framework.dto.PageDTO;


public class InqueryViewDTO extends PageDTO {

	/**
	 * serialVersionUID:用一句话描述这个变量表示什么。
	 */
	private static final long serialVersionUID = 1L;

	private Integer packageId;

	private String name;

	private Integer doctorId;

	private Integer count;

	private String unit;

	private String qaTime;

	private String timeUnit;

	private Date createTime;

	private Date updateTiem;

	private String typeId;

	private Byte telCount;

	private String telUnit;

	private Integer total;

	private Date effectTime;
	
	private String positional;
	
	private String headPic;
	
	private Integer serviceCount;

	private String grade;

	private Integer departmentId;
	
	private String healDisease;
	
	private String statusFlg;

	private String type;

	private String methodFlg;

	private String sortType;

	private Integer symptomId;

	private Float commentGrade;
	
	private Integer patientId;

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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTiem() {
		return updateTiem;
	}

	public void setUpdateTiem(Date updateTiem) {
		this.updateTiem = updateTiem;
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

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Date getEffectTime() {
		return effectTime;
	}

	public void setEffectTime(Date effectTime) {
		this.effectTime = effectTime;
	}




	/**
	 * 返回methodFlg的值
	 * 
	 * @return String methodFlg的值
	 */
	public String getMethodFlg() {
		return methodFlg;
	}

	/**
	 * 设置methodFlg的值
	 * 
	 * @param methodFlg
	 *            methodFlg的值
	 */
	public void setMethodFlg(String methodFlg) {
		this.methodFlg = methodFlg;
	}

	/**
	 * 返回sortType的值
	 * 
	 * @return String sortType的值
	 */
	public String getSortType() {
		return sortType;
	}

	/**
	 * 设置sortType的值
	 * 
	 * @param sortType
	 *            sortType的值
	 */
	public void setSortType(String sortType) {
		this.sortType = sortType;
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
	 * 返回departmentId的值
	 * @return Integer departmentId的值
	 */
	public Integer getDepartmentId() {
		return departmentId;
	}

	/**
	 * 设置departmentId的值
	 * @param  departmentId departmentId的值
	 */
	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
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
	 * 返回statusFlg的值
	 * @return String statusFlg的值
	 */
	public String getStatusFlg() {
		return statusFlg;
	}

	/**
	 * 设置statusFlg的值
	 * @param  statusFlg statusFlg的值
	 */
	public void setStatusFlg(String statusFlg) {
		this.statusFlg = statusFlg;
	}

	/**
	 * 返回type的值
	 * @return String type的值
	 */
	public String getType() {
		return type;
	}

	/**
	 * 设置type的值
	 * @param  type type的值
	 */
	public void setType(String type) {
		this.type = type;
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