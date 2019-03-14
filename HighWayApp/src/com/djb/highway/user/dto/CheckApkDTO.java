package com.djb.highway.user.dto;

import com.djb.highway.framework.dto.BaseDTO;

public class CheckApkDTO extends BaseDTO {
    /**
	 * 
	 */
    private static final long serialVersionUID = -6682908844219788244L;
    private int apkVersionCode;
    private String apkPath;
    private String apkUpdateTime;
    private boolean updateFlag;
    private String apkVersionName;

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

}