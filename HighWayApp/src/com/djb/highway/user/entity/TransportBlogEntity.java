package com.djb.highway.user.entity;

import java.util.Date;

import com.djb.highway.framework.entity.PageModel;

public class TransportBlogEntity extends PageModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2649667952492299655L;

	private Integer blog_id;

	private String blog_content;

	private String blog_group;
	private Date insert_time;
	private Date update_time;
	private UserEntity userEntity;
	

	public Integer getBlog_id() {
		return blog_id;
	}

	public void setBlog_id(Integer blog_id) {
		this.blog_id = blog_id;
	}

	public String getBlog_content() {
		return blog_content;
	}

	public void setBlog_content(String blog_content) {
		this.blog_content = blog_content;
	}

	public String getBlog_group() {
		return blog_group;
	}

	public void setBlog_group(String blog_group) {
		this.blog_group = blog_group;
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
		TransportBlogEntity other = (TransportBlogEntity) that;
		return (this.getBlog_id() == null ? other.getBlog_id() == null : this
				.getBlog_id().equals(other.getBlog_id()))
				&& (this.getBlog_content() == null ? other.getBlog_content() == null : this
						.getBlog_content().equals(other.getBlog_content()))
				&& (this.getBlog_group() == null ? other.getBlog_group() == null : this
						.getBlog_group().equals(other.getBlog_group()))
				&& (this.getInsert_time() == null ? other.getInsert_time() == null : this
						.getInsert_time().equals(other.getInsert_time()))	
				&& (this.getUpdate_time() == null ? other.getUpdate_time() == null : this
						.getUpdate_time().equals(other.getUpdate_time()));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((getBlog_id() == null) ? 0 : getBlog_id().hashCode());
		result = prime * result
				+ ((getBlog_content() == null) ? 0 : getBlog_content().hashCode());
		result = prime * result
				+ ((getBlog_group() == null) ? 0 : getBlog_group().hashCode());
		result = prime * result
				+ ((getInsert_time() == null) ? 0 : getInsert_time().hashCode());
		result = prime * result
				+ ((getUpdate_time() == null) ? 0 : getUpdate_time().hashCode());
		return result;
	}

	


}