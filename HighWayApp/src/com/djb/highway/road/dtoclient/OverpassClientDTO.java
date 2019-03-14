package com.djb.highway.road.dtoclient;

public class OverpassClientDTO extends BaseClientDTO {

    // 位置1(高速上面):1 位置2(高速下面):2
    private int locationFlg;

    private Integer o_id;

    private String o_code;

    private String o_name;

    private String entry_stake_id;

    private String exit_stake_id;

    public int getLocationFlg() {
        return locationFlg;
    }

    public void setLocationFlg(int locationFlg) {
        this.locationFlg = locationFlg;
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

}