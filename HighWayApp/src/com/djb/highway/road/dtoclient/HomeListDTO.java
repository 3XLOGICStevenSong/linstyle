package com.djb.highway.road.dtoclient;

import java.util.List;

public class HomeListDTO extends BaseClientDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 城市名
	 */
	private String cityName;

	/**
	 * 城市code
	 */
	private String cityCode;

	/**
	 * 即时温度
	 */
	private String tempReal;

	/**
	 * 白天图片
	 */
	private String day_pic_name;

	/**
	 * 晚上图片
	 */
	private String night_pic_name;

	/**
	 * 资讯数据
	 */
	private List<RoadDTO> roadList;

	/**
	 * 广告轮播数据
	 */
	private List<UrlDTO> urlList;

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

	public String getTempReal() {
		return tempReal;
	}

	public void setTempReal(String tempReal) {
		this.tempReal = tempReal;
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

	public List<RoadDTO> getRoadList() {
		return roadList;
	}

	public void setRoadList(List<RoadDTO> roadList) {
		this.roadList = roadList;
	}

	public List<UrlDTO> getUrlList() {
		return urlList;
	}

	public void setUrlList(List<UrlDTO> urlList) {
		this.urlList = urlList;
	}

}
