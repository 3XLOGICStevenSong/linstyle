package com.djb.highway.road.dtoclient;

import java.util.List;

/**
 * 天气请求数据
 * 
 * @author songcx
 * 
 */
public class CityWeatherBeanDTO {

	// 返回码
	// private int returnCode;

	// 城市名称
	private String cityName;

	// 城市Code
	private String cityCode;

	// 日期
	private String wf_date;
	// 星期
	private String weekDay;

	private List<CityWeatherBean> list;

	public String getWf_date() {
		return wf_date;
	}

	public void setWf_date(String wf_date) {
		this.wf_date = wf_date;
	}

	public String getWeekDay() {
		return weekDay;
	}

	public void setWeekDay(String weekDay) {
		this.weekDay = weekDay;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public List<CityWeatherBean> getList() {
		return list;
	}

	public void setList(List<CityWeatherBean> list) {
		this.list = list;
	}

}
