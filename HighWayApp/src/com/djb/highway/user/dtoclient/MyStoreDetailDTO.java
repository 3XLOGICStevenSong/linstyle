package com.djb.highway.user.dtoclient;

import com.djb.highway.road.dtoclient.BaseClientDTO;

/**
 * 类说明
 * 
 * @author LiWm E-mail:
 * @version 创建时间：2014年7月10日 上午10:46:39
 */

public class MyStoreDetailDTO extends BaseClientDTO {

    /**
     * 删除数据主件
     */
    private Integer our_id;

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
     * 总钱数
     */
    private String total_money;

    /**
     * 总时间
     */
    private String total_time;

    /**
     * 总距离
     */
    private String total_distance;

    private String car_type;

    public String getTotal_money() {
        return total_money;
    }

    public void setTotal_money(String total_money) {
        this.total_money = total_money;
    }

    public String getTotal_time() {
        return total_time;
    }

    public void setTotal_time(String total_time) {
        this.total_time = total_time;
    }

    public String getTotal_distance() {
        return total_distance;
    }

    public void setTotal_distance(String total_distance) {
        this.total_distance = total_distance;
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

    public String getRoad_type() {
        return road_type;
    }

    public void setRoad_type(String road_type) {
        this.road_type = road_type;
    }

    public Integer getOur_id() {
        return our_id;
    }

    public void setOur_id(Integer our_id) {
        this.our_id = our_id;
    }

    public String getCar_type() {
        return car_type;
    }

    public void setCar_type(String car_type) {
        this.car_type = car_type;
    }

}