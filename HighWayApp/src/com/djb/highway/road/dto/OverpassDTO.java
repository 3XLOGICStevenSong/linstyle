package com.djb.highway.road.dto;

import java.util.Date;
import java.util.List;

import com.djb.highway.framework.dto.BaseDTO;

public class OverpassDTO extends BaseDTO {

    /**
	 * 
	 */
    private static final long serialVersionUID = -8841800775615297873L;

    private Integer od_id;

    // 位置1(高速上面):1 位置2(高速下面):2
    /*
     * 高速上面的立交为终点 
     * LocationFlg：1 
     * ExitOverpass 
     * 高速下面的立交为起点 
     * LocationFlg：2
     * EntryOverpass
     */
    private int locationFlg;

    private int sort;

    private Integer o_id;

    private String o_code;

    private String o_name;

    private String o_city;

    private String o_desc;

    private String o_length;

    private String o_type;

    private String o_cross_type;

    private String o_status;

    private String o_pic_1;

    private String o_pic_2;

    private String o_pic_3;

    private Date create_time;

    private Date update_time;

    private String o_corss_line;

    private String entry_plaz_code;

    private String exit_plaz_code;

    private String entry_stake_id;

    private String exit_stake_id;

    private Integer entry_highway_id;

    private String entry_highway_code;

    private String entry_highway_name;

    private Integer exit_highway_id;

    private String exit_highway_code;

    private String exit_highway_name;

    private List<OverpassDetailDTO> overpassDetailDTOs;

    public Integer getOd_id() {
        return od_id;
    }

    public void setOd_id(Integer od_id) {
        this.od_id = od_id;
    }

    public int getLocationFlg() {
        return locationFlg;
    }

    public void setLocationFlg(int locationFlg) {
        this.locationFlg = locationFlg;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public Integer getO_id() {
        return o_id;
    }

    public void setO_id(Integer o_id) {
        this.o_id = o_id;
    }

    public String getO_code() {
        return o_code;
    }

    public void setO_code(String o_code) {
        this.o_code = o_code;
    }

    public String getO_name() {
        return o_name;
    }

    public void setO_name(String o_name) {
        this.o_name = o_name;
    }

    public String getO_city() {
        return o_city;
    }

    public void setO_city(String o_city) {
        this.o_city = o_city;
    }

    public String getO_desc() {
        return o_desc;
    }

    public void setO_desc(String o_desc) {
        this.o_desc = o_desc;
    }

    public String getO_length() {
        return o_length;
    }

    public void setO_length(String o_length) {
        this.o_length = o_length;
    }

    public String getO_type() {
        return o_type;
    }

    public void setO_type(String o_type) {
        this.o_type = o_type;
    }

    public String getO_cross_type() {
        return o_cross_type;
    }

    public void setO_cross_type(String o_cross_type) {
        this.o_cross_type = o_cross_type;
    }

    public String getO_status() {
        return o_status;
    }

    public void setO_status(String o_status) {
        this.o_status = o_status;
    }

    public String getO_pic_1() {
        return o_pic_1;
    }

    public void setO_pic_1(String o_pic_1) {
        this.o_pic_1 = o_pic_1;
    }

    public String getO_pic_2() {
        return o_pic_2;
    }

    public void setO_pic_2(String o_pic_2) {
        this.o_pic_2 = o_pic_2;
    }

    public String getO_pic_3() {
        return o_pic_3;
    }

    public void setO_pic_3(String o_pic_3) {
        this.o_pic_3 = o_pic_3;
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

    public String getO_corss_line() {
        return o_corss_line;
    }

    public void setO_corss_line(String o_corss_line) {
        this.o_corss_line = o_corss_line;
    }

    public String getEntry_plaz_code() {
        return entry_plaz_code;
    }

    public void setEntry_plaz_code(String entry_plaz_code) {
        this.entry_plaz_code = entry_plaz_code;
    }

    public String getExit_plaz_code() {
        return exit_plaz_code;
    }

    public void setExit_plaz_code(String exit_plaz_code) {
        this.exit_plaz_code = exit_plaz_code;
    }

    public String getExit_stake_id() {
        return exit_stake_id;
    }

    public void setExit_stake_id(String exit_stake_id) {
        this.exit_stake_id = exit_stake_id;
    }

    public String getEntry_stake_id() {
        return entry_stake_id;
    }

    public void setEntry_stake_id(String entry_stake_id) {
        this.entry_stake_id = entry_stake_id;
    }

    public Integer getEntry_highway_id() {
        return entry_highway_id;
    }

    public void setEntry_highway_id(Integer entry_highway_id) {
        this.entry_highway_id = entry_highway_id;
    }

    public String getEntry_highway_code() {
        return entry_highway_code;
    }

    public void setEntry_highway_code(String entry_highway_code) {
        this.entry_highway_code = entry_highway_code;
    }

    public String getEntry_highway_name() {
        return entry_highway_name;
    }

    public void setEntry_highway_name(String entry_highway_name) {
        this.entry_highway_name = entry_highway_name;
    }

    public Integer getExit_highway_id() {
        return exit_highway_id;
    }

    public void setExit_highway_id(Integer exit_highway_id) {
        this.exit_highway_id = exit_highway_id;
    }

    public String getExit_highway_code() {
        return exit_highway_code;
    }

    public void setExit_highway_code(String exit_highway_code) {
        this.exit_highway_code = exit_highway_code;
    }

    public String getExit_highway_name() {
        return exit_highway_name;
    }

    public void setExit_highway_name(String exit_highway_name) {
        this.exit_highway_name = exit_highway_name;
    }

    public List<OverpassDetailDTO> getOverpassDetailDTOs() {
        return overpassDetailDTOs;
    }

    public void setOverpassDetailDTOs(List<OverpassDetailDTO> overpassDetailDTOs) {
        this.overpassDetailDTOs = overpassDetailDTOs;
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

        OverpassDTO other = (OverpassDTO) that;
        return (this.getO_id() == null ? other.getO_id() == null : this.getO_id().equals(other.getO_id()))
                        && (this.getO_code() == null ? other.getO_code() == null : this.getO_code().equals(other.getO_code()))
                        && (this.getO_name() == null ? other.getO_name() == null : this.getO_name().equals(other.getO_name()));
    }

}