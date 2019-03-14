package com.djb.ylt.medicine.dtoclient;


public class MedicineCommentDetailDTO {

    private Integer comment_id;

    private Integer medicine_id;

    private Integer user_id;

    private String comment_content;

    private Float comment_grade;

    private String comment_time;

    private String medicine_pic;

    private String medicine_name;

    private String u_name;

    private String user_top_pic;

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

    public String getComment_time() {
        return comment_time;
    }

    public void setComment_time(String comment_time) {
        this.comment_time = comment_time;
    }

    public Integer getMedicine_id() {
        return medicine_id;
    }

    public void setMedicine_id(Integer medicine_id) {
        this.medicine_id = medicine_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getMedicine_name() {
        return medicine_name;
    }

    public void setMedicine_name(String medicine_name) {
        this.medicine_name = medicine_name;
    }

    public String getMedicine_pic() {
        return medicine_pic;
    }

    public void setMedicine_pic(String medicine_pic) {
        this.medicine_pic = medicine_pic;
    }

    public String getU_name() {
        return u_name;
    }

    public void setU_name(String u_name) {
        this.u_name = u_name;
    }

    public String getUser_top_pic() {
        return user_top_pic;
    }

    public void setUser_top_pic(String user_top_pic) {
        this.user_top_pic = user_top_pic;
    }

}