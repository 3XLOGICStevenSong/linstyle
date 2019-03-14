package com.djb.highway.carpool.dtoclient;

import java.util.List;

import com.djb.highway.road.dtoclient.BaseClientDTO;

public class DriverRouteDetailDTO extends BaseClientDTO {
    /**
	 * 
	 */
    private static final long serialVersionUID = 3370946888384377014L;
    
     private Integer driver_route_id;
     
     private String startcity; 
     
     private String startarea;
     
     private String endcity;
     
     private String endarea;
     
     private String starttime;
     
     private String totalnumber;
     
     private String charter; 
     
     private String price;
     
     private  String remark;
     
     private  String tele;
     
     private  List<PassengerRouteDetailDTO> passengerRouteList ;

     // 路线状态标识 ：0进行中，1已完成,2已取消 3:已过期
     private String state_flg;
     
     // 座位数量
     private Integer seat_num;
     // 剩余数量
     private Integer sup_num;
     
    public Integer getDriver_route_id() {
        return driver_route_id;
    }

    public void setDriver_route_id(Integer driver_route_id) {
        this.driver_route_id = driver_route_id;
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

    public String getTotalnumber() {
        return totalnumber;
    }

    public void setTotalnumber(String totalnumber) {
        this.totalnumber = totalnumber;
    }

    public String getCharter() {
        return charter;
    }

    public void setCharter(String charter) {
        this.charter = charter;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getTele() {
        return tele;
    }

    public void setTele(String tele) {
        this.tele = tele;
    }

    public List<PassengerRouteDetailDTO> getPassengerRouteList() {
        return passengerRouteList;
    }

    public void setPassengerRouteList(List<PassengerRouteDetailDTO> passengerRouteList) {
        this.passengerRouteList = passengerRouteList;
    }

    public String getState_flg() {
        return state_flg;
    }

    public void setState_flg(String state_flg) {
        this.state_flg = state_flg;
    }

    public Integer getSeat_num() {
        return seat_num;
    }

    public void setSeat_num(Integer seat_num) {
        this.seat_num = seat_num;
    }

    public Integer getSup_num() {
        return sup_num;
    }

    public void setSup_num(Integer sup_num) {
        this.sup_num = sup_num;
    }
     
      


}