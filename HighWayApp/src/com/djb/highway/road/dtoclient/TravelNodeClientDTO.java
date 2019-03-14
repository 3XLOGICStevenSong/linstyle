package com.djb.highway.road.dtoclient;

import java.util.List;

public class TravelNodeClientDTO {

    private Integer h_id;

    private String h_code;

    private String h_name;

    // 高速叹号flg（0：没有叹号，1：有叹号）
    private int h_flg;

    private String entry_plaz_code;

    private String exit_plaz_code;

    private String entry_plaz_name;

    private String exit_plaz_name;

    private int plaz_count;

    // 画面元素list
    private List<TravelElementClientDTO> travelElementDTOList;

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

    public int getH_flg() {
        return h_flg;
    }

    public void setH_flg(int h_flg) {
        this.h_flg = h_flg;
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

    public String getEntry_plaz_name() {
        return entry_plaz_name;
    }

    public void setEntry_plaz_name(String entry_plaz_name) {
        this.entry_plaz_name = entry_plaz_name;
    }

    public String getExit_plaz_name() {
        return exit_plaz_name;
    }

    public void setExit_plaz_name(String exit_plaz_name) {
        this.exit_plaz_name = exit_plaz_name;
    }

    public int getPlaz_count() {
        return plaz_count;
    }

    public void setPlaz_count(int plaz_count) {
        this.plaz_count = plaz_count;
    }

    public List<TravelElementClientDTO> getTravelElementDTOList() {
        return travelElementDTOList;
    }

    public void setTravelElementDTOList(List<TravelElementClientDTO> travelElementDTOList) {
        this.travelElementDTOList = travelElementDTOList;
    }

}
