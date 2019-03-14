package com.djb.highway.road.entity;

import java.util.Date;
import java.util.List;


public class OverpassEntity extends BaseTravelPlanEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8841800775615297873L;

	private Integer o_id;

	private String o_code;

	private String o_name;

	private String o_city;

	private String o_desc;

	private String o_length;

	private String o_type;

	private String o_cross_type;

	private String o_status;

	private String o_pic_1;

	private String o_pic_2;

	private String o_pic_3;

	private Date create_time;

	private Date update_time;

	private String o_corss_line;

	private String entry_plaz_code;

	private String exit_plaz_code;

	private List<OverpassDetailEntity> overpassDetailEntitys;

	public Integer getO_id() {
		return o_id;
	}

	public void setO_id(Integer o_id) {
		this.o_id = o_id;
	}

	public String getO_code() {
		return o_code;
	}

	public void setO_code(String o_code) {
		this.o_code = o_code;
	}

	public String getO_name() {
		return o_name;
	}

	public void setO_name(String o_name) {
		this.o_name = o_name;
	}

	public String getO_city() {
		return o_city;
	}

	public void setO_city(String o_city) {
		this.o_city = o_city;
	}

	public String getO_desc() {
		return o_desc;
	}

	public void setO_desc(String o_desc) {
		this.o_desc = o_desc;
	}

	public String getO_length() {
		return o_length;
	}

	public void setO_length(String o_length) {
		this.o_length = o_length;
	}

	public String getO_type() {
		return o_type;
	}

	public void setO_type(String o_type) {
		this.o_type = o_type;
	}

	public String getO_cross_type() {
		return o_cross_type;
	}

	public void setO_cross_type(String o_cross_type) {
		this.o_cross_type = o_cross_type;
	}

	public String getO_status() {
		return o_status;
	}

	public void setO_status(String o_status) {
		this.o_status = o_status;
	}

	public String getO_pic_1() {
		return o_pic_1;
	}

	public void setO_pic_1(String o_pic_1) {
		this.o_pic_1 = o_pic_1;
	}

	public String getO_pic_2() {
		return o_pic_2;
	}

	public void setO_pic_2(String o_pic_2) {
		this.o_pic_2 = o_pic_2;
	}

	public String getO_pic_3() {
		return o_pic_3;
	}

	public void setO_pic_3(String o_pic_3) {
		this.o_pic_3 = o_pic_3;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public Date getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}

	public String getO_corss_line() {
		return o_corss_line;
	}

	public void setO_corss_line(String o_corss_line) {
		this.o_corss_line = o_corss_line;
	}

	public String getEntry_plaz_code() {
		return entry_plaz_code;
	}

	public void setEntry_plaz_code(String entry_plaz_code) {
		this.entry_plaz_code = entry_plaz_code;
	}

	public String getExit_plaz_code() {
		return exit_plaz_code;
	}

	public void setExit_plaz_code(String exit_plaz_code) {
		this.exit_plaz_code = exit_plaz_code;
	}

	public List<OverpassDetailEntity> getOverpassDetailEntitys() {
		return overpassDetailEntitys;
	}

	public void setOverpassDetailEntitys(
			List<OverpassDetailEntity> overpassDetailEntitys) {
		this.overpassDetailEntitys = overpassDetailEntitys;
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
		OverpassEntity other = (OverpassEntity) that;
		return (this.getO_id() == null ? other.getO_id() == null : this
				.getO_id().equals(other.getO_id()))
				&& (this.getO_code() == null ? other.getO_code() == null : this
						.getO_code().equals(other.getO_code()))
				&& (this.getO_name() == null ? other.getO_name() == null : this
						.getO_name().equals(other.getO_name()))
				&& (this.getO_city() == null ? other.getO_city() == null : this
						.getO_city().equals(other.getO_city()))
				&& (this.getO_desc() == null ? other.getO_desc() == null : this
						.getO_desc().equals(other.getO_desc()))
				&& (this.getO_length() == null ? other.getO_length() == null
						: this.getO_length().equals(other.getO_length()))
				&& (this.getO_type() == null ? other.getO_type() == null : this
						.getO_type().equals(other.getO_type()))
				&& (this.getO_cross_type() == null ? other.getO_cross_type() == null
						: this.getO_cross_type()
								.equals(other.getO_cross_type()))
				&& (this.getO_status() == null ? other.getO_status() == null
						: this.getO_status().equals(other.getO_status()))
				&& (this.getO_pic_1() == null ? other.getO_pic_1() == null
						: this.getO_pic_1().equals(other.getO_pic_1()))
				&& (this.getO_pic_2() == null ? other.getO_pic_2() == null
						: this.getO_pic_2().equals(other.getO_pic_2()))
				&& (this.getO_pic_3() == null ? other.getO_pic_3() == null
						: this.getO_pic_3().equals(other.getO_pic_3()))
				&& (this.getCreate_time() == null ? other.getCreate_time() == null
						: this.getCreate_time().equals(other.getCreate_time()))
				&& (this.getUpdate_time() == null ? other.getUpdate_time() == null
						: this.getUpdate_time().equals(other.getUpdate_time()))
				&& (this.getO_corss_line() == null ? other.getO_corss_line() == null
						: this.getO_corss_line()
								.equals(other.getO_corss_line()));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((getO_id() == null) ? 0 : getO_id().hashCode());
		result = prime * result
				+ ((getO_code() == null) ? 0 : getO_code().hashCode());
		result = prime * result
				+ ((getO_name() == null) ? 0 : getO_name().hashCode());
		result = prime * result
				+ ((getO_city() == null) ? 0 : getO_city().hashCode());
		result = prime * result
				+ ((getO_desc() == null) ? 0 : getO_desc().hashCode());
		result = prime * result
				+ ((getO_length() == null) ? 0 : getO_length().hashCode());
		result = prime * result
				+ ((getO_type() == null) ? 0 : getO_type().hashCode());
		result = prime
				* result
				+ ((getO_cross_type() == null) ? 0 : getO_cross_type()
						.hashCode());
		result = prime * result
				+ ((getO_status() == null) ? 0 : getO_status().hashCode());
		result = prime * result
				+ ((getO_pic_1() == null) ? 0 : getO_pic_1().hashCode());
		result = prime * result
				+ ((getO_pic_2() == null) ? 0 : getO_pic_2().hashCode());
		result = prime * result
				+ ((getO_pic_3() == null) ? 0 : getO_pic_3().hashCode());
		result = prime
				* result
				+ ((getCreate_time() == null) ? 0 : getCreate_time().hashCode());
		result = prime
				* result
				+ ((getUpdate_time() == null) ? 0 : getUpdate_time().hashCode());
		result = prime
				* result
				+ ((getO_corss_line() == null) ? 0 : getO_corss_line()
						.hashCode());
		return result;
	}
}