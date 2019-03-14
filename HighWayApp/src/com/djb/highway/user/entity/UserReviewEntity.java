package com.djb.highway.user.entity;

import java.util.Date;

import com.djb.highway.framework.entity.PageModel;

public class UserReviewEntity extends PageModel {
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

	private UserEntity userEntity;

	private UserDeployPicEntity userDeployPicEntity;

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

	public String getU_name() {
		return u_name;
	}

	public void setU_name(String u_name) {
		this.u_name = u_name;
	}


	public String getReply_u_name() {
		return reply_u_name;
	}

	public void setReply_u_name(String reply_u_name) {
		this.reply_u_name = reply_u_name;
	}

	public UserEntity getUserEntity() {
		return userEntity;
	}

	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}

	public UserDeployPicEntity getUserDeployPicEntity() {
		return userDeployPicEntity;
	}

	public void setUserDeployPicEntity(UserDeployPicEntity userDeployPicEntity) {
		this.userDeployPicEntity = userDeployPicEntity;
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
		UserReviewEntity other = (UserReviewEntity) that;
		return (this.getUr_id() == null ? other.getUr_id() == null : this
				.getUr_id().equals(other.getUr_id()))
				&& (this.getU_id() == null ? other.getU_id() == null : this
						.getU_id().equals(other.getU_id()))
				&& (this.getUdp_id() == null ? other.getUdp_id() == null : this
						.getUdp_id().equals(other.getUdp_id()))
				&& (this.getUr_content() == null ? other.getUr_content() == null
						: this.getUr_content().equals(other.getUr_content()))
				&& (this.getUr_deploy_time() == null ? other
						.getUr_deploy_time() == null : this.getUr_deploy_time()
						.equals(other.getUr_deploy_time()));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((getUr_id() == null) ? 0 : getUr_id().hashCode());
		result = prime * result
				+ ((getU_id() == null) ? 0 : getU_id().hashCode());
		result = prime * result
				+ ((getUdp_id() == null) ? 0 : getUdp_id().hashCode());
		result = prime * result
				+ ((getUr_content() == null) ? 0 : getUr_content().hashCode());
		result = prime
				* result
				+ ((getUr_deploy_time() == null) ? 0 : getUr_deploy_time()
						.hashCode());
		return result;
	}
}