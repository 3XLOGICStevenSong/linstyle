package com.djb.highway.road.dtoclient;

import java.util.List;

public class CameraClearBaseDTO extends BaseClientDTO {

    private List<String> ids;

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }

}