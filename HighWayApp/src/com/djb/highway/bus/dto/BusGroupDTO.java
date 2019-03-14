package com.djb.highway.bus.dto;

import java.util.Date;

import com.djb.highway.framework.dto.PageDTO;

public class BusGroupDTO extends PageDTO {

    /**
     * 
     */
    private static final long serialVersionUID = 8119310128734951433L;

    private Integer group_id;

    private String group_name;

    private String group_password;

    private Integer buser_id;

    private String buser_name;

    private String group_old_password;

    private Integer version;

    private Date insert_time;

    private Date update_time;

    private String status_flg;

    public Integer getGroup_id() {
        return group_id;
    }

    public void setGroup_id(Integer group_id) {
        this.group_id = group_id;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public String getGroup_password() {
        return group_password;
    }

    public void setGroup_password(String group_password) {
        this.group_password = group_password;
    }

    public Integer getBuser_id() {
        return buser_id;
    }

    public void setBuser_id(Integer buser_id) {
        this.buser_id = buser_id;
    }

    public String getBuser_name() {
        return buser_name;
    }

    public void setBuser_name(String buser_name) {
        this.buser_name = buser_name;
    }

    public String getGroup_old_password() {
        return group_old_password;
    }

    public void setGroup_old_password(String group_old_password) {
        this.group_old_password = group_old_password;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Date getInsert_time() {
        return insert_time;
    }

    public void setInsert_time(Date insert_time) {
        this.insert_time = insert_time;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    public String getStatus_flg() {
        return status_flg;
    }

    public void setStatus_flg(String status_flg) {
        this.status_flg = status_flg;
    }

}