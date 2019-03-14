package com.djb.highway.road.dto;

import java.util.Date;

import com.djb.highway.framework.dto.PageDTO;

public class FeatureLNDTO extends PageDTO {
	/**
     * 
     */
	private static final long serialVersionUID = -719473171973793158L;
	private Integer ad_id;

	private Integer ad_city_id;

	private String ad_type;
	private String ad_title;

	private String ad_pic;

	private String ad_content;

	private String ad_pic_list;

	private String ad_road_line;

	private Date ad_create_time;

	private Date ad_end_time;

	private String ad_status;

	private String memo;

	private String ad_sum_content;

	private Integer level;

	public Integer getAd_id() {
		return ad_id;
	}

	public void setAd_id(Integer ad_id) {
		this.ad_id = ad_id;
	}

	public Integer getAd_city_id() {
		return ad_city_id;
	}

	public void setAd_city_id(Integer ad_city_id) {
		this.ad_city_id = ad_city_id;
	}

	public String getAd_type() {
		return ad_type;
	}

	public void setAd_type(String ad_type) {
		this.ad_type = ad_type;
	}

	public String getAd_title() {
		return ad_title;
	}

	public void setAd_title(String ad_title) {
		this.ad_title = ad_title;
	}

	public String getAd_pic() {
		return ad_pic;
	}

	public void setAd_pic(String ad_pic) {
		this.ad_pic = ad_pic;
	}

	public String getAd_content() {
		return ad_content;
	}

	public void setAd_content(String ad_content) {
		this.ad_content = ad_content;
	}

	public String getAd_pic_list() {
		return ad_pic_list;
	}

	public void setAd_pic_list(String ad_pic_list) {
		this.ad_pic_list = ad_pic_list;
	}

	public String getAd_road_line() {
		return ad_road_line;
	}

	public void setAd_road_line(String ad_road_line) {
		this.ad_road_line = ad_road_line;
	}

	public Date getAd_create_time() {
		return ad_create_time;
	}

	public void setAd_create_time(Date ad_create_time) {
		this.ad_create_time = ad_create_time;
	}

	public Date getAd_end_time() {
		return ad_end_time;
	}

	public void setAd_end_time(Date ad_end_time) {
		this.ad_end_time = ad_end_time;
	}

	public String getAd_status() {
		return ad_status;
	}

	public void setAd_status(String ad_status) {
		this.ad_status = ad_status;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getAd_sum_content() {
		return ad_sum_content;
	}

	public void setAd_sum_content(String ad_sum_content) {
		this.ad_sum_content = ad_sum_content;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}