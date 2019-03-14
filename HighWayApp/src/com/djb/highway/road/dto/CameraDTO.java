package com.djb.highway.road.dto;

import java.util.Date;

import com.djb.highway.framework.dto.PageDTO;

public class CameraDTO extends PageDTO {
    /**
     * 
     */
    private static final long serialVersionUID = -719473171973793158L;

    private Integer c_id;

    private Integer h_id;

    private String h_code;

    private String h_name;

    private Integer section_id;

    private String c_type;

    private String c_status;

    private String c_name;

    private String c_addr;

    private String c_ip;

    private String stake_id;

    private String longitude_a;

    private String latitude_a;

    private String longitude_b;

    private String latitude_b;

    private String c_memo;

    private String pic_path;

    private Date shoot_time;

    private String direction;

    private String visible;

    private String is_public;

    private Integer location_id;

    private String location_type;

    private String[] c_codeArgs;

    public String[] getC_codeArgs() {
        return c_codeArgs;
    }

    public void setC_codeArgs(String[] c_codeArgs) {
        this.c_codeArgs = c_codeArgs;
    }
    
    public Integer getC_id() {
        return c_id;
    }

    public void setC_id(Integer c_id) {
        this.c_id = c_id;
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

    public Integer getSection_id() {
        return section_id;
    }

    public void setSection_id(Integer section_id) {
        this.section_id = section_id;
    }

    public String getC_type() {
        return c_type;
    }

    public void setC_type(String c_type) {
        this.c_type = c_type;
    }

    public String getC_status() {
        return c_status;
    }

    public void setC_status(String c_status) {
        this.c_status = c_status;
    }

    public String getC_name() {
        return c_name;
    }

    public void setC_name(String c_name) {
        this.c_name = c_name;
    }

    public String getC_addr() {
        return c_addr;
    }

    public void setC_addr(String c_addr) {
        this.c_addr = c_addr;
    }

    public String getC_ip() {
        return c_ip;
    }

    public void setC_ip(String c_ip) {
        this.c_ip = c_ip;
    }

    public String getStake_id() {
        return stake_id;
    }

    public void setStake_id(String stake_id) {
        this.stake_id = stake_id;
    }

    public String getLongitude_a() {
        return longitude_a;
    }

    public void setLongitude_a(String longitude_a) {
        this.longitude_a = longitude_a;
    }

    public String getLatitude_a() {
        return latitude_a;
    }

    public void setLatitude_a(String latitude_a) {
        this.latitude_a = latitude_a;
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

    public String getC_memo() {
        return c_memo;
    }

    public void setC_memo(String c_memo) {
        this.c_memo = c_memo;
    }

    public String getPic_path() {
        return pic_path;
    }

    public void setPic_path(String pic_path) {
        this.pic_path = pic_path;
    }

    public Date getShoot_time() {
        return shoot_time;
    }

    public void setShoot_time(Date shoot_time) {
        this.shoot_time = shoot_time;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getVisible() {
        return visible;
    }

    public void setVisible(String visible) {
        this.visible = visible;
    }

    public String getIs_public() {
        return is_public;
    }

    public void setIs_public(String is_public) {
        this.is_public = is_public;
    }

    public Integer getLocation_id() {
        return location_id;
    }

    public void setLocation_id(Integer location_id) {
        this.location_id = location_id;
    }

    public String getLocation_type() {
        return location_type;
    }

    public void setLocation_type(String location_type) {
        this.location_type = location_type;
    }

}