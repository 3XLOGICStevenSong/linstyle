package com.djb.highway.road.entity;

import java.util.Date;

public class RoadControlInfoEntity extends PageModelEntity {
    /**
	 * 
	 */
    private static final long serialVersionUID = 5403226165509234889L;

    private Integer rci_id;

    private Integer h_id;

    private String h_code;

    private String h_name;

    private String start_stake_id;

    private String end_stake_id;

    private String control_scope;

    private String control_direction;

    private String control_mode;

    private Date start_time;

    private Date plan_end_time;

    private Date real_end_time;

    private String rci_status;

    private String rci_type;

    private String rci_type_pic_path;

    private Integer start_plaz_id;

    private String start_plaz_code;

    private Integer end_plaz_id;

    private String end_plaz_code;

    private String plaz_list;

    private String sa_list;

    private String passing_round_pic_url;

    private String passing_round_desc;

    private String rci_content;

    private Date deploy_time;

    private String longitude_a;

    private String latitude_a;

    private String longitude_b;

    private String latitude_b;

    private String[] h_codeArgs;

    private String[] roadControlIdArgs;
    
    private Date create_time;

    public Integer getRci_id() {
        return rci_id;
    }

    public void setRci_id(Integer rci_id) {
        this.rci_id = rci_id;
    }

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

    public String getControl_scope() {
        return control_scope;
    }

    public void setControl_scope(String control_scope) {
        this.control_scope = control_scope;
    }

    public String getControl_direction() {
        return control_direction;
    }

    public void setControl_direction(String control_direction) {
        this.control_direction = control_direction;
    }

    public String getControl_mode() {
        return control_mode;
    }

    public void setControl_mode(String control_mode) {
        this.control_mode = control_mode;
    }

    public Date getStart_time() {
        return start_time;
    }

    public void setStart_time(Date start_time) {
        this.start_time = start_time;
    }

    public Date getPlan_end_time() {
        return plan_end_time;
    }

    public void setPlan_end_time(Date plan_end_time) {
        this.plan_end_time = plan_end_time;
    }

    public Date getReal_end_time() {
        return real_end_time;
    }

    public void setReal_end_time(Date real_end_time) {
        this.real_end_time = real_end_time;
    }

    public String getRci_status() {
        return rci_status;
    }

    public void setRci_status(String rci_status) {
        this.rci_status = rci_status;
    }

    public String getRci_type() {
        return rci_type;
    }

    public void setRci_type(String rci_type) {
        this.rci_type = rci_type;
    }

    public String getRci_type_pic_path() {
        return rci_type_pic_path;
    }

    public void setRci_type_pic_path(String rci_type_pic_path) {
        this.rci_type_pic_path = rci_type_pic_path;
    }

    public Integer getStart_plaz_id() {
        return start_plaz_id;
    }

    public void setStart_plaz_id(Integer start_plaz_id) {
        this.start_plaz_id = start_plaz_id;
    }

    public String getStart_plaz_code() {
        return start_plaz_code;
    }

    public void setStart_plaz_code(String start_plaz_code) {
        this.start_plaz_code = start_plaz_code;
    }

    public Integer getEnd_plaz_id() {
        return end_plaz_id;
    }

    public void setEnd_plaz_id(Integer end_plaz_id) {
        this.end_plaz_id = end_plaz_id;
    }

    public String getEnd_plaz_code() {
        return end_plaz_code;
    }

    public void setEnd_plaz_code(String end_plaz_code) {
        this.end_plaz_code = end_plaz_code;
    }

    public String getPlaz_list() {
        return plaz_list;
    }

    public void setPlaz_list(String plaz_list) {
        this.plaz_list = plaz_list;
    }

    public String getSa_list() {
        return sa_list;
    }

    public void setSa_list(String sa_list) {
        this.sa_list = sa_list;
    }

    public String getPassing_round_pic_url() {
        return passing_round_pic_url;
    }

    public void setPassing_round_pic_url(String passing_round_pic_url) {
        this.passing_round_pic_url = passing_round_pic_url;
    }

    public String getPassing_round_desc() {
        return passing_round_desc;
    }

    public void setPassing_round_desc(String passing_round_desc) {
        this.passing_round_desc = passing_round_desc;
    }

