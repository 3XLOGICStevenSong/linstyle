package com.djb.highway.road.dtoclient;

import java.util.List;

public class InfoIdsDetailDTO {
    Integer iconId = null;
    List<String> sectionIds = null;

    public Integer getIconId() {
        return iconId;
    }

    public void setIconId(Integer iconId) {
        this.iconId = iconId;
    }

    public List<String> getSectionIds() {
        return sectionIds;
    }

    public void setSectionIds(List<String> sectionIds) {
        this.sectionIds = sectionIds;
    }

}
