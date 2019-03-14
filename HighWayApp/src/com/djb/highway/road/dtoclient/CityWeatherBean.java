package com.djb.highway.road.dtoclient;

/**
 * 天气请求数据
 * 
 * @author songcx
 * 
 */
public class CityWeatherBean {

	private String cityName;

	private String cityCode;

	private String tempRegion;

	private String day_pic_name;

	// 晚上图片
	private String night_pic_name;

	private String weather;

	private String tempReal;// 当前温度值

	private String weekDay;// 星期

	private String wf_date;

	public String getWf_date() {
		return wf_date;
	}

	public void setWf_date(String wf_date) {
		this.wf_date = wf_date;
	}

	public String getNight_pic_name() {
		return night_pic_name;
	}

	public void setNight_pic_name(String night_pic_name) {
		this.night_pic_name = night_pic_name;
	}

	public String getDay_pic_name() {
		return day_pic_name;
	}

	public void setDay_pic_name(String day_pic_name) {
		this.day_pic_name = day_pic_name;
	}

	public String getWeather() {
		return weather;
	}

	public void setWeather(String weather) {
		this.weather = weather;
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

	public String getTempRegion() {
		return tempRegion;
	}

	public void setTempRegion(String tempRegion) {
		this.tempRegion = tempRegion;
	}

	public String getTempReal() {
		return tempReal;
	}

	public void setTempReal(String tempReal) {
		this.tempReal = tempReal;
	}

	public String getWeekDay() {
		return weekDay;
	}

	public void setWeekDay(String weekDay) {
		this.weekDay = weekDay;
	}

	@Override
	public String toString() {
		return "CityWeatherBean [cityName=" + cityName + ", cityCode="
				+ cityCode + ", tempRegion=" + tempRegion + ", day_pic_name="
				+ day_pic_name + ", weather=" + weather + ", tempReal="
				+ tempReal + ", weekDay=" + weekDay + "]";
	}

}
