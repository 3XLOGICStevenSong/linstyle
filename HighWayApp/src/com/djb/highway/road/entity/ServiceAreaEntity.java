package com.djb.highway.road.entity;

import java.util.Date;

public class ServiceAreaEntity extends BaseTravelPlanEntity {
    /**
	 * 
	 */
    private static final long serialVersionUID = 833172699821342896L;

    private Integer sa_id;

    private String sa_code;

    private String sa_name;

    private Integer h_id;

    private String h_code;

    private String h_name;

    private String stake_id;

    private String sa_city;

    private String sa_tel;

    private String previous_service_area_code;

    private String previous_service_area_name;

    private String previous_plaza_code;

    private String previous_plaza_name;

    private String next_service_area_code;

    private String next_service_area_name;

    private String next_plaza_code;

    private String next_plaza_name;

    private String park_total;

    private String park_remain;

    private String supermarket_flg;

    private String restaurant_flg;

    private String hotel_flg;

    private String car_repair_flg;

    private String gas_station_flg;

    private String gas_type;

    private Integer c_id;

    private String pic_url;

    private String sa_direction;

    private String sa_status;

    private Date create_time;

    private Date update_time;

    private String location_desc;

    private String longitude_a;

    private String latitude_a;

    private String longitude_b;

    private String latitude_b;

    private String link_code;

    private String[] h_codeArgs;

    private String[] serviceIdArgs;
    private String  park_status;

    public Integer getSa_id() {
        return sa_id;
    }

    public void setSa_id(Integer sa_id) {
        this.sa_id = sa_id;
    }

    public String getSa_code() {
        return sa_code;
    }

    public void setSa_code(String sa_code) {
        this.sa_code = sa_code;
    }

    public String getSa_name() {
        return sa_name;
    }

    public void setSa_name(String sa_name) {
        this.sa_name = sa_name;
    }

    public Integer getH_id() {
        return h_id;
    }

