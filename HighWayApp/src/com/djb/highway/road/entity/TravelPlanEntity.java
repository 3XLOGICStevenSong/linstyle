package com.djb.highway.road.entity;

import java.util.Date;

public class TravelPlanEntity extends BaseTravelPlanEntity {
    /**
	 * 
	 */
    private static final long serialVersionUID = -8225364450764720016L;

    private Integer tp_id;

    private String entry_plaz_code;

    private String exit_plaz_code;

    private Integer h_id;

    private Integer entry_plaz_id;

    private Integer exit_plaz_id;

    private String travel_time;

    private String road_length;

    private String road_line;

    private String car_type;

    private String truckType1;

    private String truckType2;

    private String truckType3;

    private Integer fare;

    private Date create_time;

    private Date update_time;

    private String direction;

    private String unit_no;

    private String unit_code;

    private String[] travelTimePlazaCodeArgs;

    public Integer getTp_id() {
        return tp_id;
    }

    public void setTp_id(Integer tp_id) {
        this.tp_id = tp_id;
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

    public Integer getH_id() {
        return h_id;
    }

    public void setH_id(Integer h_id) {
        this.h_id = h_id;
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

    public String getTravel_time() {
        return travel_time;
    }

    public void setTravel_time(String travel_time) {
        this.travel_time = travel_time;
    }

    public String getRoad_length() {
        return road_length;
    }

    public void setRoad_length(String road_length) {
        this.road_length = road_length;
    }

    public String getRoad_line() {
        return road_line;
    }

    public void setRoad_line(String road_line) {
        this.road_line = road_line;
    }

    public String getCar_type() {
        return car_type;
    }

    public void setCar_type(String car_type) {
        this.car_type = car_type;
    }

    public String getTruckType1() {
        return truckType1;
    }

    public void setTruckType1(String truckType1) {
        this.truckType1 = truckType1;
    }

    public String getTruckType2() {
        return truckType2;
    }

    public void setTruckType2(String truckType2) {
        this.truckType2 = truckType2;
    }

    public String getTruckType3() {
        return truckType3;
    }

    public void setTruckType3(String truckType3) {
        this.truckType3 = truckType3;
    }

    public Integer getFare() {
        return fare;
    }

    public void setFare(Integer fare) {
        this.fare = fare;
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

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getUnit_no() {
        return unit_no;
    }

    public void setUnit_no(String unit_no) {
        this.unit_no = unit_no;
    }

    public String getUnit_code() {
        return unit_code;
    }

    public void setUnit_code(String unit_code) {
        this.unit_code = unit_code;
    }

    public String[] getTravelTimePlazaCodeArgs() {
        return travelTimePlazaCodeArgs;
    }

    public void setTravelTimePlazaCodeArgs(String[] travelTimePlazaCodeArgs) {
        this.travelTimePlazaCodeArgs = travelTimePlazaCodeArgs;
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
        TravelPlanEntity other = (TravelPlanEntity) that;
        return (this.getTp_id() == null ? other.getTp_id() == null : this.getTp_id().equals(other.getTp_id()))
                        && (this.getEntry_plaz_code() == null ? other.getEntry_plaz_code() == null : this.getEntry_plaz_code().equals(
                                        other.getEntry_plaz_code()))
                        && (this.getExit_plaz_code() == null ? other.getExit_plaz_code() == null : this.getExit_plaz_code().equals(other.getExit_plaz_code()))
                        && (this.getH_id() == null ? other.getH_id() == null : this.getH_id().equals(other.getH_id()))
                        && (this.getEntry_plaz_id() == null ? other.getEntry_plaz_id() == null : this.getEntry_plaz_id().equals(other.getEntry_plaz_id()))
                        && (this.getExit_plaz_id() == null ? other.getExit_plaz_id() == null : this.getExit_plaz_id().equals(other.getExit_plaz_id()))
                        && (this.getTravel_time() == null ? other.getTravel_time() == null : this.getTravel_time().equals(other.getTravel_time()))
                        && (this.getRoad_length() == null ? other.getRoad_length() == null : this.getRoad_length().equals(other.getRoad_length()))
                        && (this.getRoad_line() == null ? other.getRoad_line() == null : this.getRoad_line().equals(other.getRoad_line()))
                        && (this.getCar_type() == null ? other.getCar_type() == null : this.getCar_type().equals(other.getCar_type()))
                        && (this.getTruckType1() == null ? other.getTruckType1() == null : this.getTruckType1().equals(other.getTruckType1()))
                        && (this.getTruckType2() == null ? other.getTruckType2() == null : this.getTruckType2().equals(other.getTruckType2()))
                        && (this.getTruckType3() == null ? other.getTruckType3() == null : this.getTruckType3().equals(other.getTruckType3()))
                        && (this.getFare() == null ? other.getFare() == null : this.getFare().equals(other.getFare()))
                        && (this.getCreate_time() == null ? other.getCreate_time() == null : this.getCreate_time().equals(other.getCreate_time()))
                        && (this.getUpdate_time() == null ? other.getUpdate_time() == null : this.getUpdate_time().equals(other.getUpdate_time()))
                        && (this.getDirection() == null ? other.getDirection() == null : this.getDirection().equals(other.getDirection()))
                        && (this.getUnit_no() == null ? other.getUnit_no() == null : this.getUnit_no().equals(other.getUnit_no()))
                        && (this.getUnit_code() == null ? other.getUnit_code() == null : this.getUnit_code().equals(other.getUnit_code()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getTp_id() == null) ? 0 : getTp_id().hashCode());
        result = prime * result + ((getEntry_plaz_code() == null) ? 0 : getEntry_plaz_code().hashCode());
        result = prime * result + ((getExit_plaz_code() == null) ? 0 : getExit_plaz_code().hashCode());
        result = prime * result + ((getH_id() == null) ? 0 : getH_id().hashCode());
        result = prime * result + ((getEntry_plaz_id() == null) ? 0 : getEntry_plaz_id().hashCode());
        result = prime * result + ((getExit_plaz_id() == null) ? 0 : getExit_plaz_id().hashCode());
        result = prime * result + ((getTravel_time() == null) ? 0 : getTravel_time().hashCode());
        result = prime * result + ((getRoad_length() == null) ? 0 : getRoad_length().hashCode());
        result = prime * result + ((getRoad_line() == null) ? 0 : getRoad_line().hashCode());
        result = prime * result + ((getCar_type() == null) ? 0 : getCar_type().hashCode());
        result = prime * result + ((getTruckType1() == null) ? 0 : getTruckType1().hashCode());
        result = prime * result + ((getTruckType2() == null) ? 0 : getTruckType2().hashCode());
        result = prime * result + ((getTruckType3() == null) ? 0 : getTruckType3().hashCode());
        result = prime * result + ((getFare() == null) ? 0 : getFare().hashCode());
        result = prime * result + ((getCreate_time() == null) ? 0 : getCreate_time().hashCode());
        result = prime * result + ((getUpdate_time() == null) ? 0 : getUpdate_time().hashCode());
        result = prime * result + ((getDirection() == null) ? 0 : getDirection().hashCode());
        result = prime * result + ((getUnit_no() == null) ? 0 : getUnit_no().hashCode());
        result = prime * result + ((getUnit_code() == null) ? 0 : getUnit_code().hashCode());
        return result;
    }
}