package com.djb.ylt.medicine.entity;

import java.util.Date;
import java.util.List;

import com.djb.ylt.framework.entity.PageModel;


public class MedicineQuestionEntity extends PageModel {

    /**
     * 
     */
    private static final long serialVersionUID = -795278970102611509L;

    private Integer question_id;

    private String ask_user_name;

    private String ask_user_top_pic;

    private String question_content;

    private Integer medicine_id;

    private Integer userId;

    private Date create_time;

    private Date last_modify_time;

    private Integer create_user;

    private Integer last_modify_user;

    private Integer version;

    private List<MedicineAnswerEntity> medicineAnswerEntitys;

    private MedicineInfoEntity medicineInfoEntity;

    public Integer getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(Integer question_id) {
        this.question_id = question_id;
    }

    public String getQuestion_content() {
        return question_content;
    }

    public void setQuestion_content(String question_content) {
        this.question_content = question_content;
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

    public List<MedicineAnswerEntity> getMedicineAnswerEntitys() {
        return medicineAnswerEntitys;
    }

    public void setMedicineAnswerEntitys(List<MedicineAnswerEntity> medicineAnswerEntitys) {
        this.medicineAnswerEntitys = medicineAnswerEntitys;
    }

    public String getAsk_user_name() {
        return ask_user_name;
    }

    public void setAsk_user_name(String ask_user_name) {
        this.ask_user_name = ask_user_name;
    }

    public String getAsk_user_top_pic() {
        return ask_user_top_pic;
    }

    public void setAsk_user_top_pic(String ask_user_top_pic) {
        this.ask_user_top_pic = ask_user_top_pic;
    }


    public MedicineInfoEntity getMedicineInfoEntity() {
        return medicineInfoEntity;
    }

    public void setMedicineInfoEntity(MedicineInfoEntity medicineInfoEntity) {
        this.medicineInfoEntity = medicineInfoEntity;
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
        MedicineQuestionEntity other = (MedicineQuestionEntity) that;
        return (this.getQuestion_id() == null ? other.getQuestion_id() == null : this.getQuestion_id().equals(other.getQuestion_id()))
                        && (this.getAsk_user_name() == null ? other.getAsk_user_name() == null : this.getAsk_user_name().equals(other.getAsk_user_name()))
                        && (this.getAsk_user_top_pic() == null ? other.getAsk_user_top_pic() == null : this.getAsk_user_top_pic().equals(
                                        other.getAsk_user_top_pic()))
                        && (this.getQuestion_content() == null ? other.getQuestion_content() == null : this.getQuestion_content().equals(
                                        other.getQuestion_content()))
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
        result = prime * result + ((getQuestion_id() == null) ? 0 : getQuestion_id().hashCode());
        result = prime * result + ((getAsk_user_name() == null) ? 0 : getAsk_user_name().hashCode());
        result = prime * result + ((getAsk_user_top_pic() == null) ? 0 : getAsk_user_top_pic().hashCode());
        result = prime * result + ((getQuestion_content() == null) ? 0 : getQuestion_content().hashCode());
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