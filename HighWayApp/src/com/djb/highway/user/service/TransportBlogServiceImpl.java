package com.djb.highway.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.djb.highway.framework.entity.PageModel;
import com.djb.highway.framework.exception.dao.DataNotFoundException;
import com.djb.highway.framework.exception.dao.KeyExistException;
import com.djb.highway.framework.service.BaseService;
import com.djb.highway.user.dao.ITransportBlogDao;

import com.djb.highway.user.entity.TransportBlogEntity;

@Service("iTransportBlogService")
public class TransportBlogServiceImpl extends BaseService implements
		ITransportBlogService {

	@Autowired
	@Qualifier("transportBlogDao")
	private ITransportBlogDao transportBlogDao;

	public void addTransportBlog(TransportBlogEntity transportBlog) {
		// TODO Auto-generated method stub
		try {
			transportBlogDao.insert(transportBlog);
		} catch (KeyExistException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
	}

	public void deleteTransportBlog(TransportBlogEntity transportBlog) {
		// TODO Auto-generated method stub
		try {
			transportBlogDao.delete(transportBlog);
		} catch (DataNotFoundException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
	}

	public void deleteTransportBlogBatch(List<TransportBlogEntity> list) {
		// TODO Auto-generated method stub
		try {
			transportBlogDao.deleteBatch(list);
		} catch (DataNotFoundException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
	}

	public void updateTransportBlog(TransportBlogEntity transportBlog) {
		// TODO Auto-generated method stub
		try {
			transportBlogDao.update(transportBlog);
		} catch (DataNotFoundException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
	}

	public TransportBlogEntity getTransportBlogById(int blog_id) {
		// TODO Auto-generated method stub
		return transportBlogDao.getObject(blog_id);
	}

	public TransportBlogEntity getTransportBlogById(
			TransportBlogEntity transportBlog) {
		// TODO Auto-generated method stub
		return transportBlogDao.getObject(transportBlog);
	}

	public List<TransportBlogEntity> getTransportBlogList() {
		// TODO Auto-generated method stub
		return transportBlogDao.findList();
	}

	public PageModel findPagedList(TransportBlogEntity transportBlog) {
		return transportBlogDao.findPagedList(transportBlog);
	}

	public List<TransportBlogEntity> getTransportBlogEntityList(
			TransportBlogEntity transportBlog) {
		List<TransportBlogEntity> list = transportBlogDao
				.findListByCondition(transportBlog);
		return list;
	}




	@Override
	public List<TransportBlogEntity> getTransportBlogList(
			TransportBlogEntity transportBlog) {

		List<TransportBlogEntity> list = transportBlogDao
				.findListByCondition(transportBlog);
		return list;
	}

}
