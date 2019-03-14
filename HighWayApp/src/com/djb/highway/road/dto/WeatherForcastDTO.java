package com.djb.highway.road.dto;

import java.util.Date;
import java.util.List;

import com.djb.highway.framework.dto.PageDTO;

public class WeatherForcastDTO extends PageDTO {
	/**
     * 
     */
	private static final long serialVersionUID = 4666363979783068377L;

	private Integer wf_id;

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

	private List<WeatherForcastDTO> list;

	private List<List<WeatherForcastDTO>> citylist;

	// 版本时间戳
	private String weather_version_time;

	public String getWeather_version_time() {
		return weather_version_time;
	}

	public void setWeather_version_time(String weather_version_time) {
		this.weather_version_time = weather_version_time;
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

	public Integer getWf_id() {
		return wf_id;
	}

	public void setWf_id(Integer wf_id) {
		this.wf_id = wf_id;
	}

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

	public List<WeatherForcastDTO> getList() {
		return list;
	}

	public void setList(List<WeatherForcastDTO> list) {
		this.list = list;
	}

	public List<List<WeatherForcastDTO>> getCitylist() {
		return citylist;
	}

	public void setCitylist(List<List<WeatherForcastDTO>> citylist) {
		this.citylist = citylist;
	}

	public String getWeekday() {
		return weekday;
	}

	public void setWeekday(String weekday) {
		this.weekday = weekday;
	}

}