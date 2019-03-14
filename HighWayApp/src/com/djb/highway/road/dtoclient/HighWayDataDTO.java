package com.djb.highway.road.dtoclient;

import java.util.List;

public class HighWayDataDTO extends BaseClientDTO {

    private boolean markFlag;

    // 高速ID
    private Integer h_id;

    private List<StationInfoVo> stationInfoData;

    public Integer getH_id() {
        return h_id;
    }

    public void setH_id(Integer h_id) {
        this.h_id = h_id;
    }

    public HighWayDataDTO() {
    }

    public boolean isMarkFlag() {
        return markFlag;
    }

    public void setMarkFlag(boolean markFlag) {
        this.markFlag = markFlag;
    }

    public List<StationInfoVo> getStationInfoData() {
        return stationInfoData;
    }

    public void setStationInfoData(List<StationInfoVo> stationInfoData) {
        this.stationInfoData = stationInfoData;
    }

}