package com.djb.highway.road.dtoclient;

public class CameraBakClientDTO {

    /**
     * 图片路径
     */
    private String imageUrl;

    /**
     * 桩号信息
     */
    private String pileNumInfo;

    /**
     * 摄像头Id
     * 
     */
    private Integer cid;

    /**
     * 摄像头地址
     * 
     */
    private String addr;

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

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

}
