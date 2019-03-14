package com.djb.highway.user.dto;

import java.util.Date;
import java.util.List;

import com.djb.highway.framework.dto.PageDTO;

public class TransportBlogDTO extends PageDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2649667952492299655L;

	private Integer blog_id;

	private String blog_content;

	private String blog_group;
	private Date insert_time;
	private Date update_time;
	private List<TransportBlogDTO> list;

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

	public Date getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}

	public Date getInsert_time() {
		return insert_time;
	}

	public void setInsert_time(Date insert_time) {
		this.insert_time = insert_time;
	}

	public String getBlog_group() {
		return blog_group;
	}

	public void setBlog_group(String blog_group) {
		this.blog_group = blog_group;
	}

	public List<TransportBlogDTO> getList() {
		return list;
	}

	public void setList(List<TransportBlogDTO> list) {
		this.list = list;
	}

}