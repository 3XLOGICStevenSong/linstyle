package com.djb.highway.road.dtoclient;

public class PlazaClientDTO extends BaseClientDTO {

    // 收费站叹号flg（0：没有叹号，1：有叹号）
    private int plaz_flg;

    private Integer plaz_id;

    private String plaz_code;

    private String plaz_name;

    private String stake_id;

    private String h_id;

    public int getPlaz_flg() {
        return plaz_flg;
    }

    public void setPlaz_flg(int plaz_flg) {
        this.plaz_flg = plaz_flg;
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

    public String getStake_id() {
        return stake_id;
    }

    public void setStake_id(String stake_id) {
        this.stake_id = stake_id;
    }

    public Integer getPlaz_id() {
        return plaz_id;
    }

    public void setPlaz_id(Integer plaz_id) {
        this.plaz_id = plaz_id;
    }

    public String getH_id() {
        return h_id;
    }

    public void setH_id(String h_id) {
        this.h_id = h_id;
    }

}