package com.djb.ylt.health.entity;

import java.util.Date;
import java.util.List;

import com.djb.ylt.framework.entity.PageModel;
import com.djb.ylt.user.entity.DoctorEntity;

public class SymptomEntity  extends PageModel{
	
	
    /**
	 * serialVersionUID:用一句话描述这个变量表示什么。
	 */
	private static final long serialVersionUID = 1L;

	private Integer symptomId;

    private String symptomName;

    private String statusFlg;

    private String memo;

    private String symptomType;

    private String patientSex;

    private String patientAge;

    private Date createTime;

    private Date updateTime;
    
    private List<DoctorEntity> doctorEntitys;
    
    private Integer  stId;
    
    //参数
    private Integer depId;

    //
    private Float  grade;
    
    private String methodFlg;
    
    private String sortType;
    
    
    
    public Integer getSymptomId() {
        return symptomId;
    }

    public void setSymptomId(Integer symptomId) {
        this.symptomId = symptomId;
    }

    public String getSymptomName() {
        return symptomName;
    }

    public void setSymptomName(String symptomName) {
        this.symptomName = symptomName;
    }

    public String getStatusFlg() {
        return statusFlg;
    }

    public void setStatusFlg(String statusFlg) {
        this.statusFlg = statusFlg;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getSymptomType() {
        return symptomType;
    }

    public void setSymptomType(String symptomType) {
        this.symptomType = symptomType;
    }

    public String getPatientSex() {
        return patientSex;
    }

    public void setPatientSex(String patientSex) {
        this.patientSex = patientSex;
    }

    public String getPatientAge() {
        return patientAge;
    }

    public void setPatientAge(String patientAge) {
        this.patientAge = patientAge;
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
        SymptomEntity other = (SymptomEntity) that;
        return (this.getSymptomId() == null ? other.getSymptomId() == null : this.getSymptomId().equals(other.getSymptomId()))
            && (this.getSymptomName() == null ? other.getSymptomName() == null : this.getSymptomName().equals(other.getSymptomName()))
            && (this.getStatusFlg() == null ? other.getStatusFlg() == null : this.getStatusFlg().equals(other.getStatusFlg()))
            && (this.getMemo() == null ? other.getMemo() == null : this.getMemo().equals(other.getMemo()))
            && (this.getSymptomType() == null ? other.getSymptomType() == null : this.getSymptomType().equals(other.getSymptomType()))
            && (this.getPatientSex() == null ? other.getPatientSex() == null : this.getPatientSex().equals(other.getPatientSex()))
            && (this.getPatientAge() == null ? other.getPatientAge() == null : this.getPatientAge().equals(other.getPatientAge()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getSymptomId() == null) ? 0 : getSymptomId().hashCode());
        result = prime * result + ((getSymptomName() == null) ? 0 : getSymptomName().hashCode());
        result = prime * result + ((getStatusFlg() == null) ? 0 : getStatusFlg().hashCode());
        result = prime * result + ((getMemo() == null) ? 0 : getMemo().hashCode());
        result = prime * result + ((getSymptomType() == null) ? 0 : getSymptomType().hashCode());
        result = prime * result + ((getPatientSex() == null) ? 0 : getPatientSex().hashCode());
        result = prime * result + ((getPatientAge() == null) ? 0 : getPatientAge().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }

	/**
	 * 返回doctorEntitys的值
	 * @return List<DoctorEntity> doctorEntitys的值
	 */
	public List<DoctorEntity> getDoctorEntitys() {
		return doctorEntitys;
	}

	/**
	 * 设置doctorEntitys的值
	 * @param  doctorEntitys doctorEntitys的值
	 */
	public void setDoctorEntitys(List<DoctorEntity> doctorEntitys) {
		this.doctorEntitys = doctorEntitys;
	}

	/**
	 * 返回depId的值
	 * @return Integer depId的值
	 */
	public Integer getDepId() {
		return depId;
	}

	/**
	 * 设置depId的值
	 * @param  depId depId的值
	 */
	public void setDepId(Integer depId) {
		this.depId = depId;
	}

	/**
	 * 返回grade的值
	 * @return Float grade的值
	 */
	public Float getGrade() {
		return grade;
	}

	/**
	 * 设置grade的值
	 * @param  grade grade的值
	 */
	public void setGrade(Float grade) {
		this.grade = grade;
	}

	/**
	 * 返回methodFlg的值
	 * @return String methodFlg的值
	 */
	public String getMethodFlg() {
		return methodFlg;
	}

	/**
	 * 设置methodFlg的值
	 * @param  methodFlg methodFlg的值
	 */
	public void setMethodFlg(String methodFlg) {
		this.methodFlg = methodFlg;
	}

	/**
	 * 返回sortType的值
	 * @return String sortType的值
	 */
	public String getSortType() {
		return sortType;
	}

	/**
	 * 设置sortType的值
	 * @param  sortType sortType的值
	 */
	public void setSortType(String sortType) {
		this.sortType = sortType;
	}

	/**
	 * 返回stId的值
	 * @return Integer stId的值
	 */
	public Integer getStId() {
		return stId;
	}

	/**
	 * 设置stId的值
	 * @param  stId stId的值
	 */
	public void setStId(Integer stId) {
		this.stId = stId;
	}
	
}