package com.djb.highway.road.dto;

import java.util.List;

import com.djb.highway.framework.dto.PageDTO;
import com.djb.highway.road.entity.TmServicesDetailsEntity;

public class TmServicesDTO extends PageDTO {

    /**
	 * 
	 */
    private static final long serialVersionUID = -4256605503757680113L;
    private Integer id;
    private Integer sa_id;
    private String type;
    private String service_time;
    private String service_name;
    private String intro;
    private String tel;
    private String pic;
    private String introduction;

    private List<TmServicesDetailsEntity> tmServicesDetailsEntitys;

    private List<TmServicesDTO> list;

    private List<TmServicesDetailsDTO> tmServicesDetailsDTOs;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSa_id() {
        return sa_id;
    }

    public void setSa_id(Integer sa_id) {
        this.sa_id = sa_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getService_time() {
        return service_time;
    }

    public void setService_time(String service_time) {
        this.service_time = service_time;
    }

    public String getService_name() {
        return service_name;
    }

    public void setService_name(String service_name) {
        this.service_name = service_name;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public List<TmServicesDetailsEntity> getTmServicesDetailsEntitys() {
        return tmServicesDetailsEntitys;
    }

    public void setTmServicesDetailsEntitys(List<TmServicesDetailsEntity> tmServicesDetailsEntitys) {
        this.tmServicesDetailsEntitys = tmServicesDetailsEntitys;
    }

    public List<TmServicesDTO> getList() {
        return list;
    }

    public void setList(List<TmServicesDTO> list) {
        this.list = list;
    }

    public List<TmServicesDetailsDTO> getTmServicesDetailsDTOs() {
        return tmServicesDetailsDTOs;
    }

    public void setTmServicesDetailsDTOs(List<TmServicesDetailsDTO> tmServicesDetailsDTOs) {
        this.tmServicesDetailsDTOs = tmServicesDetailsDTOs;
    }

}