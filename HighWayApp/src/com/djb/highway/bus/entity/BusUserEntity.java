package com.djb.highway.bus.entity;

import java.util.Date;

import com.djb.highway.framework.entity.PageModel;

public class BusUserEntity extends PageModel {
    /**
	 * 
	 */
    private static final long serialVersionUID = -7252424445038417333L;

    private Integer buser_id;

    private Integer group_id;

    private String buser_name;

    private String buser_tel;

    private String longitude;

    private String latitude;

    private Date insert_time;

    private Date update_time;

    private String status_flg;

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
        BusUserEntity other = (BusUserEntity) that;
        return (this.getBuser_id() == null ? other.getBuser_id() == null : this.getBuser_id().equals(other.getBuser_id()))
                        && (this.getGroup_id() == null ? other.getGroup_id() == null : this.getGroup_id().equals(other.getGroup_id()))
                        && (this.getBuser_name() == null ? other.getBuser_name() == null : this.getBuser_name().equals(other.getBuser_name()))
                        && (this.getBuser_tel() == null ? other.getBuser_tel() == null : this.getBuser_tel().equals(other.getBuser_tel()))
                        && (this.getLongitude() == null ? other.getLongitude() == null : this.getLongitude().equals(other.getLongitude()))
                        && (this.getLatitude() == null ? other.getLatitude() == null : this.getLatitude().equals(other.getLatitude()))
                        && (this.getInsert_time() == null ? other.getInsert_time() == null : this.getInsert_time().equals(other.getInsert_time()))
                        && (this.getUpdate_time() == null ? other.getUpdate_time() == null : this.getUpdate_time().equals(other.getUpdate_time()))
                        && (this.getStatus_flg() == null ? other.getStatus_flg() == null : this.getStatus_flg().equals(other.getStatus_flg()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getBuser_id() == null) ? 0 : getBuser_id().hashCode());
        result = prime * result + ((getGroup_id() == null) ? 0 : getGroup_id().hashCode());
        result = prime * result + ((getBuser_name() == null) ? 0 : getBuser_name().hashCode());
        result = prime * result + ((getBuser_tel() == null) ? 0 : getBuser_tel().hashCode());
        result = prime * result + ((getLongitude() == null) ? 0 : getLongitude().hashCode());
        result = prime * result + ((getLatitude() == null) ? 0 : getLatitude().hashCode());
        result = prime * result + ((getInsert_time() == null) ? 0 : getInsert_time().hashCode());
        result = prime * result + ((getUpdate_time() == null) ? 0 : getUpdate_time().hashCode());
        result = prime * result + ((getStatus_flg() == null) ? 0 : getStatus_flg().hashCode());
        return result;
    }

}