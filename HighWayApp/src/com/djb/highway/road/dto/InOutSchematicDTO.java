package com.djb.highway.road.dto;

import java.util.List;

import com.djb.highway.framework.dto.PageDTO;

public class InOutSchematicDTO extends PageDTO {

    /**
	 * 
	 */
    private static final long serialVersionUID = -2649667952492299655L;

    private Integer s_id;
    private Integer plaza_id;
    private String direction_content;
    private String pic_path;
    private String plaz_code;

    private List<InOutSchematicDTO> list;

    public List<InOutSchematicDTO> getList() {
        return list;
    }

    public void setList(List<InOutSchematicDTO> list) {
        this.list = list;
    }

    public String getPlaz_code() {
        return plaz_code;
    }

    public void setPlaz_code(String plaz_code) {
        this.plaz_code = plaz_code;
    }

    public Integer getS_id() {
        return s_id;
    }

    public void setS_id(Integer s_id) {
        this.s_id = s_id;
    }

    public Integer getPlaza_id() {
        return plaza_id;
    }

    public void setPlaza_id(Integer plaza_id) {
        this.plaza_id = plaza_id;
    }

    public String getDirection_content() {
        return direction_content;
    }

    public void setDirection_content(String direction_content) {
        this.direction_content = direction_content;
    }

    public String getPic_path() {
        return pic_path;
    }

    public void setPic_path(String pic_path) {
        this.pic_path = pic_path;
    }

}