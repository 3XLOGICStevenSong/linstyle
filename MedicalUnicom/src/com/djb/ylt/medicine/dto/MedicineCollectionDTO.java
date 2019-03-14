package com.djb.ylt.medicine.dto;

import java.util.Date;

import com.djb.ylt.framework.dto.PageDTO;


public class MedicineCollectionDTO extends PageDTO {

    /**
     * 
     */
    private static final long serialVersionUID = 6002397765596011393L;

    private Integer collection_id;

    private String medicine_name;

    private Float medicine_price;

    private String u_name;

    private String u_top_pic;

    private Integer medicine_id;

    private Integer userId;

    private Date create_time;

    private Date last_modify_time;

    private Integer create_user;

    private Integer last_modify_user;

    private Integer version;

    private MedicineInfoDTO medicineInfoDTO;

    public Integer getCollection_id() {
        return collection_id;
    }

    public void setCollection_id(Integer collection_id) {
        this.collection_id = collection_id;
    }

    public String getMedicine_name() {
        return medicine_name;
    }

    public void setMedicine_name(String medicine_name) {
        this.medicine_name = medicine_name;
    }

    public Float getMedicine_price() {
        return medicine_price;
    }

    public void setMedicine_price(Float medicine_price) {
        this.medicine_price = medicine_price;
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

    public MedicineInfoDTO getMedicineInfoDTO() {
        return medicineInfoDTO;
    }

    public void setMedicineInfoDTO(MedicineInfoDTO medicineInfoDTO) {
        this.medicineInfoDTO = medicineInfoDTO;
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