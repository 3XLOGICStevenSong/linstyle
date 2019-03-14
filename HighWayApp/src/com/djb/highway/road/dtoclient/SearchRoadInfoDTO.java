package com.djb.highway.road.dtoclient;

import java.util.List;

public class SearchRoadInfoDTO {

    private String highWayName;

    private Integer h_id;

    public Integer getH_id() {
        return h_id;
    }

    public void setH_id(Integer h_id) {
        this.h_id = h_id;
    }

    private List<SearchInfoVo> stationInfoData;

    public List<SearchInfoVo> getStationInfoData() {
        return stationInfoData;
    }

    public void setStationInfoData(List<SearchInfoVo> stationInfoData) {
        this.stationInfoData = stationInfoData;
    }

    public String getHighWayName() {
        return highWayName;
    }

    public void setHighWayName(String highWayName) {
        this.highWayName = highWayName;
    }

}