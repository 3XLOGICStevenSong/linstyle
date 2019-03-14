package com.djb.highway.user.dtoclient;

/**
 * 类说明
 * 
 * @author LiWm E-mail:
 * @version 创建时间：2014年7月30日 下午11:20:43
 */
public class MyStoreDtos {
    /**
     * 用户id
     */
    private Integer u_id;

    /**
     * 高速id
     */
    private Integer h_id;

    /**
     * 起点code
     */
    private String plaz_code_start;

    /**
     * 终点code
     */
    private String plaz_code_end;

    /**
     * 路段名
     */
    private String our_road_name;

    /**
     * 路段类型
     */
    private String road_type;

    /**
     * 单排道双排道 路段类型
     */
    private String car_type_code;

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

    public String getRoad_type() {
        return road_type;
    }

    public void setRoad_type(String road_type) {
        this.road_type = road_type;
    }

    public String getCar_type_code() {
        return car_type_code;
    }

    public void setCar_type_code(String car_type_code) {
        this.car_type_code = car_type_code;
    }

}
