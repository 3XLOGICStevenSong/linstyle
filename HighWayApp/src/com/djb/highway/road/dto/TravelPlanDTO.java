package com.djb.highway.road.dto;

import java.util.Date;

import com.djb.highway.framework.dto.BaseDTO;

public class TravelPlanDTO extends BaseDTO {
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

	private String[] plazaCodeArgs;

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

	public String[] getPlazaCodeArgs() {
		return plazaCodeArgs;
	}

	public void setPlazaCodeArgs(String[] plazaCodeArgs) {
		this.plazaCodeArgs = plazaCodeArgs;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}