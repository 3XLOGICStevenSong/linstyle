package com.djb.highway.road.entity;

import com.djb.highway.framework.entity.BaseEntity;

public class VersionMstEntity extends BaseEntity {
    
    /**
     * 
     */
    private static final long serialVersionUID = 2582726787397513234L;
    
    private Integer camera_version;

    public Integer getCamera_version() {
        return camera_version;
    }

    public void setCamera_version(Integer camera_version) {
        this.camera_version = camera_version;
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
        VersionMstEntity other = (VersionMstEntity) that;
        return (this.getCamera_version() == null ? other.getCamera_version() == null : this.getCamera_version().equals(other.getCamera_version()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCamera_version() == null) ? 0 : getCamera_version().hashCode());
        return result;
    }
}