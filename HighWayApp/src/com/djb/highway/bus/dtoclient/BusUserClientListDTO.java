package com.djb.highway.bus.dtoclient;

import java.util.List;

import com.djb.highway.road.dtoclient.BaseClientDTO;

public class BusUserClientListDTO extends BaseClientDTO {

    private String r;
    
    private Integer v;
    
    private List<BusUserClientDTO> l;
    
    private List<BusUserClientDTO> list;

    public String getR() {
        return r;
    }

    public void setR(String r) {
        this.r = r;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

    public List<BusUserClientDTO> getL() {
        return l;
    }

    public void setL(List<BusUserClientDTO> l) {
        this.l = l;
    }

    public List<BusUserClientDTO> getList() {
        return list;
    }

    public void setList(List<BusUserClientDTO> list) {
        this.list = list;
    }
    
    

}
