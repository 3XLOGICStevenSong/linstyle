package com.djb.highway.carpool.entity;

import java.util.Date;

import com.djb.highway.framework.entity.PageModel;

public class PassengerRouteEntity extends PageModel {
    /**
	 * 
	 */
    private static final long serialVersionUID = 9022782765806039006L;

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

    private CarpoolRouteEntity carpoolRouteEntity;

    private CarpoolUserEntity carpoolUserEntity;

    private Date begin_time;

    private Date end_time;

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

    public CarpoolUserEntity getCarpoolUserEntity() {
        return carpoolUserEntity;
    }

    public void setCarpoolUserEntity(CarpoolUserEntity carpoolUserEntity) {
        this.carpoolUserEntity = carpoolUserEntity;
    }

    public CarpoolRouteEntity getCarpoolRouteEntity() {
        return carpoolRouteEntity;
    }

    public void setCarpoolRouteEntity(CarpoolRouteEntity carpoolRouteEntity) {
        this.carpoolRouteEntity = carpoolRouteEntity;
    }

    public Date getBegin_time() {
        return begin_time;
    }

    public void setBegin_time(Date begin_time) {
        this.begin_time = begin_time;
    }

    public Date getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Date end_time) {
        this.end_time = end_time;
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
        PassengerRouteEntity other = (PassengerRouteEntity) that;
        return (this.getPr_id() == null ? other.getPr_id() == null : this.getPr_id().equals(other.getPr_id()))
                        && (this.getCu_id() == null ? other.getCu_id() == null : this.getCu_id().equals(other.getCu_id()))
                        && (this.getStart_time() == null ? other.getStart_time() == null : this.getStart_time().equals(other.getStart_time()))
                        && (this.getPeople_count() == null ? other.getPeople_count() == null : this.getPeople_count().equals(other.getPeople_count()))
                        && (this.getStart_city() == null ? other.getStart_city() == null : this.getStart_city().equals(other.getStart_city()))
                        && (this.getStart_area() == null ? other.getStart_area() == null : this.getStart_area().equals(other.getStart_area()))
                        && (this.getEnd_city() == null ? other.getEnd_city() == null : this.getEnd_city().equals(other.getEnd_city()))
                        && (this.getEnd_area() == null ? other.getEnd_area() == null : this.getEnd_area().equals(other.getEnd_area()))
                        && (this.getState() == null ? other.getState() == null : this.getState().equals(other.getState()))
                        && (this.getCharter_flg() == null ? other.getCharter_flg() == null : this.getCharter_flg().equals(other.getCharter_flg()))
                        && (this.getPrice() == null ? other.getPrice() == null : this.getPrice().equals(other.getPrice()))
                        && (this.getStatus_flg() == null ? other.getStatus_flg() == null : this.getStatus_flg().equals(other.getStatus_flg()))
                        && (this.getPr_memo() == null ? other.getPr_memo() == null : this.getPr_memo().equals(other.getPr_memo()))
                        && (this.getPr_insert_time() == null ? other.getPr_insert_time() == null : this.getPr_insert_time().equals(other.getPr_insert_time()))
                        && (this.getPr_update_time() == null ? other.getPr_update_time() == null : this.getPr_update_time().equals(other.getPr_update_time()));

    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getPr_id() == null) ? 0 : getPr_id().hashCode());
        result = prime * result + ((getCu_id() == null) ? 0 : getCu_id().hashCode());
        result = prime * result + ((getStart_time() == null) ? 0 : getStart_time().hashCode());
        result = prime * result + ((getPeople_count() == null) ? 0 : getPeople_count().hashCode());
        result = prime * result + ((getStart_city() == null) ? 0 : getStart_city().hashCode());
        result = prime * result + ((getStart_area() == null) ? 0 : getStart_area().hashCode());
        result = prime * result + ((getEnd_city() == null) ? 0 : getEnd_city().hashCode());
        result = prime * result + ((getEnd_area() == null) ? 0 : getEnd_area().hashCode());
        result = prime * result + ((getState() == null) ? 0 : getState().hashCode());
        result = prime * result + ((getCharter_flg() == null) ? 0 : getCharter_flg().hashCode());
        result = prime * result + ((getPrice() == null) ? 0 : getPrice().hashCode());
        result = prime * result + ((getStatus_flg() == null) ? 0 : getStatus_flg().hashCode());
        result = prime * result + ((getPr_memo() == null) ? 0 : getPr_memo().hashCode());
        result = prime * result + ((getPr_insert_time() == null) ? 0 : getPr_insert_time().hashCode());
        result = prime * result + ((getPr_update_time() == null) ? 0 : getPr_update_time().hashCode());
        return result;
    }
}