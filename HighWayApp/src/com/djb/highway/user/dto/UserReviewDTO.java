package com.djb.highway.user.dto;

import java.util.Date;
import java.util.List;

import com.djb.highway.framework.dto.PageDTO;
import com.djb.highway.user.entity.UserEntity;

public class UserReviewDTO extends PageDTO {
	/**
     * 
     */
	private static final long serialVersionUID = 2225711258893055539L;

	private Integer ur_id;

	private Integer u_id;

	private Integer udp_id;

	private Integer ur_status;

	private String ur_content;

	private Date ur_deploy_time;

	private Integer reply_u_id;

	private String u_name;

	private String reply_u_name;

	private List<UserReviewDTO> list;

	private UserDTO userDTO;

	private UserDeployPicDTO userDeployPicDTO;

	public Integer getUr_id() {
		return ur_id;
	}

	public void setUr_id(Integer ur_id) {
		this.ur_id = ur_id;
	}

	public Integer getU_id() {
		return u_id;
	}

	public void setU_id(Integer u_id) {
		this.u_id = u_id;
	}

	public Integer getUdp_id() {
		return udp_id;
	}

	public void setUdp_id(Integer udp_id) {
		this.udp_id = udp_id;
	}

	public Integer getUr_status() {
		return ur_status;
	}

	public void setUr_status(Integer ur_status) {
		this.ur_status = ur_status;
	}

	public String getUr_content() {
		return ur_content;
	}

	public void setUr_content(String ur_content) {
		this.ur_content = ur_content == null ? null : ur_content.trim();
	}

	public Date getUr_deploy_time() {
		return ur_deploy_time;
	}

	public void setUr_deploy_time(Date ur_deploy_time) {
		this.ur_deploy_time = ur_deploy_time;
	}

	public Integer getReply_u_id() {
		return reply_u_id;
	}

	public void setReply_u_id(Integer reply_u_id) {
		this.reply_u_id = reply_u_id;
	}


	public String getReply_u_name() {
		return reply_u_name;
	}

	public void setReply_u_name(String reply_u_name) {
		this.reply_u_name = reply_u_name;
	}

	public String getU_name() {
		return u_name;
	}

	public void setU_name(String u_name) {
		this.u_name = u_name;
	}

	public List<UserReviewDTO> getList() {
		return list;
	}

	public void setList(List<UserReviewDTO> list) {
		this.list = list;
	}

	public UserDTO getUserDTO() {
		return userDTO;
	}

	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}

	public UserDeployPicDTO getUserDeployPicDTO() {
		return userDeployPicDTO;
	}

	public void setUserDeployPicDTO(UserDeployPicDTO userDeployPicDTO) {
		this.userDeployPicDTO = userDeployPicDTO;
	}
}