package com.djb.highway.road.dto;

import java.util.Date;
import java.util.List;

import com.djb.highway.framework.dto.PageDTO;

public class RoadControlInfoDTO extends PageDTO {
    /**
	 * 
	 */
    private static final long serialVersionUID = -3216245094114910095L;

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

    private Integer section_id;

    private List<String> secIds;

    private List<RoadControlInfoDTO> list;

    private String roadControlIdArgs;
    
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

    public List<String> getSecIds() {
        return secIds;
    }

    public void setSecIds(List<String> secIds) {
        this.secIds = secIds;
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

    public List<RoadControlInfoDTO> getList() {
        return list;
    }

    public void setList(List<RoadControlInfoDTO> list) {
        this.list = list;
    }

    public Integer getSection_id() {
        return section_id;
    }

    public void setSection_id(Integer section_id) {
        this.section_id = section_id;
    }

    public String getRoadControlIdArgs() {
        return roadControlIdArgs;
    }

    public void setRoadControlIdArgs(String roadControlIdArgs) {
        this.roadControlIdArgs = roadControlIdArgs;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }
  
    
}