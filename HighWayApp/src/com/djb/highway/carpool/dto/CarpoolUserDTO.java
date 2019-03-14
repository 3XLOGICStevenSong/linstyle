package com.djb.highway.carpool.dto;

import com.djb.highway.framework.dto.BaseDTO;
import com.djb.highway.framework.dto.PageDTO;
import java.util.Date;

import org.apache.struts.upload.FormFile;

public class CarpoolUserDTO extends BaseDTO {
    /**
	 * 
	 */
    private static final long serialVersionUID = 6279238288816293916L;

    private Integer cu_id;

    private String cu_name;

    private String cu_nick;

    private String id_number;

    private String driving_license_pic;

    private String car_license_pic;

    private FormFile driving_pic;

    private FormFile car_pic;

    private FormFile people_license_pic;

    private String people_license;

    private String cu_tel;

    private String car_brand;

    private String car_type;

    private String car_color;

    private String car_num;

    private String longitude;

    private String latitude;

    private String level;

    private Integer car_seat_num;

    private Integer d_success_count;

    private Integer d_fail_count;

    private Integer p_success_count;

    private Integer p_fail_count;

    private String status_flg;

    private String memo;

    private String insert_time;

    private Date update_time;

    private String dp_flag;// 0:司机 1:乘客

    private Integer dr_id;

    private String charter_flg;

    private Float price;

	// h5Client注册时间
    private String create_time;

    private String cu_code;

    private String cu_password;

    private String cu_question;

    private String cu_answer;

    private String verificationCode;
    
    private String confirm_password;
    
    private String old_password;
    
    private String agree;
    
    private String screenId;
    
    public String getScreenId() {
		return screenId;
	}

	public void setScreenId(String screenId) {
		this.screenId = screenId;
	}

    public Integer getCu_id() {
        return cu_id;
    }

    public void setCu_id(Integer cu_id) {
        this.cu_id = cu_id;
    }

    public String getCu_name() {
        return cu_name;
    }

    public void setCu_name(String cu_name) {
        this.cu_name = cu_name;
    }

    public String getCu_nick() {
        return cu_nick;
    }

    public void setCu_nick(String cu_nick) {
        this.cu_nick = cu_nick;
    }

    public String getId_number() {
        return id_number;
    }

    public void setId_number(String id_number) {
        this.id_number = id_number;
    }

    public String getDriving_license_pic() {
        return driving_license_pic;
    }

    public void setDriving_license_pic(String driving_license_pic) {
        this.driving_license_pic = driving_license_pic;
    }

    public String getCar_license_pic() {
        return car_license_pic;
    }

    public void setCar_license_pic(String car_license_pic) {
        this.car_license_pic = car_license_pic;
    }

    public FormFile getCar_pic() {
        return car_pic;
    }

    public void setCar_pic(FormFile car_pic) {
        this.car_pic = car_pic;
    }

    public FormFile getDriving_pic() {
        return driving_pic;
    }

    public void setDriving_pic(FormFile driving_pic) {
        this.driving_pic = driving_pic;
    }

    public FormFile getPeople_license_pic() {
        return people_license_pic;
    }

    public void setPeople_license_pic(FormFile people_license_pic) {
        this.people_license_pic = people_license_pic;
    }

    public String getPeople_license() {
        return people_license;
    }

    public void setPeople_license(String people_license) {
        this.people_license = people_license;
    }

    public String getCu_tel() {
        return cu_tel;
    }

    public void setCu_tel(String cu_tel) {
        this.cu_tel = cu_tel;
    }

    public String getCar_brand() {
        return car_brand;
    }

    public void setCar_brand(String car_brand) {
        this.car_brand = car_brand;
    }

    public String getCar_type() {
        return car_type;
    }

    public void setCar_type(String car_type) {
        this.car_type = car_type;
    }

    public String getCar_color() {
        return car_color;
    }

    public void setCar_color(String car_color) {
        this.car_color = car_color;
    }

    public String getCar_num() {
        return car_num;
    }

    public void setCar_num(String car_num) {
        this.car_num = car_num;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Integer getCar_seat_num() {
        return car_seat_num;
    }

    public void setCar_seat_num(Integer car_seat_num) {
        this.car_seat_num = car_seat_num;
    }

    public Integer getD_success_count() {
        return d_success_count;
    }

    public void setD_success_count(Integer d_success_count) {
        this.d_success_count = d_success_count;
    }

    public Integer getD_fail_count() {
        return d_fail_count;
    }

    public void setD_fail_count(Integer d_fail_count) {
        this.d_fail_count = d_fail_count;
    }

    public Integer getP_success_count() {
        return p_success_count;
    }

    public void setP_success_count(Integer p_success_count) {
        this.p_success_count = p_success_count;
    }

    public Integer getP_fail_count() {
        return p_fail_count;
    }

    public void setP_fail_count(Integer p_fail_count) {
        this.p_fail_count = p_fail_count;
    }

    public String getStatus_flg() {
        return status_flg;
    }

    public void setStatus_flg(String status_flg) {
        this.status_flg = status_flg;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getInsert_time() {
        return insert_time;
    }

    public void setInsert_time(String insert_time) {
        this.insert_time = insert_time;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    public String getDp_flag() {
        return dp_flag;
    }

    public void setDp_flag(String dp_flag) {
        this.dp_flag = dp_flag;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public Integer getDr_id() {
        return dr_id;
    }

    public void setDr_id(Integer dr_id) {
        this.dr_id = dr_id;
    }

    public String getCharter_flg() {
        return charter_flg;
    }

    public void setCharter_flg(String charter_flg) {
        this.charter_flg = charter_flg;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getCu_code() {
        return cu_code;
    }

    public void setCu_code(String cu_code) {
        this.cu_code = cu_code;
    }

    public String getCu_password() {
        return cu_password;
    }

    public void setCu_password(String cu_password) {
        this.cu_password = cu_password;
    }

    public String getCu_question() {
        return cu_question;
    }

    public void setCu_question(String cu_question) {
        this.cu_question = cu_question;
    }

    public String getCu_answer() {
        return cu_answer;
    }

    public void setCu_answer(String cu_answer) {
        this.cu_answer = cu_answer;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public String getConfirm_password() {
        return confirm_password;
    }

    public void setConfirm_password(String confirm_password) {
        this.confirm_password = confirm_password;
    }

    public String getAgree() {
        return agree;
    }

    public void setAgree(String agree) {
        this.agree = agree;
    }

    public String getOld_password() {
        return old_password;
    }

    public void setOld_password(String old_password) {
        this.old_password = old_password;
    }
   
}