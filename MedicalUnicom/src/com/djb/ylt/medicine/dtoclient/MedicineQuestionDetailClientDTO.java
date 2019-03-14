package com.djb.ylt.medicine.dtoclient;

import java.util.Date;
import java.util.List;

import com.djb.ylt.framework.dto.BaseClientDTO;
import com.djb.ylt.medicine.entity.MedicineAnswerEntity;


public class MedicineQuestionDetailClientDTO extends BaseClientDTO {

    private Integer question_id;

    private String ask_user_name;

    private String ask_user_top_pic;

    private String question_content;

    private Integer medicine_id;

    private Integer ask_user_id;

    private Date create_time;

    private Date last_modify_time;

    private Integer create_user;

    private Integer last_modify_user;

    private Integer version;

    private List<MedicineAnswerEntity> medicineAnswerEntitys;

    private String ask_question_time;

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

    public Integer getAsk_user_id() {
        return ask_user_id;
    }

    public void setAsk_user_id(Integer ask_user_id) {
        this.ask_user_id = ask_user_id;
    }

    public String getAsk_question_time() {
        return ask_question_time;
    }

    public void setAsk_question_time(String ask_question_time) {
        this.ask_question_time = ask_question_time;
    }

}