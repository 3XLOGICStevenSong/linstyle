package com.djb.ylt.user.entity;

import java.util.Date;


import com.djb.ylt.framework.entity.PageModel;



public class PatientEntity  extends  PageModel{
	
	
    /**
	 * serialVersionUID:用一句话描述这个变量表示什么。
	 */
	private static final long serialVersionUID = -63018386562139328L;

	private Integer patientId;
	
	private Integer appointId;

    private String name;

    private Date birth;

    private String ageUnit;

    private String sex;

    private String marry;

    private String address;

    private String bt;

    private Integer userId;

    private String medicalEatenHistory;

    private String allergyHistory;

    private String illnessHistory;

    private String amt;

    private String patientPic;

    private String email;

    private Date createTime;

    private Date updateTime;
    
    private String nickName;
    
    private String patientTel;

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getAgeUnit() {
        return ageUnit;
    }

    public void setAgeUnit(String ageUnit) {
        this.ageUnit = ageUnit;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getMarry() {
        return marry;
    }

    public void setMarry(String marry) {
        this.marry = marry;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBt() {
        return bt;
    }

    public void setBt(String bt) {
        this.bt = bt;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getMedicalEatenHistory() {
        return medicalEatenHistory;
    }

    public void setMedicalEatenHistory(String medicalEatenHistory) {
        this.medicalEatenHistory = medicalEatenHistory;
    }

    public String getAllergyHistory() {
        return allergyHistory;
    }

    public void setAllergyHistory(String allergyHistory) {
        this.allergyHistory = allergyHistory;
    }

    public String getIllnessHistory() {
        return illnessHistory;
    }

    public void setIllnessHistory(String illnessHistory) {
        this.illnessHistory = illnessHistory;
    }

    public String getAmt() {
        return amt;
    }

    public void setAmt(String amt) {
        this.amt = amt;
    }

    public String getPatientPic() {
        return patientPic;
    }

    public void setPatientPic(String patientPic) {
        this.patientPic = patientPic;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

	/**
	 * 返回nickName的值
	 * @return String nickName的值
	 */
	public String getNickName() {
		return nickName;
	}

	/**
	 * 设置nickName的值
	 * @param  nickName nickName的值
	 */
	public void setNickName(String nickName) {
		this.nickName = nickName;
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
        PatientEntity other = (PatientEntity) that;
        return (this.getPatientId() == null ? other.getPatientId() == null : this.getPatientId().equals(other.getPatientId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getBirth() == null ? other.getBirth() == null : this.getBirth().equals(other.getBirth()))
            && (this.getAgeUnit() == null ? other.getAgeUnit() == null : this.getAgeUnit().equals(other.getAgeUnit()))
            && (this.getSex() == null ? other.getSex() == null : this.getSex().equals(other.getSex()))
            && (this.getMarry() == null ? other.getMarry() == null : this.getMarry().equals(other.getMarry()))
            && (this.getAddress() == null ? other.getAddress() == null : this.getAddress().equals(other.getAddress()))
            && (this.getBt() == null ? other.getBt() == null : this.getBt().equals(other.getBt()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getMedicalEatenHistory() == null ? other.getMedicalEatenHistory() == null : this.getMedicalEatenHistory().equals(other.getMedicalEatenHistory()))
            && (this.getAllergyHistory() == null ? other.getAllergyHistory() == null : this.getAllergyHistory().equals(other.getAllergyHistory()))
            && (this.getIllnessHistory() == null ? other.getIllnessHistory() == null : this.getIllnessHistory().equals(other.getIllnessHistory()))
            && (this.getAmt() == null ? other.getAmt() == null : this.getAmt().equals(other.getAmt()))
            && (this.getPatientPic() == null ? other.getPatientPic() == null : this.getPatientPic().equals(other.getPatientPic()))
            && (this.getEmail() == null ? other.getEmail() == null : this.getEmail().equals(other.getEmail()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getPatientId() == null) ? 0 : getPatientId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getBirth() == null) ? 0 : getBirth().hashCode());
        result = prime * result + ((getAgeUnit() == null) ? 0 : getAgeUnit().hashCode());
        result = prime * result + ((getSex() == null) ? 0 : getSex().hashCode());
        result = prime * result + ((getMarry() == null) ? 0 : getMarry().hashCode());
        result = prime * result + ((getAddress() == null) ? 0 : getAddress().hashCode());
        result = prime * result + ((getBt() == null) ? 0 : getBt().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getMedicalEatenHistory() == null) ? 0 : getMedicalEatenHistory().hashCode());
        result = prime * result + ((getAllergyHistory() == null) ? 0 : getAllergyHistory().hashCode());
        result = prime * result + ((getIllnessHistory() == null) ? 0 : getIllnessHistory().hashCode());
        result = prime * result + ((getAmt() == null) ? 0 : getAmt().hashCode());
        result = prime * result + ((getPatientPic() == null) ? 0 : getPatientPic().hashCode());
        result = prime * result + ((getEmail() == null) ? 0 : getEmail().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }

	/**
	 * 返回patientTel的值
	 * @return String patientTel的值
	 */
	public String getPatientTel() {
		return patientTel;
	}

	/**
	 * 设置patientTel的值
	 * @param  patientTel patientTel的值
	 */
	public void setPatientTel(String patientTel) {
		this.patientTel = patientTel;
	}

	public Integer getAppointId() {
		return appointId;
	}

	public void setAppointId(Integer appointId) {
		this.appointId = appointId;
	}

}