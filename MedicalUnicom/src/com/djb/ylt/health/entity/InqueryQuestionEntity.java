package com.djb.ylt.health.entity;

import java.util.Date;
import java.util.List;

public class InqueryQuestionEntity {
	
    private Integer iqId;

    private Integer depId;

    private String iqName;

    private String iqType;

    private String iqStatus;

    private Date createTime;

    private String iqMemo;
    
    private Integer patientId;
    
    private List<InqueryResultEntity>  inqueryResultEntitys;
    
    private List<InqueryAnswerEntity>  inqueryAnswerEntitys;

    public Integer getIqId() {
        return iqId;
    }

    public void setIqId(Integer iqId) {
        this.iqId = iqId;
    }

    public Integer getDepId() {
        return depId;
    }

    public void setDepId(Integer depId) {
        this.depId = depId;
    }

    public String getIqName() {
        return iqName;
    }

    public void setIqName(String iqName) {
        this.iqName = iqName;
    }

    public String getIqType() {
        return iqType;
    }

    public void setIqType(String iqType) {
        this.iqType = iqType;
    }

    public String getIqStatus() {
        return iqStatus;
    }

    public void setIqStatus(String iqStatus) {
        this.iqStatus = iqStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getIqMemo() {
        return iqMemo;
    }

    public void setIqMemo(String iqMemo) {
        this.iqMemo = iqMemo;
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
        InqueryQuestionEntity other = (InqueryQuestionEntity) that;
        return (this.getIqId() == null ? other.getIqId() == null : this.getIqId().equals(other.getIqId()))
            && (this.getDepId() == null ? other.getDepId() == null : this.getDepId().equals(other.getDepId()))
            && (this.getIqName() == null ? other.getIqName() == null : this.getIqName().equals(other.getIqName()))
            && (this.getIqType() == null ? other.getIqType() == null : this.getIqType().equals(other.getIqType()))
            && (this.getIqStatus() == null ? other.getIqStatus() == null : this.getIqStatus().equals(other.getIqStatus()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getIqMemo() == null ? other.getIqMemo() == null : this.getIqMemo().equals(other.getIqMemo()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getIqId() == null) ? 0 : getIqId().hashCode());
        result = prime * result + ((getDepId() == null) ? 0 : getDepId().hashCode());
        result = prime * result + ((getIqName() == null) ? 0 : getIqName().hashCode());
        result = prime * result + ((getIqType() == null) ? 0 : getIqType().hashCode());
        result = prime * result + ((getIqStatus() == null) ? 0 : getIqStatus().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getIqMemo() == null) ? 0 : getIqMemo().hashCode());
        return result;
    }

	/**
	 * 返回inqueryResultEntitys的值
	 * @return List<InqueryResultEntity> inqueryResultEntitys的值
	 */
	public List<InqueryResultEntity> getInqueryResultEntitys() {
		return inqueryResultEntitys;
	}

	/**
	 * 设置inqueryResultEntitys的值
	 * @param  inqueryResultEntitys inqueryResultEntitys的值
	 */
	public void setInqueryResultEntitys(List<InqueryResultEntity> inqueryResultEntitys) {
		this.inqueryResultEntitys = inqueryResultEntitys;
	}

	/**
	 * 返回inqueryAnswerEntitys的值
	 * @return List<InqueryAnswerEntity> inqueryAnswerEntitys的值
	 */
	public List<InqueryAnswerEntity> getInqueryAnswerEntitys() {
		return inqueryAnswerEntitys;
	}

	/**
	 * 设置inqueryAnswerEntitys的值
	 * @param  inqueryAnswerEntitys inqueryAnswerEntitys的值
	 */
	public void setInqueryAnswerEntitys(List<InqueryAnswerEntity> inqueryAnswerEntitys) {
		this.inqueryAnswerEntitys = inqueryAnswerEntitys;
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