package com.djb.highway.road.entity;

import java.util.Date;

public class PlazaEntity extends BaseTravelPlanEntity {
    /**
	 * 
	 */
    private static final long serialVersionUID = -6479407447959908792L;

    private Integer plaz_id;

    private String plaz_code;

    private String plaz_name;

    private Integer h_id;

    private String h_code;

    private String h_name;

    private String stake_id;

    private String plaz_city;

    private String location_desc;

    private String entry_all;

    private String exit_all;

    private String etc_flg;

    private String plaz_exit_place;

    private String plaz_enter_place;

    private String entry_pass_condition;

    private String exit_pass_condition;

    private String longitude_a;

    private String latitude_a;

    private String longitude_b;

    private String latitude_b;

    private Integer c_id;

    private String pic_url;

    private String plaz_status;

    private Date create_time;

    private Date update_time;

    private String manage_code;
    private String manage_name;
    private String city_code;

    // 参数
    private String[] roadNames;

    private String[] plazaCodeArgs;

    public Integer getPlaz_id() {
        return plaz_id;
    }

    public void setPlaz_id(Integer plaz_id) {
        this.plaz_id = plaz_id;
    }

    public String getPlaz_code() {
        return plaz_code;
    }

    public void setPlaz_code(String plaz_code) {
        this.plaz_code = plaz_code;
    }

    public String getPlaz_name() {
        return plaz_name;
    }

