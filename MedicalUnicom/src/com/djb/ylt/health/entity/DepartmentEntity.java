package com.djb.ylt.health.entity;

import java.util.Date;
import java.util.List;

import com.djb.ylt.framework.entity.PageModel;

public class DepartmentEntity extends PageModel{
	
	
    /**
	 * serialVersionUID:用一句话描述这个变量表示什么。
	 */
	private static final long serialVersionUID = 6902840620406882616L;

	private Integer depId;

    private String depName;

    private Integer dcId;

    private String statusFlg;

    private Date createTime;

    private Date updateTime;
    
    private String  cellPic;
    
    private String  iconPic;
    
    private String memo;
    
    private List<SymptomEntity> symptomEntitys;
    
    private List<DepartmentSymptomEntity> departmentSymptomEntitys;
    
	// 2016.12.20 chengming modify for 常用医生 start
	// 常用医生接口，科室类别
	private DepartmentClassEntity departmentClassEntity;

	public DepartmentClassEntity getDepartmentClassEntity() {
		return departmentClassEntity;
	}

	public void setDepartmentClassEntity(DepartmentClassEntity departmentClassEntity) {
		this.departmentClassEntity = departmentClassEntity;
	}

	// 2016.12.20 chengming modify for 常用医生 end

    public Integer getDepId() {
        return depId;
    }

    public void setDepId(Integer depId) {
        this.depId = depId;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public Integer getDcId() {
        return dcId;
    }

    public void setDcId(Integer dcId) {
        this.dcId = dcId;
    }

    public String getStatusFlg() {
        return statusFlg;
    }

    public void setStatusFlg(String statusFlg) {
        this.statusFlg = statusFlg;
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
        DepartmentEntity other = (DepartmentEntity) that;
        return (this.getDepId() == null ? other.getDepId() == null : this.getDepId().equals(other.getDepId()))
            && (this.getDepName() == null ? other.getDepName() == null : this.getDepName().equals(other.getDepName()))
            && (this.getDcId() == null ? other.getDcId() == null : this.getDcId().equals(other.getDcId()))
            && (this.getStatusFlg() == null ? other.getStatusFlg() == null : this.getStatusFlg().equals(other.getStatusFlg()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getDepId() == null) ? 0 : getDepId().hashCode());
        result = prime * result + ((getDepName() == null) ? 0 : getDepName().hashCode());
        result = prime * result + ((getDcId() == null) ? 0 : getDcId().hashCode());
        result = prime * result + ((getStatusFlg() == null) ? 0 : getStatusFlg().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }

	/**
	 * 返回cellPic的值
	 * @return String cellPic的值
	 */
	public String getCellPic() {
		return cellPic;
	}

	/**
	 * 设置cellPic的值
	 * @param  cellPic cellPic的值
	 */
	public void setCellPic(String cellPic) {
		this.cellPic = cellPic;
	}

	/**
	 * 返回iconPic的值
	 * @return String iconPic的值
	 */
	public String getIconPic() {
		return iconPic;
	}

	/**
	 * 设置iconPic的值
	 * @param  iconPic iconPic的值
	 */
	public void setIconPic(String iconPic) {
		this.iconPic = iconPic;
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

	/**
	 * 返回memo的值
	 * @return String memo的值
	 */
	public String getMemo() {
		return memo;
	}

	/**
	 * 设置memo的值
	 * @param  memo memo的值
	 */
	public void setMemo(String memo) {
		this.memo = memo;
	}

	/**
	 * 返回departmentSymptomEntitys的值
	 * @return List<DepartmentSymptomEntity> departmentSymptomEntitys的值
	 */
	public List<DepartmentSymptomEntity> getDepartmentSymptomEntitys() {
		return departmentSymptomEntitys;
	}

	/**
	 * 设置departmentSymptomEntitys的值
	 * @param  departmentSymptomEntitys departmentSymptomEntitys的值
	 */
	public void setDepartmentSymptomEntitys(List<DepartmentSymptomEntity> departmentSymptomEntitys) {
		this.departmentSymptomEntitys = departmentSymptomEntitys;
	}
	
	
}