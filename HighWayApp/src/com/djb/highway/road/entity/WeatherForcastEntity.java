package com.djb.highway.road.entity;

import java.util.Date;

import com.djb.highway.framework.entity.BaseEntity;

public class WeatherForcastEntity extends BaseEntity {
	/**
     * 
     */
	private static final long serialVersionUID = 5306688429512189398L;

	private Integer wf_id;

	private String cityname;

	private String citycode;

	private String temperature;

	private String day_pic_name;

	private String night_pic_name;

	private String weather;

	private String wind;

	private String curr_temperature;

	private Date wf_date;
	private Integer city_id;
	private String weekday;

	private Date weather_deploy_time;

	public Date getWeather_deploy_time() {
		return weather_deploy_time;
	}

	public void setWeather_deploy_time(Date weather_deploy_time) {
		this.weather_deploy_time = weather_deploy_time;
	}

	public Integer getWf_id() {
		return wf_id;
	}

	public void setWf_id(Integer wf_id) {
		this.wf_id = wf_id;
	}

	public String getCityname() {
		return cityname;
	}

	public void setCityname(String cityname) {
		this.cityname = cityname;
	}

	public String getCitycode() {
		return citycode;
	}

	public void setCitycode(String citycode) {
		this.citycode = citycode;
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
		WeatherForcastEntity other = (WeatherForcastEntity) that;
		return (this.getWf_id() == null ? other.getWf_id() == null : this
				.getWf_id().equals(other.getWf_id()))
				&& (this.getCityname() == null ? other.getCityname() == null
						: this.getCityname().equals(other.getCityname()))
				&& (this.getCitycode() == null ? other.getCitycode() == null
						: this.getCitycode().equals(other.getCitycode()))
				&& (this.getTemperature() == null ? other.getTemperature() == null
						: this.getTemperature().equals(other.getTemperature()))
				&& (this.getDay_pic_name() == null ? other.getDay_pic_name() == null
						: this.getDay_pic_name()
								.equals(other.getDay_pic_name()))
				&& (this.getNight_pic_name() == null ? other
						.getNight_pic_name() == null : this.getNight_pic_name()
						.equals(other.getNight_pic_name()))
				&& (this.getWeather() == null ? other.getWeather() == null
						: this.getWeather().equals(other.getWeather()))
				&& (this.getWind() == null ? other.getWind() == null : this
						.getWind().equals(other.getWind()))
				&& (this.getCurr_temperature() == null ? other
						.getCurr_temperature() == null : this
						.getCurr_temperature().equals(
								other.getCurr_temperature()))
				&& (this.getWf_date() == null ? other.getWf_date() == null
						: this.getWf_date().equals(other.getWf_date()));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((getWf_id() == null) ? 0 : getWf_id().hashCode());
		result = prime * result
				+ ((getCityname() == null) ? 0 : getCityname().hashCode());
		result = prime * result
				+ ((getCitycode() == null) ? 0 : getCitycode().hashCode());
		result = prime
				* result
				+ ((getTemperature() == null) ? 0 : getTemperature().hashCode());
		result = prime
				* result
				+ ((getDay_pic_name() == null) ? 0 : getDay_pic_name()
						.hashCode());
		result = prime
				* result
				+ ((getNight_pic_name() == null) ? 0 : getNight_pic_name()
						.hashCode());
		result = prime * result
				+ ((getWeather() == null) ? 0 : getWeather().hashCode());
		result = prime * result
				+ ((getWind() == null) ? 0 : getWind().hashCode());
		result = prime
				* result
				+ ((getCurr_temperature() == null) ? 0 : getCurr_temperature()
						.hashCode());
		result = prime * result
				+ ((getWf_date() == null) ? 0 : getWf_date().hashCode());
		return result;
	}
}