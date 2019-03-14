package com.djb.highway.carpool.dto;

import com.djb.highway.framework.dto.PageDTO;
import java.util.Date;
import java.util.List;

import org.apache.struts.upload.FormFile;

public class DriverRouteDTO extends PageDTO {
    /**
	 * 
	 */
    private static final long serialVersionUID = -6934141444286922097L;

    private Integer dr_id;

    private Integer cu_id;

    private Date start_time;

    private String start_city;

    private String end_city;

    private String charter_flg;

    private Integer total_num;

    private Float total_price;

    private String status_flg;

    private String dr_memo;

    private Date dr_insert_time;

    private Date dr_update_time;

    private List<DriverRouteDTO> list;

    private List<CarpoolRouteDTO> carpoolRouteDTOs;

    private List<PassengerRouteDTO> passengerRouteDTOs;

    // 司机路线出发时间
    private String driverGo_time;

    // 乘客路线ID
    private Integer pr_id;

    private Date end_time;

    private String go_time;
    // 路线状态
    private String state;
    // 路线状态标识 ：0进行中，1已完成,2已取消 3:已过期
    private String state_flg;

    private String result;

    // 行号
    private Integer rowIndex;
    // 行号
    private Integer outerIndex;

    private String dp_flag;// 0:司机 1:乘客

    // 座位数量
    private Integer seat_num;
    // 剩余数量
    private Integer sup_num;
    //乘客人数
    private Integer pass_num;
    
    private String begin_time;
    
    private String pend_time;
    
    
   private Integer car_seat_num;
    
    private String cu_name;

    private String id_number;

    private FormFile driving_license_pic;

    private String car_license_pic;

    private FormFile people_license;

    private String car_brand;

    private String car_type;

    private String car_color;

    private String car_num;
    

    
    public Integer getDr_id() {
        return dr_id;
    }

    public void setDr_id(Integer dr_id) {
        this.dr_id = dr_id;
    }

    public Integer getCu_id() {
        return cu_id;
    }

    public void setCu_id(Integer cu_id) {
        this.cu_id = cu_id;
    }

    public Date getStart_time() {
        return start_time;
    }

    public void setStart_time(Date start_time) {
        this.start_time = start_time;
    }

    public String getStart_city() {
        return start_city;
    }

    public void setStart_city(String start_city) {
        this.start_city = start_city;
    }

    public String getEnd_city() {
        return end_city;
    }

    public void setEnd_city(String end_city) {
        this.end_city = end_city;
    }

    public String getCharter_flg() {
        return charter_flg;
    }

    public void setCharter_flg(String charter_flg) {
        this.charter_flg = charter_flg;
    }

    public Integer getTotal_num() {
        return total_num;
    }

    public void setTotal_num(Integer total_num) {
        this.total_num = total_num;
    }

    public Float getTotal_price() {
        return total_price;
    }

    public void setTotal_price(Float total_price) {
        this.total_price = total_price;
    }

    public String getStatus_flg() {
        return status_flg;
    }

    public void setStatus_flg(String status_flg) {
        this.status_flg = status_flg;
    }

    public String getDr_memo() {
        return dr_memo;
    }

    public void setDr_memo(String dr_memo) {
        this.dr_memo = dr_memo;
    }

    public Date getDr_insert_time() {
        return dr_insert_time;
    }

    public void setDr_insert_time(Date dr_insert_time) {
        this.dr_insert_time = dr_insert_time;
    }

    public Date getDr_update_time() {
        return dr_update_time;
    }

    public void setDr_update_time(Date dr_update_time) {
        this.dr_update_time = dr_update_time;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public List<DriverRouteDTO> getList() {
        return list;
    }

    public void setList(List<DriverRouteDTO> list) {
        this.list = list;
    }

    public List<CarpoolRouteDTO> getCarpoolRouteDTOs() {
        return carpoolRouteDTOs;
    }

    public void setCarpoolRouteDTOs(List<CarpoolRouteDTO> carpoolRouteDTOs) {
        this.carpoolRouteDTOs = carpoolRouteDTOs;
    }

    public List<PassengerRouteDTO> getPassengerRouteDTOs() {
        return passengerRouteDTOs;
    }

    public void setPassengerRouteDTOs(List<PassengerRouteDTO> passengerRouteDTOs) {
        this.passengerRouteDTOs = passengerRouteDTOs;
    }

    public String getDriverGo_time() {
        return driverGo_time;
    }

    public void setDriverGo_time(String driverGo_time) {
        this.driverGo_time = driverGo_time;
    }

    public Integer getPr_id() {
        return pr_id;
    }

    public void setPr_id(Integer pr_id) {
        this.pr_id = pr_id;
    }

    public Date getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Date end_time) {
        this.end_time = end_time;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getState_flg() {
        return state_flg;
    }

    public void setState_flg(String state_flg) {
        this.state_flg = state_flg;
    }

    public String getGo_time() {
        return go_time;
    }

    public void setGo_time(String go_time) {
        this.go_time = go_time;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Integer getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(Integer rowIndex) {
        this.rowIndex = rowIndex;
    }

    public Integer getOuterIndex() {
        return outerIndex;
    }

    public void setOuterIndex(Integer outerIndex) {
        this.outerIndex = outerIndex;
    }

    public String getDp_flag() {
        return dp_flag;
    }

    public void setDp_flag(String dp_flag) {
        this.dp_flag = dp_flag;
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

    public Integer getPass_num() {
        return pass_num;
    }

    public void setPass_num(Integer pass_num) {
        this.pass_num = pass_num;
    }

    public String getBegin_time() {
        return begin_time;
    }

    public void setBegin_time(String begin_time) {
        this.begin_time = begin_time;
    }

    public String getPend_time() {
        return pend_time;
    }

    public void setPend_time(String pend_time) {
        this.pend_time = pend_time;
    }

    public Integer getCar_seat_num() {
        return car_seat_num;
    }

    public void setCar_seat_num(Integer car_seat_num) {
        this.car_seat_num = car_seat_num;
    }

    public String getCu_name() {
        return cu_name;
    }

    public void setCu_name(String cu_name) {
        this.cu_name = cu_name;
    }

    public String getId_number() {
        return id_number;
    }

    public void setId_number(String id_number) {
        this.id_number = id_number;
    }

    public FormFile getDriving_license_pic() {
        return driving_license_pic;
    }

    public void setDriving_license_pic(FormFile driving_license_pic) {
        this.driving_license_pic = driving_license_pic;
    }

    
    public String getCar_license_pic() {
        return car_license_pic;
    }

    public void setCar_license_pic(String car_license_pic) {
        this.car_license_pic = car_license_pic;
    }

    public FormFile getPeople_license() {
        return people_license;
    }

    public void setPeople_license(FormFile people_license) {
        this.people_license = people_license;
    }

    public String getCar_brand() {
        return car_brand;
    }

    public void setCar_brand(String car_brand) {
        this.car_brand = car_brand;
    }

    public String getCar_type() {
        return car_type;
    }

    public void setCar_type(String car_type) {
        this.car_type = car_type;
    }

    public String getCar_color() {
        return car_color;
    }

    public void setCar_color(String car_color) {
        this.car_color = car_color;
    }

    public String getCar_num() {
        return car_num;
    }

    public void setCar_num(String car_num) {
        this.car_num = car_num;
    }

  

   
    
}