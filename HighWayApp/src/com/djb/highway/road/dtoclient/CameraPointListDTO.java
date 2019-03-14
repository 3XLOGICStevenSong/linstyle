package com.djb.highway.road.dtoclient;

import java.util.List;

public class CameraPointListDTO extends BaseClientDTO {

    private List<CameraPointDTO> pointList;

    public List<CameraPointDTO> getPointList() {
        return pointList;
    }

    public void setPointList(List<CameraPointDTO> pointList) {
        this.pointList = pointList;
    }

}
