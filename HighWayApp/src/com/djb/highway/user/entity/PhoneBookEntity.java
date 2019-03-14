package com.djb.highway.user.entity;

import java.util.Date;

import com.djb.highway.framework.entity.PageModel;

public class PhoneBookEntity extends PageModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2649667952492299655L;

    private Integer p_id;
    private Integer u_id;
    private String tel_num;
    private Date insert_time;
    private Date update_time;
    private String memo;
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

	public String getTel_num() {
		return tel_num;
	}

	public void setTel_num(String tel_num) {
		this.tel_num = tel_num;
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

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
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
		PhoneBookEntity other = (PhoneBookEntity) that;
		return (this.getP_id() == null ? other.getP_id() == null : this
				.getP_id().equals(other.getP_id()))
				&& (this.getU_id() == null ? other.getU_id() == null : this
						.getU_id().equals(other.getU_id()))
				&& (this.getTel_num() == null ? other.getTel_num() == null : this
						.getTel_num().equals(other.getTel_num()))
				&& (this.getInsert_time() == null ? other.getInsert_time() == null : this
						.getInsert_time().equals(other.getInsert_time()))
				&& (this.getUpdate_time()== null ? other.getUpdate_time() == null : this
						.getUpdate_time().equals(other.getUpdate_time()))
				&& (this.getMemo() == null ? other.getMemo() == null : this
								.getMemo().equals(other.getMemo()));
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
				+ ((getTel_num() == null) ? 0 : getTel_num().hashCode());
		result = prime * result
				+ ((getInsert_time() == null) ? 0 : getInsert_time().hashCode());
		result = prime * result
				+ ((getUpdate_time() == null) ? 0 : getUpdate_time().hashCode());
		result = prime * result
				+ ((getMemo() == null) ? 0 : getMemo().hashCode());
		return result;
	}

	
}