package com.djb.highway.user.entity;

import com.djb.highway.framework.entity.PageModel;

public class UserSupportEntity extends PageModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2649667952492299655L;

	private Integer us_id;

	private Integer u_id;

	private Integer udp_id;

	private UserEntity userEntity;

	public Integer getUs_id() {
		return us_id;
	}

	public void setUs_id(Integer us_id) {
		this.us_id = us_id;
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

	public UserEntity getUserEntity() {
		return userEntity;
	}

	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
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
		UserSupportEntity other = (UserSupportEntity) that;
		return (this.getUs_id() == null ? other.getUs_id() == null : this
				.getUs_id().equals(other.getUs_id()))
				&& (this.getU_id() == null ? other.getU_id() == null : this
						.getU_id().equals(other.getU_id()))
				&& (this.getUdp_id() == null ? other.getUdp_id() == null : this
						.getUdp_id().equals(other.getUdp_id()));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((getUs_id() == null) ? 0 : getUs_id().hashCode());
		result = prime * result
				+ ((getU_id() == null) ? 0 : getU_id().hashCode());
		result = prime * result
				+ ((getUdp_id() == null) ? 0 : getUdp_id().hashCode());
		return result;
	}
}