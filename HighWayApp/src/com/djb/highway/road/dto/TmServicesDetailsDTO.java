package com.djb.highway.road.dto;

import java.util.List;

import com.djb.highway.framework.dto.PageDTO;

public class TmServicesDetailsDTO extends PageDTO {

    /**
	 * 
	 */
    private static final long serialVersionUID = -9107471098457674358L;
    private Integer sd_id;
    private Integer service_id;

    private String details_num;

    private String details_name;
    private String details_pic;
    private String details_1;
    private String details_type;

    private List<TmServicesDetailsDTO> list;

    public Integer getSd_id() {
        return sd_id;
    }

    public void setSd_id(Integer sd_id) {
        this.sd_id = sd_id;
    }

    public Integer getService_id() {
        return service_id;
    }

    public void setService_id(Integer service_id) {
        this.service_id = service_id;
    }

    public String getDetails_num() {
        return details_num;
    }

    public void setDetails_num(String details_num) {
        this.details_num = details_num;
    }

    public String getDetails_name() {
        return details_name;
    }

    public void setDetails_name(String details_name) {
        this.details_name = details_name;
    }

    public String getDetails_pic() {
        return details_pic;
    }

    public void setDetails_pic(String details_pic) {
        this.details_pic = details_pic;
    }

    public String getDetails_1() {
        return details_1;
    }

    public void setDetails_1(String details_1) {
        this.details_1 = details_1;
    }

    public String getDetails_type() {
        return details_type;
    }

    public void setDetails_type(String details_type) {
        this.details_type = details_type;
    }

    public List<TmServicesDetailsDTO> getList() {
        return list;
    }

    public void setList(List<TmServicesDetailsDTO> list) {
        this.list = list;
    }

}