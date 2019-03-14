package com.djb.highway.carpool.entity;

import java.util.Date;

import com.djb.highway.framework.entity.PageModel;

public class CarpoolUserEntity extends PageModel {
    /**
	 * 
	 */
    private static final long serialVersionUID = -5712456041813582168L;

    private Integer cu_id;

    private String cu_name;

    private String cu_nick;

    private String id_number;

    private String driving_license_pic;

    private String car_license_pic;

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

    private Date insert_time;

    private Date update_time;

    // private String create_time;
    private String cu_code;

    private String cu_password;

    private String cu_question;

    private String cu_answer;



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

    public Date getInsert_time() {
        return insert_time;
    }

    public void setInsert_time(Date insert_time) {
        this.insert_time = insert_time;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
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
        CarpoolUserEntity other = (CarpoolUserEntity) that;
        return (this.getCu_id() == null ? other.getCu_id() == null : this.getCu_id().equals(other.getCu_id()))
                        && (this.getCu_name() == null ? other.getCu_name() == null : this.getCu_name().equals(other.getCu_name()))
                        && (this.getCu_nick() == null ? other.getCu_nick() == null : this.getCu_nick().equals(other.getCu_nick()))
                        && (this.getId_number() == null ? other.getId_number() == null : this.getId_number().equals(other.getId_number()))
                        && (this.getDriving_license_pic() == null ? other.getDriving_license_pic() == null : this.getDriving_license_pic().equals(
                                        other.getDriving_license_pic()))
                        && (this.getCar_license_pic() == null ? other.getCar_license_pic() == null : this.getCar_license_pic().equals(
                                        other.getCar_license_pic()))
                        && (this.getPeople_license() == null ? other.getPeople_license() == null : this.getPeople_license().equals(other.getPeople_license()))
                        && (this.getCu_tel() == null ? other.getCu_tel() == null : this.getCu_tel().equals(other.getCu_tel()))
                        && (this.getCar_brand() == null ? other.getCar_brand() == null : this.getCar_brand().equals(other.getCar_brand()))
                        && (this.getCar_type() == null ? other.getCar_type() == null : this.getCar_type().equals(other.getCar_type()))
                        && (this.getCar_color() == null ? other.getCar_color() == null : this.getCar_color().equals(other.getCar_color()))
                        && (this.getCar_num() == null ? other.getCar_num() == null : this.getCar_num().equals(other.getCar_num()))
                        && (this.getLongitude() == null ? other.getLongitude() == null : this.getLongitude().equals(other.getLongitude()))
                        && (this.getLatitude() == null ? other.getLatitude() == null : this.getLatitude().equals(other.getLatitude()))
                        && (this.getLevel() == null ? other.getLevel() == null : this.getLevel().equals(other.getLevel()))
                        && (this.getCar_seat_num() == null ? other.getCar_seat_num() == null : this.getCar_seat_num().equals(other.getCar_seat_num()))
                        && (this.getD_success_count() == null ? other.getD_success_count() == null : this.getD_success_count().equals(
                                        other.getD_success_count()))
                        && (this.getD_fail_count() == null ? other.getD_fail_count() == null : this.getD_fail_count().equals(other.getD_fail_count()))
                        && (this.getP_success_count() == null ? other.getP_success_count() == null : this.getP_success_count().equals(
                                        other.getP_success_count()))
                        && (this.getP_fail_count() == null ? other.getP_fail_count() == null : this.getP_fail_count().equals(other.getP_fail_count()))
                        && (this.getStatus_flg() == null ? other.getStatus_flg() == null : this.getStatus_flg().equals(other.getStatus_flg()))
                        && (this.getMemo() == null ? other.getMemo() == null : this.getMemo().equals(other.getMemo()))
                        && (this.getInsert_time() == null ? other.getInsert_time() == null : this.getInsert_time().equals(other.getInsert_time()))
                        && (this.getUpdate_time() == null ? other.getUpdate_time() == null : this.getUpdate_time().equals(other.getUpdate_time()))
                         && (this.getCu_code() == null ? other.getCu_code() == null : this.getCu_code().equals(other.getCu_code()))
                        && (this.getCu_password() == null ? other.getCu_password() == null : this.getCu_password().equals(other.getCu_password()))
                        && (this.getCu_question() == null ? other.getCu_question() == null : this.getCu_question().equals(other.getCu_question()))
                        && (this.getCu_answer() == null ? other.getCu_answer() == null : this.getCu_answer().equals(other.getCu_answer()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCu_id() == null) ? 0 : getCu_id().hashCode());
        result = prime * result + ((getCu_name() == null) ? 0 : getCu_name().hashCode());
        result = prime * result + ((getCu_nick() == null) ? 0 : getCu_nick().hashCode());
        result = prime * result + ((getId_number() == null) ? 0 : getId_number().hashCode());
        result = prime * result + ((getDriving_license_pic() == null) ? 0 : getDriving_license_pic().hashCode());
        result = prime * result + ((getCar_license_pic() == null) ? 0 : getCar_license_pic().hashCode());
        result = prime * result + ((getPeople_license() == null) ? 0 : getPeople_license().hashCode());
        result = prime * result + ((getCu_tel() == null) ? 0 : getCu_tel().hashCode());
        result = prime * result + ((getCar_brand() == null) ? 0 : getCar_brand().hashCode());
        result = prime * result + ((getCar_type() == null) ? 0 : getCar_type().hashCode());
        result = prime * result + ((getCar_color() == null) ? 0 : getCar_color().hashCode());
        result = prime * result + ((getCar_num() == null) ? 0 : getCar_num().hashCode());
        result = prime * result + ((getLongitude() == null) ? 0 : getLongitude().hashCode());
        result = prime * result + ((getLatitude() == null) ? 0 : getLatitude().hashCode());
        result = prime * result + ((getLevel() == null) ? 0 : getLevel().hashCode());
        result = prime * result + ((getCar_seat_num() == null) ? 0 : getCar_seat_num().hashCode());
        result = prime * result + ((getD_success_count() == null) ? 0 : getD_success_count().hashCode());
        result = prime * result + ((getD_fail_count() == null) ? 0 : getD_fail_count().hashCode());
        result = prime * result + ((getP_success_count() == null) ? 0 : getP_success_count().hashCode());
        result = prime * result + ((getP_fail_count() == null) ? 0 : getP_fail_count().hashCode());
        result = prime * result + ((getStatus_flg() == null) ? 0 : getStatus_flg().hashCode());
        result = prime * result + ((getMemo() == null) ? 0 : getMemo().hashCode());
        result = prime * result + ((getInsert_time() == null) ? 0 : getInsert_time().hashCode());
        result = prime * result + ((getUpdate_time() == null) ? 0 : getUpdate_time().hashCode());
        result = prime * result + ((getCu_code() == null) ? 0 : getCu_code().hashCode());
        result = prime * result + ((getCu_password() == null) ? 0 : getCu_password().hashCode());
        result = prime * result + ((getCu_question() == null) ? 0 : getCu_question().hashCode());
        result = prime * result + ((getCu_answer() == null) ? 0 : getCu_answer().hashCode());
        return result;
    }
}