    public void setPlaz_name(String plaz_name) {
        this.plaz_name = plaz_name;
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

    public String getStake_id() {
        return stake_id;
    }

    public void setStake_id(String stake_id) {
        this.stake_id = stake_id;
    }

    public String getPlaz_city() {
        return plaz_city;
    }

    public void setPlaz_city(String plaz_city) {
        this.plaz_city = plaz_city;
    }

    public String getLocation_desc() {
        return location_desc;
    }

    public void setLocation_desc(String location_desc) {
        this.location_desc = location_desc;
    }

    public String getEntry_all() {
        return entry_all;
    }

    public void setEntry_all(String entry_all) {
        this.entry_all = entry_all;
    }

    public String getExit_all() {
        return exit_all;
    }

    public void setExit_all(String exit_all) {
        this.exit_all = exit_all;
    }

    public String getEtc_flg() {
        return etc_flg;
    }

    public void setEtc_flg(String etc_flg) {
        this.etc_flg = etc_flg;
    }

    public String getPlaz_exit_place() {
        return plaz_exit_place;
    }

    public void setPlaz_exit_place(String plaz_exit_place) {
        this.plaz_exit_place = plaz_exit_place;
    }

    public String getPlaz_enter_place() {
        return plaz_enter_place;
    }

    public void setPlaz_enter_place(String plaz_enter_place) {
        this.plaz_enter_place = plaz_enter_place;
    }

    public String getEntry_pass_condition() {
        return entry_pass_condition;
    }

    public void setEntry_pass_condition(String entry_pass_condition) {
        this.entry_pass_condition = entry_pass_condition;
    }

    public String getExit_pass_condition() {
        return exit_pass_condition;
    }

    public void setExit_pass_condition(String exit_pass_condition) {
        this.exit_pass_condition = exit_pass_condition;
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

    public Integer getC_id() {
        return c_id;
    }

    public void setC_id(Integer c_id) {
        this.c_id = c_id;
    }

    public String getPic_url() {
        return pic_url;
    }

    public void setPic_url(String pic_url) {
        this.pic_url = pic_url;
    }

    public String getPlaz_status() {
        return plaz_status;
    }

    public void setPlaz_status(String plaz_status) {
        this.plaz_status = plaz_status;
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

    public String getManage_code() {
        return manage_code;
    }

    public void setManage_code(String manage_code) {
        this.manage_code = manage_code;
    }

    public String getManage_name() {
        return manage_name;
    }

    public void setManage_name(String manage_name) {
        this.manage_name = manage_name;
    }

    public String[] getRoadNames() {
        return roadNames;
    }

    public void setRoadNames(String[] roadNames) {
        this.roadNames = roadNames;
    }

    public String getCity_code() {
        return city_code;
    }

    public void setCity_code(String city_code) {
        this.city_code = city_code;
    }

    public String[] getPlazaCodeArgs() {
        return plazaCodeArgs;
    }

    public void setPlazaCodeArgs(String[] plazaCodeArgs) {
        this.plazaCodeArgs = plazaCodeArgs;
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
        PlazaEntity other = (PlazaEntity) that;
        return (this.getPlaz_id() == null ? other.getPlaz_id() == null : this.getPlaz_id().equals(other.getPlaz_id()))
                        && (this.getPlaz_code() == null ? other.getPlaz_code() == null : this.getPlaz_code().equals(other.getPlaz_code()))
                        && (this.getPlaz_name() == null ? other.getPlaz_name() == null : this.getPlaz_name().equals(other.getPlaz_name()))
                        && (this.getH_id() == null ? other.getH_id() == null : this.getH_id().equals(other.getH_id()))
                        && (this.getH_code() == null ? other.getH_code() == null : this.getH_code().equals(other.getH_code()))
                        && (this.getH_name() == null ? other.getH_name() == null : this.getH_name().equals(other.getH_name()))
                        && (this.getStake_id() == null ? other.getStake_id() == null : this.getStake_id().equals(other.getStake_id()))
                        && (this.getPlaz_city() == null ? other.getPlaz_city() == null : this.getPlaz_city().equals(other.getPlaz_city()))
                        && (this.getLocation_desc() == null ? other.getLocation_desc() == null : this.getLocation_desc().equals(other.getLocation_desc()))
                        && (this.getEntry_all() == null ? other.getEntry_all() == null : this.getEntry_all().equals(other.getEntry_all()))
                        && (this.getExit_all() == null ? other.getExit_all() == null : this.getExit_all().equals(other.getExit_all()))
                        && (this.getEtc_flg() == null ? other.getEtc_flg() == null : this.getEtc_flg().equals(other.getEtc_flg()))
                        && (this.getPlaz_exit_place() == null ? other.getPlaz_exit_place() == null : this.getPlaz_exit_place().equals(
                                        other.getPlaz_exit_place()))
                        && (this.getPlaz_enter_place() == null ? other.getPlaz_enter_place() == null : this.getPlaz_enter_place().equals(
                                        other.getPlaz_enter_place()))
                        && (this.getEntry_pass_condition() == null ? other.getEntry_pass_condition() == null : this.getEntry_pass_condition().equals(
                                        other.getEntry_pass_condition()))
                        && (this.getExit_pass_condition() == null ? other.getExit_pass_condition() == null : this.getExit_pass_condition().equals(
                                        other.getExit_pass_condition()))
                        && (this.getLongitude_a() == null ? other.getLongitude_a() == null : this.getLongitude_a().equals(other.getLongitude_a()))
                        && (this.getLatitude_a() == null ? other.getLatitude_a() == null : this.getLatitude_a().equals(other.getLatitude_a()))
                        && (this.getLongitude_b() == null ? other.getLongitude_b() == null : this.getLongitude_b().equals(other.getLongitude_b()))
                        && (this.getLatitude_b() == null ? other.getLatitude_b() == null : this.getLatitude_b().equals(other.getLatitude_b()))
                        && (this.getC_id() == null ? other.getC_id() == null : this.getC_id().equals(other.getC_id()))
                        && (this.getPic_url() == null ? other.getPic_url() == null : this.getPic_url().equals(other.getPic_url()))
                        && (this.getPlaz_status() == null ? other.getPlaz_status() == null : this.getPlaz_status().equals(other.getPlaz_status()))
                        && (this.getCreate_time() == null ? other.getCreate_time() == null : this.getCreate_time().equals(other.getCreate_time()))
                        && (this.getUpdate_time() == null ? other.getUpdate_time() == null : this.getUpdate_time().equals(other.getUpdate_time()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getPlaz_id() == null) ? 0 : getPlaz_id().hashCode());
        result = prime * result + ((getPlaz_code() == null) ? 0 : getPlaz_code().hashCode());
        result = prime * result + ((getPlaz_name() == null) ? 0 : getPlaz_name().hashCode());
        result = prime * result + ((getH_id() == null) ? 0 : getH_id().hashCode());
        result = prime * result + ((getH_code() == null) ? 0 : getH_code().hashCode());
        result = prime * result + ((getH_name() == null) ? 0 : getH_name().hashCode());
        result = prime * result + ((getStake_id() == null) ? 0 : getStake_id().hashCode());
        result = prime * result + ((getPlaz_city() == null) ? 0 : getPlaz_city().hashCode());
        result = prime * result + ((getLocation_desc() == null) ? 0 : getLocation_desc().hashCode());
        result = prime * result + ((getEntry_all() == null) ? 0 : getEntry_all().hashCode());
        result = prime * result + ((getExit_all() == null) ? 0 : getExit_all().hashCode());
        result = prime * result + ((getEtc_flg() == null) ? 0 : getEtc_flg().hashCode());
        result = prime * result + ((getPlaz_exit_place() == null) ? 0 : getPlaz_exit_place().hashCode());
        result = prime * result + ((getPlaz_enter_place() == null) ? 0 : getPlaz_enter_place().hashCode());
        result = prime * result + ((getEntry_pass_condition() == null) ? 0 : getEntry_pass_condition().hashCode());
        result = prime * result + ((getExit_pass_condition() == null) ? 0 : getExit_pass_condition().hashCode());
        result = prime * result + ((getLongitude_a() == null) ? 0 : getLongitude_a().hashCode());
        result = prime * result + ((getLatitude_a() == null) ? 0 : getLatitude_a().hashCode());
        result = prime * result + ((getLongitude_b() == null) ? 0 : getLongitude_b().hashCode());
        result = prime * result + ((getLatitude_b() == null) ? 0 : getLatitude_b().hashCode());
        result = prime * result + ((getC_id() == null) ? 0 : getC_id().hashCode());
        result = prime * result + ((getPic_url() == null) ? 0 : getPic_url().hashCode());
        result = prime * result + ((getPlaz_status() == null) ? 0 : getPlaz_status().hashCode());
        result = prime * result + ((getCreate_time() == null) ? 0 : getCreate_time().hashCode());
        result = prime * result + ((getUpdate_time() == null) ? 0 : getUpdate_time().hashCode());
        return result;
    }
}