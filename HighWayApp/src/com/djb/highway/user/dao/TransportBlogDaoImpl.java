package com.djb.highway.user.dao;

import org.springframework.stereotype.Repository;

import com.djb.highway.framework.dao.BaseDAOImpl;
import com.djb.highway.user.entity.TransportBlogEntity;


@Repository("transportBlogDao")
public class TransportBlogDaoImpl extends BaseDAOImpl<TransportBlogEntity> implements
		ITransportBlogDao {
}
