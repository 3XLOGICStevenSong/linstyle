package com.djb.highway.road.dto;

import java.util.Date;

import com.djb.highway.framework.dto.BaseDTO;

public class OverpassDetailDTO extends BaseDTO {

    /**
	 * 
	 */
    private static final long serialVersionUID = 109908883467611876L;

    private Integer o_id;

    private Integer od_id;

    private String entry_plaz_code;

    private String exit_plaz_code;

    private String ob_name;

    private String ob_desc;

    private String ob_length;

    private String ob_status;

    private String ob_pic;

    private Integer entry_plaz_id;

    private Integer exit_plaz_id;

    private String entry_stake_id;

    private String exit_stake_id;

    private Date create_time;

    private Date update_time;

    private Integer entry_highway_id;

    private String entry_highway_code;

    private String entry_highway_name;

    private Integer exit_highway_id;

    private String exit_highway_code;

    private String exit_highway_name;

    public Integer getO_id() {
        return o_id;
    }

    public void setO_id(Integer o_id) {
        this.o_id = o_id;
    }

    public Integer getOd_id() {
        return od_id;
    }

    public void setOd_id(Integer od_id) {
        this.od_id = od_id;
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

    public String getOb_name() {
        return ob_name;
    }

    public void setOb_name(String ob_name) {
        this.ob_name = ob_name;
    }

    public String getOb_desc() {
        return ob_desc;
    }

    public void setOb_desc(String ob_desc) {
        this.ob_desc = ob_desc;
    }

    public String getOb_length() {
        return ob_length;
    }

    public void setOb_length(String ob_length) {
        this.ob_length = ob_length;
    }

    public String getOb_status() {
        return ob_status;
    }

    public void setOb_status(String ob_status) {
        this.ob_status = ob_status;
    }

    public String getOb_pic() {
        return ob_pic;
    }

    public void setOb_pic(String ob_pic) {
        this.ob_pic = ob_pic;
    }

    public Integer getEntry_plaz_id() {
        return entry_plaz_id;
    }

    public void setEntry_plaz_id(Integer entry_plaz_id) {
        this.entry_plaz_id = entry_plaz_id;
    }

    public Integer getExit_plaz_id() {
        return exit_plaz_id;
    }

    public void setExit_plaz_id(Integer exit_plaz_id) {
        this.exit_plaz_id = exit_plaz_id;
    }

    public String getEntry_stake_id() {
        return entry_stake_id;
    }

    public void setEntry_stake_id(String entry_stake_id) {
        this.entry_stake_id = entry_stake_id;
    }

    public String getExit_stake_id() {
        return exit_stake_id;
    }

    public void setExit_stake_id(String exit_stake_id) {
        this.exit_stake_id = exit_stake_id;
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
}