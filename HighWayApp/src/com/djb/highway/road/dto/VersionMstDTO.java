package com.djb.highway.road.dto;

import com.djb.highway.framework.dto.BaseDTO;

public class VersionMstDTO extends BaseDTO {
    /**
     * 
     */
    private static final long serialVersionUID = 7221176902551716690L;

    private Integer version;

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}