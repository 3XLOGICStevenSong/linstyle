package com.djb.highway.road.dtoclient;

import java.util.List;

/**
 * 道路事件详情 DTO
 * 
 * @author
 */
public class RoadEventBaseDTO extends BaseClientDTO {

    List<RoadEventDTO> RoadEventBaseDTOList;

    public List<RoadEventDTO> getRoadEventBaseDTOList() {
        return RoadEventBaseDTOList;
    }

    public void setRoadEventBaseDTOList(List<RoadEventDTO> roadEventBaseDTOList) {
        RoadEventBaseDTOList = roadEventBaseDTOList;
    }

}