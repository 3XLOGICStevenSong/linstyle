package com.djb.highway.user.service;

import java.util.List;

import com.djb.highway.framework.entity.PageModel;
import com.djb.highway.user.entity.TransportBlogEntity;

public interface ITransportBlogService {
	public void addTransportBlog(TransportBlogEntity transportBlog);

	public void deleteTransportBlog(TransportBlogEntity transportBlog);

	public void deleteTransportBlogBatch(List<TransportBlogEntity> list);

	public void updateTransportBlog(TransportBlogEntity transportBlog);

	public TransportBlogEntity getTransportBlogById(int blog_id);

	public TransportBlogEntity getTransportBlogById(
			TransportBlogEntity transportBlog);

	public List<TransportBlogEntity> getTransportBlogList();

	public PageModel findPagedList(TransportBlogEntity transportBlog);

	public List<TransportBlogEntity> getTransportBlogList(
			TransportBlogEntity transportBlog);
}
