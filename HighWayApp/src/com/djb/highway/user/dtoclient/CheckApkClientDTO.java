package com.djb.highway.user.dtoclient;

import com.djb.highway.road.dtoclient.BaseClientDTO;

public class CheckApkClientDTO extends BaseClientDTO {
	private int apkVersionCode;
	private String apkPath;
	private String apkUpdateTime;
	private boolean updateFlag;
	private String apkVersionName;

	private String apkVersionContent;

	public int getApkVersionCode() {
		return apkVersionCode;
	}

	public void setApkVersionCode(int apkVersionCode) {
		this.apkVersionCode = apkVersionCode;
	}

	public String getApkPath() {
		return apkPath;
	}

	public void setApkPath(String apkPath) {
		this.apkPath = apkPath;
	}

	public String getApkUpdateTime() {
		return apkUpdateTime;
	}

	public void setApkUpdateTime(String apkUpdateTime) {
		this.apkUpdateTime = apkUpdateTime;
	}

	public boolean isUpdateFlag() {
		return updateFlag;
	}

	public void setUpdateFlag(boolean updateFlag) {
		this.updateFlag = updateFlag;
	}

	public String getApkVersionName() {
		return apkVersionName;
	}

	public void setApkVersionName(String apkVersionName) {
		this.apkVersionName = apkVersionName;
	}

	public String getApkVersionContent() {
		return apkVersionContent;
	}

	public void setApkVersionContent(String apkVersionContent) {
		this.apkVersionContent = apkVersionContent;
	}

}