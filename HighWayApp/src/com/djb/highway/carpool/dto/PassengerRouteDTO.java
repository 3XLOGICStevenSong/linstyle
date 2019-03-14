package com.djb.highway.carpool.dto;

import java.util.Date;
import java.util.List;

import com.djb.highway.framework.dto.PageDTO;

public class PassengerRouteDTO extends PageDTO {
    /**
	 * 
	 */
    private static final long serialVersionUID = 6777572028268224190L;

    private Integer pr_id;

    private Integer cu_id;

    private Date start_time;

    private Integer people_count;

    private String start_city;

    private String start_area;

    private String end_city;

    private String end_area;

    private String state;

    private String charter_flg;

    private Float price;

    private String status_flg;

    private String pr_memo;

    private Date pr_insert_time;

    private Date pr_update_time;

    private String begin_time;

    private String pend_time;
    
    private String go_time;

    private List<PassengerRouteDTO> passengerRoutelist;

    private CarpoolUserDTO carpoolUser;
    
    private String p_longitude;
    
    private String p_latitude;
    
    private String dp_flag;//0:司机   1:乘客
    
    private Integer dr_id;
    
    private String screenId;
    
    private String tab_show;
    
    public String getScreenId() {
		return screenId;
	}

	public void setScreenId(String screenId) {
		this.screenId = screenId;
	}

    public Integer getDr_id() {
		return dr_id;
	}

	public void setDr_id(Integer dr_id) {
		this.dr_id = dr_id;
	}

	public Integer getPr_id() {
        return pr_id;
    }

    public void setPr_id(Integer pr_id) {
        this.pr_id = pr_id;
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

    public Integer getPeople_count() {
        return people_count;
    }

    public void setPeople_count(Integer people_count) {
        this.people_count = people_count;
    }

    public String getStart_city() {
        return start_city;
    }

    public void setStart_city(String start_city) {
        this.start_city = start_city;
    }

    public String getStart_area() {
        return start_area;
    }

    public void setStart_area(String start_area) {
        this.start_area = start_area;
    }

    public String getEnd_city() {
        return end_city;
    }

    public void setEnd_city(String end_city) {
        this.end_city = end_city;
    }

    public String getEnd_area() {
        return end_area;
    }

    public void setEnd_area(String end_area) {
        this.end_area = end_area;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCharter_flg() {
        return charter_flg;
    }

    public void setCharter_flg(String charter_flg) {
        this.charter_flg = charter_flg;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getStatus_flg() {
        return status_flg;
    }

    public void setStatus_flg(String status_flg) {
        this.status_flg = status_flg;
    }

    public String getPr_memo() {
        return pr_memo;
    }

    public void setPr_memo(String pr_memo) {
        this.pr_memo = pr_memo;
    }

    public Date getPr_insert_time() {
        return pr_insert_time;
    }

    public void setPr_insert_time(Date pr_insert_time) {
        this.pr_insert_time = pr_insert_time;
    }

    public Date getPr_update_time() {
        return pr_update_time;
    }

    public void setPr_update_time(Date pr_update_time) {
        this.pr_update_time = pr_update_time;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
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

    public List<PassengerRouteDTO> getPassengerRoutelist() {
        return passengerRoutelist;
    }

    public void setPassengerRoutelist(List<PassengerRouteDTO> passengerRoutelist) {
        this.passengerRoutelist = passengerRoutelist;
    }

    public CarpoolUserDTO getCarpoolUser() {
        return carpoolUser;
    }

    public void setCarpoolUser(CarpoolUserDTO carpoolUser) {
        this.carpoolUser = carpoolUser;
    }

    public String getGo_time() {
        return go_time;
    }

    public void setGo_time(String go_time) {
        this.go_time = go_time;
    }
    
    public String getP_longitude() {
        return p_longitude;
    }

    public void setP_longitude(String p_longitude) {
        this.p_longitude = p_longitude;
    }
    
    public String getP_latitude() {
        return p_latitude;
    }

    public void setP_latitude(String p_latitude) {
        this.p_latitude = p_latitude;
    }

    public String getDp_flag() {
        return dp_flag;
    }

    public void setDp_flag(String dp_flag) {
        this.dp_flag = dp_flag;
    }

    public String getTab_show() {
        return tab_show;
    }

    public void setTab_show(String tab_show) {
        this.tab_show = tab_show;
    }

}