    public void setH_id(Integer h_id) {
        this.h_id = h_id;
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

    public String getStake_id() {
        return stake_id;
    }

    public void setStake_id(String stake_id) {
        this.stake_id = stake_id;
    }

    public String getSa_city() {
        return sa_city;
    }

    public void setSa_city(String sa_city) {
        this.sa_city = sa_city;
    }

    public String getSa_tel() {
        return sa_tel;
    }

    public void setSa_tel(String sa_tel) {
        this.sa_tel = sa_tel;
    }

    public String getPrevious_service_area_code() {
        return previous_service_area_code;
    }

    public void setPrevious_service_area_code(String previous_service_area_code) {
        this.previous_service_area_code = previous_service_area_code;
    }

    public String getPrevious_service_area_name() {
        return previous_service_area_name;
    }

    public void setPrevious_service_area_name(String previous_service_area_name) {
        this.previous_service_area_name = previous_service_area_name;
    }

    public String getPrevious_plaza_code() {
        return previous_plaza_code;
    }

    public void setPrevious_plaza_code(String previous_plaza_code) {
        this.previous_plaza_code = previous_plaza_code;
    }

    public String getPrevious_plaza_name() {
        return previous_plaza_name;
    }

    public void setPrevious_plaza_name(String previous_plaza_name) {
        this.previous_plaza_name = previous_plaza_name;
    }

    public String getNext_service_area_code() {
        return next_service_area_code;
    }

    public void setNext_service_area_code(String next_service_area_code) {
        this.next_service_area_code = next_service_area_code;
    }

    public String getNext_service_area_name() {
        return next_service_area_name;
    }

    public void setNext_service_area_name(String next_service_area_name) {
        this.next_service_area_name = next_service_area_name;
    }

    public String getNext_plaza_code() {
        return next_plaza_code;
    }

    public void setNext_plaza_code(String next_plaza_code) {
        this.next_plaza_code = next_plaza_code;
    }

    public String getNext_plaza_name() {
        return next_plaza_name;
    }

    public void setNext_plaza_name(String next_plaza_name) {
        this.next_plaza_name = next_plaza_name;
    }

    public String getPark_total() {
        return park_total;
    }

    public void setPark_total(String park_total) {
        this.park_total = park_total;
    }

    public String getPark_remain() {
        return park_remain;
    }

    public void setPark_remain(String park_remain) {
        this.park_remain = park_remain;
    }

    public String getSupermarket_flg() {
        return supermarket_flg;
    }

    public void setSupermarket_flg(String supermarket_flg) {
        this.supermarket_flg = supermarket_flg;
    }

    public String getRestaurant_flg() {
        return restaurant_flg;
    }

    public void setRestaurant_flg(String restaurant_flg) {
        this.restaurant_flg = restaurant_flg;
    }

    public String getHotel_flg() {
        return hotel_flg;
    }

    public void setHotel_flg(String hotel_flg) {
        this.hotel_flg = hotel_flg;
    }

    public String getCar_repair_flg() {
        return car_repair_flg;
    }

    public void setCar_repair_flg(String car_repair_flg) {
        this.car_repair_flg = car_repair_flg;
    }

    public String getGas_station_flg() {
        return gas_station_flg;
    }

    public void setGas_station_flg(String gas_station_flg) {
        this.gas_station_flg = gas_station_flg;
    }

    public String getGas_type() {
        return gas_type;
    }

    public void setGas_type(String gas_type) {
        this.gas_type = gas_type;
    }

    public Integer getC_id() {
        return c_id;
    }

    public void setC_id(Integer c_id) {
        this.c_id = c_id;
    }

    public String getPic_url() {
        return pic_url;
    }

    public void setPic_url(String pic_url) {
        this.pic_url = pic_url;
    }

    public String getSa_direction() {
        return sa_direction;
    }

    public void setSa_direction(String sa_direction) {
        this.sa_direction = sa_direction;
    }

    public String getSa_status() {
        return sa_status;
    }

    public void setSa_status(String sa_status) {
        this.sa_status = sa_status;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    public String getLocation_desc() {
        return location_desc;
    }

    public void setLocation_desc(String location_desc) {
        this.location_desc = location_desc;
    }

    public String getLongitude_a() {
        return longitude_a;
    }

    public void setLongitude_a(String longitude_a) {
        this.longitude_a = longitude_a;
    }

    public String getLatitude_a() {
        return latitude_a;
    }

    public void setLatitude_a(String latitude_a) {
        this.latitude_a = latitude_a;
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

    public String getLink_code() {
        return link_code;
    }

    public void setLink_code(String link_code) {
        this.link_code = link_code;
    }

    public String[] getH_codeArgs() {
        return h_codeArgs;
    }

    public void setH_codeArgs(String[] h_codeArgs) {
        this.h_codeArgs = h_codeArgs;
    }

    public String[] getServiceIdArgs() {
        return serviceIdArgs;
    }

    public void setServiceIdArgs(String[] serviceIdArgs) {
        this.serviceIdArgs = serviceIdArgs;
    }
    public String getPark_status() {
		return park_status;
	}

	public void setPark_status(String park_status) {
		this.park_status = park_status;
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
        ServiceAreaEntity other = (ServiceAreaEntity) that;
        return (this.getSa_id() == null ? other.getSa_id() == null : this.getSa_id().equals(other.getSa_id()))
                        && (this.getSa_code() == null ? other.getSa_code() == null : this.getSa_code().equals(other.getSa_code()))
                        && (this.getSa_name() == null ? other.getSa_name() == null : this.getSa_name().equals(other.getSa_name()))
                        && (this.getH_id() == null ? other.getH_id() == null : this.getH_id().equals(other.getH_id()))
                        && (this.getH_code() == null ? other.getH_code() == null : this.getH_code().equals(other.getH_code()))
                        && (this.getH_name() == null ? other.getH_name() == null : this.getH_name().equals(other.getH_name()))
                        && (this.getStake_id() == null ? other.getStake_id() == null : this.getStake_id().equals(other.getStake_id()))
                        && (this.getSa_city() == null ? other.getSa_city() == null : this.getSa_city().equals(other.getSa_city()))
                        && (this.getSa_tel() == null ? other.getSa_tel() == null : this.getSa_tel().equals(other.getSa_tel()))
                        && (this.getPrevious_service_area_code() == null ? other.getPrevious_service_area_code() == null : this.getPrevious_service_area_code()
                                        .equals(other.getPrevious_service_area_code()))
                        && (this.getPrevious_service_area_name() == null ? other.getPrevious_service_area_name() == null : this.getPrevious_service_area_name()
                                        .equals(other.getPrevious_service_area_name()))
                        && (this.getPrevious_plaza_code() == null ? other.getPrevious_plaza_code() == null : this.getPrevious_plaza_code().equals(
                                        other.getPrevious_plaza_code()))
                        && (this.getPrevious_plaza_name() == null ? other.getPrevious_plaza_name() == null : this.getPrevious_plaza_name().equals(
                                        other.getPrevious_plaza_name()))
                        && (this.getNext_service_area_code() == null ? other.getNext_service_area_code() == null : this.getNext_service_area_code().equals(
                                        other.getNext_service_area_code()))
                        && (this.getNext_service_area_name() == null ? other.getNext_service_area_name() == null : this.getNext_service_area_name().equals(
                                        other.getNext_service_area_name()))
                        && (this.getNext_plaza_code() == null ? other.getNext_plaza_code() == null : this.getNext_plaza_code().equals(
                                        other.getNext_plaza_code()))
                        && (this.getNext_plaza_name() == null ? other.getNext_plaza_name() == null : this.getNext_plaza_name().equals(
                                        other.getNext_plaza_name()))
                        && (this.getPark_total() == null ? other.getPark_total() == null : this.getPark_total().equals(other.getPark_total()))
                        && (this.getPark_remain() == null ? other.getPark_remain() == null : this.getPark_remain().equals(other.getPark_remain()))
                        && (this.getSupermarket_flg() == null ? other.getSupermarket_flg() == null : this.getSupermarket_flg().equals(
                                        other.getSupermarket_flg()))
                        && (this.getRestaurant_flg() == null ? other.getRestaurant_flg() == null : this.getRestaurant_flg().equals(other.getRestaurant_flg()))
                        && (this.getHotel_flg() == null ? other.getHotel_flg() == null : this.getHotel_flg().equals(other.getHotel_flg()))
                        && (this.getCar_repair_flg() == null ? other.getCar_repair_flg() == null : this.getCar_repair_flg().equals(other.getCar_repair_flg()))
                        && (this.getGas_station_flg() == null ? other.getGas_station_flg() == null : this.getGas_station_flg().equals(
                                        other.getGas_station_flg()))
                        && (this.getGas_type() == null ? other.getGas_type() == null : this.getGas_type().equals(other.getGas_type()))
                        && (this.getC_id() == null ? other.getC_id() == null : this.getC_id().equals(other.getC_id()))
                        && (this.getPic_url() == null ? other.getPic_url() == null : this.getPic_url().equals(other.getPic_url()))
                        && (this.getSa_direction() == null ? other.getSa_direction() == null : this.getSa_direction().equals(other.getSa_direction()))
                        && (this.getSa_status() == null ? other.getSa_status() == null : this.getSa_status().equals(other.getSa_status()))
                        && (this.getCreate_time() == null ? other.getCreate_time() == null : this.getCreate_time().equals(other.getCreate_time()))
                        && (this.getUpdate_time() == null ? other.getUpdate_time() == null : this.getUpdate_time().equals(other.getUpdate_time()))
                        && (this.getLocation_desc() == null ? other.getLocation_desc() == null : this.getLocation_desc().equals(other.getLocation_desc()))
                        && (this.getLongitude_a() == null ? other.getLongitude_a() == null : this.getLongitude_a().equals(other.getLongitude_a()))
                        && (this.getLatitude_a() == null ? other.getLatitude_a() == null : this.getLatitude_a().equals(other.getLatitude_a()))
                        && (this.getLongitude_b() == null ? other.getLongitude_b() == null : this.getLongitude_b().equals(other.getLongitude_b()))
                        && (this.getLatitude_b() == null ? other.getLatitude_b() == null : this.getLatitude_b().equals(other.getLatitude_b()))
                        && (this.getLink_code() == null ? other.getLink_code() == null : this.getLink_code().equals(other.getLink_code()))
                         && (this.getPark_status() == null ? other.getPark_status() == null : this.getPark_status().equals(other.getPark_status())) ;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getSa_id() == null) ? 0 : getSa_id().hashCode());
        result = prime * result + ((getSa_code() == null) ? 0 : getSa_code().hashCode());
        result = prime * result + ((getSa_name() == null) ? 0 : getSa_name().hashCode());
        result = prime * result + ((getH_id() == null) ? 0 : getH_id().hashCode());
        result = prime * result + ((getH_code() == null) ? 0 : getH_code().hashCode());
        result = prime * result + ((getH_name() == null) ? 0 : getH_name().hashCode());
        result = prime * result + ((getStake_id() == null) ? 0 : getStake_id().hashCode());
        result = prime * result + ((getSa_city() == null) ? 0 : getSa_city().hashCode());
        result = prime * result + ((getSa_tel() == null) ? 0 : getSa_tel().hashCode());
        result = prime * result + ((getPrevious_service_area_code() == null) ? 0 : getPrevious_service_area_code().hashCode());
        result = prime * result + ((getPrevious_service_area_name() == null) ? 0 : getPrevious_service_area_name().hashCode());
        result = prime * result + ((getPrevious_plaza_code() == null) ? 0 : getPrevious_plaza_code().hashCode());
        result = prime * result + ((getPrevious_plaza_name() == null) ? 0 : getPrevious_plaza_name().hashCode());
        result = prime * result + ((getNext_service_area_code() == null) ? 0 : getNext_service_area_code().hashCode());
        result = prime * result + ((getNext_service_area_name() == null) ? 0 : getNext_service_area_name().hashCode());
        result = prime * result + ((getNext_plaza_code() == null) ? 0 : getNext_plaza_code().hashCode());
        result = prime * result + ((getNext_plaza_name() == null) ? 0 : getNext_plaza_name().hashCode());
        result = prime * result + ((getPark_total() == null) ? 0 : getPark_total().hashCode());
        result = prime * result + ((getPark_remain() == null) ? 0 : getPark_remain().hashCode());
        result = prime * result + ((getSupermarket_flg() == null) ? 0 : getSupermarket_flg().hashCode());
        result = prime * result + ((getRestaurant_flg() == null) ? 0 : getRestaurant_flg().hashCode());
        result = prime * result + ((getHotel_flg() == null) ? 0 : getHotel_flg().hashCode());
        result = prime * result + ((getCar_repair_flg() == null) ? 0 : getCar_repair_flg().hashCode());
        result = prime * result + ((getGas_station_flg() == null) ? 0 : getGas_station_flg().hashCode());
        result = prime * result + ((getGas_type() == null) ? 0 : getGas_type().hashCode());
        result = prime * result + ((getC_id() == null) ? 0 : getC_id().hashCode());
        result = prime * result + ((getPic_url() == null) ? 0 : getPic_url().hashCode());
        result = prime * result + ((getSa_direction() == null) ? 0 : getSa_direction().hashCode());
        result = prime * result + ((getSa_status() == null) ? 0 : getSa_status().hashCode());
        result = prime * result + ((getCreate_time() == null) ? 0 : getCreate_time().hashCode());
        result = prime * result + ((getUpdate_time() == null) ? 0 : getUpdate_time().hashCode());
        result = prime * result + ((getLocation_desc() == null) ? 0 : getLocation_desc().hashCode());
        result = prime * result + ((getLongitude_a() == null) ? 0 : getLongitude_a().hashCode());
        result = prime * result + ((getLatitude_a() == null) ? 0 : getLatitude_a().hashCode());
        result = prime * result + ((getLongitude_b() == null) ? 0 : getLongitude_b().hashCode());
        result = prime * result + ((getLatitude_b() == null) ? 0 : getLatitude_b().hashCode());
        result = prime * result + ((getLink_code() == null) ? 0 : getLink_code().hashCode());
        result = prime * result + (( getPark_status() == null) ? 0 :  getPark_status().hashCode());
       
        return result;
    }

	
}