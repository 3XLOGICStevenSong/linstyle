package com.djb.highway.road.entity;

import java.util.Date;

public class SectionEntity extends BaseTravelPlanEntity {
    /**
	 * 
	 */
    private static final long serialVersionUID = 4006725059963353442L;

    private Integer section_id;

    private Integer h_id;

    private String h_code;

    private String h_name;

    private String section_name;

    private String section_type;

    private String section_status;

    private Date create_time;

    private Date update_time;

    private String direction;

    private Float speed;

    private Float volumn;

    private String level;

    public Integer getSection_id() {
        return section_id;
    }

    public void setSection_id(Integer section_id) {
        this.section_id = section_id;
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

    public String getSection_name() {
        return section_name;
    }

    public void setSection_name(String section_name) {
        this.section_name = section_name;
    }

    public String getSection_type() {
        return section_type;
    }

    public void setSection_type(String section_type) {
        this.section_type = section_type;
    }

    public String getSection_status() {
        return section_status;
    }

    public void setSection_status(String section_status) {
        this.section_status = section_status;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public Float getSpeed() {
        return speed;
    }

    public void setSpeed(Float speed) {
        this.speed = speed;
    }

    public Float getVolumn() {
        return volumn;
    }

    public void setVolumn(Float volumn) {
        this.volumn = volumn;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
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
        SectionEntity other = (SectionEntity) that;
        return (this.getSection_id() == null ? other.getSection_id() == null : this.getSection_id().equals(other.getSection_id()))
                        && (this.getH_id() == null ? other.getH_id() == null : this.getH_id().equals(other.getH_id()))
                        && (this.getH_code() == null ? other.getH_code() == null : this.getH_code().equals(other.getH_code()))
                        && (this.getH_name() == null ? other.getH_name() == null : this.getH_name().equals(other.getH_name()))
                        && (this.getSection_name() == null ? other.getSection_name() == null : this.getSection_name().equals(other.getSection_name()))
                        && (this.getSection_type() == null ? other.getSection_type() == null : this.getSection_type().equals(other.getSection_type()))
                        && (this.getSection_status() == null ? other.getSection_status() == null : this.getSection_status().equals(other.getSection_status()))
                        && (this.getCreate_time() == null ? other.getCreate_time() == null : this.getCreate_time().equals(other.getCreate_time()))
                        && (this.getUpdate_time() == null ? other.getUpdate_time() == null : this.getUpdate_time().equals(other.getUpdate_time()))
                        && (this.getDirection() == null ? other.getDirection() == null : this.getDirection().equals(other.getDirection()))
                        && (this.getSpeed() == null ? other.getSpeed() == null : this.getSpeed().equals(other.getSpeed()))
                        && (this.getVolumn() == null ? other.getVolumn() == null : this.getVolumn().equals(other.getVolumn()))
                        && (this.getLevel() == null ? other.getLevel() == null : this.getLevel().equals(other.getLevel()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getSection_id() == null) ? 0 : getSection_id().hashCode());
        result = prime * result + ((getH_id() == null) ? 0 : getH_id().hashCode());
        result = prime * result + ((getH_code() == null) ? 0 : getH_code().hashCode());
        result = prime * result + ((getH_name() == null) ? 0 : getH_name().hashCode());
        result = prime * result + ((getSection_name() == null) ? 0 : getSection_name().hashCode());
        result = prime * result + ((getSection_type() == null) ? 0 : getSection_type().hashCode());
        result = prime * result + ((getSection_status() == null) ? 0 : getSection_status().hashCode());
        result = prime * result + ((getCreate_time() == null) ? 0 : getCreate_time().hashCode());
        result = prime * result + ((getUpdate_time() == null) ? 0 : getUpdate_time().hashCode());
        result = prime * result + ((getDirection() == null) ? 0 : getDirection().hashCode());
        result = prime * result + ((getSpeed() == null) ? 0 : getSpeed().hashCode());
        result = prime * result + ((getVolumn() == null) ? 0 : getVolumn().hashCode());
        result = prime * result + ((getLevel() == null) ? 0 : getLevel().hashCode());
        return result;
    }
}