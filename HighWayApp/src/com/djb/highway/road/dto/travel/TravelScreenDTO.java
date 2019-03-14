package com.djb.highway.road.dto.travel;

import java.util.List;

import com.djb.highway.framework.dto.BaseDTO;

public class TravelScreenDTO extends BaseDTO {

    private static final long serialVersionUID = -4083558720857806561L;

    // 画面类别(1:即时路况页面(主页),2:行程规划)
    private int screenType;

    private Integer entry_plaz_id;

    private Integer exit_plaz_id;

    private String entry_plaz_code;

    private String exit_plaz_code;

    private String entry_plaz_name;

    private String exit_plaz_name;

    private String road_length;

    private String travel_time;

    private Integer fare;

    // 途径的高速List
    private List<TravelNodeDTO> travelNodeDTOList;

    public int getScreenType() {
        return screenType;
    }

    public void setScreenType(int screenType) {
        this.screenType = screenType;
    }

    public Integer getEntry_plaz_id() {
        return entry_plaz_id;
    }

    public void setEntry_plaz_id(Integer entry_plaz_id) {
        this.entry_plaz_id = entry_plaz_id;
    }

    public Integer getExit_plaz_id() {
        return exit_plaz_id;
    }

    public void setExit_plaz_id(Integer exit_plaz_id) {
        this.exit_plaz_id = exit_plaz_id;
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

    public String getRoad_length() {
        return road_length;
    }

    public void setRoad_length(String road_length) {
        this.road_length = road_length;
    }

    public String getTravel_time() {
        return travel_time;
    }

    public void setTravel_time(String travel_time) {
        this.travel_time = travel_time;
    }

    public Integer getFare() {
        return fare;
    }

    public void setFare(Integer fare) {
        this.fare = fare;
    }

    public List<TravelNodeDTO> getTravelNodeDTOList() {
        return travelNodeDTOList;
    }

    public void setTravelNodeDTOList(List<TravelNodeDTO> travelNodeDTOList) {
        this.travelNodeDTOList = travelNodeDTOList;
    }
}