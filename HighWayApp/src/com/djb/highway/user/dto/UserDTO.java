package com.djb.highway.user.dto;

import java.util.Date;
import java.util.List;

import com.djb.highway.framework.dto.BaseDTO;

public class UserDTO extends BaseDTO {
	/**
     * 
     */
	private static final long serialVersionUID = 5849783863162740191L;

	private Integer u_id;

	private String u_code;

	private String u_name;

	private String u_password;

	private String u_old_password;

	private String u_type;

	private String u_tel;

	private String u_mail;

	private String u_memo;

	private Date u_reg_time;

	private String udp_deploy_time;
	private String verificationCode;
	private String u_answer;
	private Integer u_question;
	private List<UserDTO> list;

	private List<UserDeployPicDTO> userDeployPicDTOs;

	private List<OftenUsedRoadDTO> oftenUsedRoadDTOs;

	public String getUdp_deploy_time() {
		return udp_deploy_time;
	}

	public void setUdp_deploy_time(String udp_deploy_time) {
		this.udp_deploy_time = udp_deploy_time;
	}

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

	public List<UserDTO> getList() {
		return list;
	}

	public void setList(List<UserDTO> list) {
		this.list = list;
	}

	public List<UserDeployPicDTO> getUserDeployPicDTOs() {
		return userDeployPicDTOs;
	}

	public void setUserDeployPicDTOs(List<UserDeployPicDTO> userDeployPicDTOs) {
		this.userDeployPicDTOs = userDeployPicDTOs;
	}

	public List<OftenUsedRoadDTO> getOftenUsedRoadDTOs() {
		return oftenUsedRoadDTOs;
	}

	public void setOftenUsedRoadDTOs(List<OftenUsedRoadDTO> oftenUsedRoadDTOs) {
		this.oftenUsedRoadDTOs = oftenUsedRoadDTOs;
	}

	public String getVerificationCode() {
		return verificationCode;
	}

	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}

	public String getU_old_password() {
		return u_old_password;
	}

	public void setU_old_password(String u_old_password) {
		this.u_old_password = u_old_password;
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

}