package com.djb.ylt.health.entity;

import java.util.Date;
import java.util.List;

import com.djb.ylt.framework.entity.PageModel;

public class DepartmentSymptomEntity extends PageModel {
	
	
    /**
	 * serialVersionUID:用一句话描述这个变量表示什么。
	 */
	private static final long serialVersionUID = -2952251961251780196L;

	private Integer id;

    private Integer departmentId;

    private Integer symptomId;

    private Date createTime;

    private Date updateTime;
    
    private List<SymptomEntity> SymptomEntitys;
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public Integer getSymptomId() {
        return symptomId;
    }

    public void setSymptomId(Integer symptomId) {
        this.symptomId = symptomId;
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
        DepartmentSymptomEntity other = (DepartmentSymptomEntity) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getDepartmentId() == null ? other.getDepartmentId() == null : this.getDepartmentId().equals(other.getDepartmentId()))
            && (this.getSymptomId() == null ? other.getSymptomId() == null : this.getSymptomId().equals(other.getSymptomId()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getDepartmentId() == null) ? 0 : getDepartmentId().hashCode());
        result = prime * result + ((getSymptomId() == null) ? 0 : getSymptomId().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }

	/**
	 * 返回symptomEntitys的值
	 * @return List<SymptomEntity> symptomEntitys的值
	 */
	public List<SymptomEntity> getSymptomEntitys() {
		return SymptomEntitys;
	}

	/**
	 * 设置symptomEntitys的值
	 * @param  symptomEntitys symptomEntitys的值
	 */
	public void setSymptomEntitys(List<SymptomEntity> symptomEntitys) {
		SymptomEntitys = symptomEntitys;
	}
}