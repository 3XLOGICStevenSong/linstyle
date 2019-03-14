package com.djb.highway.user.entity;

import java.util.Date;
import java.util.List;

import com.djb.highway.framework.entity.PageModel;

public class UserDeployPicEntity extends PageModel {
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

    private Date udp_deploy_time;

    private String udp_longitude;

    private String udp_latitude;

    private UserEntity UserEntity;

    private List<UserReviewEntity> userReviewEntitys;

    private List<UserSupportEntity> userSupportEntitys;

    private String[] linkCodes;
    // 高速id
    private Integer h_id;
    // 起点收费站桩号
    private String start_stake_id;
    // 终点收费站桩号
    private String end_stake_id;
    // 用于车友分享的高速起点桩号，终点桩号
    private String startStakeId;

    private String endStakeId;
    // 行驶路线收费站数组
    private String[] plazaCodeArgs;
    // 起点终点收费站数组
    private String[] plazaListArgs;
    // 用户分享信息类型： 拥堵事故、施工、话题
    private String udp_share_type;
    // 起点收费站CODE
    private String plaz_code_start;

    // 终点收费站CODE
    private String plaz_code_end;

    private String link_code;

    // 路段名称
    private String section_name;

    private Date udp_version_time;

    // 刷新时间戳
    private Date udp_flush_time;
    // 更多时间戳
    private Date udp_more_time;

    // udp_type:当为"1"时，udp_type区分方向的获取用户分享信息；当为"0"时，表示获取整个高速的用户分享信息
    private String highway_udp_type;
   
    private String h_code;
    
    public String getSection_name() {
        return section_name;
    }

    public void setSection_name(String section_name) {
        this.section_name = section_name;
    }

    public Date getUdp_version_time() {
        return udp_version_time;
    }

    public String getLink_code() {
        return link_code;
    }

    public void setLink_code(String link_code) {
        this.link_code = link_code;
    }

