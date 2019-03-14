package com.djb.highway.road.dto.push;

import java.util.List;

public class RoadControlInfoPushDTO {

    private String returnCode;

    private List<RoadControlInfoData> roadControlInfo_data;

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public List<RoadControlInfoData> getRoadControlInfo_data() {
        return roadControlInfo_data;
    }

    public void setRoadControlInfo_data(List<RoadControlInfoData> roadControlInfo_data) {
        this.roadControlInfo_data = roadControlInfo_data;
    }

}
