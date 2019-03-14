package com.djb.highway.road.dtoclient;

import java.util.List;

/**
 * 类说明
 * 
 * @author LiWm E-mail:
 * @version 创建时间：2014年9月10日 上午9:00:57
 */
public class UnitDetailDTO extends BaseClientDTO {

	/**
	 * 详细信息文字
	 */
	private String ad_content;

	/**
	 * 详细信息图片链接集合
	 */
	private List<UnitPicDTO> picList;

	/**
	 * 详细信息行程规划
	 */
	private String ad_road_line;

	public String getAd_content() {
		return ad_content;
	}

	public void setAd_content(String ad_content) {
		this.ad_content = ad_content;
	}

	public List<UnitPicDTO> getPicList() {
		return picList;
	}

	public void setPicList(List<UnitPicDTO> list) {
		this.picList = list;
	}

	public String getAd_road_line() {
		return ad_road_line;
	}

	public void setAd_road_line(String ad_road_line) {
		this.ad_road_line = ad_road_line;
	}

}
