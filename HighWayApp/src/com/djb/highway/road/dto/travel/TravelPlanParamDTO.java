package com.djb.highway.road.dto.travel;

import com.djb.highway.framework.dto.BaseDTO;

public class TravelPlanParamDTO extends BaseDTO {

    /**
	 * 
	 */
    private static final long serialVersionUID = -2447773721468696956L;

    // 画面类别(1:即时路况页面(主页),2:行程规划)
    private int screenType;
    private Integer h_id;
    private String h_code;

    // 货车类型
    private Integer truckType1;

    // 优惠货车种类
    private Integer truckType2;

    // 集装箱优惠限制
    private Integer truckType3;

    // 货车轴数
    private Integer truckShaftQTY;

    // 货车载重
    private Float truckLoadQTY;

    // 行驶长度
    private Integer pathLength;

    // 卡车过路费
    private Integer truckFare;

    private String car_type;

    private String entry_plaz_code;

    private String exit_plaz_code;

    // 初始化收藏标记的参数
    private Integer u_id;
    private String road_type;

    public int getScreenType() {
        return screenType;
    }

    public void setScreenType(int screenType) {
        this.screenType = screenType;
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

    public Integer getTruckType1() {
        return truckType1;
    }

    public void setTruckType1(Integer truckType1) {
        this.truckType1 = truckType1;
    }

    public Integer getTruckType2() {
        return truckType2;
    }

    public void setTruckType2(Integer truckType2) {
        this.truckType2 = truckType2;
    }

    public Integer getTruckType3() {
        return truckType3;
    }

    public void setTruckType3(Integer truckType3) {
        this.truckType3 = truckType3;
    }

    public Integer getTruckShaftQTY() {
        return truckShaftQTY;
    }

    public void setTruckShaftQTY(Integer truckShaftQTY) {
        this.truckShaftQTY = truckShaftQTY;
    }

    public Float getTruckLoadQTY() {
        return truckLoadQTY;
    }

    public void setTruckLoadQTY(Float truckLoadQTY) {
        this.truckLoadQTY = truckLoadQTY;
    }

    public Integer getPathLength() {
        return pathLength;
    }

    public void setPathLength(Integer pathLength) {
        this.pathLength = pathLength;
    }

    public Integer getTruckFare() {
        return truckFare;
    }

    public void setTruckFare(Integer truckFare) {
        this.truckFare = truckFare;
    }

    public String getCar_type() {
        return car_type;
    }

    public void setCar_type(String car_type) {
        this.car_type = car_type;
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

    public Integer getU_id() {
        return u_id;
    }

    public void setU_id(Integer u_id) {
        this.u_id = u_id;
    }

    public String getRoad_type() {
        return road_type;
    }

    public void setRoad_type(String road_type) {
        this.road_type = road_type;
    }

}