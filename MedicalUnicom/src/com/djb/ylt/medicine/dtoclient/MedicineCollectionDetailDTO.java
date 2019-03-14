package com.djb.ylt.medicine.dtoclient;


public class MedicineCollectionDetailDTO {

    private Integer collection_id;

    private Integer medicine_id;

    private Integer userId;

    private String medicine_name;

    private Float medicine_price;
    private String medicine_factory;
    private Float medicine_grade;

    private String medicine_pic;

    private String medicine_effect;

    public Integer getCollection_id() {
        return collection_id;
    }

    public void setCollection_id(Integer collection_id) {
        this.collection_id = collection_id;
    }

    public Integer getMedicine_id() {
        return medicine_id;
    }

    public void setMedicine_id(Integer medicine_id) {
        this.medicine_id = medicine_id;
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

    public String getMedicine_factory() {
        return medicine_factory;
    }

    public void setMedicine_factory(String medicine_factory) {
        this.medicine_factory = medicine_factory;
    }

    public Float getMedicine_grade() {
        return medicine_grade;
    }

    public void setMedicine_grade(Float medicine_grade) {
        this.medicine_grade = medicine_grade;
    }

    public String getMedicine_pic() {
        return medicine_pic;
    }

    public void setMedicine_pic(String medicine_pic) {
        this.medicine_pic = medicine_pic;
    }

    public String getMedicine_effect() {
        return medicine_effect;
    }

    public void setMedicine_effect(String medicine_effect) {
        this.medicine_effect = medicine_effect;
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