package com.djb.highway.road.dtoclient;

import java.util.List;

public class ServiceAreaClientDTO {
    /**
     * 服务区id
     */
    private Integer areaId;

    /**
     * 服务区名
     */
    private String areaName;

    /**
     * 城市名
     */
    private String cityName;

    /**
     * 桩号
     */
    private String pileNum;

    /**
     * 电话号
     */
    private String telNum;

    /**
     * 下一服务区
     */
    private String nextArea;

    /**
     * 下一收费站
     */
    private String nextGasStation;

    /**
     * 超市标记
     */
    private boolean superMarktFlag;

    /**
     * 餐厅标记
     */
    private boolean restaurantFlag;

    /**
     * 旅馆标记
     */
    private boolean hotelFlag;

    /**
     * 修车标记
     */
    private boolean CarrepairFlag;

    /**
     * 加油站标记
     */
    private boolean gasStationFlag;

    /**
     * 油的种类
     */
    private String gasClass;

    /**
     * 停车场剩余车辆数量
     */
    private String parkNumber;

    /**
     * 服务区摄像头Ids
     */
    private List<Integer> c_ids;

    // 情报板所属的收费站或服务区的Id
    private Integer location_id;
    // 情报板是否显示的标示
    private boolean ib_flag;

    /*
     * 服务内容：餐厅，超市，修车，宾馆，加油站，停车位；
     */

    private String serviceContent;

    // 高速Code
    private String h_code;

    // 高速名称
    private String h_name;

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getPileNum() {
        return pileNum;
    }

    public void setPileNum(String pileNum) {
        this.pileNum = pileNum;
    }

    public String getTelNum() {
        return telNum;
    }

    public void setTelNum(String telNum) {
        this.telNum = telNum;
    }

    public String getNextArea() {
        return nextArea;
    }

    public void setNextArea(String nextArea) {
        this.nextArea = nextArea;
    }

    public String getNextGasStation() {
        return nextGasStation;
    }

    public void setNextGasStation(String nextGasStation) {
        this.nextGasStation = nextGasStation;
    }

    public boolean isSuperMarktFlag() {
        return superMarktFlag;
    }

    public void setSuperMarktFlag(boolean superMarktFlag) {
        this.superMarktFlag = superMarktFlag;
    }

    public boolean isRestaurantFlag() {
        return restaurantFlag;
    }

    public void setRestaurantFlag(boolean restaurantFlag) {
        this.restaurantFlag = restaurantFlag;
    }

    public boolean isHotelFlag() {
        return hotelFlag;
    }

    public void setHotelFlag(boolean hotelFlag) {
        this.hotelFlag = hotelFlag;
    }

    public boolean isCarrepairFlag() {
        return CarrepairFlag;
    }

    public void setCarrepairFlag(boolean carrepairFlag) {
        CarrepairFlag = carrepairFlag;
    }

    public boolean isGasStationFlag() {
        return gasStationFlag;
    }

    public void setGasStationFlag(boolean gasStationFlag) {
        this.gasStationFlag = gasStationFlag;
    }

    public String getGasClass() {
        return gasClass;
    }

    public void setGasClass(String gasClass) {
        this.gasClass = gasClass;
    }

    public String getParkNumber() {
        return parkNumber;
    }

    public void setParkNumber(String parkNumber) {
        this.parkNumber = parkNumber;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public String getServiceContent() {
        return serviceContent;
    }

    public void setServiceContent(String serviceContent) {
        this.serviceContent = serviceContent;
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

    public List<Integer> getC_ids() {
        return c_ids;
    }

    public void setC_ids(List<Integer> c_ids) {
        this.c_ids = c_ids;
    }

    public Integer getLocation_id() {
        return location_id;
    }

    public void setLocation_id(Integer location_id) {
        this.location_id = location_id;
    }

    public boolean isIb_flag() {
        return ib_flag;
    }

    public void setIb_flag(boolean ib_flag) {
        this.ib_flag = ib_flag;
    }

}
