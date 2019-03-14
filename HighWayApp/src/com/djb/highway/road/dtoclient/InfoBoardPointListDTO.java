package com.djb.highway.road.dtoclient;

import java.util.List;

public class InfoBoardPointListDTO extends BaseClientDTO {

    private List<InfoBoardPointDTO> pointList;

    public List<InfoBoardPointDTO> getPointList() {
        return pointList;
    }

    public void setPointList(List<InfoBoardPointDTO> pointList) {
        this.pointList = pointList;
    }

}
