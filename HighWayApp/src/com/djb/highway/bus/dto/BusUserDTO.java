package com.djb.highway.bus.dto;

import java.util.Date;

import com.djb.highway.framework.dto.PageDTO;

public class BusUserDTO extends PageDTO {

    /**
	 * 
	 */
    private static final long serialVersionUID = -4531508578165598561L;

    private Integer buser_id;

    private Integer group_id;

    private String buser_name;

    private String buser_tel;

    private String longitude;

    private String latitude;

    private Integer uid;

    private String o;

    private String a;

    private Integer gid;

    private Date insert_time;

    private Date update_time;

    private String status_flg;
    
    private String lnglat;
    
    private String u;

    public Integer getBuser_id() {
        return buser_id;
    }

    public void setBuser_id(Integer buser_id) {
        this.buser_id = buser_id;
    }

    public Integer getGroup_id() {
        return group_id;
    }

    public void setGroup_id(Integer group_id) {
        this.group_id = group_id;
    }

    public String getBuser_name() {
        return buser_name;
    }

    public void setBuser_name(String buser_name) {
        this.buser_name = buser_name;
    }

    public String getBuser_tel() {
        return buser_tel;
    }

    public void setBuser_tel(String buser_tel) {
        this.buser_tel = buser_tel;
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

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getO() {
        return o;
    }

    public void setO(String o) {
        this.o = o;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
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

    public String getLnglat() {
        return lnglat;
    }

    public void setLnglat(String lnglat) {
        this.lnglat = lnglat;
    }

    public String getU() {
        return u;
    }

    public void setU(String u) {
        this.u = u;
    }

}