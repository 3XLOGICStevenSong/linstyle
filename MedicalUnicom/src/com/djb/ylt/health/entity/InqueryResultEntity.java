package com.djb.ylt.health.entity;

import java.util.Date;

public class InqueryResultEntity {
    private Integer irId;

    private Integer iqId;

    private String irName;

    private String irMemo;

    private Date createTime;

    private String irStatus;
    
    private String irType;
    
    private String iaContent;
    
    
    public Integer getIrId() {
        return irId;
    }

    public void setIrId(Integer irId) {
        this.irId = irId;
    }

    public Integer getIqId() {
        return iqId;
    }

    public void setIqId(Integer iqId) {
        this.iqId = iqId;
    }

    public String getIrName() {
        return irName;
    }

    public void setIrName(String irName) {
        this.irName = irName;
    }

    public String getIrMemo() {
        return irMemo;
    }

    public void setIrMemo(String irMemo) {
        this.irMemo = irMemo;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getIrStatus() {
        return irStatus;
    }

    public void setIrStatus(String irStatus) {
        this.irStatus = irStatus;
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
        InqueryResultEntity other = (InqueryResultEntity) that;
        return (this.getIrId() == null ? other.getIrId() == null : this.getIrId().equals(other.getIrId()))
            && (this.getIqId() == null ? other.getIqId() == null : this.getIqId().equals(other.getIqId()))
            && (this.getIrName() == null ? other.getIrName() == null : this.getIrName().equals(other.getIrName()))
            && (this.getIrMemo() == null ? other.getIrMemo() == null : this.getIrMemo().equals(other.getIrMemo()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getIrStatus() == null ? other.getIrStatus() == null : this.getIrStatus().equals(other.getIrStatus()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getIrId() == null) ? 0 : getIrId().hashCode());
        result = prime * result + ((getIqId() == null) ? 0 : getIqId().hashCode());
        result = prime * result + ((getIrName() == null) ? 0 : getIrName().hashCode());
        result = prime * result + ((getIrMemo() == null) ? 0 : getIrMemo().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getIrStatus() == null) ? 0 : getIrStatus().hashCode());
        return result;
    }

	/**
	 * 返回irType的值
	 * @return String irType的值
	 */
	public String getIrType() {
		return irType;
	}

	/**
	 * 设置irType的值
	 * @param  irType irType的值
	 */
	public void setIrType(String irType) {
		this.irType = irType;
	}
    
}