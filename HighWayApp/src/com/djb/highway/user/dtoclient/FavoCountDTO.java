package com.djb.highway.user.dtoclient;


import com.djb.highway.road.dtoclient.BaseClientDTO;
public class FavoCountDTO extends BaseClientDTO {
    /**
     * 
     */
    private static final long serialVersionUID = -378848295830417702L;

    private String zanCount;

    public String getZanCount() {
        return zanCount;
    }

    public void setZanCount(String zanCount) {
        this.zanCount = zanCount;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}