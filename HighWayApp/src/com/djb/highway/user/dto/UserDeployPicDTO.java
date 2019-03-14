package com.djb.highway.user.dto;

import java.util.List;

import org.apache.struts.upload.FormFile;

import com.djb.highway.framework.dto.PageDTO;
import com.djb.highway.road.dto.PlazaDTO;

public class UserDeployPicDTO extends PageDTO {
    /**
     * 
     */
    private static final long serialVersionUID = -378848295830417701L;

    private Integer udp_id;

    private Integer u_id;

    private Long section_id;

    private Integer udp_status;

    private String udp_pic_path;

    private String deploy_msg;

    private Integer commend_count;

    private String commend_ucode;

    private String udp_deploy_time;

    private String udp_longitude;

    private String udp_latitude;

    private List<UserDeployPicDTO> list;

    private UserDTO UserDTO;

    private List<UserReviewDTO> userReviewDTOs;

    private FormFile image;

    // 用户分享信息类型： 拥堵事故、施工、话题
    private String udp_share_type;

    // 起点收费站CODE
    private String plaz_code_start;

    // 终点收费站CODE
    private String plaz_code_end;

    // 路段名称
    private String section_name;
    // 高速id
    private Integer h_id;
    // 起点收费站桩号
    private String start_stake_id;
    // 终点收费站桩号
    private String end_stake_id;

    // 用于车友分享的高速起点桩号，终点桩号
    private String startStakeId;

    private String endStakeId;
    // linkCode
    private String link_code;

    // 收费站list（初始化下拉列表用）
    private List<PlazaDTO> plazaDTOs;

    // 用于显示点赞用户的昵称
    private List<UserDTO> nickNames;

    private List<UserSupportDTO> userSupportDTOs;

    private String udp_version_time;

    // 刷新时间戳
    private String udp_flush_time;
    // 更多时间戳
    private String udp_more_time;

    private String type;

    // udp_type:当为"1"时，udp_type区分方向的获取用户分享信息；当为"0"时，表示获取整个高速的用户分享信息
    private String highway_udp_type;

    private String h_code;
    
    
    public String getSection_name() {
        return section_name;
    }

    public void setSection_name(String section_name) {
        this.section_name = section_name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLink_code() {
        return link_code;
    }

    public void setLink_code(String link_code) {
        this.link_code = link_code;
    }

    public String getUdp_version_time() {
        return udp_version_time;
    }

    public void setUdp_version_time(String udp_version_time) {
        this.udp_version_time = udp_version_time;
    }

    public FormFile getImage() {
        return image;
    }

    public void setImage(FormFile image) {
        this.image = image;
    }

    public Integer getUdp_id() {
        return udp_id;
    }

    public void setUdp_id(Integer udp_id) {
        this.udp_id = udp_id;
    }

    public Integer getU_id() {
        return u_id;
    }

    public void setU_id(Integer u_id) {
        this.u_id = u_id;
    }

    public Long getSection_id() {
        return section_id;
    }

    public void setSection_id(Long section_id) {
        this.section_id = section_id;
    }

    public Integer getUdp_status() {
        return udp_status;
    }

    public void setUdp_status(Integer udp_status) {
        this.udp_status = udp_status;
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

    public Integer getCommend_count() {
        return commend_count;
    }

    public void setCommend_count(Integer commend_count) {
        this.commend_count = commend_count;
    }

    public String getCommend_ucode() {
        return commend_ucode;
    }

    public void setCommend_ucode(String commend_ucode) {
        this.commend_ucode = commend_ucode;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public String getUdp_longitude() {
        return udp_longitude;
    }

    public void setUdp_longitude(String udp_longitude) {
        this.udp_longitude = udp_longitude;
    }

    public String getUdp_latitude() {
        return udp_latitude;
    }

    public void setUdp_latitude(String udp_latitude) {
        this.udp_latitude = udp_latitude;
    }

    public List<UserDeployPicDTO> getList() {
        return list;
    }

    public void setList(List<UserDeployPicDTO> list) {
        this.list = list;
    }

    public UserDTO getUserDTO() {
        return UserDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        UserDTO = userDTO;
    }

    public List<UserReviewDTO> getUserReviewDTOs() {
        return userReviewDTOs;
    }

    public void setUserReviewDTOs(List<UserReviewDTO> userReviewDTOs) {
        this.userReviewDTOs = userReviewDTOs;
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

    public List<PlazaDTO> getPlazaDTOs() {
        return plazaDTOs;
    }

    public void setPlazaDTOs(List<PlazaDTO> plazaDTOs) {
        this.plazaDTOs = plazaDTOs;
    }

    public List<UserSupportDTO> getUserSupportDTOs() {
        return userSupportDTOs;
    }

    public void setUserSupportDTOs(List<UserSupportDTO> userSupportDTOs) {
        this.userSupportDTOs = userSupportDTOs;
    }

    public List<UserDTO> getNickNames() {
        return nickNames;
    }

    public void setNickNames(List<UserDTO> nickNames) {
        this.nickNames = nickNames;
    }

    public String getUdp_share_type() {
        return udp_share_type;
    }

    public void setUdp_share_type(String udp_share_type) {
        this.udp_share_type = udp_share_type;
    }

    public String getUdp_deploy_time() {
        return udp_deploy_time;
    }

    public void setUdp_deploy_time(String udp_deploy_time) {
        this.udp_deploy_time = udp_deploy_time;
    }

    public Integer getH_id() {
        return h_id;
    }

    public void setH_id(Integer h_id) {
        this.h_id = h_id;
    }

    public String getStart_stake_id() {
        return start_stake_id;
    }

    public void setStart_stake_id(String start_stake_id) {
        this.start_stake_id = start_stake_id;
    }

    public String getEnd_stake_id() {
        return end_stake_id;
    }

    public void setEnd_stake_id(String end_stake_id) {
        this.end_stake_id = end_stake_id;
    }

    public String getStartStakeId() {
        return startStakeId;
    }

    public void setStartStakeId(String startStakeId) {
        this.startStakeId = startStakeId;
    }

    public String getEndStakeId() {
        return endStakeId;
    }

    public void setEndStakeId(String endStakeId) {
        this.endStakeId = endStakeId;
    }

    public String getUdp_flush_time() {
        return udp_flush_time;
    }

    public void setUdp_flush_time(String udp_flush_time) {
        this.udp_flush_time = udp_flush_time;
    }

    public String getUdp_more_time() {
        return udp_more_time;
    }

    public void setUdp_more_time(String udp_more_time) {
        this.udp_more_time = udp_more_time;
    }

    public String getHighway_udp_type() {
        return highway_udp_type;
    }

    public void setHighway_udp_type(String highway_udp_type) {
        this.highway_udp_type = highway_udp_type;
    }

    public String getH_code() {
        return h_code;
    }

    public void setH_code(String h_code) {
        this.h_code = h_code;
    }

}