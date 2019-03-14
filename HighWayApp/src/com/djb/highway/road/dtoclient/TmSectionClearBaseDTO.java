package com.djb.highway.road.dtoclient;

import java.util.List;

public class TmSectionClearBaseDTO extends BaseClientDTO {

    /**
     * 
     */
    private static final long serialVersionUID = -8989880632972446456L;

    /**
     * 收藏标记
     */
    private boolean markFlag;

    List<TmSectionClearDTO> list;

    public List<TmSectionClearDTO> getList() {
        return list;
    }

    public void setList(List<TmSectionClearDTO> list) {
        this.list = list;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public boolean isMarkFlag() {
        return markFlag;
    }

    public void setMarkFlag(boolean markFlag) {
        this.markFlag = markFlag;
    }

}