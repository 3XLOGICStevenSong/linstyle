package com.djb.highway.road.entity;

import java.util.Date;

import com.djb.highway.framework.entity.BaseEntity;

public class CameraEntity extends BaseEntity {
    /**
     * 
     */
    private static final long serialVersionUID = 2240303270916345847L;

    private Integer c_id;
    
    private String c_code;
    
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

    private String[] h_codeArgs;

    private String stakeIdStart;

    private String stakeIdEnd;

    // 新追加的属性：用于确定摄像头所属的位置（收费站、服务区、立交桥、路段等）
    private Integer location_id;
    private String location_type;

    private String[] c_codeArgs;
    
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

    public String getC_code() {
        return c_code;
    }

    public void setC_code(String c_code) {
        this.c_code = c_code;
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

    public String[] getH_codeArgs() {
        return h_codeArgs;
    }

    public void setH_codeArgs(String[] h_codeArgs) {
        this.h_codeArgs = h_codeArgs;
    }

    public String getStakeIdStart() {
        return stakeIdStart;
    }

    public void setStakeIdStart(String stakeIdStart) {
        this.stakeIdStart = stakeIdStart;
    }

    public String getStakeIdEnd() {
        return stakeIdEnd;
    }

    public void setStakeIdEnd(String stakeIdEnd) {
        this.stakeIdEnd = stakeIdEnd;
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

    public String[] getC_codeArgs() {
        return c_codeArgs;
    }

    public void setC_codeArgs(String[] c_codeArgs) {
        this.c_codeArgs = c_codeArgs;
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
        CameraEntity other = (CameraEntity) that;
        return (this.getC_id() == null ? other.getC_id() == null : this.getC_id().equals(other.getC_id()))
                        && (this.getH_id() == null ? other.getH_id() == null : this.getH_id().equals(other.getH_id()))
                        && (this.getH_code() == null ? other.getH_code() == null : this.getH_code().equals(other.getH_code()))
                        && (this.getH_name() == null ? other.getH_name() == null : this.getH_name().equals(other.getH_name()))
                        && (this.getSection_id() == null ? other.getSection_id() == null : this.getSection_id().equals(other.getSection_id()))
                        && (this.getC_type() == null ? other.getC_type() == null : this.getC_type().equals(other.getC_type()))
                        && (this.getC_status() == null ? other.getC_status() == null : this.getC_status().equals(other.getC_status()))
                        && (this.getC_name() == null ? other.getC_name() == null : this.getC_name().equals(other.getC_name()))
                        && (this.getC_addr() == null ? other.getC_addr() == null : this.getC_addr().equals(other.getC_addr()))
                        && (this.getC_ip() == null ? other.getC_ip() == null : this.getC_ip().equals(other.getC_ip()))
                        && (this.getStake_id() == null ? other.getStake_id() == null : this.getStake_id().equals(other.getStake_id()))
                        && (this.getLongitude_a() == null ? other.getLongitude_a() == null : this.getLongitude_a().equals(other.getLongitude_a()))
                        && (this.getLatitude_a() == null ? other.getLatitude_a() == null : this.getLatitude_a().equals(other.getLatitude_a()))
                        && (this.getLongitude_b() == null ? other.getLongitude_b() == null : this.getLongitude_b().equals(other.getLongitude_b()))
                        && (this.getLatitude_b() == null ? other.getLatitude_b() == null : this.getLatitude_b().equals(other.getLatitude_b()))
                        && (this.getC_memo() == null ? other.getC_memo() == null : this.getC_memo().equals(other.getC_memo()))
                        && (this.getPic_path() == null ? other.getPic_path() == null : this.getPic_path().equals(other.getPic_path()))
                        && (this.getShoot_time() == null ? other.getShoot_time() == null : this.getShoot_time().equals(other.getShoot_time()))
                        && (this.getDirection() == null ? other.getDirection() == null : this.getDirection().equals(other.getDirection()))
                        && (this.getVisible() == null ? other.getVisible() == null : this.getVisible().equals(other.getVisible()))
                        && (this.getIs_public() == null ? other.getIs_public() == null : this.getIs_public().equals(other.getIs_public()))
                        && (this.getLocation_id() == null ? other.getLocation_id() == null : this.getLocation_id().equals(other.getLocation_id()))
                        && (this.getLocation_type() == null ? other.getLocation_type() == null : this.getLocation_type().equals(other.getLocation_type()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getC_id() == null) ? 0 : getC_id().hashCode());
        result = prime * result + ((getH_id() == null) ? 0 : getH_id().hashCode());
        result = prime * result + ((getH_code() == null) ? 0 : getH_code().hashCode());
        result = prime * result + ((getH_name() == null) ? 0 : getH_name().hashCode());
        result = prime * result + ((getSection_id() == null) ? 0 : getSection_id().hashCode());
        result = prime * result + ((getC_type() == null) ? 0 : getC_type().hashCode());
        result = prime * result + ((getC_status() == null) ? 0 : getC_status().hashCode());
        result = prime * result + ((getC_name() == null) ? 0 : getC_name().hashCode());
        result = prime * result + ((getC_addr() == null) ? 0 : getC_addr().hashCode());
        result = prime * result + ((getC_ip() == null) ? 0 : getC_ip().hashCode());
        result = prime * result + ((getStake_id() == null) ? 0 : getStake_id().hashCode());
        result = prime * result + ((getLongitude_a() == null) ? 0 : getLongitude_a().hashCode());
        result = prime * result + ((getLatitude_a() == null) ? 0 : getLatitude_a().hashCode());
        result = prime * result + ((getLongitude_b() == null) ? 0 : getLongitude_b().hashCode());
        result = prime * result + ((getLatitude_b() == null) ? 0 : getLatitude_b().hashCode());
        result = prime * result + ((getC_memo() == null) ? 0 : getC_memo().hashCode());
        result = prime * result + ((getPic_path() == null) ? 0 : getPic_path().hashCode());
        result = prime * result + ((getShoot_time() == null) ? 0 : getShoot_time().hashCode());
        result = prime * result + ((getDirection() == null) ? 0 : getDirection().hashCode());
        result = prime * result + ((getVisible() == null) ? 0 : getVisible().hashCode());
        result = prime * result + ((getIs_public() == null) ? 0 : getIs_public().hashCode());
        result = prime * result + ((getLocation_id() == null) ? 0 : getLocation_id().hashCode());
        result = prime * result + ((getLocation_type() == null) ? 0 : getLocation_type().hashCode());

        return result;
    }

}