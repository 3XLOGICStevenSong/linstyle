package com.djb.highway.road.entity;

import java.util.Date;


import com.djb.highway.framework.entity.PageModel;

public class FeatureLNEntity extends PageModel {
	/**
     * 
     */
	private static final long serialVersionUID = 2240303270916345847L;

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
		FeatureLNEntity other = (FeatureLNEntity) that;
		return (this.getAd_id() == null ? other.getAd_id() == null : this
				.getAd_id().equals(other.getAd_id()))
				&& (this.getAd_city_id()  == null ? other.getAd_city_id() == null : this
						.getAd_city_id().equals(other.getAd_city_id()))
				&& (this.getAd_type() == null ? other.getAd_type() == null : this
						.getAd_type().equals(other.getAd_type()))
				&& (this.getAd_title() == null ? other.getAd_title()== null : this
						.getAd_title().equals(other.getAd_title()))
				&& (this.getAd_pic() == null ? other.getAd_pic()== null
						: this.getAd_pic().equals(other.getAd_pic()))
			    && (this.getAd_content() == null ? other.getAd_content()== null
						: this.getAd_content().equals(other.getAd_content()))
				&& (this.getAd_pic_list() == null ? other.getAd_pic_list() == null : this
						.getAd_pic_list().equals(other.getAd_pic_list()))
				&& (this. getAd_road_line() == null ? other. getAd_road_line() == null
						: this. getAd_road_line().equals(other. getAd_road_line()))
				&& (this. getAd_create_time() == null ? other. getAd_create_time() == null : this
						. getAd_create_time().equals(other. getAd_create_time()))
				&& (this.getAd_end_time() == null ? other.getAd_end_time() == null : this
						.getAd_end_time().equals(other.getAd_end_time()))
				&& (this.getAd_status() == null ? other.getAd_status() == null : this
						.getAd_status().equals(other.getAd_status()))
				&& (this.getMemo() == null ? other.getMemo() == null
						: this.getMemo().equals(other.getMemo()))
				&& (this.getAd_sum_content() == null ? other.getAd_sum_content() == null
						: this.getAd_sum_content().equals(other.getAd_sum_content())
				&& (this.getLevel() == null ? other.getLevel() == null
						: this.getLevel().equals(other.getLevel())));
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((getAd_id()== null) ? 0 : getAd_id().hashCode());
		result = prime * result
				+ ((getAd_city_id() == null) ? 0 : getAd_city_id().hashCode());
		result = prime * result
				+ ((getAd_type()  == null) ? 0 : getAd_type() .hashCode());
		result = prime * result
				+ ((getAd_title() == null) ? 0 : getAd_title().hashCode());
		result = prime * result
				+ ((getAd_pic() == null) ? 0 : getAd_pic().hashCode());
		result = prime * result
				+ ((getAd_content() == null) ? 0 : getAd_content().hashCode());
		result = prime * result
				+ ((getAd_pic_list()== null) ? 0 : getAd_pic_list().hashCode());
		result = prime * result
				+ ((getAd_road_line() == null) ? 0 : getAd_road_line().hashCode());
		result = prime * result
				+ ((getAd_create_time() == null) ? 0 :getAd_create_time().hashCode());
		result = prime * result
				+ ((getAd_end_time() == null) ? 0 : getAd_end_time().hashCode());
		result = prime * result
				+ ((getAd_status() == null) ? 0 : getAd_status().hashCode());
		result = prime * result
				+ ((getMemo() == null) ? 0 : getMemo().hashCode());
		result = prime
				* result
				+ ((getAd_sum_content() == null) ? 0 : getAd_sum_content().hashCode());
		result = prime * result
				+ ((getLevel() == null) ? 0 : getLevel().hashCode());
		return result;
	}

	

}