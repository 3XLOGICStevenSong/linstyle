package com.djb.highway.carpool.entity;

import java.util.Date;
import java.util.List;

import com.djb.highway.framework.entity.PageModel;

public class DriverRouteEntity extends PageModel {
    /**
	 * 
	 */
    private static final long serialVersionUID = -267247274928851913L;

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

    private Date end_time;

    private List<CarpoolRouteEntity> carpoolRouteEntitys;

    private Date from_time;

    private Date to_time;

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

    public Date getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Date end_time) {
        this.end_time = end_time;
    }

    public List<CarpoolRouteEntity> getCarpoolRouteEntitys() {
        return carpoolRouteEntitys;
    }

    public void setCarpoolRouteEntitys(List<CarpoolRouteEntity> carpoolRouteEntitys) {
        this.carpoolRouteEntitys = carpoolRouteEntitys;
    }

    public Date getFrom_time() {
        return from_time;
    }

    public void setFrom_time(Date from_time) {
        this.from_time = from_time;
    }

    public Date getTo_time() {
        return to_time;
    }

    public void setTo_time(Date to_time) {
        this.to_time = to_time;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        DriverRouteEntity other = (DriverRouteEntity) that;
        return (this.getDr_id() == null ? other.getDr_id() == null : this.getDr_id().equals(other.getDr_id()))
                        && (this.getCu_id() == null ? other.getCu_id() == null : this.getCu_id().equals(other.getCu_id()))
                        && (this.getStart_time() == null ? other.getStart_time() == null : this.getStart_time().equals(other.getStart_time()))
                        && (this.getStart_city() == null ? other.getStart_city() == null : this.getStart_city().equals(other.getStart_city()))
                        && (this.getEnd_city() == null ? other.getEnd_city() == null : this.getEnd_city().equals(other.getEnd_city()))
                        && (this.getCharter_flg() == null ? other.getCharter_flg() == null : this.getCharter_flg().equals(other.getCharter_flg()))
                        && (this.getTotal_num() == null ? other.getTotal_num() == null : this.getTotal_num().equals(other.getTotal_num()))
                        && (this.getTotal_price() == null ? other.getTotal_price() == null : this.getTotal_price().equals(other.getTotal_price()))
                        && (this.getStatus_flg() == null ? other.getStatus_flg() == null : this.getStatus_flg().equals(other.getStatus_flg()))
                        && (this.getDr_memo() == null ? other.getDr_memo() == null : this.getDr_memo().equals(other.getDr_memo()))
                        && (this.getDr_insert_time() == null ? other.getDr_insert_time() == null : this.getDr_insert_time().equals(other.getDr_insert_time()))
                        && (this.getDr_update_time() == null ? other.getDr_update_time() == null : this.getDr_update_time().equals(other.getDr_update_time()))
                        && (this.getEnd_time() == null ? other.getEnd_time() == null : this.getEnd_time().equals(other.getEnd_time()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getDr_id() == null) ? 0 : getDr_id().hashCode());
        result = prime * result + ((getCu_id() == null) ? 0 : getCu_id().hashCode());
        result = prime * result + ((getStart_time() == null) ? 0 : getStart_time().hashCode());
        result = prime * result + ((getStart_city() == null) ? 0 : getStart_city().hashCode());
        result = prime * result + ((getEnd_city() == null) ? 0 : getEnd_city().hashCode());
        result = prime * result + ((getCharter_flg() == null) ? 0 : getCharter_flg().hashCode());
        result = prime * result + ((getTotal_num() == null) ? 0 : getTotal_num().hashCode());
        result = prime * result + ((getTotal_price() == null) ? 0 : getTotal_price().hashCode());
        result = prime * result + ((getStatus_flg() == null) ? 0 : getStatus_flg().hashCode());
        result = prime * result + ((getDr_memo() == null) ? 0 : getDr_memo().hashCode());
        result = prime * result + ((getDr_insert_time() == null) ? 0 : getDr_insert_time().hashCode());
        result = prime * result + ((getDr_update_time() == null) ? 0 : getDr_update_time().hashCode());
        result = prime * result + ((getEnd_time() == null) ? 0 : getEnd_time().hashCode());
        return result;
    }
}