package com.djb.ylt.user.entity;

import com.djb.ylt.framework.entity.PageModel;

public class InqueryViewEntity extends PageModel {

	/**
	 * serialVersionUID:用一句话描述这个变量表示什么。
	 */
	private static final long serialVersionUID = -4940652290048723707L;

	private Integer packageId;

	private String name;

	private Integer doctorId;

	private Integer count;

	private String unit;

	private String qaTime;

	private String timeUnit;

	private String positional;

	private String typeId;

	private Integer telCount;

	private String telUnit;

	private Double total;

	private String effectTime;

	private String telTime;

	private String telTimeUnit;

	private String type;

	private Integer departmentId;

	private String grade;

	private String methodFlg;

	private String sortType;

	private Integer symptomId;

	private Float commentGrade;

	private String headPic;

	private Integer serviceCount;

	private String healDisease;

	private String statusFlg;

	private String hospitalName;
	
    private String departmentName;
    
    private String dcName;
    
    private Integer dcId;
    
    private Integer patientId;
    
    private Integer  followCount;
    
    private String depName;
    
    private String  serviceType;
    
    private Integer scheduleFlag;
    
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

	public String getTelUnit() {
		return telUnit;
	}

	public void setTelUnit(String telUnit) {
		this.telUnit = telUnit;
	}

	@Override
	public boolean equals(Object that) {
		if (this == that) {
			return true;
		}
		if (that == null) {
			return false;
		}
		if (getClass() != that.getClass()) {
			return false;
		}
		InqueryViewEntity other = (InqueryViewEntity) that;
		return (this.getPackageId() == null ? other.getPackageId() == null
				: this.getPackageId().equals(other.getPackageId()))
				&& (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
				&& (this.getDoctorId() == null ? other.getDoctorId() == null
						: this.getDoctorId().equals(other.getDoctorId()))
				&& (this.getCount() == null ? other.getCount() == null : this.getCount().equals(other.getCount()))
				&& (this.getUnit() == null ? other.getUnit() == null : this.getUnit().equals(other.getUnit()))
				&& (this.getQaTime() == null ? other.getQaTime() == null : this.getQaTime().equals(other.getQaTime()))
				&& (this.getTimeUnit() == null ? other.getTimeUnit() == null
						: this.getTimeUnit().equals(other.getTimeUnit()))

				&& (this.getTypeId() == null ? other.getTypeId() == null : this.getTypeId().equals(other.getTypeId()))
				&& (this.getTelCount() == null ? other.getTelCount() == null
						: this.getTelCount().equals(other.getTelCount()))
				&& (this.getTelUnit() == null ? other.getTelUnit() == null
						: this.getTelUnit().equals(other.getTelUnit()))
				&& (this.getTotal() == null ? other.getTotal() == null : this.getTotal().equals(other.getTotal()))
				&& (this.getEffectTime() == null ? other.getEffectTime() == null
						: this.getEffectTime().equals(other.getEffectTime()))
				&& (this.getTelTime() == null ? other.getTelTime() == null
						: this.getTelTime().equals(other.getTelTime()))
				&& (this.getTelTimeUnit() == null ? other.getTelTimeUnit() == null
						: this.getTelUnit().equals(other.getTelTimeUnit()));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getPackageId() == null) ? 0 : getPackageId().hashCode());
		result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
		result = prime * result + ((getDoctorId() == null) ? 0 : getDoctorId().hashCode());
		result = prime * result + ((getCount() == null) ? 0 : getCount().hashCode());
		result = prime * result + ((getUnit() == null) ? 0 : getUnit().hashCode());
		result = prime * result + ((getQaTime() == null) ? 0 : getQaTime().hashCode());
		result = prime * result + ((getTimeUnit() == null) ? 0 : getTimeUnit().hashCode());

		result = prime * result + ((getTypeId() == null) ? 0 : getTypeId().hashCode());
		result = prime * result + ((getTelCount() == null) ? 0 : getTelCount().hashCode());
		result = prime * result + ((getTelUnit() == null) ? 0 : getTelUnit().hashCode());
		result = prime * result + ((getTotal() == null) ? 0 : getTotal().hashCode());
		result = prime * result + ((getEffectTime() == null) ? 0 : getEffectTime().hashCode());
		result = prime * result + ((getTelTime() == null) ? 0 : getTelTime().hashCode());
		result = prime * result + ((getTelTimeUnit() == null) ? 0 : getTelTimeUnit().hashCode());
		return result;
	}

	/**
	 * 返回telTime的值
	 * 
	 * @return String telTime的值
	 */
	public String getTelTime() {
		return telTime;
	}

	/**
	 * 设置telTime的值
	 * 
	 * @param telTime
	 *            telTime的值
	 */
	public void setTelTime(String telTime) {
		this.telTime = telTime;
	}

	/**
	 * 返回telTimeUnit的值
	 * 
	 * @return String telTimeUnit的值
	 */
	public String getTelTimeUnit() {
		return telTimeUnit;
	}

	/**
	 * 设置telTimeUnit的值
	 * 
	 * @param telTimeUnit
	 *            telTimeUnit的值
	 */
	public void setTelTimeUnit(String telTimeUnit) {
		this.telTimeUnit = telTimeUnit;
	}

	/**
	 * 返回type的值
	 * 
	 * @return String type的值
	 */
	public String getType() {
		return type;
	}

	/**
	 * 设置type的值
	 * 
	 * @param type
	 *            type的值
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * 返回telCount的值
	 * 
	 * @return Integer telCount的值
	 */
	public Integer getTelCount() {
		return telCount;
	}

	/**
	 * 设置telCount的值
	 * 
	 * @param telCount
	 *            telCount的值
	 */
	public void setTelCount(Integer telCount) {
		this.telCount = telCount;
	}

	/**
	 * 返回effectTime的值
	 * 
	 * @return String effectTime的值
	 */
	public String getEffectTime() {
		return effectTime;
	}

	/**
	 * 设置effectTime的值
	 * 
	 * @param effectTime
	 *            effectTime的值
	 */
	public void setEffectTime(String effectTime) {
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
	 * 返回statusFlg的值
	 * 
	 * @return String statusFlg的值
	 */
	public String getStatusFlg() {
		return statusFlg;
	}

	/**
	 * 设置statusFlg的值
	 * 
	 * @param statusFlg
	 *            statusFlg的值
	 */
	public void setStatusFlg(String statusFlg) {
		this.statusFlg = statusFlg;
	}

	/**
	 * 返回total的值
	 * 
	 * @return Double total的值
	 */
	public Double getTotal() {
		return total;
	}

	/**
	 * 设置total的值
	 * 
	 * @param total
	 *            total的值
	 */
	public void setTotal(Double total) {
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

	/**
	 * 返回dcId的值
	 * @return Integer dcId的值
	 */
	public Integer getDcId() {
		return dcId;
	}

	/**
	 * 设置dcId的值
	 * @param  dcId dcId的值
	 */
	public void setDcId(Integer dcId) {
		this.dcId = dcId;
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

	/**
	 * 返回followCount的值
	 * @return Integer followCount的值
	 */
	public Integer getFollowCount() {
		return followCount;
	}

	/**
	 * 设置followCount的值
	 * @param  followCount followCount的值
	 */
	public void setFollowCount(Integer followCount) {
		this.followCount = followCount;
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