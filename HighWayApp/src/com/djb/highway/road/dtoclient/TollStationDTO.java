package com.djb.highway.road.dtoclient;

import java.util.List;

public class TollStationDTO extends BaseClientDTO {
    /**
     * 收费站名称
     */
    private String stationName;
    /**
     * 收费站Code
     */
    private String toll_station_code;
    /**
     * 高速名
     */
    private String highWayName;
    /**
     * 判断ETC自动收费口:0 没有,1 有
     */
    private String etc;
    /**
     * 桩号
     */
    private String mileAge;
    /**
     * 城市
     */
    private String ctiy;
    /**
     * 收费站状态 0开通，1出口封闭，2入口封闭，3双向封闭
     */
    private String stationState;
    /**
     * 出口车道数
     */
    private String exitRoadNum;
    /**
     * 入口车道数
     */
    private String entranceRoadNum;
    /**
     * 出口通达地点
     */
    private String exitReach;
    /**
     * 入口通达地点
     */
    private String entranceReach;
    /**
     * 出口拥堵指数
     */
    private String exitFlowInfo;
    /**
     * 入口拥堵指数
     */
    private String entranceFlowInfo;
    /**
     * 绕行方案图片路径
     */
    private String roundUrl;
    /**
     * 绕行方案文字
     */
    private String roundWord;
    /**
     * 即时影响图片路径
     */
    private String shootUrl;
    /**
     * 出入口示意图路径
     */
    private String sketchMap;

    /**
     * 摄像头ID
     * 
     */
    private Integer c_id;

    /**
     * 摄像头IDs
     * 
     */
    private List<Integer> c_ids;

    private String location_desc;

    // 情报板所属的收费站或服务区的Id
    private Integer location_id;
    // 情报板是否显示的标示
    private boolean ib_flag;

    public Integer getC_id() {
        return c_id;
    }

    public void setC_id(Integer c_id) {
        this.c_id = c_id;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getHighWayName() {
        return highWayName;
    }

    public void setHighWayName(String highWayName) {
        this.highWayName = highWayName;
    }

    public String getMileAge() {
        return mileAge;
    }

    public void setMileAge(String mileAge) {
        this.mileAge = mileAge;
    }

    public String getCtiy() {
        return ctiy;
    }

    public void setCtiy(String ctiy) {
        this.ctiy = ctiy;
    }

    public String getStationState() {
        return stationState;
    }

    public void setStationState(String stationState) {
        this.stationState = stationState;
    }

    public String getExitRoadNum() {
        return exitRoadNum;
    }

    public void setExitRoadNum(String exitRoadNum) {
        this.exitRoadNum = exitRoadNum;
    }

    public String getEntranceRoadNum() {
        return entranceRoadNum;
    }

    public void setEntranceRoadNum(String entranceRoadNum) {
        this.entranceRoadNum = entranceRoadNum;
    }

    public String getExitReach() {
        return exitReach;
    }

    public void setExitReach(String exitReach) {
        this.exitReach = exitReach;
    }

    public String getEntranceReach() {
        return entranceReach;
    }

    public void setEntranceReach(String entranceReach) {
        this.entranceReach = entranceReach;
    }

    public String getExitFlowInfo() {
        return exitFlowInfo;
    }

    public void setExitFlowInfo(String exitFlowInfo) {
        this.exitFlowInfo = exitFlowInfo;
    }

    public String getEntranceFlowInfo() {
        return entranceFlowInfo;
    }

    public void setEntranceFlowInfo(String entranceFlowInfo) {
        this.entranceFlowInfo = entranceFlowInfo;
    }

    public String getRoundUrl() {
        return roundUrl;
    }

    public void setRoundUrl(String roundUrl) {
        this.roundUrl = roundUrl;
    }

    public String getShootUrl() {
        return shootUrl;
    }

    public void setShootUrl(String shootUrl) {
        this.shootUrl = shootUrl;
    }

    public String getSketchMap() {
        return sketchMap;
    }

    public void setSketchMap(String sketchMap) {
        this.sketchMap = sketchMap;
    }

    public String getRoundWord() {
        return roundWord;
    }

    public void setRoundWord(String roundWord) {
        this.roundWord = roundWord;
    }

    public String getEtc() {
        return etc;
    }

    public void setEtc(String etc) {
        this.etc = etc;
    }

    public TollStationDTO() {
        stationName = null;
        etc = null;
        mileAge = null;
        ctiy = null;
        stationState = null;
        exitRoadNum = null;
        entranceRoadNum = null;
        exitReach = null;
        entranceReach = null;
        exitFlowInfo = null;
        entranceFlowInfo = null;
        roundUrl = null;
        roundWord = null;
        shootUrl = null;
        sketchMap = null;

    }

    public String getLocation_desc() {
        return location_desc;
    }

    public void setLocation_desc(String location_desc) {
        this.location_desc = location_desc;
    }

    public String getToll_station_code() {
        return toll_station_code;
    }

    public void setToll_station_code(String toll_station_code) {
        this.toll_station_code = toll_station_code;
    }

    public List<Integer> getC_ids() {
        return c_ids;
    }

    public void setC_ids(List<Integer> c_ids) {
        this.c_ids = c_ids;
    }

    public Integer getLocation_id() {
        return location_id;
    }

    public void setLocation_id(Integer location_id) {
        this.location_id = location_id;
    }

    public boolean isIb_flag() {
        return ib_flag;
    }

    public void setIb_flag(boolean ib_flag) {
        this.ib_flag = ib_flag;
    }

}