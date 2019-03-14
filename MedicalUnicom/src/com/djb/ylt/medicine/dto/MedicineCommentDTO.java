package com.djb.ylt.medicine.dto;

import java.util.Date;

import com.djb.ylt.framework.dto.PageDTO;
import com.djb.ylt.medicine.entity.MedicineInfoEntity;

public class MedicineCommentDTO extends PageDTO {
    /**
     * 
     */
    private static final long serialVersionUID = -6985523223584612781L;

    private Integer comment_id;

    private String comment_content;

    private Float comment_grade;

    private Date comment_time;

    private String u_name;

    private String u_top_pic;

    private Integer medicine_id;

    private Integer userId;

    private Date create_time;

    private Date last_modify_time;

    private Integer create_user;

    private Integer last_modify_user;

    private Integer version;

    private MedicineInfoEntity medicineInfoEntity;

    public Integer getComment_id() {
        return comment_id;
    }

    public void setComment_id(Integer comment_id) {
        this.comment_id = comment_id;
    }

    public String getComment_content() {
        return comment_content;
    }

    public void setComment_content(String comment_content) {
        this.comment_content = comment_content;
    }

    public Float getComment_grade() {
        return comment_grade;
    }

    public void setComment_grade(Float comment_grade) {
        this.comment_grade = comment_grade;
    }

    public Date getComment_time() {
        return comment_time;
    }

    public void setComment_time(Date comment_time) {
        this.comment_time = comment_time;
    }

    public String getU_name() {
        return u_name;
    }

    public void setU_name(String u_name) {
        this.u_name = u_name;
    }

    public String getU_top_pic() {
        return u_top_pic;
    }

    public void setU_top_pic(String u_top_pic) {
        this.u_top_pic = u_top_pic;
    }

    public Integer getMedicine_id() {
        return medicine_id;
    }

    public void setMedicine_id(Integer medicine_id) {
        this.medicine_id = medicine_id;
    }

   

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getLast_modify_time() {
        return last_modify_time;
    }

    public void setLast_modify_time(Date last_modify_time) {
        this.last_modify_time = last_modify_time;
    }

    public Integer getCreate_user() {
        return create_user;
    }

    public void setCreate_user(Integer create_user) {
        this.create_user = create_user;
    }

    public Integer getLast_modify_user() {
        return last_modify_user;
    }

    public void setLast_modify_user(Integer last_modify_user) {
        this.last_modify_user = last_modify_user;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public MedicineInfoEntity getMedicineInfoEntity() {
        return medicineInfoEntity;
    }

    public void setMedicineInfoEntity(MedicineInfoEntity medicineInfoEntity) {
        this.medicineInfoEntity = medicineInfoEntity;
    }

	/**
	 * 返回userId的值
	 * @return Integer userId的值
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * 设置userId的值
	 * @param  userId userId的值
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

}