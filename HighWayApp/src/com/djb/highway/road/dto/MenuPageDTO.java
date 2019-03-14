package com.djb.highway.road.dto;

import java.util.Date;
import java.util.List;

import com.djb.highway.framework.dto.PageDTO;

public class MenuPageDTO extends PageDTO {

	private static final long serialVersionUID = 4666363979783068377L;

	private String city_name;

	private String city_code;

	private String temperature;

	private String day_pic_name;

	private String night_pic_name;

	private String weather;

	private String wind;

	private String curr_temperature;

	private Date wf_date;

	private Integer city_id;
	private String weekday;
	private Integer rci_id;

	private Integer h_id;

	private String h_code;

	private String h_name;

	private String start_stake_id;

	private String end_stake_id;

	private String control_scope;

	private String control_direction;

	private String control_mode;

	private Date start_time;

	private Date plan_end_time;

	private Date real_end_time;

	private String rci_status;

	private String rci_type;

	private Integer start_plaz_id;

	private String start_plaz_code;

	private Integer end_plaz_id;

	private String end_plaz_code;

	private String rci_content;

	private Date deploy_time;

	private Integer section_id;
	 private List<MenuPageDTO> list;

	public String getCity_name() {
		return city_name;
	}

	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}

	public String getCity_code() {
		return city_code;
	}

	public void setCity_code(String city_code) {
		this.city_code = city_code;
	}

	public String getTemperature() {
		return temperature;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	public String getDay_pic_name() {
		return day_pic_name;
	}

	public void setDay_pic_name(String day_pic_name) {
		this.day_pic_name = day_pic_name;
	}

	public String getNight_pic_name() {
		return night_pic_name;
	}

	public void setNight_pic_name(String night_pic_name) {
		this.night_pic_name = night_pic_name;
	}

	public String getWeather() {
		return weather;
	}

	public void setWeather(String weather) {
		this.weather = weather;
	}

	public String getWind() {
		return wind;
	}

	public void setWind(String wind) {
		this.wind = wind;
	}

	public String getCurr_temperature() {
		return curr_temperature;
	}

	public void setCurr_temperature(String curr_temperature) {
		this.curr_temperature = curr_temperature;
	}

	public Date getWf_date() {
		return wf_date;
	}

	public void setWf_date(Date wf_date) {
		this.wf_date = wf_date;
	}

	public Integer getCity_id() {
		return city_id;
	}

	public void setCity_id(Integer city_id) {
		this.city_id = city_id;
	}

	public String getWeekday() {
		return weekday;
	}

	public void setWeekday(String weekday) {
		this.weekday = weekday;
	}

	public Integer getRci_id() {
		return rci_id;
	}

	public void setRci_id(Integer rci_id) {
		this.rci_id = rci_id;
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

	public String getStart_stake_id() {
		return start_stake_id;
	}

	public void setStart_stake_id(String start_stake_id) {
		this.start_stake_id = start_stake_id;
	}

	public String getEnd_stake_id() {
		return end_stake_id;
	}

	public void setEnd_stake_id(String end_stake_id) {
		this.end_stake_id = end_stake_id;
	}

	public String getControl_scope() {
		return control_scope;
	}

	public void setControl_scope(String control_scope) {
		this.control_scope = control_scope;
	}

	public String getControl_direction() {
		return control_direction;
	}

	public void setControl_direction(String control_direction) {
		this.control_direction = control_direction;
	}

	public String getControl_mode() {
		return control_mode;
	}

	public void setControl_mode(String control_mode) {
		this.control_mode = control_mode;
	}

	public Date getStart_time() {
		return start_time;
	}

	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}

	public Date getPlan_end_time() {
		return plan_end_time;
	}

	public void setPlan_end_time(Date plan_end_time) {
		this.plan_end_time = plan_end_time;
	}

	public Date getReal_end_time() {
		return real_end_time;
	}

	public void setReal_end_time(Date real_end_time) {
		this.real_end_time = real_end_time;
	}

	public String getRci_status() {
		return rci_status;
	}

	public void setRci_status(String rci_status) {
		this.rci_status = rci_status;
	}

	public String getRci_type() {
		return rci_type;
	}

	public void setRci_type(String rci_type) {
		this.rci_type = rci_type;
	}

	public Integer getStart_plaz_id() {
		return start_plaz_id;
	}

	public void setStart_plaz_id(Integer start_plaz_id) {
		this.start_plaz_id = start_plaz_id;
	}

	public String getStart_plaz_code() {
		return start_plaz_code;
	}

	public void setStart_plaz_code(String start_plaz_code) {
		this.start_plaz_code = start_plaz_code;
	}

	public Integer getEnd_plaz_id() {
		return end_plaz_id;
	}

	public void setEnd_plaz_id(Integer end_plaz_id) {
		this.end_plaz_id = end_plaz_id;
	}

	public String getEnd_plaz_code() {
		return end_plaz_code;
	}

	public void setEnd_plaz_code(String end_plaz_code) {
		this.end_plaz_code = end_plaz_code;
	}

	public String getRci_content() {
		return rci_content;
	}

	public void setRci_content(String rci_content) {
		this.rci_content = rci_content;
	}

	public Date getDeploy_time() {
		return deploy_time;
	}

	public void setDeploy_time(Date deploy_time) {
		this.deploy_time = deploy_time;
	}

	public Integer getSection_id() {
		return section_id;
	}

	public void setSection_id(Integer section_id) {
		this.section_id = section_id;
	}

	public List<MenuPageDTO> getList() {
		return list;
	}

	public void setList(List<MenuPageDTO> list) {
		this.list = list;
	}

}