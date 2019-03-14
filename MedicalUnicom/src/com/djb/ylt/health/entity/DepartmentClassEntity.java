package com.djb.ylt.health.entity;

import java.util.Date;
import java.util.List;

public class DepartmentClassEntity {
    private Integer dcId;

    private String dcName;

    private String statusFlg;

    private Date createTime;

    private Date updateTime;

    private String iconPic;

    private String cellPic;

    private String memo;
    
    private List<DepartmentEntity> departmentEntitys;

    public Integer getDcId() {
        return dcId;
    }

    public void setDcId(Integer dcId) {
        this.dcId = dcId;
    }

    public String getDcName() {
        return dcName;
    }

    public void setDcName(String dcName) {
        this.dcName = dcName;
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

    public String getIconPic() {
        return iconPic;
    }

    public void setIconPic(String iconPic) {
        this.iconPic = iconPic;
    }

    public String getCellPic() {
        return cellPic;
    }

    public void setCellPic(String cellPic) {
        this.cellPic = cellPic;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
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
        DepartmentClassEntity other = (DepartmentClassEntity) that;
        return (this.getDcId() == null ? other.getDcId() == null : this.getDcId().equals(other.getDcId()))
            && (this.getDcName() == null ? other.getDcName() == null : this.getDcName().equals(other.getDcName()))
            && (this.getStatusFlg() == null ? other.getStatusFlg() == null : this.getStatusFlg().equals(other.getStatusFlg()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getIconPic() == null ? other.getIconPic() == null : this.getIconPic().equals(other.getIconPic()))
            && (this.getCellPic() == null ? other.getCellPic() == null : this.getCellPic().equals(other.getCellPic()))
            && (this.getMemo() == null ? other.getMemo() == null : this.getMemo().equals(other.getMemo()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getDcId() == null) ? 0 : getDcId().hashCode());
        result = prime * result + ((getDcName() == null) ? 0 : getDcName().hashCode());
        result = prime * result + ((getStatusFlg() == null) ? 0 : getStatusFlg().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getIconPic() == null) ? 0 : getIconPic().hashCode());
        result = prime * result + ((getCellPic() == null) ? 0 : getCellPic().hashCode());
        result = prime * result + ((getMemo() == null) ? 0 : getMemo().hashCode());
        return result;
    }

	/**
	 * 返回departmentEntitys的值
	 * @return List<DepartmentEntity> departmentEntitys的值
	 */
	public List<DepartmentEntity> getDepartmentEntitys() {
		return departmentEntitys;
	}

	/**
	 * 设置departmentEntitys的值
	 * @param  departmentEntitys departmentEntitys的值
	 */
	public void setDepartmentEntitys(List<DepartmentEntity> departmentEntitys) {
		this.departmentEntitys = departmentEntitys;
	}
}