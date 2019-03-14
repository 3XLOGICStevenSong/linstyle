package com.djb.highway.carpool.dtoclient;

import java.util.List;

import com.djb.highway.road.dtoclient.BaseClientDTO;

public class DriverRouteListDTO extends BaseClientDTO {
    /**
	 * 
	 */
    private static final long serialVersionUID = 3370946888384377014L;
    
    private List<DriverRouteDetailDTO> list;

    public List<DriverRouteDetailDTO> getList() {
        return list;
    }

    public void setList(List<DriverRouteDetailDTO> list) {
        this.list = list;
    }

    

    
    

}