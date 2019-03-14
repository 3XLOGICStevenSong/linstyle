package com.djb.highway.road.dtoclient;

import java.util.List;

public class InfoIdsDetailClientDTO extends BaseClientDTO {

    private List<InfoIdsDetailDTO> secIds;

    public List<InfoIdsDetailDTO> getSecIds() {
        return secIds;
    }

    public void setSecIds(List<InfoIdsDetailDTO> secIds) {
        this.secIds = secIds;
    }

}
