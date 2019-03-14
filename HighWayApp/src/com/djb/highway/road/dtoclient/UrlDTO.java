package com.djb.highway.road.dtoclient;

public class UrlDTO extends BaseClientDTO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 图片路径
	 */
	private String url;

	/**
	 * 本地默认图片路径
	 */
	private int urlDefault;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getUrlDefault() {
		return urlDefault;
	}

	public void setUrlDefault(int urlDefault) {
		this.urlDefault = urlDefault;
	}

	@Override
	public String toString() {
		return "UrlDTO [url=" + url + ", urlDefault=" + urlDefault + "]";
	}

}
