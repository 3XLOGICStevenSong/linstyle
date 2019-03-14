package com.djb.highway.road.entity;

import java.util.Date;

public class MapLocationEntity {
    private Integer id;

    private Integer u_id;

    private String u_code;

    private String u_tel;

    private String longitude;

    private String latitude;

    private Date calltime;

    private String status;

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

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        MapLocationEntity other = (MapLocationEntity) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                        && (this.getU_id() == null ? other.getU_id() == null : this.getU_id().equals(other.getU_id()))
                        && (this.getU_code() == null ? other.getU_code() == null : this.getU_code().equals(other.getU_code()))
                        && (this.getU_tel() == null ? other.getU_tel() == null : this.getU_tel().equals(other.getU_tel()))
                        && (this.getLongitude() == null ? other.getLongitude() == null : this.getLongitude().equals(other.getLongitude()))
                        && (this.getLatitude() == null ? other.getLatitude() == null : this.getLatitude().equals(other.getLatitude()))
                        && (this.getCalltime() == null ? other.getCalltime() == null : this.getCalltime().equals(other.getCalltime()))
                        && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getU_id() == null) ? 0 : getU_id().hashCode());
        result = prime * result + ((getU_code() == null) ? 0 : getU_code().hashCode());
        result = prime * result + ((getU_tel() == null) ? 0 : getU_tel().hashCode());
        result = prime * result + ((getLongitude() == null) ? 0 : getLongitude().hashCode());
        result = prime * result + ((getLatitude() == null) ? 0 : getLatitude().hashCode());
        result = prime * result + ((getCalltime() == null) ? 0 : getCalltime().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        return result;
    }
}