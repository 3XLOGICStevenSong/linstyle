package com.djb.highway.road.dto;

import java.util.Date;

import com.djb.highway.framework.dto.BaseDTO;

public class PlazaDTO extends BaseDTO {

    /**
     * 
     */
    private static final long serialVersionUID = -4433174740416001333L;

    // 收费站叹号flg（0：没有叹号，1：有叹号）
    private int plaz_flg;

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
    private String city_code;
    private Integer section_id;
    private String[] roadNames;

    public String[] getRoadNames() {
        return roadNames;
    }

    public void setRoadNames(String[] roadNames) {
        this.roadNames = roadNames;
    }

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

    public int getPlaz_flg() {
        return plaz_flg;
    }

    public void setPlaz_flg(int plaz_flg) {
        this.plaz_flg = plaz_flg;
    }

    public String getCity_code() {
        return city_code;
    }

    public void setCity_code(String city_code) {
        this.city_code = city_code;
    }
	public Integer getSection_id() {
		return section_id;
	}

	public void setSection_id(Integer section_id) {
		this.section_id = section_id;
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

        PlazaDTO other = (PlazaDTO) that;
        return (this.getH_id() == null ? other.getH_id() == null : this.getH_id().equals(other.getH_id()))
                        && (this.getPlaz_id() == null ? other.getPlaz_id() == null : this.getPlaz_id().equals(other.getPlaz_id()))
                        && (this.getPlaz_code() == null ? other.getPlaz_code() == null : this.getPlaz_code().equals(other.getPlaz_code()))
                        && (this.getPlaz_name() == null ? other.getPlaz_name() == null : this.getPlaz_name().equals(other.getPlaz_name()));
    }



}