package com.djb.highway.user.entity;

import java.util.Date;

import com.djb.highway.framework.entity.PageModel;

public class PushEntity extends PageModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2649667952492299655L;

    private Integer p_id;
    private Integer u_id;
    private Integer h_id;
    private UserEntity userEntity;
  
    public Integer getP_id() {
	return p_id;
	}
	public void setP_id(Integer p_id) {
		this.p_id = p_id;
	}

	public Integer getU_id() {
		return u_id;
	}

	public void setU_id(Integer u_id) {
		this.u_id = u_id;
	}


	public Integer getH_id() {
		return h_id;
	}
	public void setH_id(Integer h_id) {
		this.h_id = h_id;
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
		PushEntity other = (PushEntity) that;
		return (this.getP_id() == null ? other.getP_id() == null : this
				.getP_id().equals(other.getP_id()))
				&& (this.getU_id() == null ? other.getU_id() == null : this
						.getU_id().equals(other.getU_id()))
				&& (this.getH_id() == null ? other.getH_id() == null : this
						.getH_id().equals(other.getH_id()))
				;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((getP_id() == null) ? 0 : getP_id().hashCode());
		result = prime * result
				+ ((getU_id() == null) ? 0 : getU_id().hashCode());
		result = prime * result
				+ ((getH_id() == null) ? 0 : getH_id().hashCode());
		return result;
	}
	
	
}