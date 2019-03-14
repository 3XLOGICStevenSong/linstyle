package com.djb.highway.user.dto;

import java.util.Date;
import java.util.List;

import com.djb.highway.framework.dto.PageDTO;

public class OpinionDTO extends PageDTO {

    /**
     * 
     */
    private static final long serialVersionUID = 6458285003292444595L;

    private Integer opinion_id;

    private Integer user_id;

    private String user_code;

    private String content;

    private Date insert_time;
    private List<OpinionDTO> list;

    public Integer getOpinion_id() {
        return opinion_id;
    }

    public void setOpinion_id(Integer opinion_id) {
        this.opinion_id = opinion_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getUser_code() {
        return user_code;
    }

    public void setUser_code(String user_code) {
        this.user_code = user_code;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getInsert_time() {
        return insert_time;
    }

    public void setInsert_time(Date insert_time) {
        this.insert_time = insert_time;
    }

    public List<OpinionDTO> getList() {
        return list;
    }

    public void setList(List<OpinionDTO> list) {
        this.list = list;
    }

}