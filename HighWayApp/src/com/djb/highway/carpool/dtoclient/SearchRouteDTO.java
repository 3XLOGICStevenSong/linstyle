package com.djb.highway.carpool.dtoclient;

import java.util.List;

import com.djb.highway.road.dtoclient.BaseClientDTO;

public class SearchRouteDTO extends BaseClientDTO {
    /**
	 * 
	 */
    private static final long serialVersionUID = 3370946888384377014L;
    
    private List<PassengerRouteDetailDTO> list;

    public List<PassengerRouteDetailDTO> getList() {
        return list;
    }

    public void setList(List<PassengerRouteDetailDTO> list) {
        this.list = list;
    }

    

}