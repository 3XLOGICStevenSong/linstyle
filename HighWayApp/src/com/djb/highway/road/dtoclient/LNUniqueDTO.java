package com.djb.highway.road.dtoclient;

public class LNUniqueDTO extends BaseClientDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 详情id
	 */
	private int ad_id;

	/**
	 * 信息名称
	 */
	private String ad_title;

	/**
	 * 图片url
	 */
	private String ad_pic;

	/**
	 * 详情
	 */
	private String ad_sum_content;
	public int getAd_id() {
		return ad_id;
	}

	public void setAd_id(int ad_id) {
		this.ad_id = ad_id;
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

	public String getAd_sum_content() {
		return ad_sum_content;
	}

	public void setAd_sum_content(String ad_sum_content) {
		this.ad_sum_content = ad_sum_content;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}
