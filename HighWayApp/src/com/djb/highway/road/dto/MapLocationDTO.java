package com.djb.highway.road.dto;

import java.util.Date;
import java.util.List;

import com.djb.highway.framework.dto.BaseDTO;

public class MapLocationDTO extends BaseDTO {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer u_id;

    private String u_code;

    private String u_tel;

    private String longitude;

    private String latitude;

    private Date calltime;

    private String status;

    private List<MapLocationDTO> list;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getU_id() {
        return u_id;
    }

    public void setU_id(Integer u_id) {
        this.u_id = u_id;
    }

    public String getU_code() {
        return u_code;
    }

    public void setU_code(String u_code) {
        this.u_code = u_code;
    }

    public String getU_tel() {
        return u_tel;
    }

    public void setU_tel(String u_tel) {
        this.u_tel = u_tel;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public Date getCalltime() {
        return calltime;
    }

    public void setCalltime(Date calltime) {
        this.calltime = calltime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<MapLocationDTO> getList() {
        return list;
    }

    public void setList(List<MapLocationDTO> list) {
        this.list = list;
    }

}