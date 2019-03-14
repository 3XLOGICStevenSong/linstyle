
package com.djb.highway.bus.dtoclient;

import java.util.List;

import com.djb.highway.road.dtoclient.BaseClientDTO;

public class GroupClientDTO extends BaseClientDTO {

    private List<GroupDetailClientDTO>  list;

    public List<GroupDetailClientDTO> getList() {
        return list;
    }

    public void setList(List<GroupDetailClientDTO> list) {
        this.list = list;
    }
     
    

}

