package com.djb.highway.carpool.dtoclient;

import java.util.Date;
import java.util.List;


import com.djb.highway.road.dtoclient.BaseClientDTO;

public class PassengerRouteDetailDTO extends BaseClientDTO {
    /**
	 * 
	 */
    private static final long serialVersionUID = 3370946888384377014L;

    private Integer passenger_route_id;

    private String name;

    private String startcity;

    private String startarea;

    private String endcity;

    private String endarea;

    private String starttime;

    private Integer number;

    private String charter;

    private Float price;

    private String  remark;
    
    private String  state;

    private CarpoolUserClientDTO carpoolUser; 
    
    private DriverClientDTO driver; 
    
    public Integer getPassenger_route_id() {
        return passenger_route_id;
    }

    public void setPassenger_route_id(Integer passenger_route_id) {
        this.passenger_route_id = passenger_route_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartcity() {
        return startcity;
    }

    public void setStartcity(String startcity) {
        this.startcity = startcity;
    }

    public String getStartarea() {
        return startarea;
    }

    public void setStartarea(String startarea) {
        this.startarea = startarea;
    }

    public String getEndcity() {
        return endcity;
    }

    public void setEndcity(String endcity) {
        this.endcity = endcity;
    }

    public String getEndarea() {
        return endarea;
    }

    public void setEndarea(String endarea) {
        this.endarea = endarea;
    }


    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getCharter() {
        return charter;
    }

    public void setCharter(String charter) {
        this.charter = charter;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }


    public CarpoolUserClientDTO getCarpoolUser() {
        return carpoolUser;
    }

    public void setCarpoolUser(CarpoolUserClientDTO carpoolUser) {
        this.carpoolUser = carpoolUser;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    
    public DriverClientDTO getDriver() {
        return driver;
    }

    public void setDriver(DriverClientDTO driver) {
        this.driver = driver;
    }
    
}