package com.djb.highway.road.dtoclient;

import java.util.List;

public class RoadControlInfoClearBaseDTO extends BaseClientDTO {
    /**
     * 
     */
    private static final long serialVersionUID = -3482034926600127754L;

    List<RoadControlInfoClearDTO> list;
    private List<String> secIds;

    public List<String> getSecIds() {
        return secIds;
    }

    public void setSecIds(List<String> secIds) {
        this.secIds = secIds;
    }

    public List<RoadControlInfoClearDTO> getList() {
        return list;
    }

    public void setList(List<RoadControlInfoClearDTO> list) {
        this.list = list;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}