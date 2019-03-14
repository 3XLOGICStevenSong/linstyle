package com.djb.highway.road.dto;

import java.util.Date;

import com.djb.highway.framework.dto.BaseDTO;

public class InfoBoardDTO extends BaseDTO {

    /**
	 * 
	 */
    private static final long serialVersionUID = 2430416877262044101L;

    private Integer ib_id;

    private Integer h_id;

    private String h_code;

    private String h_name;

    private Integer section_id;

    private String ib_addr;

    private String stake_id;

    private String ib_status;

    private String longitude_a;

    private String latitude_a;

    private String longitude_b;

    private String latitude_b;

    private String ib_content;

    private String pic_path;

    private Date deploy_time;

    private String direction;

    private String visible;

    private String is_public;
    private String type;
    private Integer location_id;
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

    public Integer getSection_id() {
        return section_id;
    }

    public void setSection_id(Integer section_id) {
        this.section_id = section_id;
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

    public String getIb_status() {
        return ib_status;
    }

    public void setIb_status(String ib_status) {
        this.ib_status = ib_status;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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