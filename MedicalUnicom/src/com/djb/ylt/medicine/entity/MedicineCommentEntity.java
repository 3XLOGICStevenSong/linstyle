package com.djb.ylt.medicine.entity;

import java.util.Date;

import com.djb.ylt.framework.entity.PageModel;
import com.djb.ylt.user.entity.UserLoginEntity;



public class MedicineCommentEntity extends PageModel {
    /**
     * 
     */
    private static final long serialVersionUID = 2561237062779707988L;

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

    private UserLoginEntity userLoginEntity;

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

    public UserLoginEntity getUserLoginEntity() {
        return userLoginEntity;
    }

    public void setUserLoginEntity(UserLoginEntity userLoginEntity) {
        this.userLoginEntity = userLoginEntity;
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
        MedicineCommentEntity other = (MedicineCommentEntity) that;
        return (this.getComment_id() == null ? other.getComment_id() == null : this.getComment_id().equals(other.getComment_id()))
                        && (this.getComment_content() == null ? other.getComment_content() == null : this.getComment_content().equals(
                                        other.getComment_content()))
                        && (this.getComment_grade() == null ? other.getComment_grade() == null : this.getComment_grade().equals(other.getComment_grade()))
                        && (this.getComment_time() == null ? other.getComment_time() == null : this.getComment_time().equals(other.getComment_time()))
                        && (this.getU_name() == null ? other.getU_name() == null : this.getU_name().equals(other.getU_name()))
                        && (this.getU_top_pic() == null ? other.getU_top_pic() == null : this.getU_top_pic().equals(other.getU_top_pic()))
                        && (this.getMedicine_id() == null ? other.getMedicine_id() == null : this.getMedicine_id().equals(other.getMedicine_id()))
                        && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
                        && (this.getCreate_time() == null ? other.getCreate_time() == null : this.getCreate_time().equals(other.getCreate_time()))
                        && (this.getLast_modify_time() == null ? other.getLast_modify_time() == null : this.getLast_modify_time().equals(
                                        other.getLast_modify_time()))
                        && (this.getCreate_user() == null ? other.getCreate_user() == null : this.getCreate_user().equals(other.getCreate_user()))
                        && (this.getLast_modify_user() == null ? other.getLast_modify_user() == null : this.getLast_modify_user().equals(
                                        other.getLast_modify_user()))
                        && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getComment_id() == null) ? 0 : getComment_id().hashCode());
        result = prime * result + ((getComment_content() == null) ? 0 : getComment_content().hashCode());
        result = prime * result + ((getComment_grade() == null) ? 0 : getComment_grade().hashCode());
        result = prime * result + ((getComment_time() == null) ? 0 : getComment_time().hashCode());
        result = prime * result + ((getU_name() == null) ? 0 : getU_name().hashCode());
        result = prime * result + ((getU_top_pic() == null) ? 0 : getU_top_pic().hashCode());
        result = prime * result + ((getMedicine_id() == null) ? 0 : getMedicine_id().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getCreate_time() == null) ? 0 : getCreate_time().hashCode());
        result = prime * result + ((getLast_modify_time() == null) ? 0 : getLast_modify_time().hashCode());
        result = prime * result + ((getCreate_user() == null) ? 0 : getCreate_user().hashCode());
        result = prime * result + ((getLast_modify_user() == null) ? 0 : getLast_modify_user().hashCode());
        result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
        return result;
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