package com.djb.highway.road.dtoclient;

public class RoadControlInfoClearDTO extends BaseClientDTO {

    /**
     * 默认版本号
     */
    private static final long serialVersionUID = 1L;

    /**
     * 高速id
     */
    private Integer h_id;

    /**
     * 高速Code
     */
    private String h_code;

    /**
     * 高速名称
     */
    private String h_name;
    /**
     * 管制范围
     */
    private String control_scope;

    /**
     * 管制方式
     */
    private String control_mode;

    /**
     * 管制原因
     */
    private String control_reason;

    /**
     * 开始时间
     */
    private String start_time;

    /**
     * 预计结束时间
     */
    private String plan_end_time;

    /**
     * 发布时间
     */
    private String deploy_time;

    /**
     * 事件描述
     */
    private String eventInfo;
    /**
     * 绕行方案标记
     */
    private boolean roundFlag = false;

    /**
     * 绕行方案图片地址
     */
    private String roundingUrl;
    /**
     * 绕行方案文字
     */
    private String roundingWord;
    /**
     * 所在方向
     */
    private String direction;
    /**
     * 高速id
     */
    private Integer rci_id;
    public Integer getH_id() {
        return h_id;
    }

    public void setH_id(Integer h_id) {
        this.h_id = h_id;
    }

    public String getH_code() {
        return h_code;
    }

    public void setH_code(String h_code) {
        this.h_code = h_code;
    }

    public String getH_name() {
        return h_name;
    }

    public void setH_name(String h_name) {
        this.h_name = h_name;
    }

    public String getControl_scope() {
        return control_scope;
    }

    public void setControl_scope(String control_scope) {
        this.control_scope = control_scope;
    }

    public String getControl_mode() {
        return control_mode;
    }

    public void setControl_mode(String control_mode) {
        this.control_mode = control_mode;
    }

    public String getControl_reason() {
        return control_reason;
    }

    public void setControl_reason(String control_reason) {
        this.control_reason = control_reason;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getPlan_end_time() {
        return plan_end_time;
    }

    public void setPlan_end_time(String plan_end_time) {
        this.plan_end_time = plan_end_time;
    }

    public String getDeploy_time() {
        return deploy_time;
    }

    public void setDeploy_time(String deploy_time) {
        this.deploy_time = deploy_time;
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

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public Integer getRci_id() {
        return rci_id;
    }

    public void setRci_id(Integer rci_id) {
        this.rci_id = rci_id;
    }

}
