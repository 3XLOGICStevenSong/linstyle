package com.djb.highway.road.dtoclient;

import java.util.List;

public class SectionImageListDTO extends BaseClientDTO {

    /**
     * 上行桩号
     */
    private List<SectionImageDTO> listUp;
    /**
     * 下行桩号
     */
    private List<SectionImageDTO> listDown;

    private List<String> listIds;

    /**
     * 收费站或互通立交起点名称
     */
    private String mStationNameStart;

    /**
     * 收费站或互通立交起点Code
     */
    private String mStationCodeStart;

    /**
     * 收费站或互通立交终点名称
     */
    private String mStationNameEnd;

    /**
     * 收费站或互通立交终点Code
     */
    private String mStationCodeEnd;

    /**
     * 收费站或互通立交起点桩号
     */
    private String mStationPileStart;
    /**
     * 收费站或互通立交终点桩号
     */
    private String mStationPileEnd;

    /**
     * 判断起点收费站或互通立交 1为收费站 2互通立交
     */
    private boolean mFlagStart;

    /**
     * 判断终点收费站或互通立交 1为收费站 2互通立交
     */
    private boolean mFlagEnd;

    public List<SectionImageDTO> getListUp() {
        return listUp;
    }

    public void setListUp(List<SectionImageDTO> listUp) {
        this.listUp = listUp;
    }

    public List<SectionImageDTO> getListDown() {
        return listDown;
    }

    public void setListDown(List<SectionImageDTO> listDown) {
        this.listDown = listDown;
    }

    public List<String> getListIds() {
        return listIds;
    }

    public void setListIds(List<String> listIds) {
        this.listIds = listIds;
    }

    public String getmStationNameStart() {
        return mStationNameStart;
    }

    public void setmStationNameStart(String mStationNameStart) {
        this.mStationNameStart = mStationNameStart;
    }

    public String getmStationNameEnd() {
        return mStationNameEnd;
    }

    public void setmStationNameEnd(String mStationNameEnd) {
        this.mStationNameEnd = mStationNameEnd;
    }

    public String getmStationPileStart() {
        return mStationPileStart;
    }

    public void setmStationPileStart(String mStationPileStart) {
        this.mStationPileStart = mStationPileStart;
    }

    public String getmStationPileEnd() {
        return mStationPileEnd;
    }

    public void setmStationPileEnd(String mStationPileEnd) {
        this.mStationPileEnd = mStationPileEnd;
    }

    public boolean ismFlagStart() {
        return mFlagStart;
    }

    public void setmFlagStart(boolean mFlagStart) {
        this.mFlagStart = mFlagStart;
    }

    public boolean ismFlagEnd() {
        return mFlagEnd;
    }

    public void setmFlagEnd(boolean mFlagEnd) {
        this.mFlagEnd = mFlagEnd;
    }

    public String getmStationCodeStart() {
        return mStationCodeStart;
    }

    public void setmStationCodeStart(String mStationCodeStart) {
        this.mStationCodeStart = mStationCodeStart;
    }

    public String getmStationCodeEnd() {
        return mStationCodeEnd;
    }

    public void setmStationCodeEnd(String mStationCodeEnd) {
        this.mStationCodeEnd = mStationCodeEnd;
    }

}