    public String getRci_content() {
        return rci_content;
    }

    public void setRci_content(String rci_content) {
        this.rci_content = rci_content;
    }

    public Date getDeploy_time() {
        return deploy_time;
    }

    public void setDeploy_time(Date deploy_time) {
        this.deploy_time = deploy_time;
    }

    public String getLongitude_a() {
        return longitude_a;
    }

    public void setLongitude_a(String longitude_a) {
        this.longitude_a = longitude_a;
    }

    public String getLatitude_a() {
        return latitude_a;
    }

    public void setLatitude_a(String latitude_a) {
        this.latitude_a = latitude_a;
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

    public String[] getH_codeArgs() {
        return h_codeArgs;
    }

    public void setH_codeArgs(String[] h_codeArgs) {
        this.h_codeArgs = h_codeArgs;
    }

    public String[] getRoadControlIdArgs() {
        return roadControlIdArgs;
    }

    public void setRoadControlIdArgs(String[] roadControlIdArgs) {
        this.roadControlIdArgs = roadControlIdArgs;
    }
    
    

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
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
        RoadControlInfoEntity other = (RoadControlInfoEntity) that;
        return (this.getRci_id() == null ? other.getRci_id() == null : this.getRci_id().equals(other.getRci_id()))
                        && (this.getH_id() == null ? other.getH_id() == null : this.getH_id().equals(other.getH_id()))
                        && (this.getH_code() == null ? other.getH_code() == null : this.getH_code().equals(other.getH_code()))
                        && (this.getH_name() == null ? other.getH_name() == null : this.getH_name().equals(other.getH_name()))
                        && (this.getStart_stake_id() == null ? other.getStart_stake_id() == null : this.getStart_stake_id().equals(other.getStart_stake_id()))
                        && (this.getEnd_stake_id() == null ? other.getEnd_stake_id() == null : this.getEnd_stake_id().equals(other.getEnd_stake_id()))
                        && (this.getControl_scope() == null ? other.getControl_scope() == null : this.getControl_scope().equals(other.getControl_scope()))
                        && (this.getControl_direction() == null ? other.getControl_direction() == null : this.getControl_direction().equals(
                                        other.getControl_direction()))
                        && (this.getControl_mode() == null ? other.getControl_mode() == null : this.getControl_mode().equals(other.getControl_mode()))
                        && (this.getStart_time() == null ? other.getStart_time() == null : this.getStart_time().equals(other.getStart_time()))
                        && (this.getPlan_end_time() == null ? other.getPlan_end_time() == null : this.getPlan_end_time().equals(other.getPlan_end_time()))
                        && (this.getReal_end_time() == null ? other.getReal_end_time() == null : this.getReal_end_time().equals(other.getReal_end_time()))
                        && (this.getRci_status() == null ? other.getRci_status() == null : this.getRci_status().equals(other.getRci_status()))
                        && (this.getRci_type() == null ? other.getRci_type() == null : this.getRci_type().equals(other.getRci_type()))
                        && (this.getRci_type_pic_path() == null ? other.getRci_type_pic_path() == null : this.getRci_type_pic_path().equals(
                                        other.getRci_type_pic_path()))
                        && (this.getStart_plaz_id() == null ? other.getStart_plaz_id() == null : this.getStart_plaz_id().equals(other.getStart_plaz_id()))
                        && (this.getStart_plaz_code() == null ? other.getStart_plaz_code() == null : this.getStart_plaz_code().equals(
                                        other.getStart_plaz_code()))
                        && (this.getEnd_plaz_id() == null ? other.getEnd_plaz_id() == null : this.getEnd_plaz_id().equals(other.getEnd_plaz_id()))
                        && (this.getEnd_plaz_code() == null ? other.getEnd_plaz_code() == null : this.getEnd_plaz_code().equals(other.getEnd_plaz_code()))
                        && (this.getPlaz_list() == null ? other.getPlaz_list() == null : this.getPlaz_list().equals(other.getPlaz_list()))
                        && (this.getSa_list() == null ? other.getSa_list() == null : this.getSa_list().equals(other.getSa_list()))
                        && (this.getPassing_round_pic_url() == null ? other.getPassing_round_pic_url() == null : this.getPassing_round_pic_url().equals(
                                        other.getPassing_round_pic_url()))
                        && (this.getPassing_round_desc() == null ? other.getPassing_round_desc() == null : this.getPassing_round_desc().equals(
                                        other.getPassing_round_desc()))
                        && (this.getRci_content() == null ? other.getRci_content() == null : this.getRci_content().equals(other.getRci_content()))
                        && (this.getDeploy_time() == null ? other.getDeploy_time() == null : this.getDeploy_time().equals(other.getDeploy_time()))
                        && (this.getLongitude_a() == null ? other.getLongitude_a() == null : this.getLongitude_a().equals(other.getLongitude_a()))
                        && (this.getLatitude_a() == null ? other.getLatitude_a() == null : this.getLatitude_a().equals(other.getLatitude_a()))
                        && (this.getLongitude_b() == null ? other.getLongitude_b() == null : this.getLongitude_b().equals(other.getLongitude_b()))
                        && (this.getLatitude_b() == null ? other.getLatitude_b() == null : this.getLatitude_b().equals(other.getLatitude_b()))
                         && (this.getCreate_time() == null ? other.getCreate_time() == null : this.getCreate_time().equals(other.getCreate_time()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getRci_id() == null) ? 0 : getRci_id().hashCode());
        result = prime * result + ((getH_id() == null) ? 0 : getH_id().hashCode());
        result = prime * result + ((getH_code() == null) ? 0 : getH_code().hashCode());
        result = prime * result + ((getH_name() == null) ? 0 : getH_name().hashCode());
        result = prime * result + ((getStart_stake_id() == null) ? 0 : getStart_stake_id().hashCode());
        result = prime * result + ((getEnd_stake_id() == null) ? 0 : getEnd_stake_id().hashCode());
        result = prime * result + ((getControl_scope() == null) ? 0 : getControl_scope().hashCode());
        result = prime * result + ((getControl_direction() == null) ? 0 : getControl_direction().hashCode());
        result = prime * result + ((getControl_mode() == null) ? 0 : getControl_mode().hashCode());
        result = prime * result + ((getStart_time() == null) ? 0 : getStart_time().hashCode());
        result = prime * result + ((getPlan_end_time() == null) ? 0 : getPlan_end_time().hashCode());
        result = prime * result + ((getReal_end_time() == null) ? 0 : getReal_end_time().hashCode());
        result = prime * result + ((getRci_status() == null) ? 0 : getRci_status().hashCode());
        result = prime * result + ((getRci_type() == null) ? 0 : getRci_type().hashCode());
        result = prime * result + ((getRci_type_pic_path() == null) ? 0 : getRci_type_pic_path().hashCode());
        result = prime * result + ((getStart_plaz_id() == null) ? 0 : getStart_plaz_id().hashCode());
        result = prime * result + ((getStart_plaz_code() == null) ? 0 : getStart_plaz_code().hashCode());
        result = prime * result + ((getEnd_plaz_id() == null) ? 0 : getEnd_plaz_id().hashCode());
        result = prime * result + ((getEnd_plaz_code() == null) ? 0 : getEnd_plaz_code().hashCode());
        result = prime * result + ((getPlaz_list() == null) ? 0 : getPlaz_list().hashCode());
        result = prime * result + ((getSa_list() == null) ? 0 : getSa_list().hashCode());
        result = prime * result + ((getPassing_round_pic_url() == null) ? 0 : getPassing_round_pic_url().hashCode());
        result = prime * result + ((getPassing_round_desc() == null) ? 0 : getPassing_round_desc().hashCode());
        result = prime * result + ((getRci_content() == null) ? 0 : getRci_content().hashCode());
        result = prime * result + ((getDeploy_time() == null) ? 0 : getDeploy_time().hashCode());
        result = prime * result + ((getLongitude_a() == null) ? 0 : getLongitude_a().hashCode());
        result = prime * result + ((getLatitude_a() == null) ? 0 : getLatitude_a().hashCode());
        result = prime * result + ((getLongitude_b() == null) ? 0 : getLongitude_b().hashCode());
        result = prime * result + ((getLatitude_b() == null) ? 0 : getLatitude_b().hashCode());
        result = prime * result + ((getCreate_time()== null) ? 0 : getCreate_time().hashCode());
        return result;
    }
}