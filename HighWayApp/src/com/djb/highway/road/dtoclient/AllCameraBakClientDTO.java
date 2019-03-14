package com.djb.highway.road.dtoclient;

import java.util.List;

public class AllCameraBakClientDTO extends BaseClientDTO {

    private Integer version;

    private List<CameraBakClientDTO> cameraList;

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public List<CameraBakClientDTO> getCameraList() {
        return cameraList;
    }

    public void setCameraList(List<CameraBakClientDTO> cameraList) {
        this.cameraList = cameraList;
    }

}
