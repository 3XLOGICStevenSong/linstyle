package com.djb.highway.user.entity;

import java.util.Date;

import com.djb.highway.framework.entity.PageModel;

public class OftenUsedRoadEntity extends PageModel {
    /**
     * 
     */
    private static final long serialVersionUID = 7646770708589906346L;

    // 路段ID
    private Integer our_id;

    // 用户ID
    private Integer u_id;

    // 高速ID
    private Integer h_id;

    // 起点收费站CODE
    private String plaz_code_start;

    // 终点收费站CODE
    private String plaz_code_end;

    // 收藏路段名称
    private String our_road_name;

    // 起点收费站ID
    private Integer plaz_id_start;

    // 终点收费站ID
    private Integer plaz_id_end;

    // 收费站方向
    private String our_direction;

    // 备注
    private String our_memo;

    // 收藏时间
    private Date our_reg_time;

    // 版本时间戳
    private Date our_version_time;
    private String road_type;
    // 客车的类型
    private String car_type;

    public Date getOur_version_time() {
        return our_version_time;
    }

    public void setOur_version_time(Date our_version_time) {
        this.our_version_time = our_version_time;
    }

    public Integer getOur_id() {
        return our_id;
    }

    public void setOur_id(Integer our_id) {
        this.our_id = our_id;
    }

    public Integer getU_id() {
        return u_id;
    }

    public void setU_id(Integer u_id) {
        this.u_id = u_id;
    }

    public Integer getH_id() {
        return h_id;
    }

    public void setH_id(Integer h_id) {
        this.h_id = h_id;
    }

    public String getPlaz_code_start() {
        return plaz_code_start;
    }

    public void setPlaz_code_start(String plaz_code_start) {
        this.plaz_code_start = plaz_code_start;
    }

    public String getPlaz_code_end() {
        return plaz_code_end;
    }

    public void setPlaz_code_end(String plaz_code_end) {
        this.plaz_code_end = plaz_code_end;
    }

    public String getOur_road_name() {
        return our_road_name;
    }

    public void setOur_road_name(String our_road_name) {
        this.our_road_name = our_road_name;
    }

    public Integer getPlaz_id_start() {
        return plaz_id_start;
    }

    public void setPlaz_id_start(Integer plaz_id_start) {
        this.plaz_id_start = plaz_id_start;
    }

    public Integer getPlaz_id_end() {
        return plaz_id_end;
    }

    public void setPlaz_id_end(Integer plaz_id_end) {
        this.plaz_id_end = plaz_id_end;
    }

    public String getOur_direction() {
        return our_direction;
    }

    public void setOur_direction(String our_direction) {
        this.our_direction = our_direction;
    }

    public String getOur_memo() {
        return our_memo;
    }

    public void setOur_memo(String our_memo) {
        this.our_memo = our_memo;
    }

    public Date getOur_reg_time() {
        return our_reg_time;
    }

    public void setOur_reg_time(Date our_reg_time) {
        this.our_reg_time = our_reg_time;
    }

    public String getRoad_type() {
        return road_type;
    }

    public void setRoad_type(String road_type) {
        this.road_type = road_type;
    }

    public String getCar_type() {
        return car_type;
    }

    public void setCar_type(String car_type) {
        this.car_type = car_type;
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
        OftenUsedRoadEntity other = (OftenUsedRoadEntity) that;
        return (this.getOur_id() == null ? other.getOur_id() == null : this.getOur_id().equals(other.getOur_id()))
                        && (this.getU_id() == null ? other.getU_id() == null : this.getU_id().equals(other.getU_id()))
                        && (this.getH_id() == null ? other.getH_id() == null : this.getH_id().equals(other.getH_id()))
                        && (this.getPlaz_id_start() == null ? other.getPlaz_id_start() == null : this.getPlaz_id_start().equals(other.getPlaz_id_start()))
                        && (this.getPlaz_id_end() == null ? other.getPlaz_id_end() == null : this.getPlaz_id_end().equals(other.getPlaz_id_end()))
                        && (this.getOur_direction() == null ? other.getOur_direction() == null : this.getOur_direction().equals(other.getOur_direction()))
                        && (this.getOur_memo() == null ? other.getOur_memo() == null : this.getOur_memo().equals(other.getOur_memo()))
                        && (this.getOur_reg_time() == null ? other.getOur_reg_time() == null : this.getOur_reg_time().equals(other.getOur_reg_time()))
                        && (this.getRoad_type() == null ? other.getRoad_type() == null : this.getRoad_type().equals(other.getRoad_type()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getOur_id() == null) ? 0 : getOur_id().hashCode());
        result = prime * result + ((getU_id() == null) ? 0 : getU_id().hashCode());
        result = prime * result + ((getH_id() == null) ? 0 : getH_id().hashCode());
        result = prime * result + ((getPlaz_id_start() == null) ? 0 : getPlaz_id_start().hashCode());
        result = prime * result + ((getPlaz_id_end() == null) ? 0 : getPlaz_id_end().hashCode());
        result = prime * result + ((getOur_direction() == null) ? 0 : getOur_direction().hashCode());
        result = prime * result + ((getOur_memo() == null) ? 0 : getOur_memo().hashCode());
        result = prime * result + ((getOur_reg_time() == null) ? 0 : getOur_reg_time().hashCode());
        result = prime * result + ((getRoad_type() == null) ? 0 : getRoad_type().hashCode());

        return result;
    }

}