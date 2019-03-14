package com.djb.highway.road.dtoclient;

import java.util.List;

/**
 * 天气请求数据
 * 
 * @author songcx
 * 
 */
public class CityWeatherBeanBaseDTO {

	// 返回码
	private String returnCode;

	private List<CityWeatherBeanDTO> citylist;

	private String weather_version_time;

	public String getWeather_version_time() {
		return weather_version_time;
	}

	public void setWeather_version_time(String weather_version_time) {
		this.weather_version_time = weather_version_time;
	}

	public String getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}

	public List<CityWeatherBeanDTO> getCitylist() {
		return citylist;
	}

	public void setCitylist(List<CityWeatherBeanDTO> citylist) {
		this.citylist = citylist;
	}

}
