package com.djb.highway.user.entity;

import java.util.Date;

import com.djb.highway.framework.entity.PageModel;

public class OpinionEntity extends PageModel {

    /**
     * 
     */
    private static final long serialVersionUID = 5626000968964290681L;

    private Integer opinion_id;

    private Integer user_id;

    private String user_code;

    private String content;

    private Date insert_time;

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
        OpinionEntity other = (OpinionEntity) that;
        return (this.getOpinion_id() == null ? other.getOpinion_id() == null
                : this.getOpinion_id().equals(other.getOpinion_id()))
                && (this.getUser_id() == null ? other.getUser_id() == null
                        : this.getUser_id().equals(other.getUser_id()))
                && (this.getUser_code() == null ? other.getUser_code() == null
                        : this.getUser_code().equals(other.getUser_code()))
                && (this.getContent() == null ? other.getContent() == null
                        : this.getContent().equals(other.getContent()))
                && (this.getInsert_time() == null ? other.getInsert_time() == null
                        : this.getInsert_time().equals(other.getInsert_time()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((getOpinion_id() == null) ? 0 : getOpinion_id().hashCode());
        result = prime * result
                + ((getUser_id() == null) ? 0 : getUser_id().hashCode());
        result = prime * result
                + ((getUser_code() == null) ? 0 : getUser_code().hashCode());
        result = prime * result
                + ((getContent() == null) ? 0 : getContent().hashCode());
        result = prime
                * result
                + ((getInsert_time() == null) ? 0 : getInsert_time().hashCode());
        return result;
    }
}