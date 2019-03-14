package com.djb.highway.road.dtoclient;

public class CameraPointDTO extends BaseClientDTO {

    // 摄像头ID
    private Integer c_id;
    // 摄像头桩号
    private String stake_id;
    // 图片路径
    private String pic_path;
    // 摄像头地址
    private String c_addr;

    // 经度
    private String longitude_b;

    // 纬度
    private String latitude_b;
    
    private String  location_type;

    public Integer getC_id() {
        return c_id;
    }

    public void setC_id(Integer c_id) {
        this.c_id = c_id;
    }

    public String getStake_id() {
        return stake_id;
    }

    public void setStake_id(String stake_id) {
        this.stake_id = stake_id;
    }

    public String getPic_path() {
        return pic_path;
    }

    public void setPic_path(String pic_path) {
        this.pic_path = pic_path;
    }

    public String getC_addr() {
        return c_addr;
    }

    public void setC_addr(String c_addr) {
        this.c_addr = c_addr;
    }

    public String getLongitude_b() {
        return longitude_b;
    }

    public void setLongitude_b(String longitude_b) {
        this.longitude_b = longitude_b;
    }

    public String getLatitude_b() {
        return latitude_b;
    }

    public void setLatitude_b(String latitude_b) {
        this.latitude_b = latitude_b;
    }

    public String getLocation_type() {
        return location_type;
    }

    public void setLocation_type(String location_type) {
        this.location_type = location_type;
    }

}