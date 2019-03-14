package com.djb.highway.road.dtoclient;

/**
 * 道路事件详情 DTO
 * 
 * @author
 */
public class RoadEventDTO {

    /**
     * 高速id
     */
    private String highwayId;

    /**
     * 高速名称
     */
    private String highwayName;

    /**
     * 管制类型
     */
    private String eventType;

    /**
     * 管制方式
     */
    private String controlWay;

    /**
     * 管制范围
     */
    private String controlScope;

    /**
     * 所在方向
     */
    private String direction;

    /**
     * 开始时间
     */
    private String startTime;

    /**
     * 预计结束时间
     */
    private String endTime;

    /**
     * 发布时间
     */
    private String publishTime;

    /**
     * 事件描述
     */
    private String eventInfo;

    /**
     * 绕行方案标记
     */
    private boolean roundFlag = false;

    // 绕行方案URL
    private String roundingUrl;;
    // 绕行方案Content
    private String roundingWord;;

    public String getHighwayId() {
        return highwayId;
    }

    public void setHighwayId(String highwayId) {
        this.highwayId = highwayId;
    }

    public String getHighwayName() {
        return highwayName;
    }

    public void setHighwayName(String highwayName) {
        this.highwayName = highwayName;
    }

    public String getControlWay() {
        return controlWay;
    }

    public void setControlWay(String controlWay) {
        this.controlWay = controlWay;
    }

    public String getControlScope() {
        return controlScope;
    }

    public void setControlScope(String controlScope) {
        this.controlScope = controlScope;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public String getEventInfo() {
        return eventInfo;
    }

    public void setEventInfo(String eventInfo) {
        this.eventInfo = eventInfo;
    }

    public boolean isRoundFlag() {
        return roundFlag;
    }

    public void setRoundFlag(boolean roundFlag) {
        this.roundFlag = roundFlag;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getRoundingUrl() {
        return roundingUrl;
    }

    public void setRoundingUrl(String roundingUrl) {
        this.roundingUrl = roundingUrl;
    }

    public String getRoundingWord() {
        return roundingWord;
    }

    public void setRoundingWord(String roundingWord) {
        this.roundingWord = roundingWord;
    }

}