package com.djb.highway.road.dtoclient;

import java.util.List;

public class TollStationBaseDTO extends BaseClientDTO {

    List<TollStationDTO> list;

    public List<TollStationDTO> getList() {
        return list;
    }

    public void setList(List<TollStationDTO> list) {
        this.list = list;
    }

}