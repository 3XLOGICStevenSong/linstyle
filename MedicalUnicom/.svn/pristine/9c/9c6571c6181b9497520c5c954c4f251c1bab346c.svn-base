package com.djb.ylt.health.entity;

import java.util.List;

public class SymptomTypeEntity {
    private Integer stId;

    private String stName;

    private String stIcon;

    private String stCell;

    private String statusFlg;
    
    private List <SymptomEntity>  symptomEntitys;

    public Integer getStId() {
        return stId;
    }

    public void setStId(Integer stId) {
        this.stId = stId;
    }

    public String getStName() {
        return stName;
    }

    public void setStName(String stName) {
        this.stName = stName;
    }

    public String getStIcon() {
        return stIcon;
    }

    public void setStIcon(String stIcon) {
        this.stIcon = stIcon;
    }

    public String getStCell() {
        return stCell;
    }

    public void setStCell(String stCell) {
        this.stCell = stCell;
    }

    public String getStatusFlg() {
        return statusFlg;
    }

    public void setStatusFlg(String statusFlg) {
        this.statusFlg = statusFlg;
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
        SymptomTypeEntity other = (SymptomTypeEntity) that;
        return (this.getStId() == null ? other.getStId() == null : this.getStId().equals(other.getStId()))
            && (this.getStName() == null ? other.getStName() == null : this.getStName().equals(other.getStName()))
            && (this.getStIcon() == null ? other.getStIcon() == null : this.getStIcon().equals(other.getStIcon()))
            && (this.getStCell() == null ? other.getStCell() == null : this.getStCell().equals(other.getStCell()))
            && (this.getStatusFlg() == null ? other.getStatusFlg() == null : this.getStatusFlg().equals(other.getStatusFlg()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getStId() == null) ? 0 : getStId().hashCode());
        result = prime * result + ((getStName() == null) ? 0 : getStName().hashCode());
        result = prime * result + ((getStIcon() == null) ? 0 : getStIcon().hashCode());
        result = prime * result + ((getStCell() == null) ? 0 : getStCell().hashCode());
        result = prime * result + ((getStatusFlg() == null) ? 0 : getStatusFlg().hashCode());
        return result;
    }

	/**
	 * 返回symptomEntitys的值
	 * @return List<SymptomEntity> symptomEntitys的值
	 */
	public List<SymptomEntity> getSymptomEntitys() {
		return symptomEntitys;
	}

	/**
	 * 设置symptomEntitys的值
	 * @param  symptomEntitys symptomEntitys的值
	 */
	public void setSymptomEntitys(List<SymptomEntity> symptomEntitys) {
		this.symptomEntitys = symptomEntitys;
	}
}