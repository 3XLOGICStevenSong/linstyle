package com.djb.highway.road.dtoclient;

import java.util.List;

public class PlazaPointListDTO extends BaseClientDTO {

    private List<PlazaPointDTO> pointList;

    public List<PlazaPointDTO> getPointList() {
        return pointList;
    }

    public void setPointList(List<PlazaPointDTO> pointList) {
        this.pointList = pointList;
    }

}
