package com.djb.ylt.user.entity;

import java.util.Date;

import com.djb.ylt.framework.entity.BaseEntity;

public class QaTypeEntity  extends BaseEntity{
	
	
	
    /**
	 * serialVersionUID:用一句话描述这个变量表示什么。
	 */
	private static final long serialVersionUID = -7213751562623293531L;

	private Integer qaTypeId;

    private String qaTypeName;

    private String status;

    private Date createTime;

    private Date updateTime;

    public Integer getQaTypeId() {
        return qaTypeId;
    }

    public void setQaTypeId(Integer qaTypeId) {
        this.qaTypeId = qaTypeId;
    }

    public String getQaTypeName() {
        return qaTypeName;
    }

    public void setQaTypeName(String qaTypeName) {
        this.qaTypeName = qaTypeName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
        QaTypeEntity other = (QaTypeEntity) that;
        return (this.getQaTypeId() == null ? other.getQaTypeId() == null : this.getQaTypeId().equals(other.getQaTypeId()))
            && (this.getQaTypeName() == null ? other.getQaTypeName() == null : this.getQaTypeName().equals(other.getQaTypeName()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getQaTypeId() == null) ? 0 : getQaTypeId().hashCode());
        result = prime * result + ((getQaTypeName() == null) ? 0 : getQaTypeName().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }
}