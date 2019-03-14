package com.djb.highway.road.dtoclient;

import java.util.List;

/**
 * 辽宁省高速公路天气数据实体类
 * 
 * @author songcx
 * @version 创建时间: 2014年7月14日 上午9:04:00
 */
public class WeatherCityDTO extends BaseClientDTO {
	public static final int REPORT_DAYS = 4;

	public String mCityID;
	public String mCityName;
	public String mCityWeather;
	public List<WeatherInfoItemDTO> mWeatherInfo;

	public String getmCityID() {
		return mCityID;
	}

	public void setmCityID(String mCityID) {
		this.mCityID = mCityID;
	}

	public String getmCityWeather() {
		return mCityWeather;
	}

	public void setmCityWeather(String mCityWeather) {
		this.mCityWeather = mCityWeather;
	}

	public String getmCityName() {
		return mCityName;
	}

	public void setmCityName(String mCityName) {
		this.mCityName = mCityName;
	}

	public List<WeatherInfoItemDTO> getmWeatherInfo() {
		return mWeatherInfo;
	}

	public void setmWeatherInfo(List<WeatherInfoItemDTO> mWeatherInfo) {
		this.mWeatherInfo = mWeatherInfo;
	}

	public static int getReportDays() {
		return REPORT_DAYS;
	}

}