    public void setUdp_version_time(Date udp_version_time) {
        this.udp_version_time = udp_version_time;
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

    public Date getUdp_deploy_time() {
        return udp_deploy_time;
    }

    public void setUdp_deploy_time(Date udp_deploy_time) {
        this.udp_deploy_time = udp_deploy_time;
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

    public UserEntity getUserEntity() {
        return UserEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        UserEntity = userEntity;
    }

    public List<UserReviewEntity> getUserReviewEntitys() {
        return userReviewEntitys;
    }

    public void setUserReviewEntitys(List<UserReviewEntity> userReviewEntitys) {
        this.userReviewEntitys = userReviewEntitys;
    }

    public List<UserSupportEntity> getUserSupportEntitys() {
        return userSupportEntitys;
    }

    public void setUserSupportEntitys(List<UserSupportEntity> userSupportEntitys) {
        this.userSupportEntitys = userSupportEntitys;
    }

    public String getUdp_share_type() {
        return udp_share_type;
    }

    public void setUdp_share_type(String udp_share_type) {
        this.udp_share_type = udp_share_type;
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

    public String[] getPlazaCodeArgs() {
        return plazaCodeArgs;
    }

    public void setPlazaCodeArgs(String[] plazaCodeArgs) {
        this.plazaCodeArgs = plazaCodeArgs;
    }

    public String[] getLinkCodes() {
        return linkCodes;
    }

    public void setLinkCodes(String[] linkCodes) {
        this.linkCodes = linkCodes;
    }

    public String[] getPlazaListArgs() {
        return plazaListArgs;
    }

    public void setPlazaListArgs(String[] plazaListArgs) {
        this.plazaListArgs = plazaListArgs;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        UserDeployPicEntity other = (UserDeployPicEntity) that;
        return (this.getUdp_id() == null ? other.getUdp_id() == null : this.getUdp_id().equals(other.getUdp_id()))
                        && (this.getU_id() == null ? other.getU_id() == null : this.getU_id().equals(other.getU_id()))
                        && (this.getSection_id() == null ? other.getSection_id() == null : this.getSection_id().equals(other.getSection_id()))
                        && (this.getUdp_pic_path() == null ? other.getUdp_pic_path() == null : this.getUdp_pic_path().equals(other.getUdp_pic_path()))
                        && (this.getDeploy_msg() == null ? other.getDeploy_msg() == null : this.getDeploy_msg().equals(other.getDeploy_msg()))
                        && (this.getCommend_count() == null ? other.getCommend_count() == null : this.getCommend_count().equals(other.getCommend_count()))
                        && (this.getUdp_deploy_time() == null ? other.getUdp_deploy_time() == null : this.getUdp_deploy_time().equals(
                                        other.getUdp_deploy_time()))
                        && (this.getUdp_longitude() == null ? other.getUdp_longitude() == null : this.getUdp_longitude().equals(other.getUdp_longitude()))
                        && (this.getUdp_latitude() == null ? other.getUdp_latitude() == null : this.getUdp_latitude().equals(other.getUdp_latitude()))
                        && (this.getUdp_share_type() == null ? other.getUdp_share_type() == null : this.getUdp_share_type().equals(other.getUdp_share_type()))
                        && (this.getPlaz_code_start() == null ? other.getPlaz_code_start() == null : this.getPlaz_code_start().equals(
                                        other.getPlaz_code_start()))
                        && (this.getPlaz_code_end() == null ? other.getPlaz_code_end() == null : this.getPlaz_code_end().equals(other.getPlaz_code_end()))
                        && (this.getLink_code() == null ? other.getLink_code() == null : this.getLink_code().equals(other.getLink_code()))
                        && (this.getSection_name() == null ? other.getSection_name() == null : this.getSection_name().equals(other.getSection_name()))
                        && (this.getH_id() == null ? other.getH_id() == null : this.getH_id().equals(other.getH_id()))
                        && (this.getStart_stake_id() == null ? other.getStart_stake_id() == null : this.getStart_stake_id().equals(other.getStart_stake_id()))
                        && (this.getEnd_stake_id() == null ? other.getEnd_stake_id() == null : this.getEnd_stake_id().equals(other.getEnd_stake_id()))
                        && (this.getH_code() == null ? other.getH_code() == null : this.getH_code().equals(other.getH_code()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getUdp_id() == null) ? 0 : getUdp_id().hashCode());
        result = prime * result + ((getU_id() == null) ? 0 : getU_id().hashCode());
        result = prime * result + ((getSection_id() == null) ? 0 : getSection_id().hashCode());
        result = prime * result + ((getUdp_pic_path() == null) ? 0 : getUdp_pic_path().hashCode());
        result = prime * result + ((getDeploy_msg() == null) ? 0 : getDeploy_msg().hashCode());
        result = prime * result + ((getCommend_count() == null) ? 0 : getCommend_count().hashCode());
        result = prime * result + ((getUdp_deploy_time() == null) ? 0 : getUdp_deploy_time().hashCode());
        result = prime * result + ((getUdp_longitude() == null) ? 0 : getUdp_longitude().hashCode());
        result = prime * result + ((getUdp_latitude() == null) ? 0 : getUdp_latitude().hashCode());
        result = prime * result + ((getUdp_share_type() == null) ? 0 : getUdp_share_type().hashCode());
        result = prime * result + ((getPlaz_code_start() == null) ? 0 : getPlaz_code_start().hashCode());
        result = prime * result + ((getPlaz_code_end() == null) ? 0 : getPlaz_code_end().hashCode());
        result = prime * result + ((getLink_code() == null) ? 0 : getLink_code().hashCode());
        result = prime * result + ((getSection_name() == null) ? 0 : getSection_name().hashCode());
        result = prime * result + ((getH_id() == null) ? 0 : getH_id().hashCode());
        result = prime * result + ((getStart_stake_id() == null) ? 0 : getStart_stake_id().hashCode());
        result = prime * result + ((getEnd_stake_id() == null) ? 0 : getEnd_stake_id().hashCode());
        result = prime * result + ((getH_code() == null) ? 0 : getH_code().hashCode());
        
        return result;
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

    public Date getUdp_flush_time() {
        return udp_flush_time;
    }

    public void setUdp_flush_time(Date udp_flush_time) {
        this.udp_flush_time = udp_flush_time;
    }

    public Date getUdp_more_time() {
        return udp_more_time;
    }

    public void setUdp_more_time(Date udp_more_time) {
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