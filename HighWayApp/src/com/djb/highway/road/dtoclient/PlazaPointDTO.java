package com.djb.highway.road.dtoclient;

public class PlazaPointDTO {
    /**
     * 收费站id
     */
    private Integer plaz_id;
    private String longitude_b;

    private String latitude_b;

    public Integer getPlaz_id() {
        return plaz_id;
    }

    public void setPlaz_id(Integer plaz_id) {
        this.plaz_id = plaz_id;
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

}
