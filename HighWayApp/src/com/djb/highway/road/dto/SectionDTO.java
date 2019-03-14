package com.djb.highway.road.dto;

import java.util.Date;
import java.util.List;

import com.djb.highway.framework.dto.PageDTO;

public class SectionDTO extends PageDTO {

    /**
	 * 
	 */
    private static final long serialVersionUID = -6041614202185221883L;

    private Integer section_id;

    private Integer h_id;

    private String h_code;

    private String h_name;

    private String section_name;

    private String section_type;

    private String section_status;

    private Date create_time;

    private Date update_time;

    private String direction;

    private Float speed;

    private Float volumn;

    private String level;

    private List<SectionDTO> list;

    // 初始化收藏标记的参数
    private Integer u_id;
    private String plaz_code_start;
    private String plaz_code_end;
    private String road_type;

    public Integer getSection_id() {
        return section_id;
    }

    public void setSection_id(Integer section_id) {
        this.section_id = section_id;
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

    public String getSection_name() {
        return section_name;
    }

    public void setSection_name(String section_name) {
        this.section_name = section_name;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    public String getSection_type() {
        return section_type;
    }

    public void setSection_type(String section_type) {
        this.section_type = section_type;
    }

    public String getSection_status() {
        return section_status;
    }

    public void setSection_status(String section_status) {
        this.section_status = section_status;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public Float getSpeed() {
        return speed;
    }

    public void setSpeed(Float speed) {
        this.speed = speed;
    }

    public Float getVolumn() {
        return volumn;
    }

    public void setVolumn(Float volumn) {
        this.volumn = volumn;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public List<SectionDTO> getList() {
        return list;
    }

    public void setList(List<SectionDTO> list) {
        this.list = list;
    }

    public Integer getU_id() {
        return u_id;
    }

    public void setU_id(Integer u_id) {
        this.u_id = u_id;
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

    public String getRoad_type() {
        return road_type;
    }

    public void setRoad_type(String road_type) {
        this.road_type = road_type;
    }

}