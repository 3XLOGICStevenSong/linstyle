package com.djb.highway.bus.entity;

import java.util.Date;

import com.djb.highway.framework.entity.PageModel;

public class BusGroupEntity extends PageModel {
    /**
     * 
     */
    private static final long serialVersionUID = 7646770708589906346L;

    private Integer group_id;

    private String group_name;

    private String group_password;

    private Integer buser_id;

    private String buser_name;

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

    public static long getSerialversionuid() {
        return serialVersionUID;
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
        BusGroupEntity other = (BusGroupEntity) that;
        return (this.getGroup_id() == null ? other.getGroup_id() == null : this.getGroup_id().equals(other.getGroup_id()))
                        && (this.getGroup_name() == null ? other.getGroup_name() == null : this.getGroup_name().equals(other.getGroup_name()))
                        && (this.getGroup_password() == null ? other.getGroup_password() == null : this.getGroup_password().equals(other.getGroup_password()))
                        && (this.getBuser_id() == null ? other.getBuser_id() == null : this.getBuser_id().equals(other.getBuser_id()))
                        && (this.getBuser_name() == null ? other.getBuser_name() == null : this.getBuser_name().equals(other.getBuser_name()))
                        && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()))
                        && (this.getInsert_time() == null ? other.getInsert_time() == null : this.getInsert_time().equals(other.getInsert_time()))
                        && (this.getUpdate_time() == null ? other.getUpdate_time() == null : this.getUpdate_time().equals(other.getUpdate_time()))
                        && (this.getStatus_flg() == null ? other.getStatus_flg() == null : this.getStatus_flg().equals(other.getStatus_flg()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getGroup_id() == null) ? 0 : getGroup_id().hashCode());
        result = prime * result + ((getGroup_name() == null) ? 0 : getGroup_name().hashCode());
        result = prime * result + ((getGroup_password() == null) ? 0 : getGroup_password().hashCode());
        result = prime * result + ((getBuser_name() == null) ? 0 : getBuser_name().hashCode());
        result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
        result = prime * result + ((getInsert_time() == null) ? 0 : getInsert_time().hashCode());
        result = prime * result + ((getUpdate_time() == null) ? 0 : getUpdate_time().hashCode());
        result = prime * result + ((getStatus_flg() == null) ? 0 : getStatus_flg().hashCode());
        return result;
    }

}