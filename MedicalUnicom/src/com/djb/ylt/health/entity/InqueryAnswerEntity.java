package com.djb.ylt.health.entity;

import java.util.Date;
import java.util.List;

import com.djb.ylt.framework.entity.PageModel;

public class InqueryAnswerEntity  extends PageModel{
	
    /**
	 * serialVersionUID:用一句话描述这个变量表示什么。
	 */
	private static final long serialVersionUID = 1L;

	private Integer iaId;

    private Integer patientId;

    private Integer iqId;

    private Integer irId;

    private String iaContent;

    private Integer depId;

    private Date iaTime;

    private String iaMemo;

    private Integer recordId;

    private String irName;
    
    private String  iaFlag;
    
    private  List<InqueryAnswerEntity> inqueryAnswerEntitys;
    
    public Integer getIaId() {
        return iaId;
    }

    public void setIaId(Integer iaId) {
        this.iaId = iaId;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public Integer getIqId() {
        return iqId;
    }

    public void setIqId(Integer iqId) {
        this.iqId = iqId;
    }

    public Integer getIrId() {
        return irId;
    }

    public void setIrId(Integer irId) {
        this.irId = irId;
    }

    public String getIaContent() {
        return iaContent;
    }

    public void setIaContent(String iaContent) {
        this.iaContent = iaContent;
    }

    public Integer getDepId() {
        return depId;
    }

    public void setDepId(Integer depId) {
        this.depId = depId;
    }

    public Date getIaTime() {
        return iaTime;
    }

    public void setIaTime(Date iaTime) {
        this.iaTime = iaTime;
    }

    public String getIaMemo() {
        return iaMemo;
    }

    public void setIaMemo(String iaMemo) {
        this.iaMemo = iaMemo;
    }

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
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
        InqueryAnswerEntity other = (InqueryAnswerEntity) that;
        return (this.getIaId() == null ? other.getIaId() == null : this.getIaId().equals(other.getIaId()))
            && (this.getPatientId() == null ? other.getPatientId() == null : this.getPatientId().equals(other.getPatientId()))
            && (this.getIqId() == null ? other.getIqId() == null : this.getIqId().equals(other.getIqId()))
            && (this.getIrId() == null ? other.getIrId() == null : this.getIrId().equals(other.getIrId()))
            && (this.getIaContent() == null ? other.getIaContent() == null : this.getIaContent().equals(other.getIaContent()))
            && (this.getDepId() == null ? other.getDepId() == null : this.getDepId().equals(other.getDepId()))
            && (this.getIaTime() == null ? other.getIaTime() == null : this.getIaTime().equals(other.getIaTime()))
            && (this.getIaMemo() == null ? other.getIaMemo() == null : this.getIaMemo().equals(other.getIaMemo()))
            && (this.getRecordId() == null ? other.getRecordId() == null : this.getRecordId().equals(other.getRecordId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getIaId() == null) ? 0 : getIaId().hashCode());
        result = prime * result + ((getPatientId() == null) ? 0 : getPatientId().hashCode());
        result = prime * result + ((getIqId() == null) ? 0 : getIqId().hashCode());
        result = prime * result + ((getIrId() == null) ? 0 : getIrId().hashCode());
        result = prime * result + ((getIaContent() == null) ? 0 : getIaContent().hashCode());
        result = prime * result + ((getDepId() == null) ? 0 : getDepId().hashCode());
        result = prime * result + ((getIaTime() == null) ? 0 : getIaTime().hashCode());
        result = prime * result + ((getIaMemo() == null) ? 0 : getIaMemo().hashCode());
        result = prime * result + ((getRecordId() == null) ? 0 : getRecordId().hashCode());
        return result;
    }

	/**
	 * 返回irName的值
	 * @return String irName的值
	 */
	public String getIrName() {
		return irName;
	}

	/**
	 * 设置irName的值
	 * @param  irName irName的值
	 */
	public void setIrName(String irName) {
		this.irName = irName;
	}

	/**
	 * 返回iaFlag的值
	 * @return String iaFlag的值
	 */
	public String getIaFlag() {
		return iaFlag;
	}

	/**
	 * 设置iaFlag的值
	 * @param  iaFlag iaFlag的值
	 */
	public void setIaFlag(String iaFlag) {
		this.iaFlag = iaFlag;
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
	
}