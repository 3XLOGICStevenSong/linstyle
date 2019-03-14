package com.djb.highway.road.dtoclient;

import java.util.List;

public class ServiceAreaPointListDTO extends BaseClientDTO {

    private List<ServiceAreaPointDTO> pointList;

    public List<ServiceAreaPointDTO> getPointList() {
        return pointList;
    }

    public void setPointList(List<ServiceAreaPointDTO> pointList) {
        this.pointList = pointList;
    }

}
