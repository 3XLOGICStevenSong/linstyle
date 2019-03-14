package com.djb.ylt.user.entity;

import java.util.Date;
import java.util.List;

import com.djb.ylt.framework.entity.BaseEntity;

public class InterrogationPackageEntity extends BaseEntity {

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

	private Date createTime;

	private Date updateTiem;

	private String typeId;

	private Integer telCount;

	private String telUnit;

	private Double total;

	private String effectTime;

	private String telTime;

	private String telTimeUnit;

	private String type;

	private List<QaTypeEntity> qaTypeEntity;

	private DoctorEntity doctorEntity;

	private String workType;

	private String earlyTime;

	private Double dayTotal;

	private Double nightTotal;
	

	
	private Double minTotal;
	
	private Integer freeTotal;
   
	public Double getMinTotal() {
		return minTotal;
	}

	public void setMinTotal(Double minTotal) {
		this.minTotal = minTotal;
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
		InterrogationPackageEntity other = (InterrogationPackageEntity) that;
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
				&& (this.getCreateTime() == null ? other.getCreateTime() == null
						: this.getCreateTime().equals(other.getCreateTime()))
				&& (this.getUpdateTiem() == null ? other.getUpdateTiem() == null
						: this.getUpdateTiem().equals(other.getUpdateTiem()))
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
		result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
		result = prime * result + ((getUpdateTiem() == null) ? 0 : getUpdateTiem().hashCode());
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
	 * 返回qaTypeEntity的值
	 * 
	 * @return List<QaTypeEntity> qaTypeEntity的值
	 */
	public List<QaTypeEntity> getQaTypeEntity() {
		return qaTypeEntity;
	}

	/**
	 * 设置qaTypeEntity的值
	 * 
	 * @param qaTypeEntity
	 *            qaTypeEntity的值
	 */
	public void setQaTypeEntity(List<QaTypeEntity> qaTypeEntity) {
		this.qaTypeEntity = qaTypeEntity;
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
	 * 返回doctorEntity的值
	 * 
	 * @return DoctorEntity doctorEntity的值
	 */
	public DoctorEntity getDoctorEntity() {
		return doctorEntity;
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
	 * 设置doctorEntity的值
	 * 
	 * @param doctorEntity
	 *            doctorEntity的值
	 */
	public void setDoctorEntity(DoctorEntity doctorEntity) {
		this.doctorEntity = doctorEntity;
	}

	/**
	 * 返回workType的值
	 * 
	 * @return String workType的值
	 */
	public String getWorkType() {
		return workType;
	}

	/**
	 * 设置workType的值
	 * 
	 * @param workType
	 *            workType的值
	 */
	public void setWorkType(String workType) {
		this.workType = workType;
	}

	/**
	 * 返回earlyTime的值
	 * 
	 * @return String earlyTime的值
	 */
	public String getEarlyTime() {
		return earlyTime;
	}

	/**
	 * 设置earlyTime的值
	 * 
	 * @param earlyTime
	 *            earlyTime的值
	 */
	public void setEarlyTime(String earlyTime) {
		this.earlyTime = earlyTime;
	}

	/**
	 * 返回dayTotal的值
	 * 
	 * @return Double dayTotal的值
	 */
	public Double getDayTotal() {
		return dayTotal;
	}

	/**
	 * 设置dayTotal的值
	 * 
	 * @param dayTotal
	 *            dayTotal的值
	 */
	public void setDayTotal(Double dayTotal) {
		this.dayTotal = dayTotal;
	}

	/**
	 * 返回nightTotal的值
	 * 
	 * @return Double nightTotal的值
	 */
	public Double getNightTotal() {
		return nightTotal;
	}

	/**
	 * 设置nightTotal的值
	 * 
	 * @param nightTotal
	 *            nightTotal的值
	 */
	public void setNightTotal(Double nightTotal) {
		this.nightTotal = nightTotal;
	}

	/**
	 * 返回freeTotal的值
	 * @return Integer freeTotal的值
	 */
	public Integer getFreeTotal() {
		return freeTotal;
	}

	/**
	 * 设置freeTotal的值
	 * @param  freeTotal freeTotal的值
	 */
	public void setFreeTotal(Integer freeTotal) {
		this.freeTotal = freeTotal;
	}

}