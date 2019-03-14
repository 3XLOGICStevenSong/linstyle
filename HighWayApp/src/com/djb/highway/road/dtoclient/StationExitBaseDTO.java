package com.djb.highway.road.dtoclient;

import java.util.List;

/**
 * 类说明
 * 
 * @author LiWm E-mail:
 * @version 创建时间：2014年7月11日 下午2:43:47
 */
public class StationExitBaseDTO extends BaseClientDTO {

    private List<StationExitDTO> list;

    public List<StationExitDTO> getList() {
        return list;
    }

    public void setList(List<StationExitDTO> list) {
        this.list = list;
    }

}
