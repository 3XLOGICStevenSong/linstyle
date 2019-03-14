package com.djb.highway.road.dto.push;

import java.util.Date;

public class RoadControlInfoData {

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

    private String rci_content;

    private Date deploy_time;

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

}