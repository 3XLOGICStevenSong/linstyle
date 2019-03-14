package com.djb.highway.road.dtoclient;

import java.util.List;

/**
 * 类说明
 * 
 * @author LiWm E-mail:
 * @version 创建时间：2014年7月11日 下午2:43:47
 */
public class OverpassBaseDTO extends BaseClientDTO {
    private String location_des;
    private String picUrl;

    private List<OverpassDetailClientDTO> list;

    public List<OverpassDetailClientDTO> getList() {
        return list;
    }

    public void setList(List<OverpassDetailClientDTO> list) {
        this.list = list;
    }

    public String getLocation_des() {
        return location_des;
    }

    public void setLocation_des(String location_des) {
        this.location_des = location_des;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

}
