package com.djb.highway.user.dto;

import java.util.List;

import com.djb.highway.framework.dto.PageDTO;

public class OftenUsedRoadDTO extends PageDTO {
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
    private String our_reg_time;

    // 版本时间戳
    private String our_version_time;
    private String road_type;

    // 客车类型
    private String car_type;

    public String getOur_version_time() {
        return our_version_time;
    }

    public void setOur_version_time(String our_version_time) {
        this.our_version_time = our_version_time;
    }

    public void setOur_reg_time(String our_reg_time) {
        this.our_reg_time = our_reg_time;
    }

    public String getOur_reg_time() {
        return our_reg_time;
    }

    private List<OftenUsedRoadDTO> list;

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

    public String getOur_road_name() {
        return our_road_name;
    }

    public void setOur_road_name(String our_road_name) {
        this.our_road_name = our_road_name;
    }

    public String getOur_direction() {
        return our_direction;
    }

    public void setOur_direction(String our_direction) {
        this.our_direction = our_direction == null ? null : our_direction.trim();
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

    public String getOur_memo() {
        return our_memo;
    }

    public void setOur_memo(String our_memo) {
        this.our_memo = our_memo == null ? null : our_memo.trim();
    }

    public List<OftenUsedRoadDTO> getList() {
        return list;
    }

    public void setList(List<OftenUsedRoadDTO> list) {
        this.list = list;
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

}