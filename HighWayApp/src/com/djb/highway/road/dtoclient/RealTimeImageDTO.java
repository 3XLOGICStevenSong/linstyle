package com.djb.highway.road.dtoclient;

public class RealTimeImageDTO extends BaseClientDTO {

	/**
	 * 图片路径
	 */
	private String imageUrl;

	/**
	 * 桩号信息
	 */
	private String pileNumInfo;

	/**
	 * 拍摄时间
	 * 
	 */
	private String takeTime;

	// 摄像头地址
	private String c_addr;

	/**
	 * 起点收费站
	 */
	private String mStartStationName;
	/**
	 * 起点收费站code
	 */
	private String mStartStationCode;

	/**
	 * 终点收费站
	 */
	private String mEndStationName;

	/**
	 * 终点收费站code
	 */
	private String mEndStationCode;

	public String getmStartStationName() {
		return mStartStationName;
	}

	public void setmStartStationName(String mStartStationName) {
		this.mStartStationName = mStartStationName;
	}

	public String getmStartStationCode() {
		return mStartStationCode;
	}

	public void setmStartStationCode(String mStartStationCode) {
		this.mStartStationCode = mStartStationCode;
	}

	public String getmEndStationName() {
		return mEndStationName;
	}

	public void setmEndStationName(String mEndStationName) {
		this.mEndStationName = mEndStationName;
	}

	public String getmEndStationCode() {
		return mEndStationCode;
	}

	public void setmEndStationCode(String mEndStationCode) {
		this.mEndStationCode = mEndStationCode;
	}

	public String getC_addr() {
		return c_addr;
	}

	public void setC_addr(String c_addr) {
		this.c_addr = c_addr;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getPileNumInfo() {
		return pileNumInfo;
	}

	public void setPileNumInfo(String pileNumInfo) {
		this.pileNumInfo = pileNumInfo;
	}

	public String getTakeTime() {
		return takeTime;
	}

	public void setTakeTime(String takeTime) {
		this.takeTime = takeTime;
	}

}
