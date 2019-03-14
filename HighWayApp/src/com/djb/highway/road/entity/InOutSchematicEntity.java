package com.djb.highway.road.entity;

import java.util.Date;

import com.djb.highway.framework.entity.PageModel;

public class InOutSchematicEntity extends PageModel {

    /**
	 * 
	 */
    private static final long serialVersionUID = -2649667952492299655L;

    private Integer s_id;
    private Integer plaza_id;
    private String direction_content;
    private String pic_path;

    private String plaz_code;

    public String getPlaz_code() {
        return plaz_code;
    }

    public void setPlaz_code(String plaz_code) {
        this.plaz_code = plaz_code;
    }

    public Integer getS_id() {
        return s_id;
    }

    public void setS_id(Integer s_id) {
        this.s_id = s_id;
    }

    public Integer getPlaza_id() {
        return plaza_id;
    }

    public void setPlaza_id(Integer plaza_id) {
        this.plaza_id = plaza_id;
    }

    public String getDirection_content() {
        return direction_content;
    }

    public void setDirection_content(String direction_content) {
        this.direction_content = direction_content;
    }

    public String getPic_path() {
        return pic_path;
    }

    public void setPic_path(String pic_path) {
        this.pic_path = pic_path;
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
        InOutSchematicEntity other = (InOutSchematicEntity) that;
        return (this.getS_id() == null ? other.getS_id() == null : this.getS_id().equals(other.getS_id()))
                        && (this.getPlaza_id() == null ? other.getPlaza_id() == null : this.getPlaza_id().equals(other.getPlaza_id()))
                        && (this.getDirection_content() == null ? other.getDirection_content() == null : this.getDirection_content().equals(
                                        other.getDirection_content()))
                        && (this.getPic_path() == null ? other.getPic_path() == null : this.getPic_path().equals(other.getPic_path()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getS_id() == null) ? 0 : getS_id().hashCode());
        result = prime * result + ((getPlaza_id() == null) ? 0 : getPlaza_id().hashCode());
        result = prime * result + ((getDirection_content() == null) ? 0 : getDirection_content().hashCode());
        result = prime * result + ((getPic_path() == null) ? 0 : getPic_path().hashCode());
        return result;
    }

}