package com.djb.highway.user.dtoclient;

import com.djb.highway.road.dtoclient.BaseClientDTO;

public class UserDeployPicClearDTO extends BaseClientDTO {
    /**
     * 用户Id
     */
    private Integer u_id;
    /**
     * 分享数据Id
     */
    private Integer udp_id;
    /**
     * 分享图片路径
     */
    private String udp_pic_path;
    /**
     * 分享文字
     */
    private String deploy_msg;
    /**
     * 分享时间
     */
    private String udp_deploy_time;
    /**
     * 赞的个数
     */
    private String zanCount;
    /**
     * 起点收费站CODE
     */
    private String plaz_code_start;
    /**
     * 终点收费站CODE
     */
    private String plaz_code_end;
    /**
     * 话题
     */
    private String udp_share_type;

    // 路段名称
    private String section_name;

    // 经度
    private String longitude_b;

    // 纬度
    private String latitude_b;

    // 用户名称
    private String u_name;
    // 高速ID
    private Integer h_id;

    public String getSection_name() {
        return section_name;
    }

    public void setSection_name(String section_name) {
        this.section_name = section_name;
    }

    public Integer getU_id() {
        return u_id;
    }

    public void setU_id(Integer u_id) {
        this.u_id = u_id;
    }

    public Integer getUdp_id() {
        return udp_id;
    }

    public void setUdp_id(Integer udp_id) {
        this.udp_id = udp_id;
    }

    public String getUdp_pic_path() {
        return udp_pic_path;
    }

    public void setUdp_pic_path(String udp_pic_path) {
        this.udp_pic_path = udp_pic_path == null ? null : udp_pic_path.trim();
    }

    public String getDeploy_msg() {
        return deploy_msg;
    }

    public void setDeploy_msg(String deploy_msg) {
        this.deploy_msg = deploy_msg == null ? null : deploy_msg.trim();
    }

    public String getUdp_deploy_time() {
        return udp_deploy_time;
    }

    public void setUdp_deploy_time(String udp_deploy_time) {
        this.udp_deploy_time = udp_deploy_time;
    }

    public String getZanCount() {
        return zanCount;
    }

    public void setZanCount(String zanCount) {
        this.zanCount = zanCount;
    }

    public String getPlaz_code_start() {
        return plaz_code_start;
    }

    public void setPlaz_code_start(String plaz_code_start) {
        this.plaz_code_start = plaz_code_start;
    }

    public String getPlaz_code_end() {
        return plaz_code_end;
    }

    public void setPlaz_code_end(String plaz_code_end) {
        this.plaz_code_end = plaz_code_end;
    }

    public String getUdp_share_type() {
        return udp_share_type;
    }

    public void setUdp_share_type(String udp_share_type) {
        this.udp_share_type = udp_share_type;
    }

    public String getLongitude_b() {
        return longitude_b;
    }

    public void setLongitude_b(String longitude_b) {
        this.longitude_b = longitude_b;
    }

    public String getLatitude_b() {
        return latitude_b;
    }

    public void setLatitude_b(String latitude_b) {
        this.latitude_b = latitude_b;
    }

    public String getU_name() {
        return u_name;
    }

    public void setU_name(String u_name) {
        this.u_name = u_name;
    }

    public Integer getH_id() {
        return h_id;
    }

    public void setH_id(Integer h_id) {
        this.h_id = h_id;
    }

}