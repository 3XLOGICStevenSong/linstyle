package com.djb.highway.road.dtoclient;

import java.util.Date;

public class InfoBoardPointDTO extends BaseClientDTO {

    private Integer h_id;
    private String h_code;
    private String h_name;
    private String ib_addr;
    private String stake_id;
    private String ib_content;
    private String pic_path;
    private Date deploy_time;
    private Integer ib_id;
    private String longitude_b;
    private String latitude_b;
    private String section_name;
    private String location_type;
    public Integer getIb_id() {
        return ib_id;
    }

    public void setIb_id(Integer ib_id) {
        this.ib_id = ib_id;
    }

    public Integer getH_id() {
        return h_id;
    }

    public void setH_id(Integer h_id) {
        this.h_id = h_id;
    }

    public String getH_code() {
        return h_code;
    }

    public void setH_code(String h_code) {
        this.h_code = h_code;
    }

    public String getH_name() {
        return h_name;
    }

    public void setH_name(String h_name) {
        this.h_name = h_name;
    }

    public String getIb_addr() {
        return ib_addr;
    }

    public void setIb_addr(String ib_addr) {
        this.ib_addr = ib_addr;
    }

    public String getStake_id() {
        return stake_id;
    }

    public void setStake_id(String stake_id) {
        this.stake_id = stake_id;
    }

    public String getIb_content() {
        return ib_content;
    }

    public void setIb_content(String ib_content) {
        this.ib_content = ib_content;
    }

    public String getPic_path() {
        return pic_path;
    }

    public void setPic_path(String pic_path) {
        this.pic_path = pic_path;
    }

    public Date getDeploy_time() {
        return deploy_time;
    }

    public void setDeploy_time(Date deploy_time) {
        this.deploy_time = deploy_time;
    }

    public String getLongitude_b() {
        return longitude_b;
    }

    public void setLongitude_b(String longitude_b) {
        this.longitude_b = longitude_b;
    }

    public String getLatitude_b() {
        return latitude_b;
    }

    public void setLatitude_b(String latitude_b) {
        this.latitude_b = latitude_b;
    }

    public String getSection_name() {
        return section_name;
    }

    public void setSection_name(String section_name) {
        this.section_name = section_name;
    }

    public String getLocation_type() {
        return location_type;
    }

    public void setLocation_type(String location_type) {
        this.location_type = location_type;
    }

}