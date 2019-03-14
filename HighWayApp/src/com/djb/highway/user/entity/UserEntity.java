package com.djb.highway.user.entity;

import java.util.Date;
import java.util.List;

import com.djb.highway.framework.entity.PageModel;

public class UserEntity extends PageModel {

	/**
     * 
     */
	private static final long serialVersionUID = 159103098962955592L;

	private Integer u_id;

	private String u_code;

	private String u_name;

	private String u_password;

	private String u_type;

	private String u_tel;

	private String u_mail;

	private String u_memo;

	private Date u_reg_time;
	private String u_answer;
	private Integer u_question;

	private List<UserDeployPicEntity> userDeployPicEntitys;

	private List<OftenUsedRoadEntity> oftenUsedRoadEntitys;

	public Integer getU_id() {
		return u_id;
	}

	public void setU_id(Integer u_id) {
		this.u_id = u_id;
	}

	public String getU_code() {
		return u_code;
	}

	public void setU_code(String u_code) {
		this.u_code = u_code == null ? null : u_code.trim();
	}

	public String getU_name() {
		return u_name;
	}

	public void setU_name(String u_name) {
		this.u_name = u_name == null ? null : u_name.trim();
	}

	public String getU_password() {
		return u_password;
	}

	public void setU_password(String u_password) {
		this.u_password = u_password == null ? null : u_password.trim();
	}

	public String getU_type() {
		return u_type;
	}

	public void setU_type(String u_type) {
		this.u_type = u_type == null ? null : u_type.trim();
	}

	public String getU_tel() {
		return u_tel;
	}

	public void setU_tel(String u_tel) {
		this.u_tel = u_tel == null ? null : u_tel.trim();
	}

	public String getU_mail() {
		return u_mail;
	}

	public void setU_mail(String u_mail) {
		this.u_mail = u_mail == null ? null : u_mail.trim();
	}

	public String getU_memo() {
		return u_memo;
	}

	public void setU_memo(String u_memo) {
		this.u_memo = u_memo == null ? null : u_memo.trim();
	}

	public Date getU_reg_time() {
		return u_reg_time;
	}

	public void setU_reg_time(Date u_reg_time) {
		this.u_reg_time = u_reg_time;
	}

	public List<UserDeployPicEntity> getUserDeployPicEntitys() {
		return userDeployPicEntitys;
	}

	public void setUserDeployPicEntitys(
			List<UserDeployPicEntity> userDeployPicEntitys) {
		this.userDeployPicEntitys = userDeployPicEntitys;
	}

	public List<OftenUsedRoadEntity> getOftenUsedRoadEntitys() {
		return oftenUsedRoadEntitys;
	}

	public void setOftenUsedRoadEntitys(
			List<OftenUsedRoadEntity> oftenUsedRoadEntitys) {
		this.oftenUsedRoadEntitys = oftenUsedRoadEntitys;
	}

	public String getU_answer() {
		return u_answer;
	}

	public void setU_answer(String u_answer) {
		this.u_answer = u_answer == null ? null : u_answer.trim();
	}

	public Integer getU_question() {
		return u_question;
	}

	public void setU_question(Integer u_question) {
		this.u_question = u_question;
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
		UserEntity other = (UserEntity) that;
		return (this.getU_id() == null ? other.getU_id() == null : this
				.getU_id().equals(other.getU_id()))
				&& (this.getU_code() == null ? other.getU_code() == null : this
						.getU_code().equals(other.getU_code()))
				&& (this.getU_name() == null ? other.getU_name() == null : this
						.getU_name().equals(other.getU_name()))
				&& (this.getU_password() == null ? other.getU_password() == null
						: this.getU_password().equals(other.getU_password()))
				&& (this.getU_type() == null ? other.getU_type() == null : this
						.getU_type().equals(other.getU_type()))
				&& (this.getU_tel() == null ? other.getU_tel() == null : this
						.getU_tel().equals(other.getU_tel()))
				&& (this.getU_mail() == null ? other.getU_mail() == null : this
						.getU_mail().equals(other.getU_mail()))
				&& (this.getU_memo() == null ? other.getU_memo() == null : this
						.getU_memo().equals(other.getU_memo()))
				&& (this.getU_reg_time() == null ? other.getU_reg_time() == null
						: this.getU_reg_time().equals(other.getU_reg_time()))
				&& (this.getU_answer() == null ? other.getU_answer() == null
						: this.getU_answer().equals(other.getU_answer()))
				&& (this.getU_question() == null ? other.getU_question() == null
						: this.getU_question().equals(other.getU_question()));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((getU_id() == null) ? 0 : getU_id().hashCode());
		result = prime * result
				+ ((getU_code() == null) ? 0 : getU_code().hashCode());
		result = prime * result
				+ ((getU_name() == null) ? 0 : getU_name().hashCode());
		result = prime * result
				+ ((getU_password() == null) ? 0 : getU_password().hashCode());
		result = prime * result
				+ ((getU_type() == null) ? 0 : getU_type().hashCode());
		result = prime * result
				+ ((getU_tel() == null) ? 0 : getU_tel().hashCode());
		result = prime * result
				+ ((getU_mail() == null) ? 0 : getU_mail().hashCode());
		result = prime * result
				+ ((getU_memo() == null) ? 0 : getU_memo().hashCode());
		result = prime * result
				+ ((getU_reg_time() == null) ? 0 : getU_reg_time().hashCode());
		result = prime * result
				+ ((getU_answer() == null) ? 0 : getU_answer().hashCode());
		result = prime * result
				+ ((getU_question() == null) ? 0 : getU_question().hashCode());
		return result;
	}
}