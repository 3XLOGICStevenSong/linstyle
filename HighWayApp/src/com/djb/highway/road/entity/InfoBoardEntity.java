package com.djb.highway.road.entity;

import java.util.Date;

public class InfoBoardEntity extends BaseTravelPlanEntity {
    /**
	 * 
	 */
    private static final long serialVersionUID = 2894162589292268110L;

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

    private String stakeIdStart;

    private String stakeIdEnd;

    private String[] idsAgs;

    private String type;

    private String location_type;

    private Integer location_id;

    public String[] getIdsAgs() {
        return idsAgs;
    }

    public void setIdsAgs(String[] idsAgs) {
        this.idsAgs = idsAgs;
    }

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLocation_type() {
        return location_type;
    }

    public void setLocation_type(String location_type) {
        this.location_type = location_type;
    }

    public Integer getLocation_id() {
        return location_id;
    }

    public void setLocation_id(Integer location_id) {
        this.location_id = location_id;
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
        InfoBoardEntity other = (InfoBoardEntity) that;
        return (this.getIb_id() == null ? other.getIb_id() == null : this.getIb_id().equals(other.getIb_id()))
                        && (this.getH_id() == null ? other.getH_id() == null : this.getH_id().equals(other.getH_id()))
                        && (this.getH_code() == null ? other.getH_code() == null : this.getH_code().equals(other.getH_code()))
                        && (this.getH_name() == null ? other.getH_name() == null : this.getH_name().equals(other.getH_name()))
                        && (this.getSection_id() == null ? other.getSection_id() == null : this.getSection_id().equals(other.getSection_id()))
                        && (this.getIb_addr() == null ? other.getIb_addr() == null : this.getIb_addr().equals(other.getIb_addr()))
                        && (this.getStake_id() == null ? other.getStake_id() == null : this.getStake_id().equals(other.getStake_id()))
                        && (this.getIb_status() == null ? other.getIb_status() == null : this.getIb_status().equals(other.getIb_status()))
                        && (this.getLongitude_a() == null ? other.getLongitude_a() == null : this.getLongitude_a().equals(other.getLongitude_a()))
                        && (this.getLatitude_a() == null ? other.getLatitude_a() == null : this.getLatitude_a().equals(other.getLatitude_a()))
                        && (this.getLongitude_b() == null ? other.getLongitude_b() == null : this.getLongitude_b().equals(other.getLongitude_b()))
                        && (this.getLatitude_b() == null ? other.getLatitude_b() == null : this.getLatitude_b().equals(other.getLatitude_b()))
                        && (this.getIb_content() == null ? other.getIb_content() == null : this.getIb_content().equals(other.getIb_content()))
                        && (this.getPic_path() == null ? other.getPic_path() == null : this.getPic_path().equals(other.getPic_path()))
                        && (this.getDeploy_time() == null ? other.getDeploy_time() == null : this.getDeploy_time().equals(other.getDeploy_time()))
                        && (this.getDirection() == null ? other.getDirection() == null : this.getDirection().equals(other.getDirection()))
                        && (this.getVisible() == null ? other.getVisible() == null : this.getVisible().equals(other.getVisible()))
                        && (this.getIs_public() == null ? other.getIs_public() == null : this.getIs_public().equals(other.getIs_public()))
                        && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
                        && (this.getLocation_type() == null ? other.getLocation_type() == null : this.getLocation_type().equals(other.getLocation_type()))
                        && (this.getLocation_id() == null ? other.getLocation_id() == null : this.getLocation_id().equals(other.getLocation_id()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getIb_id() == null) ? 0 : getIb_id().hashCode());
        result = prime * result + ((getH_id() == null) ? 0 : getH_id().hashCode());
        result = prime * result + ((getH_code() == null) ? 0 : getH_code().hashCode());
        result = prime * result + ((getH_name() == null) ? 0 : getH_name().hashCode());
        result = prime * result + ((getSection_id() == null) ? 0 : getSection_id().hashCode());
        result = prime * result + ((getIb_addr() == null) ? 0 : getIb_addr().hashCode());
        result = prime * result + ((getStake_id() == null) ? 0 : getStake_id().hashCode());
        result = prime * result + ((getIb_status() == null) ? 0 : getIb_status().hashCode());
        result = prime * result + ((getLongitude_a() == null) ? 0 : getLongitude_a().hashCode());
        result = prime * result + ((getLatitude_a() == null) ? 0 : getLatitude_a().hashCode());
        result = prime * result + ((getLongitude_b() == null) ? 0 : getLongitude_b().hashCode());
        result = prime * result + ((getLatitude_b() == null) ? 0 : getLatitude_b().hashCode());
        result = prime * result + ((getIb_content() == null) ? 0 : getIb_content().hashCode());
        result = prime * result + ((getPic_path() == null) ? 0 : getPic_path().hashCode());
        result = prime * result + ((getDeploy_time() == null) ? 0 : getDeploy_time().hashCode());
        result = prime * result + ((getDirection() == null) ? 0 : getDirection().hashCode());
        result = prime * result + ((getVisible() == null) ? 0 : getVisible().hashCode());
        result = prime * result + ((getIs_public() == null) ? 0 : getIs_public().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getLocation_type() == null) ? 0 : getLocation_type().hashCode());
        result = prime * result + ((getLocation_id() == null) ? 0 : getLocation_id().hashCode());
        return result;
    }
}